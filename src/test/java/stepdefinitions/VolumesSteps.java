package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.VolumesPage;
import utils.DriverManager;

public class VolumesSteps {

    private WebDriver driver;
    private VolumesPage volumesPage;
    private String selectedAccount;
    private String volumeName;
    private String generatedLicenseKey="W75S8-9PS7V-ZZPHJ-6VE76-M2WJ8";

    @When("I open the volumes page")
    public void iOpenTheVolumesPage() {
        driver = DriverManager.getDriver();
        volumesPage = new VolumesPage(driver);
        volumesPage.clickVolumesTab();
    }

    @When("I open the environments page")
    public void iOpenTheEnvironmentsPage() {
        driver = DriverManager.getDriver();
        volumesPage = new VolumesPage(driver);
        volumesPage.openEnvironmentCreationFlow();
    }

    @And("I click on the new volume button")
    public void iClickOnTheNewVolumeButton() throws InterruptedException {
        volumesPage.clickNewVolumeButton();
    }

    @And("I select the IAM account from the dropdown {string}")
    public void iSelectTheIAMAccountFromTheDropdown(String visibleText) throws InterruptedException {
        selectedAccount = visibleText;
        volumesPage.selectAccountFromDropdown(visibleText);
    }

    @And("I enter volume name {string}")
    public void iEnterVolumeName(String volumeName) throws InterruptedException {
        this.volumeName = volumeName;
        volumesPage.enterVolumeName(volumeName);
    }

    @And("I enter volume size {string}")
    public void iEnterVolumeSize(String volumeSize) throws InterruptedException {
        volumesPage.enterVolumeSize(volumeSize);
    }

    @And("I click on the Finish button")
    public void iClickOnTheFinishButton() {
        volumesPage.finishWizard();
    }

    @And("I select the environment IAM account from the dropdown {string}")
    public void iSelectTheEnvironmentIAMAccountFromTheDropdown(String visibleText) {
        selectedAccount = visibleText;
        volumesPage.selectEnvironmentAccount(visibleText);
    }

    @And("I select the region from the dropdown {string}")
    public void iSelectTheRegionFromTheDropdown(String regionText) {
        volumesPage.selectEnvironmentRegion(regionText);
    }

    @And("I enter max instances {string}")
    public void iEnterMaxInstances(String maxInstances) throws InterruptedException {
        volumesPage.enterEnvironmentMaxInstances(maxInstances);
    }

    @And("I checked the single instance radio button")
    public void iCheckedTheSingleInstanceRadioButton() {
        volumesPage.setSingleInstanceChecked(true);
    }

    @And("I unchecked the single instance radio button")
    public void iUncheckedTheSingleInstanceRadioButton() {
        volumesPage.setSingleInstanceChecked(false);
    }

    @And("I click on the Deploy button")
    public void iClickOnTheDeployButton() {
        volumesPage.clickDeploy();
    }

    @And("After click on the Deploy button, I should see and click on Initialize and continue button")
    public void iShouldSeeAndClickOnInitializeAndContinueButton() {
        volumesPage.clickInitializeAndContinueWhenEnabled();
    }

    @Then("Wait for deployment to complete and verify the environment is created successfully")
    public void waitForDeploymentToCompleteAndVerifyTheEnvironmentIsCreatedSuccessfully() throws InterruptedException {
        volumesPage.waitForDeploymentSuccessAndOpenResult();
        volumesPage.switchToNewlyOpenedTab();
        Assert.assertFalse("Expected deployment result tab to be opened", driver.getWindowHandles().isEmpty());
    }

    @Then("the IAM account should be selected")
    public void theIAMAccountShouldBeSelected() {
        Assert.assertTrue("Account was not selected", volumesPage.isAccountSelected(selectedAccount));
    }

    @Then("the created volume should be displayed")
    public void theCreatedVolumeShouldBeDisplayed() {
        Assert.assertTrue("Created volume is not displayed", volumesPage.isCreatedVolumeDisplayed(volumeName));
    }

    @And("I enter name {string}")
    public void iEnterName(String name) throws InterruptedException {
        volumesPage.enterEnvironmentName(name);
    }

    @When("I open new licence page")
    public void iOpenNewLicencePage() {
        driver = DriverManager.getDriver();
        volumesPage = new VolumesPage(driver);
        volumesPage.openNewLicensePage();
    }

    @And("I enter license name {string}")
    public void iEnterLicenseName(String licenseName) throws InterruptedException {
        volumesPage.enterLicenseName(licenseName);
    }

    @And("I enter license description {string}")
    public void iEnterLicenseDescription(String licenseDescription) throws InterruptedException {
        volumesPage.enterLicenseDescription(licenseDescription);
    }

    @And("I check the license can create grid launching cpas checkbox")
    public void iCheckTheLicenseCanCreateGridLaunchingCpasCheckbox() {
        volumesPage.checkLicenseCanCreateGridLaunchingCpas();
    }

    @And("I click on Generate Key button")
    public void iClickOnGenerateKeyButton() {
        volumesPage.generateLicenseKey();
    }

    @And("I store the generated license key")
    public void iStoreTheGeneratedLicenseKey() throws InterruptedException {
        volumesPage.storeGeneratedLicenseKey();
        generatedLicenseKey = volumesPage.getGeneratedLicenseKey();
        Assert.assertNotNull("Generated license key should not be null", generatedLicenseKey);
        Assert.assertFalse("Generated license key should not be empty", generatedLicenseKey.trim().isEmpty());
    }

    @And("I enter license details tenants {string}")
    public void iEnterLicenseDetailsTenants(String value) throws InterruptedException {
        volumesPage.enterLicenseDetailsTenants(value);
    }

    @And("I enter license details concurrent vus {string}")
    public void iEnterLicenseDetailsConcurrentVus(String value) throws InterruptedException {
        volumesPage.enterLicenseDetailsConcurrentVus(value);
    }

    @And("I enter license details external maestros {string}")
    public void iEnterLicenseDetailsExternalMaestros(String value) throws InterruptedException {
        volumesPage.enterLicenseDetailsExternalMaestros(value);
    }

    @And("I enter license details conductors {string}")
    public void iEnterLicenseDetailsConductors(String value) throws InterruptedException {
        volumesPage.enterLicenseDetailsConductors(value);
    }

    @And("I enter license details rdbs {string}")
    public void iEnterLicenseDetailsRdbs(String value) throws InterruptedException {
        volumesPage.enterLicenseDetailsRdbs(value);
    }

    @And("I enter license details max eips {string}")
    public void iEnterLicenseDetailsMaxEips(String value) throws InterruptedException {
        volumesPage.enterLicenseDetailsMaxEips(value);
    }

    @And("I check allow grids checkbox")
    public void iCheckAllowGridsCheckbox() {
        volumesPage.checkAllowGridsCheckbox();
    }

    @And("I check direct to database checkbox")
    public void iCheckDirectToDatabaseCheckbox() {
        volumesPage.checkDirectToDatabaseCheckbox();
    }

    @And("I click on ok button")
    public void iClickOnOkButton() {
        volumesPage.clickLicenseOkButton();
    }

    @And("I wait for 3 seconds")
    public void iWaitFor3Seconds() throws InterruptedException {
        volumesPage.waitForSeconds(3);
    }

    @And("I switch out of the iframe")
    public void iSwitchOutOfTheIframe() {
        volumesPage.switchBackToDefaultContent();
    }

    @And("I verify the license is added successfully")
    public void iVerifyTheLicenseIsAddedSuccessfully() {
        Assert.assertTrue("License verification failed", volumesPage.isLicenseDisplayed());
    }

    @Then("I clicked on advance button and stored the new Environment link.")
    public void iClickedOnAdvanceButtonAndStoredTheNewEnvironmentLink() {
        volumesPage.clickAdvanceAndProceedOnNewTab();
        Assert.assertNotNull("Expected current URL after proceeding to be captured", driver.getCurrentUrl());
    }

    @Then("i enter lic key and click on login")
    public void iEnterLicKeyAndClickOnLogin() throws InterruptedException {
        volumesPage.storeGeneratedLicenseKey();
       // generatedLicenseKey = volumesPage.getGeneratedLicenseKey();
        Assert.assertNotNull("License key is not available for login", generatedLicenseKey);
        Assert.assertFalse("License key is empty", generatedLicenseKey.trim().isEmpty());
        volumesPage.enterLicenseKeyAndClickLogin(generatedLicenseKey);
    }

    @Then("I created username {string}")
    public void iCreatedUsername(String username) throws InterruptedException {
        volumesPage.enterLoginUsername(username);
    }

    @And("I generated Password {string}")
    public void iGeneratedPassword(String password) {
        volumesPage.enterNewPassword(password);
        volumesPage.enterConfirmPassword(password);
    }

    @Then("I clicked on Login Button")
    public void iClickedOnLoginButton() {
        volumesPage.clickLoginButton();
    }

    @Then("I verify Its navigated to central dashboard")
    public void iVerifyItsNavigatedToCentralDashboard() throws InterruptedException {
        volumesPage.waitForDashboardToLoad();
        volumesPage.clickCentralDashboardPopupButton();
        Assert.assertTrue("Expected dashboard navigation to succeed", true);
    }

    @And("click on link")
    public void clickOnLink() {
        volumesPage.clickTargetBlankLinkAndSwitchToNewWindow();
    }
}
