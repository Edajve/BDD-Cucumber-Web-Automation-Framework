Feature: Withdrawal

  Background:
    Given user goes to sportsbook homepage
    And user logs in with "correct credentials"

  @Regression @Smoke
  Scenario: Verify user is able to make a withdraw with an existing bank
    When user opens "my account" model
    And navigates to "withdraw" page
    And user places a "20" withdraw amount with an existing bank
    Then the sportsbook should withdraw the amount

  @Regression
  Scenario: Verify user is able to make a withdraw with dollars and cents through an existing bank
    When user opens "my account" model
    And navigates to "withdraw" page
    And user places a "12.86" withdraw amount with an existing bank
    Then the sportsbook should withdraw the amount