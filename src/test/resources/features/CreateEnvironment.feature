@Environment
Feature: Create Environment
  As a user of CloudTest Manager
  I want to create environments using the environment creation flow
  So that I can create single and dual instance environments

  Background:
    Given I navigate to the CloudTest Manager login page
    When I enter username "viprasad"
    And I enter password "password123"
    And I click on the Login button
    Then I should be logged in successfully and redirected to dashboard

  @SingleInstance
  Scenario: Create environment with single instance
    When I open the volumes page
    And I click on the new volume button
    And I select the IAM account from the dropdown "Vishal IAM account-19june-testing"
    And I enter volume name "vishal-v2"
    And I enter volume size "20"
    And I click on the Finish button
    Then the created volume should be displayed
    When I open new licence page
    And I enter license name "v1"
    And I enter license description "description"
    And I check the license can create grid launching cpas checkbox
    And I click on Generate Key button
    And I store the generated license key
    And I enter license details tenants "10"
    And I enter license details concurrent vus "10"
    And I enter license details external maestros "10"
    And I enter license details conductors "10"
    And I enter license details rdbs "10"
    And I enter license details max eips "10"
    And I check allow grids checkbox
    And I check direct to database checkbox
    And I click on ok button
    And I wait for 3 seconds
    And I switch out of the iframe
    And I verify the license is added successfully
    When I open the environments page
    #And I click on the new volume button
    And I select the environment IAM account from the dropdown "Vishal IAM account-19june-testing"
    And I select the region from the dropdown "af-south-1"
    And I enter max instances "100"
    And I enter name "Vishal1"
    And I checked the single instance radio button
    And I click on the Deploy button
    And After click on the Deploy button, I should see and click on Initialize and continue button
    Then Wait for deployment to complete and verify the environment is created successfully

  @DualInstance
  Scenario: Create environment with dual instance
    When I open the volumes page
    And I click on the new volume button
    And I select the IAM account from the dropdown "Vishal IAM account-19june-testing"
    And I enter volume name "vishal-v3"
    And I enter volume size "20"
    And I click on the Finish button
    Then the created volume should be displayed
    When I open new licence page
    And I enter license name "v1"
    And I enter license description "description"
    And I check the license can create grid launching cpas checkbox
    And I click on Generate Key button
    And I store the generated license key
    And I enter license details tenants "10"
    And I enter license details concurrent vus "10"
    And I enter license details external maestros "10"
    And I enter license details conductors "10"
    And I enter license details rdbs "10"
    And I enter license details max eips "10"
    And I check allow grids checkbox
    And I check direct to database checkbox
    And I click on ok button
    And I wait for 3 seconds
    And I switch out of the iframe
    And I verify the license is added successfully
    When I open the environments page
    #And I click on the new volume button
    And I select the environment IAM account from the dropdown "Vishal IAM account-19june-testing"
    And I select the region from the dropdown "af-south-1"
    And I enter max instances "100"
    And I enter name "Vishal1"
    And I unchecked the single instance radio button
    And I click on the Deploy button
    And After click on the Deploy button, I should see and click on Initialize and continue button
    Then Wait for deployment to complete and verify the environment is created successfully
    Then I clicked on advance button and stored the new Environment link.
    Then i enter lic key and click on login
    Then I created username "vip"
    And I generated Password "password123"
    Then I clicked on Login Button
    Then I verify Its navigated to central dashboard
