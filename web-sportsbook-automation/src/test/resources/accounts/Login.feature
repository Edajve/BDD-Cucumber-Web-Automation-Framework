Feature: Login

  Background:
    Given user goes to sportsbook homepage

  @Regression @Smoke
  Scenario: Log in to sportsbook as valid user
    When user logs in with "correct credentials"
    Then user should be logged into their account

  @Regression
  Scenario: User denied due to invalid credentials
    When user logs in with "incorrect credentials"
    Then user should receive an login error message of "That email and password combination is not valid." not allowing them to login

  @Regression
  Scenario: Suspended user attempts to log in to sportsbook
    When user logs in with "suspended account"
    Then user should receive an login error message of "Your account has been suspended by Fubo Sportsbook. You are now unable to place a wager, deposit funds and withdraw funds. In order to remedy the situation please contact Fubo Sportbook customer service." not allowing them to login

  @Regression
  Scenario: User with deactivated account tries to log in
    When user logs in with "deactivated account"
    Then user should receive an login error message of "Account status is DEACTIVATED" not allowing them to login

  @Regression
  Scenario: User navigates to sign up page from login model
    When navigates to "login" page
    And clicks "Sign Up" link
    Then user should be navigated to the sign up model
