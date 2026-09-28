package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverManager;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;

    @Given("I navigate to the CloudTest Manager login page")
    public void iNavigateToTheCloudTestManagerLoginPage() {
        driver = DriverManager.getDriver();
        String url = ConfigReader.getConfigProperty("base.url");
        driver.get(url);
        loginPage = new LoginPage(driver);
        //Assert.assertTrue("Login page is not displayed", loginPage.isLoginPageDisplayed());
    }

    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("I enter password {string}")
    public void iEnterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("I click on the Login button")
    public void iClickOnTheLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("I should be logged in successfully and redirected to dashboard")
    public void iShouldBeLoggedInSuccessfullyAndRedirectedToDashboard() {
        Assert.assertTrue("Login was not successful - Dashboard not displayed",
                loginPage.isLoginSuccessful());
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        Assert.assertTrue("Error message is not displayed after invalid login",
                loginPage.isErrorMessageDisplayed());
    }

    @Then("I should see a validation message")
    public void iShouldSeeAValidationMessage() {
        // Check for either validation message or error message for empty fields
        boolean hasMessage = loginPage.isValidationMessageDisplayed() || loginPage.isErrorMessageDisplayed();
        Assert.assertTrue("Validation message is not displayed for empty credentials", hasMessage);
    }
}

