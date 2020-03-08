/**
 * 
 */
package test;

/**
 * @author AhmetEnes
 *
 */
public class eval {
	int pos=-1;
	int ch;
	public String parse(String str) {
		nextChar(str);
		double x = parseExpression(str);
		if (pos < str.length())
            throw new RuntimeException("Unexpected: " + (char) ch);
        return String.valueOf(x);
		
	}
	private void nextChar(String str) {
            ch = (++pos < str.length()) ? str.charAt(pos) : -1;
    }
	private boolean eat(int charToEat,String str) {
        while (ch == ' ')
            nextChar(str);
        if (ch == charToEat) {
            nextChar(str);
            return true;
        }
        return false;
    }
	private double parseExpression(String str) {
        double x = parseTerm(str);
        for (;;) {
            if (eat('+',str))
                x += parseTerm(str); // addition
            else if (eat('-',str))
                x -= parseTerm(str); // subtraction
            else
                return x;
        }
    }
	private double parseTerm(String str) {
        double x = parseFactor(str);
        for (;;) {
            if (eat('*',str))
                x *= parseFactor(str); // multiplication
            else if (eat('/',str))
                x /= parseFactor(str); // division
            else
                return x;
        }
    }
	private double parseFactor(String str) {
        if (eat('+',str)) return parseFactor(str); // unary plus
        if (eat('-',str)) return -parseFactor(str); // unary minus

        double x;
        int startPos = pos;
        if (eat('(',str)) { // parentheses
            x = parseExpression(str);
            eat(')',str);
        } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
            while ((ch >= '0' && ch <= '9') || ch == '.') nextChar(str);
            x = Double.parseDouble(str.substring(startPos, pos));
        } else if (ch >= 'a' && ch <= 'z') { // functions
            while (ch >= 'a' && ch <= 'z') nextChar(str);
            String func = str.substring(startPos, pos);
            x = parseFactor(str);
            if (func.equals("sqrt")) x = Math.sqrt(x);
            else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
            else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
            else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
            else throw new RuntimeException("Unknown function: " + func);
        } else {
            throw new RuntimeException("Unexpected: " + (char)ch);
        }

        if (eat('^',str)) x = Math.pow(x, parseFactor(str)); // exponentiation

        return x;
    }

}
