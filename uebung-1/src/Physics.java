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
	// dT = T2 -T1
	// dT(N * kB) / V = dP
	public static double computeDeltaPisochore(double v, double deltaT) {
		double deltaP = (deltaT * (AVOGARDO * BOLTZMANN)) / v;
		return deltaP;
	}

	// Given gas-volume v (m*m*m), temperature t (K) and a change in volume of
	// deltaV (m*m*m),
	// this method computes and returns the change in pressure (Pa).
	// v2 = deltaV + V1
	// p2 = nRT/(deltaV + V1)
	// p1 = nRT/V1
	// deltaP = p2 - p1
	public static double computeDeltaPisotherm(double v, double t, double deltaV) {
		double pressure2 = (AVOGARDO * BOLTZMANN * t) / (deltaV + v);
		double pressure1 = (AVOGARDO * BOLTZMANN * t) / v;

		return pressure2 - pressure1;
	}

	// Given temperature t (K) and molar mass of particle m (kg/mol),
	// this method computes and returns the average speed of a particle.
	// 1/2(m)v^2 = 3/2 (kB) T
	// v = sqrt(3kB * T / m * N)
	public static double computeAverageSpeed(double t, double m) {
		double velocity = Math.sqrt((3 * BOLTZMANN * t) / m * AVOGARDO);
		return velocity;
	}

}
