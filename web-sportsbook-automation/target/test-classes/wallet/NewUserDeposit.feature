Feature: New User Deposit
#This feature file requires a sportsbook account to be made, verified,
#and credentials for the account needs to be in the config properties need to be before running

  Background:
    Given user goes to sportsbook homepage
    And user logs in with "first time deposit account"

  @NewUserRegression @NewUserSmoke
  Scenario: Verify user is able to make a deposit through trustly with a new bank
    When user opens "my account" model
    And navigates to "deposit" page
    And user places a "25" deposit amount through Trustly with new bank
    Then the sportsbook should deposit the amount

  @NewUserRegression @NewUserSmoke
  Scenario: Verify user is able to make a deposit with a new credit card
    When user opens "my account" model
    And navigates to "deposit" page
    And user places a "12" deposit amount through credit card with Paysafe for the first time
    Then the sportsbook should deposit the amount