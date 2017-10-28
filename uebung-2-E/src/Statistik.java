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
		if(a < b && a < c && a < d && a < e &&  a < f && a < g) {
			System.out.println("A is min: " + a);
			return a;
		} else if(b < a && b < c && b < d && b < e &&  b < f && b < g) {
			System.out.println("B is min: " + b);
			return b;
		} else if(c < b && c < a && c < d && c < e && c < f && c < g) {
			System.out.println("C is min: " + c);
			return c;
		} else if (d < b && d < c && d < a && d < e && d < f && d < g) {
			System.out.println("D is min: " + d);
			return d;
		} else if(e < b && e < c && e < d && e < a && e < f && e < g) {
			System.out.println("E is min: " + e);
			return e;
		} else if(f < b && f < c && f < d && f < e && f < a && f < g) {
			System.out.println("F is min: " + f);
			return f;
		} else {
			System.out.println("G is min: " + g);
			return g;
			
		}
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
		if(a >= b && a >= c && a >= d && a >= e &&  a >= f && a >= g) {
			return a;
		} else if(b >= a && b >= c && b >= d && b >= e &&  b >= f && b >= g) {
			return b;
		} else if(c >= b && c >= a && c >= d && c >= e && c >= f && c >= g) {
			return c;
		} else if (d >= b && d >= c && d >= a && d >= e && d >= f && d >= g) {
			return d;
		} else if(e >= b && e >= c && e >= d && e >= a && e >= f && e >= g) {
			return e;
		} else if(f >= b && f >= c && f >= d && f >= e && f >= a && f >= g) {
			return f;
		} else {
			return g;
		}
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
	private double getRequestedNumber(double a, double b, double c, double d, double e, double f, double g, int request) {
		double response = 0.0;
		switch(request) {
			case 1:
				response = a;
				break;
			case 2:
				response = b;
				break;
			case 3:
				response = c;
				break;
			case 4:
				response = d;
				break;
			case 5:
				response = e;
				break;
			case 6:
				response = f;
				break;
			case 7:
				response = g;
				break;
		}
		return response;
	}
	public static double median7(double a, double b, double c, double d, double e, double f, double g) {
		double min = min7(a, b, c, d, e, f, g);
		double max = max7(a, b, c, d, e, f, g);
		
		double a1, b1, c1, d1, e1;
		for(int i = 1; i <= 7; i++) {
			if (getRequestedNumber(a, b, c, d, e, f, g, i) != min )
		}
		
		System.out.println("A: " + a + " B: " + b + " C: " + c + " D: " + d + " E: " + e + " F: " + f + " G: " + g);
		System.out.println("Min: " + min + " Max: " + max);
		return a;
		
	}
}
