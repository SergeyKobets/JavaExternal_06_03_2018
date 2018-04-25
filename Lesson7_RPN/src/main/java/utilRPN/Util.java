package utilRPN;

import java.util.Arrays;
import java.util.List;

public class Util {
    public static final String NEGATIVE_SIGN = "ns";
    public static final String OPERATORS = "+-*/^";
    public static final String BRACKETS = "()";
    public static final List<String> FUNCTIONS = Arrays.asList("sin", "cos", "sqrt");

    public static boolean isDelimiter(String token) {
        if (token.length() != 1) return false;
        for (int i = 0; i < (OPERATORS + BRACKETS).length(); i++) {
            if (token.charAt(0) == (OPERATORS + BRACKETS).charAt(i)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isOperator(String token) {
        if (token.equals("")) return false;
        for (int i = 0; i < OPERATORS.length(); i++) {
            if (token.charAt(0) == OPERATORS.charAt(i)) return true;
        }
        return false;
    }

    public static boolean isOperand(String token) {
        return token != null && token.matches("[+-]?\\d*\\.?\\d+");
    }

    public static boolean isFunction(String token) {
        if (FUNCTIONS.contains(token)) return true;
        return false;
    }

}
