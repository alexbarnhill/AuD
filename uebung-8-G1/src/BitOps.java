public class BitOps {
	public static long set(long bitSet, int bitIndex) {
		return bitSet | (1L << bitIndex);
	}
	
	public static boolean isSet(long bitSet, int bitIndex) {
		return (bitSet & (1L << bitIndex)) == (1L << bitIndex);
	}
	
	public static long clear(long bitSet, int bitIndex) {
		return bitSet & ~(1L << bitIndex);
	}
	
	public static long flip(long bitSet, int bitIndex) {
		return bitSet ^ (1L << bitIndex);
	}
}
