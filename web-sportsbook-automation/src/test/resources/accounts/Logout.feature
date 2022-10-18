Feature: Logout

  Background:
    Given user goes to sportsbook homepage
    And user logs in with "correct credentials"

  @Regression @Smoke
  Scenario: Validate user can log out of their account
    When user logs out
    Then user should see "You have been logged out" text upon logout