Feature: Wager

  Background:
    Given user goes to sportsbook homepage
    And user logs in with "correct credentials"

  @Regression @Smoke
  Scenario: Verify user is able to place a single bets
    When user places a bet for "8" dollars
    Then bet should be placed

  @Regression
  Scenario: Verify user is able to place a multiple bets
    When user places "2" bets for "8" dollars
    Then "2" bet should be placed