public class Assert {
	/**
	 * Asserts that two objects are equal. If they are not, an {@code AssertionError} is thrown with the given message. If {@code expected} and {@code actual} are {@code null}, they are considered equal.
	 * 
	 * @param message
	 *            the identifying message for the AssertionError (null okay)
	 * @param expected
	 *            expected value
	 * @param actual
	 *            actual value
	 */
	public static void assertEquals(java.lang.String message, java.lang.Object expected, java.lang.Object actual) {
		Log.logAssert(actual);
		if ((expected == null && actual != null) || (expected != null && actual == null) || (expected != null && actual != null && !expected.equals(actual))) {
			throw new AssertionError("aud.fake.ComparisonFailure: " + message + " expected:<[" + expected + "]> but was:<[" + actual + "]>");
		}
	}
}