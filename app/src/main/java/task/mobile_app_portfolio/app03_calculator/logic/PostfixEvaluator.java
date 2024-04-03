package task.mobile_app_portfolio.app03_calculator.logic;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class PostfixEvaluator {
    private final Stack<Double> valueStack = new Stack<>();

    public double evaluate(ArrayList<String> postfixExpression)
            throws ArithmeticException, IllegalArgumentException {
        if (postfixExpression == null || postfixExpression.isEmpty()) {
            throw new IllegalArgumentException("Empty or null postfix expression.");
        }

        valueStack.clear();

        for (String term : postfixExpression) {
            if (isOperator(term)) {
                try {
                    double b = valueStack.pop();
                    double a = valueStack.pop();
                    double result = applyOperator(a, b, term);
                    valueStack.push(result);
                } catch (EmptyStackException e) {
                    throw new IllegalArgumentException("Malformed postfix expression: not enough operands for operator.");
                }
            } else {
                try {
                    double value = Double.parseDouble(term);
                    valueStack.push(value);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Malformed postfix expression: invalid term encountered.");
                }
            }
        }

        if (valueStack.size() != 1) {
            throw new IllegalArgumentException("Malformed postfix expression: too many operands.");
        }

        return valueStack.peek();
    }

    private static boolean isOperator(String term) {
        return term.equals("+") || term.equals("-") || term.equals("*") || term.equals("/") ||
                term.equals("^") || term.equals("×") || term.equals("÷");
    }

    private static double applyOperator(double a, double b, String operator) throws ArithmeticException {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
            case "×":
                return a * b;
            case "/":
            case "÷":
                if (b == 0) {
                    throw new ArithmeticException("Division by zero.");
                }
                return a / b;
            case "^":
                return Math.pow(a, b);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}