package calculator;

public class Calculator {
    public String evaluate(String expression) {
        String[] tokens = expression.split(" ");
        int operand1 = Integer.parseInt(tokens[0]);
        String operator = tokens[1];
        int operand2 = Integer.parseInt(tokens[2]);

        int result;
        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
        return String.valueOf(result);
    }
}
