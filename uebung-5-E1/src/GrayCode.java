public class GrayCode {
	private static int get2Multiple(int len) {
		int result = 1;
		while(result < len) {
			if ((result << 1) > len) {
				break;
			} else {
				result = result << 1;
			}
		}
		
		return result;
	}
	
	private static int nextPos(String[] codes) {
		int pos = 0;
		for(int i = 0; i < codes.length; i++) {
			if (codes[i] == null) {
				pos = i;
				break;
			}
		}
		
		return pos;
	}
	
	
	private static void printCode(String[] codes) {
		for(int i = 0; i < codes.length; i++) {
			System.out.printf("%s, ", codes[i]);
		}
		System.out.println();
	}
	
	public static int prevLength(int len) {
		if(len % 2 == 0) {
			return (len / 2);
		} else {
			return get2Multiple(len);
		}
	}
	
	private static String[] prefix(String[] codes, String prefix) {
		String[] code = new String[codes.length];
		for(int i = 0; i < codes.length; i++) {
			String codeword = prefix + codes[i];
			code[i] = codeword;
		}
		return code;
	}
	
	private static String[] reflect(String[] codes) {
		String[] codeset = new String[codes.length];
		int counter = 0;
		for(int i = codes.length - 1; i >= 0 ; i--) {
			codeset[counter] = codes[i];
			counter++;
		}
		
		return codeset;
	}
	
	private static String[] concat(String[] first, String[] second) {
		String[] concatenated = new String[first.length + second.length];
		for(int i = 0; i < first.length; i++) {
			concatenated[i] = first[i];
		}
		int counter = 0;
		for(int j = nextPos(concatenated); j < concatenated.length; j++) {
			concatenated[j] = second[counter];
			counter++;
		}
		return concatenated;
		
	}
	
	private static String[] shave(String[] codes, int amountToRemove) {
		String[] shaved = new String[codes.length - amountToRemove];
		for(int i = 0; i < codes.length - amountToRemove; i++) {
			shaved[i] = codes[i];
		}
		
		return shaved;
	}
	
	public static String[] generate(GrayCodeControl gcc, int len) {
		gcc.logGenerate(gcc, len);
		System.out.println("Generating Gray Code with length " + len);
		String[] code = new String[len];
		if(len == 1) {
			code[nextPos(code)] = "0";
		} else if(len == 2) {
			code[nextPos(code)] = "0";
			code[nextPos(code)] = "1";
		} else {
			String[] original = generate(gcc, prevLength(len));
			String[] reflected = reflect(original);
			
			String[] originalPrefixed = prefix(original, "0");
			String[] reflectedPrefixed = prefix(reflected, "1");
	
			String[] combined = concat(originalPrefixed, reflectedPrefixed);
			
			if(len < combined.length) {
				combined = shave(combined, combined.length - len);
			}
			printCode(combined);
			return combined;
		}
		
		return code;
		
	}
}
