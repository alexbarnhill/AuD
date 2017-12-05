public abstract class AbstrakterGemischterBruch implements Comparable<AbstrakterGemischterBruch> {
	private final boolean positiv;
	private final long ganzzahligerAnteil;
	private final long zaehler;
	private final long nenner;

	public AbstrakterGemischterBruch(boolean positiv, long ganzzahligerAnteil, long zaehler, long nenner) {
		this.positiv = positiv;
		this.ganzzahligerAnteil = Math.abs(ganzzahligerAnteil);
		this.zaehler = Math.abs(zaehler);
		this.nenner = Math.abs(nenner);
	}

	public final boolean istPositiv() {
		return positiv;
	}

	public final long holeGanzzahligerAnteil() {
		return ganzzahligerAnteil;
	}

	public final long holeZaehler() {
		return zaehler;
	}

	public final long holeNenner() {
		return nenner;
	}

	public abstract AbstrakterGemischterBruch vereinfache();

	public abstract AbstrakterGemischterBruch multipliziereMit(AbstrakterGemischterBruch andere);

	public abstract AbstrakterGemischterBruch dividiereDurch(AbstrakterGemischterBruch andere);

	public abstract AbstrakterGemischterBruch addiereZu(AbstrakterGemischterBruch andere);

	public abstract AbstrakterGemischterBruch subtrahiereDavon(AbstrakterGemischterBruch andere);

	@Override
	public final String toString() {
		return " [" + (positiv ? "" : "- ") + holeGanzzahligerAnteil() + " | " + holeZaehler() + " / " + holeNenner() + "] ";
	}

	protected final long ggT(long a, long b) {
		return b == 0 ? a : ggT(b, a % b);
	}
}