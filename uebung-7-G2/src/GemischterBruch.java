public class GemischterBruch extends AbstrakterGemischterBruch {	
	public GemischterBruch(long x, long y, long z) {
		super(true, x, y, z);
	}
	
	public GemischterBruch(long x, long y) {
		super(true, 0, x, y);
	}

	@Override
	public int compareTo(AbstrakterGemischterBruch arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public GemischterBruch vereinfache() {
		System.out.println("A: " + super.holeGanzzahligerAnteil() + " B: " + super.holeZaehler() + " C: " + super.holeNenner());
		return new GemischterBruch()
	}

	@Override
	public AbstrakterGemischterBruch multipliziereMit(AbstrakterGemischterBruch andere) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstrakterGemischterBruch dividiereDurch(AbstrakterGemischterBruch andere) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstrakterGemischterBruch addiereZu(AbstrakterGemischterBruch andere) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstrakterGemischterBruch subtrahiereDavon(AbstrakterGemischterBruch andere) {
		// TODO Auto-generated method stub
		return null;
	}

}
