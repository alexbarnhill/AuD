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
		
		return 0;
	}
	
	public static double fibDvEHelper (GenericFibKontrolle gfk, double a, double
			b, int c, int n, int i, double mem1, double mem2, double mem3) {
		
		return 0;
	}
}
