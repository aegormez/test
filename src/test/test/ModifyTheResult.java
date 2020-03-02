package test;

public class ModifyTheResult {
	public static String Modify(String result) {
		String[] afterDot=result.split("\\.");
		if(afterDot[1].equals("0")) {
			result=afterDot[0];
		}
		return result;
	}
	
}
