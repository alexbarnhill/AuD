public class Physics {

	private static double AVOGARDO = 6.0221409e+23;

	// m2 kg s-2 K-1
	private static double BOLTZMANN = 1.38064852e-23;
	//
	// p · V = N · kB · T

	// Given gas-volume v (m*m*m) and temperature t (K),
	// this method computes and returns the pressure p (Pa).
	// p = (N * kB * T) / V
	public static double computeP(double v, double t) {
		double pressure = (AVOGARDO * BOLTZMANN * t) / v;
		return pressure;
	}

	// Given pressure p (Pa) and temperature t (K),
	// this method computes and returns the gas-volume v (m*m*m).
	// V = (N * kB * T / V) / p
	public static double computeV(double p, double t) {
		double volume = (AVOGARDO * BOLTZMANN * t) / p;
		return volume;
	}

	// Given pressure p (Pa) and gas-volume v (m*m*m),
	// this method computes and returns the temperature t (K).
	// T = (p * V) / (N * kB);
	public static double computeT(double p, double v) {
		double temperature = (p * v) / (AVOGARDO * BOLTZMANN);
		return temperature;
	}

	// Given gas-volume v (m*m*m) and a change in temperature of deltaT (K),
	// this method computes and returns the change in pressure (Pa).
	//
	public static double computeDeltaPisochore(double v, double deltaT) {
		return 0.0;
	}

	// Given gas-volume v (m*m*m), temperature t (K) and a change in volume of deltaV (m*m*m),
	// this method computes and returns the change in pressure (Pa).
	public static double computeDeltaPisotherm(double v, double t, double deltaV) {
		return 0.0; // TODO
	}

	// Given temperature t (K) and molar mass of particle m (kg/mol),
	// this method computes and returns the average speed of a particle.
	// 1/2(m)v^2 = 3/2 (kB) T
	// v = sqrt(3kB * T / m)
	public static double computeAverageSpeed(double t, double m) {
		double velocity = Math.sqrt((3 * BOLTZMANN * t) / m);
		return velocity;
	}

	public static void main(String[] args) {
		double v = computeAverageSpeed(373, 1.9944235e-26);
		System.out.println(v);
	}
}
