public class Lotterie {
	// (1) computes the binomial coefficient ("n-choose-m")
	public static double binomialkoeffizient(int m, int n) {
		double numerator = fakultaet(n);
		double denomenator = fakultaet(m) * (fakultaet(n-m));
		
		return numerator /denomenator;
	}

	// (2) computes k!
	public static double fakultaet(int k) {
		double result = 1.0;
		for(int i= 1; i <= k; i++) {
			result *= (double) i;
		}
		return result;
	}

	// (3) computes probability p of winning a "n-choose-m" lottery game
	public static double gewinnchance(int m, int n) {
		return 1 / binomialkoeffizient(m, n);
	}
}