Feature: Deposit

  Background:
    Given user goes to sportsbook homepage
    And user logs in with "correct credentials"

  @Regression
  Scenario: Verify user is able to make a deposit with an existing bank
    When user opens "my account" model
    And navigates to "deposit" page
    And user places a "13" deposit amount with an existing bank
    Then the sportsbook should deposit the amount

  @Regression
  Scenario: Verify user is able to make a deposit with a existing credit card
    When user opens "my account" model
    And navigates to "deposit" page
    And user places a "12" deposit amount with an existing credit card
    Then the sportsbook should deposit the amount

  @Regression
  Scenario: Verify user is able to make a deposit with dollars and cents through an existing bank
    When user opens "my account" model
    And navigates to "deposit" page
    And user places a "12.79" deposit amount with an existing bank
    Then the sportsbook should deposit the amount