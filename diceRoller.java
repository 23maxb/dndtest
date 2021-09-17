import java.util.regex.*; // ignore error this is used
import java.lang.Math;

public class diceRoller {

    // returns the value of a single roll of a ?d?
    public static int singleRoll(String roll) {
        String s = roll;
        s = s.replaceAll("\\s", "");
        s = s.toLowerCase();
        int z = 0;
        System.out.println("Rolling " + Integer.parseInt(s.substring(0, s.indexOf("d"))) + "d"
                + Integer.parseInt(s.substring(s.indexOf("d") + 1, s.length())) + ".");
        for (int i = 0; i < Integer.parseInt(s.substring(0, s.indexOf("d"))); i++) {
            int out = getRandomIntegerInRange(1, Integer.parseInt(s.substring(s.indexOf("d") + 1, s.length())));
            System.out.println("Rolled a " + out + ".");
            z += out;
        }
        System.out.println("Rolling " + s + " gave " + z + ".");
        return z;
    }

    // returns the value of a complex roll in integer form
    public static int multiIntRoll(String roll) throws Exception {
        return (int) (Math.round(multiRoll(roll)));
    }

    // returns the value of a complex roll in double form
    public static double multiRoll(String roll) throws Exception {
        String s = roll;
        s = s.replaceAll("\\s", "");
        s = s.toLowerCase();
        int start = 0;
        int end = 0;
        if (countCharOcc(s, "(") != countCharOcc(s, ")"))
            throw new Exception("Mismatched Parentheses");
        while (s.indexOf("d") != -1) {

            // determining the starting index for a role in the first part of the expression
            for (int i = s.indexOf("d") - 1; true; i--) {
                if (!(i >= 0 && (s.substring(i, i + 1).compareTo("0") == 0 || s.substring(i, i + 1).compareTo("1") == 0
                        || s.substring(i, i + 1).compareTo("2") == 0 || s.substring(i, i + 1).compareTo("3") == 0
                        || s.substring(i, i + 1).compareTo("4") == 0 || s.substring(i, i + 1).compareTo("5") == 0
                        || s.substring(i, i + 1).compareTo("6") == 0 || s.substring(i, i + 1).compareTo("7") == 0
                        || s.substring(i, i + 1).compareTo("8") == 0 || s.substring(i, i + 1).compareTo("9") == 0))) {
                    start = i + 1;
                    break;
                }
            }

            // determining the ending index for a role in the first part of the expression
            for (int i = s.indexOf("d"); true; i++) {
                if (!(i <= s.length() - 1 && (s.substring(i, i + 1).compareTo("0") == 0
                        || s.substring(i, i + 1).compareTo("1") == 0 || s.substring(i, i + 1).compareTo("2") == 0
                        || s.substring(i, i + 1).compareTo("3") == 0 || s.substring(i, i + 1).compareTo("4") == 0
                        || s.substring(i, i + 1).compareTo("5") == 0 || s.substring(i, i + 1).compareTo("6") == 0
                        || s.substring(i, i + 1).compareTo("7") == 0 || s.substring(i, i + 1).compareTo("8") == 0
                        || s.substring(i, i + 1).compareTo("9") == 0 || s.substring(i, i + 1).compareTo("d") == 0))) {

                    end = i;
                    break;
                }
            }
            s = s.substring(0, start) + singleRoll(s.substring(start, end)) + s.substring(end);
        }
        return eval(s);
    }

    // returns a random integer in the range min to max including min and max
    private static int getRandomIntegerInRange(int min, int max) {
        // flipping parameters in case of min and max swap
        if (min >= max) {
            return getRandomIntegerInRange(max, min);
        }
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    // counts the amount of character occurences in a string
    private static int countCharOcc(String str, String findStr) {
        int lastIndex = 0;
        int count = 0;
        while (lastIndex != -1) {
            lastIndex = str.indexOf(findStr, lastIndex);
            if (lastIndex != -1) {
                count++;
                lastIndex += findStr.length();
            }
        }
        return count;
    }

    // evaluates a mathematical expression in a string
    // stolen from stack overflow
    public static double eval(final String str) {
        if (str.compareTo("") == 0)
            return 0;
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ')
                    nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length())
                    throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            // | number | functionName factor | factor `^` factor
            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+'))
                        x += parseTerm(); // addition
                    else if (eat('-'))
                        x -= parseTerm(); // subtraction
                    else
                        return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*'))
                        x *= parseFactor(); // multiplication
                    else if (eat('/'))
                        x /= parseFactor(); // division
                    else
                        return x;
                }
            }

            double parseFactor() {
                if (eat('+'))
                    return parseFactor(); // unary plus
                if (eat('-'))
                    return -parseFactor(); // unary minus
                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.')
                        nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z')
                        nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt"))
                        x = Math.sqrt(x);
                    else if (func.equals("sin"))
                        x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos"))
                        x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan"))
                        x = Math.tan(Math.toRadians(x));
                    else
                        throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^'))
                    x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

}
