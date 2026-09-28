package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.Set;

public class VolumesPage extends BasePage {

    @FindBy(xpath = "//span[text()='Volumes']")
    private WebElement volumesTab;

    @FindBy(xpath = "//span[text()='Environments']")
    private WebElement environmentsTab;

    @FindBy(id = "new_button_icon")
    private WebElement newVolumeButton;

    @FindBy(xpath = "//select[@name='vs_cpa_sbox']")
    private WebElement accountDropdown;

    @FindBy(xpath = "//div[@id='WizardNextButton']")
    private WebElement wizardNextButton;

    @FindBy(xpath = "//input[@id='vs_create_name']")
    private WebElement volumeNameInput;

    @FindBy(xpath = "//input[@id='vs_create_size']")
    private WebElement volumeSizeInput;

    @FindBy(xpath = "//div[@id='WizardFinishButton']")
    private WebElement wizardFinishButton;

    @FindBy(xpath = "//img[@id='new_button_icon']")
    private WebElement newEnvironmentButton;

    @FindBy(xpath = "//select[@id='tenv_launch_cpa']")
    private WebElement environmentAccountDropdown;

    @FindBy(xpath = "//select[@id='tenv_region']")
    private WebElement environmentRegionDropdown;

    @FindBy(xpath = "//input[@id='tenv_max_instances_launch']")
    private WebElement maxInstancesInput;

    @FindBy(xpath = "//div[@id='deploy_btn']")
    private WebElement deployButton;

    @FindBy(xpath = "//input[@id='tenv_db_separate_instance']")
    private WebElement singleInstanceCheckbox;

    @FindBy(xpath = "//span[text()='Initialize and Continue']")
    private WebElement initializeAndContinueButton;

    @FindBy(xpath = "//img[@id='success_stamp']")
    private WebElement successStamp;

    @FindBy(xpath = "//td[@id='cs_mi_4']")
    private WebElement environmentResultLink;

    @FindBy(xpath = "//input[@name='tenv_name']")
    private WebElement environmentNameInput;

    @FindBy(xpath = "//iframe[@src='LicenseDialog?licenseID=-1']")
    private WebElement licenseDialogFrame;

    @FindBy(id = "license_name")
    private WebElement licenseNameInput;

    @FindBy(id = "license_description")
    private WebElement licenseDescriptionInput;

    @FindBy(xpath = "//input[@name='license_can_create_grid_launching_cpas']")
    private WebElement licenseCreateGridLaunchingCpasCheckbox;

    @FindBy(xpath = "//span[text()='Generate Key']")
    private WebElement generateKeyButton;

    @FindBy(xpath = "//input[@id='license_key']")
    private WebElement licenseKeyInput;

    @FindBy(id = "license_details_tenants")
    private WebElement licenseDetailsTenantsInput;

    @FindBy(id = "license_details_concurrent_vus")
    private WebElement licenseDetailsConcurrentVusInput;

    @FindBy(id = "license_details_external_maestros")
    private WebElement licenseDetailsExternalMaestrosInput;

    @FindBy(id = "license_details_conductors")
    private WebElement licenseDetailsConductorsInput;

    @FindBy(id = "license_details_rdbs")
    private WebElement licenseDetailsRdbsInput;

    @FindBy(id = "license_details_max_eips")
    private WebElement licenseDetailsMaxEipsInput;

    @FindBy(xpath = "//input[@name ='license_details_allow_grids']")
    private WebElement allowGridsCheckbox;

    @FindBy(xpath = "//input[@name ='license_details_direct_to_database']")
    private WebElement directToDatabaseCheckbox;

    @FindBy(id = "btnOk")
    private WebElement licenseOkButton;

    @FindBy(xpath = "//span[text()='Licenses']")
    private WebElement licensesTab;

    @FindBy(name = "userName")
    private WebElement loginUserNameInput;

    @FindBy(name = "newPassword")
    private WebElement newPasswordInput;

    @FindBy(name = "confirmPassword")
    private WebElement confirmPasswordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//div[text()='c']")
    private WebElement centralDashboardPopupButton;

    private String generatedLicenseKey;

    private static final By ACCOUNT_DROPDOWN_BY = By.xpath("//select[@name='vs_cpa_sbox']");
    private static final By ENVIRONMENT_ACCOUNT_DROPDOWN_BY = By.xpath("//select[@id='tenv_launch_cpa']");
    private static final By ENVIRONMENT_REGION_DROPDOWN_BY = By.xpath("//select[@id='tenv_region']");
    private static final String POPUP_FRAME_ID = "FrameOverlay_12532300002175";
    private static final String ENVIRONMENT_FRAME_ID = "concertoPage_page1";
    private static final By INITIALIZE_AND_CONTINUE_BY = By.xpath("//span[text()='Initialize and Continue']");
    private static final By SUCCESS_STAMP_BY = By.xpath("//img[@id='success_stamp']");
    private static final By ENVIRONMENT_RESULT_LINK_BY = By.xpath("//td[@id='cs_mi_4']");

    public VolumesPage(WebDriver driver) {
        super(driver);
    }

    public VolumesPage clickVolumesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(volumesTab)).click();
        return this;
    }

    public VolumesPage clickEnvironmentsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(environmentsTab)).click();
        return this;
    }

    public VolumesPage clickNewVolumeButton() throws InterruptedException {
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(newVolumeButton));
        try {
            newVolumeButton.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", newVolumeButton);
        }
        return this;
    }

    public VolumesPage clickNewEnvironmentButton() {
        wait.until(ExpectedConditions.elementToBeClickable(newEnvironmentButton));
        try {
            newEnvironmentButton.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", newEnvironmentButton);
        }
        return this;
    }

    private WebElement findDropdownInCurrentContext(By by) {
        if (!driver.findElements(by).isEmpty()) {
            return driver.findElement(by);
        }
        return null;
    }

    private WebElement findDropdownInFramesOrWindows(By by, String frameId) {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (Exception ignored) {
        }

        try {
            driver.switchTo().defaultContent();
        } catch (Exception ignored) {
        }

        if (frameId != null) {
            try {
                driver.switchTo().frame(frameId);
                WebElement found = findDropdownInCurrentContext(by);
                if (found != null) {
                    return found;
                }
            } catch (Exception ignored) {
            }
        }

        try {
            driver.switchTo().defaultContent();
            WebElement inDefault = findDropdownInCurrentContext(by);
            if (inDefault != null) {
                return inDefault;
            }
        } catch (Exception ignored) {
        }

        try {
            for (WebElement frame : driver.findElements(By.tagName("iframe"))) {
                driver.switchTo().defaultContent();
                driver.switchTo().frame(frame);
                WebElement found = findDropdownInCurrentContext(by);
                if (found != null) {
                    return found;
                }
            }
        } catch (Exception ignored) {
        }

        try {
            String originalWindow = driver.getWindowHandle();
            Set<String> windows = driver.getWindowHandles();
            for (String window : windows) {
                driver.switchTo().window(window);
                WebElement found = findDropdownInCurrentContext(by);
                if (found != null) {
                    return found;
                }
            }
            driver.switchTo().window(originalWindow);
        } catch (Exception ignored) {
        }

        return null;
    }

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
    }

    private void clickWizardNextButton() {
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(wizardNextButton));
        scrollIntoView(nextButton);
        try {
            nextButton.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButton);
        }
    }

    private void clickWizardFinishButton() {
        WebElement finishButton = wait.until(ExpectedConditions.elementToBeClickable(wizardFinishButton));
        scrollIntoView(finishButton);
        try {
            finishButton.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", finishButton);
        }
    }

    private void clickDeployButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(deployButton));
        scrollIntoView(button);
        try {
            button.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
    }

    private void typeIntoVisibleField(WebElement element, String value) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(element));
        //scrollIntoView(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
        element.clear();
        element.sendKeys(value);
        Thread.sleep(3000);
    }

    private void selectFromDropdown(By by, String value, String frameId) {
        WebElement dropdown = findDropdownInFramesOrWindows(by, frameId);
        if (dropdown == null) {
            throw new NoSuchElementException("Unable to locate dropdown: " + by);
        }
        wait.until(ExpectedConditions.visibilityOf(dropdown));
        scrollIntoView(dropdown);
        wait.until(ExpectedConditions.elementToBeClickable(dropdown));
        try {
            dropdown.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdown);
        }
        new Select(dropdown).selectByVisibleText(value);
        dropdown.click();
    }

    public VolumesPage selectAccountFromDropdown(String visibleText) throws InterruptedException {
        selectFromDropdown(ACCOUNT_DROPDOWN_BY, visibleText, POPUP_FRAME_ID);
        Thread.sleep(5000);
        clickWizardNextButton();
        return this;
    }

    public VolumesPage enterVolumeName(String volumeName) throws InterruptedException {
        typeIntoVisibleField(volumeNameInput, volumeName);
        return this;
    }

    public VolumesPage enterVolumeSize(String volumeSize) throws InterruptedException {
        typeIntoVisibleField(volumeSizeInput, volumeSize);
        return this;
    }

    public VolumesPage finishWizard() {
        clickWizardFinishButton();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return this;
    }

    public boolean isAccountSelected(String visibleText) {
        try {
            WebElement dropdown = findDropdownInFramesOrWindows(ACCOUNT_DROPDOWN_BY, POPUP_FRAME_ID);
            if (dropdown == null) {
                return false;
            }
            return visibleText.equals(new Select(dropdown).getFirstSelectedOption().getText().trim());
        } catch (NoSuchElementException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public VolumesPage openEnvironmentCreationFlow() {
        clickEnvironmentsTab();
        //clickNewEnvironmentButton();
        return this;
    }

    public VolumesPage selectEnvironmentAccount(String visibleText) {
        selectFromDropdown(By.xpath("//select[@id='tenv_launch_cpa']"), visibleText, ENVIRONMENT_FRAME_ID);
        return this;
    }

    public VolumesPage selectEnvironmentRegion(String visibleText) {
        selectFromDropdown(ENVIRONMENT_REGION_DROPDOWN_BY, visibleText, ENVIRONMENT_FRAME_ID);
        return this;
    }

    public VolumesPage enterEnvironmentMaxInstances(String instances) throws InterruptedException {
        WebElement input = findDropdownInFramesOrWindows(By.xpath("//input[@id='tenv_max_instances_launch']"), ENVIRONMENT_FRAME_ID);
        if (input == null) {
            throw new NoSuchElementException("Unable to locate max instances input");
        }
        typeIntoVisibleField(input, instances);
        return this;
    }

    public VolumesPage enterEnvironmentName(String name) throws InterruptedException {
        WebElement input = findDropdownInFramesOrWindows(By.xpath("//input[@name='tenv_name']"), ENVIRONMENT_FRAME_ID);
        if (input == null) {
            throw new NoSuchElementException("Unable to locate environment name input");
        }
        typeIntoVisibleField(input, name);
        return this;
    }

    public VolumesPage clickDeploy() {
        WebElement button = findDropdownInFramesOrWindows(By.xpath("//div[@id='deploy_btn']"), ENVIRONMENT_FRAME_ID);
        if (button == null) {
            throw new NoSuchElementException("Unable to locate deploy button");
        }
        wait.until(ExpectedConditions.elementToBeClickable(button));
        scrollIntoView(button);
        try {
            button.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
        return this;
    }

    public VolumesPage clickInitializeAndContinueWhenEnabled() {
        long end = System.currentTimeMillis() + 5 * 60 * 1000L;
        while (System.currentTimeMillis() < end) {
            try {
                driver.switchTo().defaultContent();
                WebElement button = findDropdownInFramesOrWindows(INITIALIZE_AND_CONTINUE_BY, ENVIRONMENT_FRAME_ID);
                if (button != null && button.isDisplayed() && button.isEnabled()) {
                    scrollIntoView(button);
                    button.click();
                    return this;
                }
            } catch (Exception ignored) {
            }
            try {
                Thread.sleep(120000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        throw new NoSuchElementException("Initialize and Continue button was not enabled within 5 minutes");
    }

    public VolumesPage waitForDeploymentSuccessAndOpenResult() {
        long end = System.currentTimeMillis() + 10 * 60 * 1000L;
        while (System.currentTimeMillis() < end) {
            try {
                driver.switchTo().defaultContent();
                WebElement stamp = findDropdownInFramesOrWindows(SUCCESS_STAMP_BY, ENVIRONMENT_FRAME_ID);
                if (stamp != null && stamp.isDisplayed()) {
                    try {
                        Thread.sleep(3000L);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    WebElement link = findDropdownInFramesOrWindows(ENVIRONMENT_RESULT_LINK_BY, ENVIRONMENT_FRAME_ID);
                    if (link != null && link.isDisplayed()) {
                        scrollIntoView(link);
                        try {
                            link.click();
                        } catch (Exception e) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
                        }
                        if (driver.getWindowHandles().size() > 1) {
                            switchToNewlyOpenedTab();
                        }
                        return this;
                    }
                }
            } catch (Exception ignored) {
            }
            try {
                Thread.sleep(180000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        throw new NoSuchElementException("Deployment success stamp was not visible within 10 minutes");
    }

    public VolumesPage switchBackToMainAndResultTab() {
        driver.switchTo().defaultContent();
        return this;
    }

    private void switchToDefaultContent() {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception ignored) {
        }
    }

    private void switchToFrame(String frameId) {
        try {
            driver.switchTo().frame(frameId);
        } catch (Exception ignored) {
        }
    }

    private void switchToWindow(String windowHandle) {
        try {
            driver.switchTo().window(windowHandle);
        } catch (Exception ignored) {
        }
    }

    public VolumesPage switchToEnvironmentFrame() {
        switchToDefaultContent();
        switchToFrame(ENVIRONMENT_FRAME_ID);
        return this;
    }

    public VolumesPage switchToMainFrame() {
        switchToDefaultContent();
        return this;
    }

    public VolumesPage switchToNewlyOpenedTab() throws InterruptedException {
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                switchToWindow(windowHandle);
                break;
            }
        }
        Thread.sleep(5000);
        return this;
    }

    public VolumesPage clickAdvanceAndProceedOnNewTab() {
        try {
            String currentWindow = driver.getWindowHandle();
            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(currentWindow)) {
                    driver.switchTo().window(windowHandle);
                    break;
                }
            }
            WebElement advanceButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("details-button")));
            advanceButton.click();
            WebElement proceedLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("proceed-link")));
            proceedLink.click();
        } catch (Exception e) {
            throw new NoSuchElementException("Unable to click advance/proceed on new tab: " + e.getMessage());
        }
        return this;
    }

    public boolean isCreatedVolumeDisplayed(String volumeName) {
        try {
            driver.switchTo().defaultContent();
            By createdVolumeBy = By.xpath(String.format("//span[text()='%s']", volumeName));
            WebElement createdVolume = wait.until(ExpectedConditions.visibilityOfElementLocated(createdVolumeBy));
            scrollIntoView(createdVolume);
            return createdVolume.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public VolumesPage setSingleInstanceChecked(boolean checked) {
        WebElement checkbox = findDropdownInFramesOrWindows(By.xpath("//input[@id='tenv_db_separate_instance']"), ENVIRONMENT_FRAME_ID);
        if (checkbox == null) {
            throw new NoSuchElementException("Unable to locate single instance checkbox");
        }
        wait.until(ExpectedConditions.elementToBeClickable(checkbox));
        boolean selected = checkbox.isSelected();
        if (checked != selected) {
            scrollIntoView(checkbox);
            try {
                checkbox.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
            }
        }
        return this;
    }

    public VolumesPage openNewLicensePage() {
        wait.until(ExpectedConditions.elementToBeClickable(licensesTab)).click();
        wait.until(ExpectedConditions.elementToBeClickable(newVolumeButton)).click();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(licenseDialogFrame));
        return this;
    }

    public VolumesPage enterLicenseName(String licenseName) throws InterruptedException {
        typeIntoVisibleField(licenseNameInput, licenseName);
        return this;
    }

    public VolumesPage enterLicenseDescription(String licenseDescription) throws InterruptedException {
        typeIntoVisibleField(licenseDescriptionInput, licenseDescription);
        return this;
    }

    public VolumesPage checkLicenseCanCreateGridLaunchingCpas() {
        if (!licenseCreateGridLaunchingCpasCheckbox.isSelected()) {
            scrollIntoView(licenseCreateGridLaunchingCpasCheckbox);
            licenseCreateGridLaunchingCpasCheckbox.click();
        }
        return this;
    }

    public VolumesPage generateLicenseKey() {
        scrollIntoView(generateKeyButton);
        generateKeyButton.click();
        return this;
    }

    public VolumesPage storeGeneratedLicenseKey() throws InterruptedException {
        Thread.sleep(4000);
        String key = licenseKeyInput.getAttribute("value");
        if (key == null || key.trim().isEmpty()) {
            key = licenseKeyInput.getDomProperty("value");
        }
        if (key == null || key.trim().isEmpty()) {
            key = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='license_key']"))).getAttribute("value");
        }
        generatedLicenseKey = key != null ? key.trim() : null;
        System.out.println(generatedLicenseKey);
        return this;
    }

    public String getGeneratedLicenseKey() {
        if (generatedLicenseKey == null || generatedLicenseKey.trim().isEmpty()) {
            try {
                generatedLicenseKey = licenseKeyInput.getAttribute("value");
                if (generatedLicenseKey == null || generatedLicenseKey.trim().isEmpty()) {
                    generatedLicenseKey = licenseKeyInput.getDomProperty("value");
                }
                if (generatedLicenseKey != null) {
                    generatedLicenseKey = generatedLicenseKey.trim();
                }
            } catch (Exception ignored) {
            }
        }
        System.out.println(generatedLicenseKey + " outside");
        return generatedLicenseKey;
    }

    public VolumesPage enterLicenseKeyAndClickLogin(String licenseKey) throws InterruptedException {
        WebElement keyInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='license_key']")));
        scrollIntoView(keyInput);
        try {
            keyInput.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", keyInput);
        }
        keyInput.clear();

        String valueToType = licenseKey != null ? licenseKey.trim() : "";
        for (int i = 0; i < valueToType.length(); i++) {
            keyInput.sendKeys(String.valueOf(valueToType.charAt(i)));
            Thread.sleep(120L);
        }

        Thread.sleep(1000L);
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='LOGIN']")));
        scrollIntoView(loginButton);
        try {
            loginButton.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginButton);
        }
        return this;
    }

    public VolumesPage enterLicenseDetailsTenants(String value) throws InterruptedException {
        typeIntoVisibleField(licenseDetailsTenantsInput, value);
        return this;
    }

    public VolumesPage enterLicenseDetailsConcurrentVus(String value) throws InterruptedException {
        typeIntoVisibleField(licenseDetailsConcurrentVusInput, value);
        return this;
    }

    public VolumesPage enterLicenseDetailsExternalMaestros(String value) throws InterruptedException {
        typeIntoVisibleField(licenseDetailsExternalMaestrosInput, value);
        return this;
    }

    public VolumesPage enterLicenseDetailsConductors(String value) throws InterruptedException {
        typeIntoVisibleField(licenseDetailsConductorsInput, value);
        return this;
    }

    public VolumesPage enterLicenseDetailsRdbs(String value) throws InterruptedException {
        typeIntoVisibleField(licenseDetailsRdbsInput, value);
        return this;
    }

    public VolumesPage enterLicenseDetailsMaxEips(String value) throws InterruptedException {
        typeIntoVisibleField(licenseDetailsMaxEipsInput, value);
        return this;
    }

    public VolumesPage checkAllowGridsCheckbox() {
        if (!allowGridsCheckbox.isSelected()) {
            scrollIntoView(allowGridsCheckbox);
            allowGridsCheckbox.click();
        }
        return this;
    }

    public VolumesPage checkDirectToDatabaseCheckbox() {
        if (!directToDatabaseCheckbox.isSelected()) {
            scrollIntoView(directToDatabaseCheckbox);
            directToDatabaseCheckbox.click();
        }
        return this;
    }

    public VolumesPage clickLicenseOkButton() {
        scrollIntoView(licenseOkButton);
        licenseOkButton.click();
        return this;
    }

    public VolumesPage waitForSeconds(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000L);
        return this;
    }

    public VolumesPage switchBackToDefaultContent() {
        driver.switchTo().defaultContent();
        return this;
    }

    public boolean isLicenseDisplayed() {
        driver.switchTo().defaultContent();
        return !driver.findElements(By.xpath("//tr[starts-with(@id,'SelectableListItem')]")).isEmpty();
    }

    public VolumesPage enterLoginUsername(String username) throws InterruptedException {
        WebElement input = wait.until(ExpectedConditions.visibilityOf(loginUserNameInput));
        scrollIntoView(input);
        try {
            input.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", input);
        }
        input.clear();
        input.sendKeys(username);
        return this;
    }

    public VolumesPage enterNewPassword(String password) {
        WebElement input = wait.until(ExpectedConditions.visibilityOf(newPasswordInput));
        scrollIntoView(input);
        input.click();
        input.clear();
        input.sendKeys(password);
        return this;
    }

    public VolumesPage enterConfirmPassword(String password) {
        WebElement input = wait.until(ExpectedConditions.visibilityOf(confirmPasswordInput));
        scrollIntoView(input);
        input.click();
        input.clear();
        input.sendKeys(password);
        return this;
    }

    public VolumesPage clickLoginButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        scrollIntoView(button);
        try {
            button.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
        return this;
    }

    public VolumesPage waitForDashboardToLoad() throws InterruptedException {
        Thread.sleep(8000);
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("Central"),
                ExpectedConditions.titleContains("Central")
        ));
        return this;
    }

    public VolumesPage clickCentralDashboardPopupButton() {
        WebElement popup = wait.until(ExpectedConditions.elementToBeClickable(centralDashboardPopupButton));
        scrollIntoView(popup);
        try {
            popup.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", popup);
        }
        return this;
    }

    public VolumesPage clickTargetBlankLinkAndSwitchToNewWindow() {
        String originalWindow = driver.getWindowHandle();
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@target='_blank']")));
        scrollIntoView(link);
        link.click();
        for (String windowHandle : driver.getWindowHandles()) {
            if (windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        return this;
    }
}
