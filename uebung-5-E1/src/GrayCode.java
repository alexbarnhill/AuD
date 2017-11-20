public class GrayCode {
	
	/**
	 * This method returns the multiple of 2 nearest without going over (price is right style)
	 * 
	 * @param int len The desired length
	 * @return int The nearest multiple of 2 which is not greater than the length
	 */
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
	
	/**
	 * This method takes an array and gives the first open position in it.
	 * 
	 * @param String[] codes
	 * @return int The next available position in the array
	 */
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
	
	/**
	 * A simple method to print the list of codes.
	 * 
	 * @param codes The array to print
	 */
	private static void printCode(String[] codes) {
		for(int i = 0; i < codes.length; i++) {
			System.out.printf("%s, ", codes[i]);
		}
		System.out.println();
	}
	
	/**
	 * A method returning the next possible value for the length
	 * of a gray code. Following the formula (length / 2) if length is even
	 * and returning the next lowest multiple of 2 if not
	 * 
	 * @param len
	 * @return The next smallest possible length for a grey code.
	 */
	public static int prevLength(int len) {
		if(len % 2 == 0) {
			return (len / 2);
		} else {
			return get2Multiple(len);
		}
	}
	
	/**
	 * An array to make sure the codes have the correct prefix.
	 * 
	 * @param codes String[] the array to be prefixed
	 * @param prefix String The desired prefix
	 * @return An array of prefixed strings
	 */
	private static String[] prefix(String[] codes, String prefix) {
		String[] code = new String[codes.length];
		for(int i = 0; i < codes.length; i++) {
			String codeword = prefix + codes[i];
			code[i] = codeword;
		}
		return code;
	}
	
	/**
	 * Reflects the value of an array.
	 * 
	 * @param codes
	 * @return The new reflected array
	 */
	private static String[] reflect(String[] codes) {
		String[] codeset = new String[codes.length];
		int counter = 0;
		for(int i = codes.length - 1; i >= 0 ; i--) {
			codeset[counter] = codes[i];
			counter++;
		}
		
		return codeset;
	}
	
	/**
	 * A simple method to join two arrays
	 * @param first String[] array which will be first in the larger array.
	 * @param second String[] array which will be second.
	 * @return String[] the combined array
	 */
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
	
	/**
	 * Trims an array down to a specified length by trimming off the values at the end
	 * @param String[] the codes to trim
	 * @param amountToRemove The amount to remove
	 * @return The new shorter array
	 */
	private static String[] shave(String[] codes, int amountToRemove) {
		String[] shaved = new String[codes.length - amountToRemove];
		for(int i = 0; i < codes.length - amountToRemove; i++) {
			shaved[i] = codes[i];
		}
		
		return shaved;
	}
	
	/**
	 * Generates an array of gray codes based on the idea of prefixing, mirroring, and joining two smaller
	 * arrays of Gray Codes
	 * 
	 * @param gcc
	 * @param len - Desired length of code
	 * @return An Array with Gray Codes
	 */
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
