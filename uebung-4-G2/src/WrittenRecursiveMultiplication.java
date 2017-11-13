public class WrittenRecursiveMultiplication {
	/**
	 * DO NOT MODIFY OR DELETE THIS FIELD OTHERWISE YOUR SUBMISSION WILL BE GRADED 0 POINTS!
	 * 
	 * Used to trace your recursive method calls.
	 */
	public static WRM_Logger LOGGER;

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
		// TODO
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
		// TODO
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
		// TODO
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
		// TODO
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
		// TODO
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
		// TODO
	}
}