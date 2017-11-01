public class Statistik {
	// ==================== min ====================
	public static double min2(double a, double b) {
		if (a > b) return b;
		return a;
	}

	public static double min3(double a, double b, double c) {
		if (a < b && a < c) {
			return a;
		} else if (b < a && b < c) {
			return b;
		}
		return c;

	}

	public static double min7(double a, double b, double c, double d, double e, double f, double g) {
		double min = sort7(a, b, c, d, e, f, g, "min");
		return min;
	}

	// ==================== max ====================
	public static double max2(double a, double b) {
		if (a > b) {
			return a;
		} else {
			return b;
		}
	}

	public static double max3(double a, double b, double c) {
		if (a >= b && a >= c) {
			return a;
		} else if (b >= a && b >= c) {
			return b;
		} else {
			return c;
		}
	}

	public static double max7(double a, double b, double c, double d, double e, double f, double g) {
		double max = sort7(a, b, c, d, e, f, g, "max");
		return max;
	}
	

	// ==================== Mittelwert ====================
	// Hinweis: hier ist ausnahmsweise keine Behandlung von double-overflow (Ueberlauf) notwendig...
	public static double mittelwert3(double a, double b, double c) {
		double mean = (a + b + c) / (3.0d);
		return mean;
		
	}

	// Hinweis: hier ist ausnahmsweise keine Behandlung von double-overflow (Ueberlauf) notwendig...
	public static double mittelwert7(double a, double b, double c, double d, double e, double f, double g) {
		double mean = (a + b + c + d + e + f + g) / (7.0d);
		return mean;
	}

	// ==================== Median ====================
	public static double median3(double a, double b, double c) {
		// if  b - a - c or c - a - b;
		if((b < a && a < c) || (c < a && a < b)) {
			return a;
		// if a - b - c or c - b - a
		} else if ((a < b && b < c) || (c < b && b < a)) {
			return b;
		} else {
			return c;
		}
	}
	
	public static double sort7(double a, double b, double c, double d, double e, double f, double g, String request) {
		double tmp;
		double response = 0.0;
		for (int i = 0; i <= 6; i++) {
			if(a > b) {
				tmp = a;
				a = b;
				b = tmp;
			}
			
			if (b > c) {
				tmp = b;
				b = c;
				c = tmp;
			}
			
			if (c > d) {
				tmp = c;
				c = d;
				d = tmp;
			}
			
			if(d > e) {
				tmp = d;
				d = e;
				e = tmp;
			}
			
			if(e > f) {
				tmp = e;
				e = f;
				f = tmp;
			}
			
			if(f > g) {
				tmp = f;
				f = g;
				g = tmp;
			}
		}
		
		switch(request) {
			case("min"):
				response = a;
				break;
			case("max"):
				response = g;
				break;
			case("med"):
				response =  d;
				break;
		}
		
		return response;
	}

	public static double median7(double a, double b, double c, double d, double e, double f, double g) {
		
		double median = sort7(a, b, c, d, e, f, g, "med");
		return median;
		
	}
}
