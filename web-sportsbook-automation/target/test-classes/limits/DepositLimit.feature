Feature: Deposit Limit

  @Regression
  Scenario: Verify user is not able deposit past a set daily deposit limit
    Given user goes to sportsbook homepage
    And user logs in with "a account that has daily deposit limit set"
    When user opens "my account" model
    And navigates to "deposit" page
    And tries to deposit more their "daily" limit
    Then the sportsbook should show a "You have exceeded your daily deposit limit" error message

  @Regression
  Scenario: Verify user is not able deposit past a set weekly deposit limit
    Given user goes to sportsbook homepage
    And user logs in with "a account that has weekly deposit limit set"
    When user opens "my account" model
    And navigates to "deposit" page
    And tries to deposit more their "weekly" limit
    Then the sportsbook should show a "You have exceeded your weekly deposit limit" error message

  @Regression
  Scenario: Verify user is not able deposit past a set monthly deposit limit
    Given user goes to sportsbook homepage
    And user logs in with "a account that has monthly deposit limit set"
    When user opens "my account" model
    And navigates to "deposit" page
    And tries to deposit more their "monthly" limit
    Then the sportsbook should show a "You have exceeded your monthly deposit limit" error message