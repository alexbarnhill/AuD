public class KonkreteAlternativeAmpel extends AbstrakteAmpel implements AlternativeAmpel {
	public int umschaltZeit = 42;
	public String umschalten(long zeit) {
		return "Umschalten, aber konkret!";
	}
}
