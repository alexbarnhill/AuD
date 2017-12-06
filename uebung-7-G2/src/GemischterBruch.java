public class GemischterBruch extends AbstrakterGemischterBruch {	
	public GemischterBruch(long x, long y, long z) {
		super( !( (x < 0) ^ (y < 0) ^ (z < 0) ), x, y, z);
	}
	
	public GemischterBruch(long x, long y) {
		super(!(x < 0 ^ y < 0), 0, x, y);
	}
	
	private GemischterBruch(boolean positive, long x, long y, long z) {
		super(positive, x, y, z);
	}

	@Override
	public int compareTo(AbstrakterGemischterBruch arg0) {

		GemischterBruch firstSimplified = this.vereinfache();
		GemischterBruch arg0Simplified = (GemischterBruch) arg0.vereinfache();
	
		if(firstSimplified.holeGanzzahligerAnteil() < arg0Simplified.holeGanzzahligerAnteil()) {
			return -1;
		} else if(firstSimplified.holeGanzzahligerAnteil() > arg0Simplified.holeGanzzahligerAnteil()) {
			return 1;
		} else if(firstSimplified.holeGanzzahligerAnteil() == arg0Simplified.holeGanzzahligerAnteil()) {
			try {
				double first = (double) firstSimplified.holeZaehler() / (double) firstSimplified.holeNenner();
				double second = (double) arg0Simplified.holeZaehler() / (double) arg0Simplified.holeNenner();
				if (first < second) {
					return -1;
				} else if(first > second) {
					return 1;
				} else {
					return 0;
				}
			} catch(IllegalArgumentException e) {
				System.err.println(e.getMessage());
			} catch(ArithmeticException e) {
				System.err.println(e.getMessage());
			} catch(Exception e) {
				System.err.println(e.getMessage());
			}
			
				
					
		}
		return 0;
	}

	@Override
	public GemischterBruch vereinfache() {
		long ggt = super.ggT(super.holeZaehler(), super.holeNenner());
		long newDen = super.holeNenner() / ggt;
		long newNom = super.holeZaehler() / ggt;
		long newWhole = super.holeGanzzahligerAnteil();
		if(newNom >= newDen) {
			while(newNom >= newDen) {
				newNom -= newDen;
				newWhole++;
			}
		}

		if(!super.istPositiv()) {
			newNom *= -1;
		}
		
		return new GemischterBruch(newWhole, newNom , newDen);
	}

	@Override
	public GemischterBruch multipliziereMit(AbstrakterGemischterBruch andere) {
		boolean isPositive = !(super.istPositiv() ^ andere.istPositiv());
		int sign = isPositive ? 1 : -1;
		
		GemischterBruch first = new GemischterBruch((super.holeNenner() * super.holeGanzzahligerAnteil()) + super.holeZaehler(), super.holeNenner());
		GemischterBruch second = new GemischterBruch((andere.holeNenner() * andere.holeGanzzahligerAnteil()) + andere.holeZaehler(), andere.holeNenner());
		GemischterBruch result = new GemischterBruch(first.holeZaehler() * second.holeZaehler() * sign, first.holeNenner() * second.holeNenner());
		
		return result;
	}

	@Override
	public AbstrakterGemischterBruch dividiereDurch(AbstrakterGemischterBruch andere) {
		boolean isPositive = !(super.istPositiv() ^ andere.istPositiv());
		int sign = isPositive ? 1 : -1;
		
		GemischterBruch first = new GemischterBruch((super.holeNenner() * super.holeGanzzahligerAnteil()) + super.holeZaehler(), super.holeNenner());
		GemischterBruch second = new GemischterBruch((andere.holeNenner() * andere.holeGanzzahligerAnteil()) + andere.holeZaehler(), andere.holeNenner());
		GemischterBruch result = new GemischterBruch(first.holeZaehler() * second.holeNenner() * sign, first.holeNenner() * second.holeZaehler());
		
		return result;
	}

	@Override
	public GemischterBruch addiereZu(AbstrakterGemischterBruch andere) {
		if(this.istPositiv() && andere.istPositiv()) {
			GemischterBruch first = new GemischterBruch(((super.holeNenner() * super.holeGanzzahligerAnteil()) + super.holeZaehler()) * andere.holeNenner(), super.holeNenner() * andere.holeNenner());
			GemischterBruch second = new GemischterBruch(((andere.holeNenner() * andere.holeGanzzahligerAnteil()) + andere.holeZaehler()) * super.holeNenner(), andere.holeNenner() * super.holeNenner());
			
			GemischterBruch result = new GemischterBruch(first.holeZaehler() + second.holeZaehler(), first.holeNenner()).vereinfache();
			return result;
		} else if(this.istPositiv() && !andere.istPositiv()) {
			GemischterBruch newOther = new GemischterBruch(+andere.holeGanzzahligerAnteil(), +andere.holeZaehler(), +andere.holeNenner());
			return this.subtrahiereDavon(newOther);
		}
		
		return new GemischterBruch(0, 0, 0);
		
	}

	@Override
	public GemischterBruch subtrahiereDavon(AbstrakterGemischterBruch andere) {
		
		// if x > 0 and y > 0 -- OK to do normal subtraction
		if(super.istPositiv() && andere.istPositiv()) {
		
			GemischterBruch first = new GemischterBruch(((super.holeNenner() * super.holeGanzzahligerAnteil()) + super.holeZaehler()) * andere.holeNenner(), super.holeNenner() * andere.holeNenner());
			GemischterBruch second = new GemischterBruch(((andere.holeNenner() * andere.holeGanzzahligerAnteil()) + andere.holeZaehler()) * super.holeNenner(), andere.holeNenner() * super.holeNenner());
			
			GemischterBruch result = new GemischterBruch(first.holeZaehler() - second.holeZaehler(), first.holeNenner()).vereinfache();
			return result;
			// else if x > 0 and y < 0 --- This is then normal addition
		} else if(this.istPositiv() && !andere.istPositiv()) {
			GemischterBruch newOther = new GemischterBruch(+andere.holeGanzzahligerAnteil(), +andere.holeZaehler(), +andere.holeNenner());
			return this.addiereZu(newOther);
		}
		
		return new GemischterBruch(0, 0, 0);
	}

}
