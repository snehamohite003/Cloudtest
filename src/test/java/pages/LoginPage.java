package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object Model for Login Page.
 */
public class LoginPage extends BasePage {

    // ============== Web Elements ==============

    @FindBy(xpath = "(//label[text()='User Name'])[1]/following::input[1]")
    private WebElement usernameField;

    @FindBy(xpath = "(//label[text()='Password'])[1]/following::input[1]")
    private WebElement passwordField;

    @FindBy(xpath = "//a[@id='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//*[contains(@class,'error') or contains(@class,'alert') or contains(@id,'error') or contains(@id,'alert')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//*[contains(@class,'validation') or contains(@class,'field-error') or contains(@class,'help-block')]")
    private WebElement validationMessage;

    @FindBy(xpath = "//*[contains(@class,'dashboard') or contains(@id,'central') or contains(@class,'central-dashboard') or contains(@aria-label,'dashboard')]")
    private WebElement dashboardElement;

    // ============== Constructor ==============

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // ============== Page Actions ==============

    private void clickClearAndType(WebElement element, String value) {
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        try {
            element.click();
        } catch (Exception ignored) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        element.clear();
        element.sendKeys(value);
    }

    /**
     * Enter username in the username field
     */
    public LoginPage enterUsername(String username) {
        clickClearAndType(usernameField, username);
        return this;
    }

    /**
     * Enter password in the password field
     */
    public LoginPage enterPassword(String password) {
        clickClearAndType(passwordField, password);
        return this;
    }

    /**
     * Click on Login button
     */
    public LoginPage clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        try {
            loginButton.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButton);
        }
        return this;
    }

    /**
     * Perform complete login action
     */
    public LoginPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        return this;
    }

    // ============== Verification Methods ==============

    /**
     * Check if login was successful by verifying dashboard is displayed
     */
    public boolean isLoginSuccessful() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(dashboardElement),
                    ExpectedConditions.urlContains("Central")
            ));
            return isElementDisplayed(dashboardElement) || driver.getCurrentUrl().contains("Central");
        } catch (Exception e) {
            return !driver.getCurrentUrl().contains("Login");
        }
    }

    /**
     * Get error message text
     */
    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Check if error message is displayed
     */
    public boolean isErrorMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if validation message is displayed
     */
    public boolean isValidationMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(validationMessage));
            return validationMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the current page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Check if login page is displayed
     */
    public boolean isLoginPageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOf(usernameField));
            wait.until(ExpectedConditions.visibilityOf(passwordField));
            wait.until(ExpectedConditions.visibilityOf(loginButton));
            return isElementDisplayed(usernameField)
                    && isElementDisplayed(passwordField)
                    && isElementDisplayed(loginButton)
                    && driver.getCurrentUrl().contains("/concerto/Login");
        } catch (Exception e) {
            return false;
        }
    }
}
