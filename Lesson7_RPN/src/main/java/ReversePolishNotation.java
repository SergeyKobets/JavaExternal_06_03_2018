import exception.WrongExpressionException;

import java.util.*;

import static utilRPN.Util.*;

public class ReversePolishNotation {


    private List<String> reversePolishNotation = new ArrayList<>();
    private Deque<String> operationStack = new ArrayDeque<>();
    private String expression = "";

    public ReversePolishNotation(String expr) {
        expression = expr.replaceAll(" ", "");
    }

    public List<String> getReversePolishNotation() {
        return reversePolishNotation;
    }

    public int precedence(String token) {
        if (token.equals("(")) return 1;
        if (token.equals("+") || token.equals("-")) return 2;
        if (token.equals("*") || token.equals("/")) return 3;
        if (token.equals("^") || isFunction(token)) return 4;
        return 5;
    }

    public void parse() throws WrongExpressionException {
        StringTokenizer tokenizer = new StringTokenizer(expression, (OPERATORS + BRACKETS), true);

        String previous = "";
        String current = "";

        while (tokenizer.hasMoreTokens()) {
            current = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens() && isOperator(current))
                throw new WrongExpressionException("Wrong expression!");

            if (isOperand(current)) {
                if (previous.equals(NEGATIVE_SIGN))
                    reversePolishNotation.add("-" + current);
                else
                    reversePolishNotation.add(current);
            } else if (current.equals("(")) {
                operationStack.add(current);
            } else if (current.equals(")")) {
                String currentOperator = operationStack.pollLast();
                while (!operationStack.isEmpty() && !currentOperator.equals("(")) {
                    reversePolishNotation.add(currentOperator);
                    currentOperator = operationStack.pollLast();
                }
            } else {
                if ((previous.equals("") || isDelimiter(previous)) && current.equals("-")) {
                    previous = NEGATIVE_SIGN;
                    continue;
                }
                addOperatorToStack(current);
            }
            previous = current;
        }

        while (!operationStack.isEmpty())
            reversePolishNotation.add(operationStack.pollLast());
    }

    private void addOperatorToStack(String operator) {
        if (operationStack.isEmpty()) {
            operationStack.add(operator);
        } else {
            if (!(precedence(operator) > precedence(operationStack.getLast()))) {
                reversePolishNotation.add(operationStack.pollLast());
                addOperatorToStack(operator);
            } else {
                operationStack.add(operator);
            }
        }
    }

    public double calculate() {
        double returnValue = 0;
        Stack<String> stack = new Stack<>();
        for (String t : reversePolishNotation) {
            if (isOperand(t)) {
                stack.push(t);
            } else {
                if (isFunction(t)) {
                    int index = FUNCTIONS.indexOf(t);
                    double a = Double.valueOf(stack.pop());
                    switch (index) {
                        case 0:
                            stack.push(String.valueOf(Math.sin(Math.toRadians(a))));
                            break;
                        case 1:
                            stack.push(String.valueOf(Math.cos(Math.toRadians(a))));
                            break;
                        case 2:
                            stack.push(String.valueOf(Math.sqrt(a)));
                            break;
                    }
                } else {
                    int index = OPERATORS.indexOf(t);
                    double a = Double.valueOf(stack.pop());
                    double b = Double.valueOf(stack.pop());
                    switch (index) {
                        case 0:
                            stack.push(String.valueOf(a + b));
                            break;
                        case 1:
                            stack.push(String.valueOf(b - a));
                            break;
                        case 2:
                            stack.push(String.valueOf(a * b));
                            break;
                        case 3:
                            if (a == 0 || b == 0) {
                                throw new NullPointerException();
                            }
                            stack.push(String.valueOf(b / a));
                            break;
                        case 4:
                            stack.push(String.valueOf((int) b ^ (int) a));
                            break;
                    }
                }

            }
        }
        returnValue = Double.valueOf(stack.pop());

        return returnValue;

    }

    @Override
    public String toString() {
        return reversePolishNotation.toString();
    }
}
