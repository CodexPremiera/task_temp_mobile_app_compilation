package task.mobile_app_portfolio.app03_calculator.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class PostfixConverter {
    private final Stack<Character> operatorStack = new Stack<>();

    private final Stack<Boolean> doParenthesisMultiply = new Stack<>();
    private final Stack<Boolean> doParenthesisHaveSigns = new Stack<>();
    private final Stack<String> parenthesisSigns = new Stack<>();

    private final ArrayList<String> unarySigns = new ArrayList<>();
    private final ArrayList<String> outputQueue = new ArrayList<>();

    private final Map<Character, Integer> precedence = new HashMap<>();

    // CONSTRUCTOR
    public PostfixConverter() {
        precedence.put('+', 1);
        precedence.put('-', 1);
        precedence.put('*', 2);
        precedence.put('/', 2);
        precedence.put('^', 3);
    }


    // TODO fix parenthesis signs
    public ArrayList<String> convertToPostfix(String infixExpression) {
        // Prepare List
        operatorStack.clear();
        doParenthesisMultiply.clear();
        doParenthesisHaveSigns.clear();
        parenthesisSigns.clear();
        unarySigns.clear();
        outputQueue.clear();

        infixExpression = infixExpression.replace(" ", "");
        boolean numberHasSigns = false;
        int unaryCount = 0;


        for (int i = 0; i < infixExpression.length(); i++) {
            Character token = infixExpression.charAt(i);
            char prevToken = (i > 0) ? infixExpression.charAt(i - 1) : ' ';


            // OPEN PARENTHESIS: push, see if it has signs, see if previous is ')' meaning multiply
            if (token == '(') {
                operatorStack.push(token);

                // record parenthesis signs
                if (unaryCount > 0) {
                    doParenthesisHaveSigns.push(true);
                    moveUnarySignsToParenthesisSigns();
                    unaryCount = 0;
                } else {
                    doParenthesisHaveSigns.push(false);
                }

                // record parenthesis multiplication
                boolean isParenthesisMultiply = prevToken == ')';
                doParenthesisMultiply.push(isParenthesisMultiply);
            }

            // CLOSE PARENTHESIS: pop and enqueue signs until open parenthesis, load pending signs
            else if (token == ')') {
                boolean parenthesisHasSigns = doParenthesisHaveSigns.pop();

                // load variable signs
                if (numberHasSigns && !parenthesisHasSigns) {
                    moveUnarySignsToOutput();
                    numberHasSigns = false;
                }

                // load signs from stack
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(')
                    outputQueue.add(String.valueOf(operatorStack.pop()));
                operatorStack.pop();

                // load parenthesis signs
                if (parenthesisHasSigns)
                    moveParenthesisSignsToOutput();

                // see if parenthesis is to multiply
                if (doParenthesisMultiply.pop())
                    outputQueue.add("*");
            }

            // NUMBER OR DECIMAL POINT: add token to output
            else if (Character.isDigit(token) || token == '.') {
                // if followed by unary signs
                if (unaryCount > 0) {
                    numberHasSigns = true;
                    unaryCount = 0;
                }

                // multi digit
                if (Character.isDigit(prevToken) || prevToken == '.')
                    outputQueue.add(outputQueue.remove(outputQueue.size() - 1) + token);

                    // single digit
                else
                    outputQueue.add(String.valueOf(token));
            }

            // OPERATORS: pop and enqueue higher precedent tops
            else {
                // set aside unary signs
                if ( (precedence.containsKey(prevToken) || prevToken == ' ' || prevToken == '(') &&
                        (token == '-' || token == '+')) {
                    unaryCount++;
                    if (token == '-')
                        unarySigns.add("-1");
                    else
                        unarySigns.add("1");
                    continue;
                }

                // load unary signs before operation
                if (numberHasSigns) {
                    moveUnarySignsToOutput();
                    numberHasSigns = false;
                }

                // load operations from stack
                while ( !operatorStack.isEmpty() ) {
                    int stackPrecedence;
                    int tokenPrecedence;

                    try {
                        stackPrecedence = precedence.getOrDefault(operatorStack.peek(), 0);
                        tokenPrecedence = precedence.get(token);
                    } catch (NullPointerException ne) {
                        break;
                    }

                    if (stackPrecedence < tokenPrecedence) break;

                    outputQueue.add(String.valueOf(operatorStack.pop()));
                }

                operatorStack.push(token);
            }
        }

        // pop remaining signs and operators
        if (numberHasSigns)
            moveUnarySignsToOutput();

        while (!operatorStack.isEmpty())
            outputQueue.add(String.valueOf(operatorStack.pop()));

        // return new arraylist of postfix items
        return new ArrayList<>(outputQueue);
    }


    // HELPERS
    private void moveUnarySignsToOutput() {
        int signsCount = unarySigns.size();
        for (int j = 0; j < signsCount; j++) {
            outputQueue.add(unarySigns.remove(0));
            outputQueue.add("*");
        }
    }

    private void moveUnarySignsToParenthesisSigns() {
        // separator
        parenthesisSigns.push( "," );

        // Reverse the order of unary signs before adding them
        Collections.reverse(unarySigns);

        int signsCount = unarySigns.size();
        for (int j = 0; j < signsCount; j++)
            parenthesisSigns.push( unarySigns.remove(0) );
    }

    private void moveParenthesisSignsToOutput() {
        System.out.println(parenthesisSigns);
        while (!Objects.equals(parenthesisSigns.peek(), ",")) {
            outputQueue.add(parenthesisSigns.pop());
            outputQueue.add("*");
        }

        // pop the separator
        parenthesisSigns.pop();
    }
}