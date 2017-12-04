public class AmpelTest {
public static void main(String[] args) {
		Ampel ka = new KonkreteAmpel();
		System.out.println("1: " + ka.umschalten(500)); // *** (1) ***
		System.out.println("2: " + ka.umschaltZeit); // *** (2) ***
		
		AbstrakteAmpel ka2 = new KonkreteAmpel();
		System.out.println("3: " + ka2.umschaltZeit); // *** (3) ***
		
		KonkreteAmpel ka3 = new KonkreteAmpel();
		System.out.println("4: " + ka3.umschalten(500)); // *** (4) ***
		System.out.println("5: " + ka3.umschaltZeit); // *** (5) ***
		
		AlternativeAmpel kaa = new KonkreteAlternativeAmpel();
		System.out.println("6: " + kaa.umschalten(500)); // *** (6) ***
		System.out.println("7: " + kaa.umschaltZeit); // *** (7) ***
		
		KonkreteAlternativeAmpel kaa2 = new KonkreteAlternativeAmpel();
		System.out.println("8: " + kaa2.umschalten(500)); // *** (8) ***
		System.out.println("9: " + kaa2.umschaltZeit); // *** (9) ***
		System.out.println("10: " + kaa2.umschalten(500L)); // *** (10) ***
	}
}
