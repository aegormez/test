/**
 * 
 */
package test;

/**
 * @author AhmetEnes
 *
 */
public class Evaluator {
	String str;
	int pos=-1;
	int ch;
	public String evaluate(String str) {
		this.str=str;
		getChar();
		try {
			str=String.valueOf(parseString());
		} catch (Exception RuntimeException){
			return "Unknown Constant";
		}
		System.out.println(ModifyTheResult.Modify(str));
		return ModifyTheResult.Modify(str);
	}
	
	private void getChar() {
		ch = (++pos < str.length()) ? str.charAt(pos) : -1;
	}
	
	private double parseString() {
        double x = executeSubCalculation();
        for (;;) {
            if (checkNextChar('+'))
                x += executeSubCalculation(); // addition
            else if (checkNextChar('-'))
                x -= executeSubCalculation(); // subtraction
            else
                return x;
        }
    }
	
	private double executeSubCalculation() {
        double x = parseTerm();
        for (;;) {
            if (checkNextChar('*'))
                x *= parseTerm(); // multiplication
            else if (checkNextChar('/'))
                x /= parseTerm(); // division
            else
                return x;
        }
    }
	private double parseTerm() {
		if (checkNextChar('+')) return parseTerm(); // unary plus
        if (checkNextChar('-')) return -parseTerm(); // unary minus
        
		double x;
		int startPos=pos;
		
		if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
            while ((ch >= '0' && ch <= '9') || ch == '.') getChar();
            x = Double.parseDouble(str.substring(startPos, pos));
        }else if (ch =='p') { // functions
            while (ch >= 'a' && ch <= 'z') getChar();
            String cons = str.substring(startPos, pos);
            System.out.println(cons);
            //x = parseTerm();
            if (cons.equals("pi")) x = 3.14;
            else throw new RuntimeException("Unknown constant: " + cons);
        } 
		else {
            throw new RuntimeException("Unexpected: " + (char)ch);
        }
		
		return x;
	}
	private boolean checkNextChar(int charToCheck) {
        while (ch == ' '|| ch=='	')
            getChar();
        if (ch == charToCheck) {
            getChar();
            return true;
        }
        return false;
    }
}
