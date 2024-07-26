Feature: Search Functionality

  Scenario: Successful search with a valid keyword
    Given the user is on the homepage
    When the user enters "Cucumber" into the search bar
    And the user presses the search button
    Then the search results should display items related to "Banana"
