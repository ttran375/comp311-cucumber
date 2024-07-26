package stepdefinitions;

import calculator.Calculator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertEquals;

public class SearchSteps {
    private Calculator calculator;
    private String result;

    @Given("the calculator is started")
    public void the_calculator_is_started() {
        calculator = new Calculator();
    }

    @When("the user enters {string}")
    public void the_user_enters(String expression) {
        result = calculator.evaluate(expression);
    }

    @Then("the result should be {string}")
    public void the_result_should_be(String expectedResult) {
        assertEquals(expectedResult, result);
    }
}
