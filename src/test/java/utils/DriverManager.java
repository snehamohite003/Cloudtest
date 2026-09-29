package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Initialize WebDriver based on browser type from config
     */
    public static WebDriver initDriver() {
        String browser = ConfigReader.getConfigProperty("browser").toLowerCase();
        int implicitWait = Integer.parseInt(ConfigReader.getConfigProperty("implicit.wait"));
        int pageLoadTimeout = Integer.parseInt(ConfigReader.getConfigProperty("page.load.timeout"));

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                // CloudTest's Util.isWebUITest() returns true when navigator.webdriver is true,
                // which makes the login/license pages use test-mode shortcuts (no real license check).
                // Hide the automation flag so the app behaves like a real user session.
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                chromeOptions.setExperimentalOption("useAutomationExtension", false);
                ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);
                // Fallback for newer Chrome versions: override navigator.webdriver on every new document.
                Map<String, Object> cdpArgs = new HashMap<>();
                cdpArgs.put("source", "Object.defineProperty(navigator, 'webdriver', {get: () => false});");
                chromeDriver.executeCdpCommand("Page.addScriptToEvaluateOnNewDocument", cdpArgs);
                driver.set(chromeDriver);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                break;
            default:
                throw new RuntimeException("Browser not supported: " + browser);
        }

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
        getDriver().manage().window().maximize();

        return getDriver();
    }

    /**
     * Get WebDriver instance
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Quit WebDriver and remove from ThreadLocal
     */
    public static void quitDriver() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}

