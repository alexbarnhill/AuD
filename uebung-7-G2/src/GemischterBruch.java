public class GemischterBruch extends AbstrakterGemischterBruch {

	public GemischterBruch(long a, long b) {
		super(!((a < 0) ^ (b < 0)), 0, a, b);
	}

	public GemischterBruch(long x, long y, long z) {
		super(!((x < 0) ^ (y < 0) ^ (z < 0)), x, y, z);

	}

	@Override
	public int compareTo(AbstrakterGemischterBruch arg0) {
		long a = super.holeGanzzahligerAnteil();
		long b = super.holeZaehler();
		long c = super.holeNenner();

		long x = arg0.holeGanzzahligerAnteil();
		long z = arg0.holeNenner();
		long y = arg0.holeZaehler();

		double bruch1 =  (double) (a * c + b) / (double) c;
		double bruch2 =  (double) (x * z + y) / (double) z;
		if (bruch1 < bruch2) {
			return -1;
		}
		if (bruch1 == bruch2) {
			return 0;
		}
		if (bruch1 > bruch2) {
			return 1;
		}
		return 0;


	}

	@Override
	public AbstrakterGemischterBruch vereinfache() {
		long a = super.holeGanzzahligerAnteil();
		long b = super.holeZaehler();
		long c = super.holeNenner();

		long yn = (a * c + b) % c;
		long x = ((a * c + b) - yn) / c;

		long y = yn / ggT(yn, c);
		long z = c / ggT(yn, c);

		if (super.istPositiv() == false) {
			y = y * (-1);
		}

		AbstrakterGemischterBruch b1 = new GemischterBruch(x, y, z);
		// TODO Auto-generated method stub
		return b1;
	}

	@Override
	public AbstrakterGemischterBruch multipliziereMit(AbstrakterGemischterBruch andere) {
		// TODO Auto-generated method stub
		long x = andere.holeGanzzahligerAnteil();
		long z = andere.holeNenner();
		long y = andere.holeZaehler();

		long a = super.holeGanzzahligerAnteil();
		long b = super.holeZaehler();
		long c = super.holeNenner();

		if ((super.istPositiv() == false && andere.istPositiv())) {
			b = b * (-1);
		} else if ((super.istPositiv() && andere.istPositiv() == false)) {
			y = y * (-1);
		} else if ((super.istPositiv() == false && andere.istPositiv() == false)) {
			b = b * (-1);
			y = y * (-1);
		}

		long zaehler = (a * c + b) * (x * z + y);
		long nenner = c * z;

		AbstrakterGemischterBruch b1 = new GemischterBruch(0, zaehler, nenner);

		return b1.vereinfache();

	}

	@Override
	public AbstrakterGemischterBruch dividiereDurch(AbstrakterGemischterBruch andere) {
		long x = andere.holeGanzzahligerAnteil();
		long z = andere.holeNenner();
		long y = andere.holeZaehler();

		long a = super.holeGanzzahligerAnteil();
		long b = super.holeZaehler();
		long c = super.holeNenner();

		if ((super.istPositiv() == false && andere.istPositiv())) {
			b = b * (-1);
		} else if ((super.istPositiv() && andere.istPositiv() == false)) {
			y = y * (-1);
		} else if ((super.istPositiv() == false && andere.istPositiv() == false)) {
			b = b * (-1);
			y = y * (-1);
		}

		long zaehler = (a * c + b) * z;
		long nenner = c * (x * z + y);

		AbstrakterGemischterBruch b1 = new GemischterBruch(0, zaehler, nenner);

		return b1.vereinfache();

	}

	@Override
	public AbstrakterGemischterBruch addiereZu(AbstrakterGemischterBruch andere) {
		long x = andere.holeGanzzahligerAnteil();
		long z = andere.holeNenner();
		long y = andere.holeZaehler();

		long a = super.holeGanzzahligerAnteil();
		long b = super.holeZaehler();
		long c = super.holeNenner();

		int vz1 = 1;
		int vz2 = 1;

		if ((super.istPositiv() == false && andere.istPositiv())) {
			vz1 = -1;
		} else if ((super.istPositiv() && andere.istPositiv() == false)) {
			vz2 = -1;
		} else if ((super.istPositiv() == false && andere.istPositiv() == false)) {
			vz1 = -1;
			vz2 = -1;
		}

		long zaehler = vz1 * (a * c + b) * z + vz2 * (x * z + y) * c;
		long nenner = c * z;

		AbstrakterGemischterBruch b1 = new GemischterBruch(0, zaehler, nenner);

		return b1.vereinfache();


	}

	@Override
	public AbstrakterGemischterBruch subtrahiereDavon(AbstrakterGemischterBruch andere) {
		long x = andere.holeGanzzahligerAnteil();
		long z = andere.holeNenner();
		long y = andere.holeZaehler();

		long a = super.holeGanzzahligerAnteil();
		long b = super.holeZaehler();
		long c = super.holeNenner();
		int vz1 = 1;
		int vz2 = 1;

		if ((super.istPositiv() == false && andere.istPositiv())) {
			vz1 = (-1);
		} else if ((super.istPositiv() && andere.istPositiv() == false)) {
			vz2 = (-1);
		} else if ((super.istPositiv() == false && andere.istPositiv() == false)) {
			vz1 = (-1);
			vz2 = (-1);
		}

		long zaehler = vz1 * (a * c + b) * z - vz2 * (x * z + y) * c;
		long nenner = c * z;

		AbstrakterGemischterBruch b1 = new GemischterBruch(0, zaehler, nenner);

		return b1.vereinfache();

	}

}