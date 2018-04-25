import exception.WrongExpressionException;

public class Main {
    public static void main(String[] args) {
        ReversePolishNotation rpn = null;
        try {
            rpn = new ReversePolishNotation("90 + 5*(4 /2)");
            rpn.parse();
        } catch (WrongExpressionException e) {
            System.out.println(e.getMessage());
        }


        System.out.println(rpn);
        System.out.println(rpn.calculate());
    }
}
