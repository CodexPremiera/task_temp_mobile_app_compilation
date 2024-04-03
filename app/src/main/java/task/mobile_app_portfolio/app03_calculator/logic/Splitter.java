package task.mobile_app_portfolio.app03_calculator.logic;

import java.util.ArrayList;

public class Splitter {
    private static final ArrayList<String> outputQueue = new ArrayList<>();


    // TODO fix parenthesis signs
    public static ArrayList<String> getTermsList(String infixExpression) {
        // Prepare List
        outputQueue.clear();
        infixExpression = infixExpression.replace(" ", "");


        for (int i = 0; i < infixExpression.length(); i++) {
            Character token = infixExpression.charAt(i);
            char prevToken = (i > 0) ? infixExpression.charAt(i - 1) : ' ';

            // DIGITS
            if (Character.isDigit(token) || token == '.') {
                // multi digit
                if (Character.isDigit(prevToken) || prevToken == '.')
                    outputQueue.add(outputQueue.remove(outputQueue.size() - 1) + token);

                    // single digit
                else
                    outputQueue.add(String.valueOf(token));
            }

            // OPERATORS
            else {
                outputQueue.add(String.valueOf(token));
            }
        }

        return new ArrayList<>(outputQueue);
    }
}