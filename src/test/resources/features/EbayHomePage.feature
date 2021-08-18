@feature1
Feature: Feature 1

  @t1
  Scenario: Verify ebAy Home page is displayed
    Given I am on ebay home page
    Then i See homepage

  @t2
  Scenario: verify user is able to perfrom search in ebayHome page
    Given I am on ebay home page
    When I perfrom search operartion with "Lenovo"
    Then I see results matching to my search "Lenovo"

  @t3
  Scenario Outline: verify user is able to perfrom search in ebayHome page
    Given I am on ebay home page
    When I perfrom search operartion with "<SearchString>"
    Then I see results matching to my search "<SearchString>"

    Examples:
      | SearchString |
      | Lenovo   |
      | Dell    |


#
#  @t2
#  Scenario Outline: Test For test2
#    Given I am on ebay home page
#    Then i See homepage "<Test>"
#
#    Examples:
#
#      | Test |
#      | 1    |
#      | 2    |