public class NewtonIteration {
	// computes x^n
	public static double power(double x, int n) {
		double result = 1.0;
		if(n == 0) {
			return 1.0;
		} 
		for(int i = 1; i <= n; i++) {
			result *= x;
		}
		return result;
	}

	// computes f := y^n - x
	public static double fun(double y, int n, double x) {
		return (power(y, n) - x);
	}

	// computes f' = d/dy f
	public static double funDeriv(double y, int n, double x) {
		return n * power(y, n-1);
	}

	// represents ONE iteration step of the Newton method
	public static double newtonStep(double x, int n, double yAlt) {
		double proportion = fun(yAlt, n, x) / funDeriv(yAlt, n, x);
		return yAlt - proportion;
	}

	// computes x^(1/n) with precision epsilon
	public static double approxRoot(double x, int n, double epsilon) {
		double yOld = 1.0;
		double yNew = newtonStep(x,n, yOld);
		
		while(Math.abs(yNew - yOld) > epsilon) {
			yOld = yNew;
			yNew = newtonStep(x, n, yOld);
		}
		
		return yNew;
	}
}