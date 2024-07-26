Feature: Calculator

  Scenario: Addition
    Given the calculator is started
    When the user enters "2 + 3"
    Then the result should be "5"

  Scenario: Subtraction
    Given the calculator is started
    When the user enters "5 - 2"
    Then the result should be "3"