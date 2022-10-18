Feature:Registration

  Background:
    Given user goes to sportsbook homepage

  @Regression @Smoke
  Scenario: Sign up to the sportsbook
    Given user signs up using this information
      | Email           | Password        | Zip Code |
      | dechols@fubo.tv | FuboTV#yyyyMMdd | 60677    |
    And completes KYC with this information
      | First Name | Last Name  | Date Of Birth | House Number    | Street Name | City      | State    | Zip Code | Phone Number     | SSN           | Strong Auth | Promotion Code |
      | Test       | Automation | 4/18/1983     | 450 Test Avenue | Test        | Test Park | Illinois | 60677    | Generated Number | Generated SSN | No          | N/A            |
    Then the user should be registered but not verified to the sportsbook

  @Regression
  Scenario: User under 21 attempts to register to the sportsbook
    Given user signs up using this information
      | Email           | Password        | Zip Code |
      | dechols@fubo.tv | FuboTV#yyyyMMdd | 60677    |
    And completes KYC with date of birth information under twenty-one
      | First Name | Last Name  | Date Of Birth | House Number    | Street Name | City      | State    | Zip Code | Phone Number     | SSN           | Strong Auth | Promotion Code |
      | Android    | Automation | 4/13/2010     | 450 Test Avenue | Test        | Test Park | Illinois | 60677    | Generated Number | Generated SSN | No          | N/A            |
    Then the sportsbook should give a "Birth Date is invalid" error message and not allowed to sign up to the sportsbook