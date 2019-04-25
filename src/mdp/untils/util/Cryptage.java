package mdp.untils.util;

public class Cryptage {

	public static String generateKey(String mot) {
		String key = "";
		
		for(int i = 0; i < mot.length(); i++) {
			char c = (char) (int) (Math.random() * 94 + 34);
			if (c == 59) {
				c = 33;
			}
			key += c;
		}
		return key;
	}
	
	public static String codage(String mot, String key) {
		String result = "";
		int car = 0;
		
		for(int i = 0; i < mot.length(); i++) {
			car = mot.charAt(i) + key.charAt(i);
			if (car > 126) {
				car -= 93;
			}
			if (car == 59) {
				car = 33;
			}
			result += (char) car;
		}
		return result;
	}
	
	public static String decodage(String mot, String key) {
		String result = "";
		int car = 0;
		
		for (int i = 0; i < mot.length(); i++) {
			car =mot.charAt(i) - key.charAt(i);
			if (car < 33) {
				car += 93;
			}
			if (car == 33) {
				car = 59;
			}
			result += (char) car;
		}
		return result;
	}
}
