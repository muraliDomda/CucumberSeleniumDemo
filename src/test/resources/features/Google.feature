@feature1
Feature: Google Search

@GS_001
  Scenario: Search anything in Google
    Given I am on Google Search page
    When i perfrom search with "Testing" text
    Then I see Search results matching to "Testing" string
