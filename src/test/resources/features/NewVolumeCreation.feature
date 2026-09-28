@Volumes
Feature: New Volume Creation
  As a user of CloudTest Manager
  I want to create a new volume
  So that I can select the required IAM account

  Background:
    Given I navigate to the CloudTest Manager login page
    When I enter username "viprasad"
    And I enter password "password123"
    And I click on the Login button
    Then I should be logged in successfully and redirected to dashboard

  @test1
  Scenario: Create new volume and select IAM account
#    When I open the volumes page
#    And I click on the new volume button
#    And I select the IAM account from the dropdown "Vishal IAM account-19june-testing"
#    And I enter volume name "vishal-v1"
#    And I enter volume size "20"
#    And I click on the Finish button
#    Then the created volume should be displayed
    When I open the environments page
    And click on link
    Then I clicked on advance button and stored the new Environment link.
    Then i enter lic key and click on login
    Then I created username "vip"
    And I generated Password "password123"
    Then I clicked on Login Button
    Then I verify Its navigated to central dashboard
