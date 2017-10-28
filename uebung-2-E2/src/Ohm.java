/* Ohm's law: U = R*I */
public class Ohm {
	/* Calculate the voltage using Ohm's law */
	public static double voltage(double resistance, double current) {
		double voltage = resistance * current;
		return voltage;
	}

	/* Calculate the current using Ohm's law */
	public static double current(double voltage, double resistance) {
		double current = voltage / resistance;
		return current;
	}

	/* Calculate the resistance using Ohm's law */
	public static double resistance(double voltage, double current) {
		double resistance = voltage / current;
		return resistance;
	}

	/* Calculate the resistance of two serial connected resistances */
	public static double resistanceSerialConnection2(double r1, double r2) {
		double resistance = r1 + r2;
		return resistance;
	}

	/* Calculate the resistance of two parallel connected resistances */
	public static double resistanceParallelConnection2(double r1, double r2) {
		double resistance = (r1 * r2) / (r1 + r2);
		return resistance;
	}

	/* Calculate the resistance of N serial connected resistances */
	public static double resistanceSerialConnectionN(double[] rs) {
		double resistance = 0.0;
		for(int i = 0; i < rs.length; i++) {
			resistance += rs[i];
		}
		return resistance;
	}

	/* Calculate the resistance of N parallel connected resistances */
	public static double resistanceParallelConnectionN(double[] rs) {
		
		double resistanceDenomenator = 0.0;
		for(int i = 0; i < rs.length; i++) {
			if((double) rs[i] != 0.0) {
				resistanceDenomenator += 1/rs[i];
			}
			
			
		}
		double resistance = 1 / resistanceDenomenator;

		return resistance;
	}
}