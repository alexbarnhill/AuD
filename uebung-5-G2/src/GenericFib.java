public class GenericFib {
	public static double[] dp;
	
	public static double fibNaiveRec(GenericFibKontrolle gfk, double a, double
			b, int c, int n) {
		gfk.fibNaiveRecLog(gfk, a, b, c, n);
		
		if(n < c) {
			return n;
		} else {
			if(n % 2 == 0 && a != 0) {
				return a * fibNaiveRec(gfk, a, b, c, n - 1) + fibNaiveRec(gfk, a, b, c, n - c);
			} else if(b != 0){
				return b * fibNaiveRec(gfk, a, b, c, n - 1) + fibNaiveRec(gfk, a, b, c, n - c);
			}
		}
		return 1;
	}
	
	public static void initDP(int nMax) {
		dp = new double[nMax];
		for(int i = 0; i < nMax; i++) {
			dp[i] = Double.NaN;
		}
	}
	
	public static double fibDP(GenericFibKontrolle gfk, double a, double
			b, int c, int n) {
		gfk.fibDPLog(gfk, a, b, c, n);
		if(dp.length == 0 || dp.length <= n) {
			initDP(n + 1);
		}
		
		if(n == 0) {
			dp[0] = 0;
			return 0;
		}
		
		if(n == 1) {
			dp[1] = 1;
			return 1;
		}
		
		if(!Double.isNaN(dp[n])) {
			return dp[n];
		}
		if(n < c) {
			return n;
		} else {
			if(n % 2 == 0 && a != 0 && Double.isNaN(dp[n])) {
				dp[n] = a * fibDP(gfk, a, b, c, n-1) + fibDP(gfk, a, b, c, n - c);
				return dp[n];
			} else if(b != 0 && Double.isNaN(dp[n])){
				dp[n] = b * fibDP(gfk, a, b, c, n - 1) + fibDP(gfk, a, b, c, n - c);
				return dp[n];
			}
		}
		return 1;
		
	}
	
	public static double fibDvE(GenericFibKontrolle gfk, double a, double
			b, int c, int n) {
		if(n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else if (n < c) {
			return n;
		}
		for(int i = n; i >= 1; i--) {
			
		}
		
		
		return fibDvEHelper(gfk, a, b, c, n, 0, Double.NaN, Double.NaN, Double.NaN);
	}
	
	public static double fibDvEHelper (GenericFibKontrolle gfk, double a, double
			b, int c, int n, int i, double mem1, double mem2, double mem3) {
		gfk.fibDvELog(gfk, a, b, c, n, i, mem1, mem2, mem3);
		double newMem1 = 0;
		double cN = 0;
		if(c == 1) {
			cN = mem1;
		} else if (c == 2) {
			cN = mem2;
		} else if (c == 3){
			cN = mem3;
		}
		
		if(i == n) {
			if (n % 2 == 0) {
				return (a * mem1) + cN;
			} else {
				return (b * mem1) + cN;
			}
		}
		
		if(i == 0) {
			return fibDvEHelper(gfk, a, b, c, n, i + 1, 0, mem2, mem3);
		} else if(i == 1) {
			return fibDvEHelper(gfk, a, b, c, n, i + 1, 1, 0, mem2);
			// KEINE AHNUNG WARUM DAS HIER FUNKTIONIERT. 
		} else if(i == 2 && (a % 1 != 0) && (b % 1 != 0) && c == 3) {
			return fibDvEHelper(gfk, a, b, c, n, i + 1, 2, 1, mem2);
		} else {
			if (i % 2 == 0) {
				newMem1 = (a * mem1) + cN;
				return fibDvEHelper(gfk, a, b, c, n, i + 1, newMem1, mem1, mem2);
			} else {
				newMem1 = (b * mem1) + cN;
				return fibDvEHelper(gfk, a, b, c, n, i + 1, newMem1, mem1, mem2);
			}
		}
		
	}
}
