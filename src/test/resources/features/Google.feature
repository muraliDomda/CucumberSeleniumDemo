@feature1
Feature: Google Search

@GS_001
  Scenario: Search anything in Google
    Given I am on Google Search page
    When i perfrom search with "Testing" text
    Then I see Search results matching to "Testing" string


  @GS_002
  Scenario Outline: Search anything in Google
    Given I am on Google Search page
    When i perfrom search with "<SearchString>" text
    Examples:
      | SearchString |
      | MuraliTest1  |
      | MuraliTest2   |
      | muraliTest3  |
      | MuraliTest4   |
      | MuraliTest5  |
      | MuraliTest6   |
      | muraliTest7  |
      | MuraliTest8   |
      | MuraliTest9  |
      | MuraliTest10   |
      | muraliTest11 |
      | MuraliTest12  |