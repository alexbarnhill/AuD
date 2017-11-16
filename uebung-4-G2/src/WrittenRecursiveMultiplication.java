public class WrittenRecursiveMultiplication {
	/**
	 * DO NOT MODIFY OR DELETE THIS FIELD OTHERWISE YOUR SUBMISSION WILL BE GRADED 0 POINTS!
	 * 
	 * Used to trace your recursive method calls.
	 */
	
	
	public static WRM_Logger LOGGER;
	
	private static long maskHelper(int bits) {
		long mask = createMask(bits, 1) - 1;
		return mask;
	}

	/**
	 * Method to calculate the number of bits in the binary representation of the given number {@code num}
	 * 
	 * Special {@code num 0} is expected to contain a single bit.
	 * 
	 * @param num
	 *            A non-negative {@code long} number
	 */
	public static int countUsedBits(long num) {
		LOGGER.log_countUsedBits(num); // DO NOT MODIFY OR REMOVE THIS LINE!
		int bits = 1;
		if(num != 1) {
			return 1 + countUsedBits(num >> 1);
		}
		return bits;
	}
	
	private static long createMask(int bits, long mask) {
		if(bits > 0) {
			return createMask(bits - 1, mask << 1);
		}
		return mask;
	}

	/**
	 * Method to extract the lower {@code lowerBits} number of bits from the given number {@code num}
	 * 
	 * @param lowerBits
	 *            A non-negative {@code int} value
	 * @param num
	 *            A non-negative {@code long} number
	 * @return {@code long} representing the lower {@code lowerBits} bits of {@code num}
	 */
	public static long extractLowerBits(int lowerBits, long num) {
		long mask = maskHelper(lowerBits);
		return num & mask;
	}

	/**
	 * Method to extract the upper {@code (n - lowerBits)} number of bits from the given number {@code num}
	 * 
	 * @param lowerBits
	 *            A non-negative {@code int} value
	 * @param num
	 *            A non-negative {@code long} number
	 * @return {@code long} representing the upper {@code (n - lowerBits)} bits of {@code num}
	 */
	public static long extractHigherBits(int lowerBits, long num) {
		int upperBits = countUsedBits(num) - lowerBits;
		long mask = maskHelper(upperBits) << lowerBits;
		return (num & mask) >> lowerBits;
	}

	/**
	 * Method to merge the three parts of a number to a single number.
	 * 
	 * @param up
	 *            The highest bits of the result (the number of bits is {@code bits})
	 * @param mid
	 *            The following middle bits of the result (the number of bits is {@code bits})
	 * @param low
	 *            The finally following lower bits of the result (the number of bits is again {@code bits})
	 * @param bits
	 *            The number of bits in each segment ({@code up, mid, low}) of the result
	 */
	public static long combine(long up, long mid, long low, int bits) {
		long result = up << bits;
		result = result | mid;
		result = result << bits;
		result = result | low;
		return result;
	}

	/**
	 * Recursive implementation of the developed integer multiplication using four recursive calls (according to equation (2) in the exercise sheet).
	 * 
	 * @param x
	 *            A non-negative {@code long} value
	 * @param y
	 *            A non-negative {@code long} value
	 * @return Result of the multiplication x*y
	 */
	public static long writtenMulRec4(long x, long y) {
		LOGGER.log_writtenMulRec4(x, y); // DO NOT MODIFY OR REMOVE THIS LINE!
		long up = 0;
		long middle = 0;
		long low = 0;
		int count = 0;
		if(x <= 0 || y <= 0) {
			return 0;
		} else if(x == 1) {
			return y;
		} else if (y == 1) {
			return x;
			
		} else {
			int bits = countUsedBits(x) > countUsedBits(y) ? countUsedBits(x) : countUsedBits(y);
			
			int lowerCount = (bits >> 1);
			int upperCount = bits - lowerCount;
			System.out.printf("Using %s bits with %s lower bits and %s upper bits\n", bits, lowerCount, upperCount);
			long lowerBitsX = extractLowerBits(lowerCount, x);
			System.out.println("Lower Bits on x: " + lowerBitsX);
			long upperBitsX = extractHigherBits(upperCount, x);
			System.out.println("Upper Bits on x: " + upperBitsX);
		
			long lowerBitsY = extractLowerBits(lowerCount, y);
			long upperBitsY = extractHigherBits(upperCount, y);
			
			
			up = writtenMulRec4(upperBitsX, upperBitsY);
			System.out.println("First multiplying " + upperBitsX + " and " + upperBitsY + " = " + up);
			
			middle = writtenMulRec4(upperBitsX, lowerBitsY) + writtenMulRec4(lowerBitsX, upperBitsY);
			System.out.println("(" + upperBitsX + " * " + lowerBitsY + ") + (" + lowerBitsX + " * " + upperBitsY + ") = " + middle);
			
			low = writtenMulRec4(lowerBitsX, lowerBitsY);
			System.out.println("At the end comes: " + lowerBitsX + " * " + lowerBitsY + " = " + low);
			
			count = upperCount;
			System.out.println("Combining: " + up + " " + middle + " " + low + " With Bits = " + count);
			
			
			
		}
		
		long test = combine(up, middle, low, count);
		System.out.println("Combined: " + test);
		return test;
	}

	/**
	 * Efficient recursive implementation of the developed integer multilongcation using only three recursive calls.
	 * 
	 * @param x
	 *            A non-negative {@code long} value
	 * @param y
	 *            A non-negative {@code long} value
	 * @return Result of the multiplication x*y
	 */
	public static long writtenMulRec3(long x, long y) {
		LOGGER.log_writtenMulRec3(x, y); // DO NOT MODIFY OR REMOVE THIS LINE!
		if(x <= 0 || y <= 0) {
			return 0;
		} else if(x == 1 && y == 1) {
			return 1;
		} else {
			int bitsX = countUsedBits(x);
			int lowerCountX = bitsX % 2 == 0 ? bitsX / 2 : (bitsX + 1) / 2;
			long lowerBitsX = extractLowerBits(lowerCountX, x);
			int upperCountX = bitsX - lowerCountX;
			long upperBitsX = extractHigherBits(upperCountX, x);
			
			int bitsY = countUsedBits(y);
			int lowerCountY = bitsY % 2 == 0 ? bitsY / 2 : (bitsY + 1) / 2;
			long lowerBitsY = extractLowerBits(lowerCountY, y);
			int upperCountY = bitsY - lowerCountY;
			long upperBitsY = extractHigherBits(upperCountY, y);
			return ((upperBitsX << upperBitsY) << lowerCountY) - writtenMulRec4(x >> 1, y >> 1);
		}	
	}
}