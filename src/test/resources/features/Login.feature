@Login
Feature: Login Functionality
  As a user of CloudTest Manager
  I want to login to the application
  So that I can access the Central dashboard

  Background:
    Given I navigate to the CloudTest Manager login page

  @login @Smoke
  Scenario: Login with valid credentials
    When I enter username "viprasad"
    And I enter password "password123"
    And I click on the Login button
    Then I should be logged in successfully and redirected to dashboard

  @InvalidLogin
  Scenario: Login with invalid credentials
    When I enter username "invaliduser"
    And I enter password "invalidpass123"
    And I click on the Login button
    Then I should see an error message

  @EmptyCredentials
  Scenario: Login with empty credentials
    When I enter username ""
    And I enter password ""
    And I click on the Login button
    Then I should see a validation message

