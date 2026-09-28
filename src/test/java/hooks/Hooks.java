package hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;
import utils.ScreenshotUtil;

public class Hooks {

    private WebDriver driver;

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("========================================");
        System.out.println("Starting Scenario: " + scenario.getName());
        System.out.println("========================================");
        driver = DriverManager.initDriver();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        // Capture screenshot after each step for both pass and fail
        if (driver != null) {
            if (scenario.isFailed()) {
                byte[] screenshot = ScreenshotUtil.captureScreenshotAsBytes(driver);
                scenario.attach(screenshot, "image/png", "Failed_Step_Screenshot");
                // Also save to disk
                ScreenshotUtil.captureScreenshot(driver, "FAILED_" + scenario.getName().replaceAll(" ", "_"));
            } else {
                // Capture screenshot on pass as well (for assertion evidence)
                byte[] screenshot = ScreenshotUtil.captureScreenshotAsBytes(driver);
                scenario.attach(screenshot, "image/png", "Passed_Step_Screenshot");
            }
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        System.out.println("========================================");
        System.out.println("Finished Scenario: " + scenario.getName());
        System.out.println("Status: " + scenario.getStatus());
        System.out.println("========================================");

        // Final screenshot capture
        if (driver != null) {
            if (scenario.isFailed()) {
                byte[] screenshot = ScreenshotUtil.captureScreenshotAsBytes(driver);
                scenario.attach(screenshot, "image/png", "Final_Failure_Screenshot");
                ScreenshotUtil.captureScreenshot(driver, "FINAL_FAILED_" + scenario.getName().replaceAll(" ", "_"));
            } else {
                byte[] screenshot = ScreenshotUtil.captureScreenshotAsBytes(driver);
                scenario.attach(screenshot, "image/png", "Final_Pass_Screenshot");
            }
        }

        DriverManager.quitDriver();
    }
}

