import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.*;

public class ZirkulaereDoppeltVerkettetePrioritaetswarteschlangePublicTest {

	// ========== define some COMPs... ==========
	private static final Comparator<Integer> COMP_INTEGER = new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1 - o2;
		}
	};
	private static final Comparator<String> COMP_STRING_LEX = new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
	};
	private static final Comparator<String> COMP_STRING_ANTI_LEX = new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			return o2.compareTo(o1);
		}
	};
	private static final Comparator<?> COMP_MAGIC = new Comparator<ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<?>>() {
		@Override
		public int compare(ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<?> o1, ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<?> o2) {
			return 0;
		}
	};
	private static final Comparator<Boolean> COMP_BOOL_ONE = new Comparator<Boolean>() {
		@Override
		public int compare(Boolean o1, Boolean o2) {
			return 1;
		}
	};

	// ========== check intestines of ZirkulaereDoppeltVerkettetePrioritaetswarteschlange... ==========
	// @AuD-STUDENT: DO NOT USE REFLECTION IN YOUR OWN SUBMISSION! BITTE KEINE REFLECTION IN DER EIGENEN ABGABE VERWENDEN! => "0 Punkte"!
	@Test(timeout = 666)
	public void pubTest_intestines() {
		@SuppressWarnings("rawtypes") // DO NOT USE THAT IN YOUR OWN SUBMISSION!
		Class<ZirkulaereDoppeltVerkettetePrioritaetswarteschlange> zdvpwsClass = ZirkulaereDoppeltVerkettetePrioritaetswarteschlange.class;
		@SuppressWarnings("rawtypes") // DO NOT USE THAT IN YOUR OWN SUBMISSION!
		Class<AbstrakteZirkulaereDoppeltVerkettetePrioritaetswarteschlange> azdvpwsClass = AbstrakteZirkulaereDoppeltVerkettetePrioritaetswarteschlange.class;
		assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal super class.", azdvpwsClass, zdvpwsClass.getSuperclass());
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal interfaces.", 0, zdvpwsClass.getInterfaces().length);
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal inner annotations.", 0, zdvpwsClass.getDeclaredAnnotations().length);
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal inner classes.", 0, zdvpwsClass.getDeclaredClasses().length);
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal number of generic types.", 1, zdvpwsClass.getTypeParameters().length);
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal number of bounds for your generic type.", 1, zdvpwsClass.getTypeParameters()[0].getBounds().length);
		assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal bounds for your generic type.", Object.class, zdvpwsClass.getTypeParameters()[0].getBounds()[0]);
		Field[] zdvpwsFields = zdvpwsClass.getDeclaredFields();
		for (Field field : zdvpwsFields) {
			if (!(field.getName().equals("$assertionsDisabled") && field.getType() == boolean.class)) { // damn java hack
				fail("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal attribute (field: " + field + ").");
			}
		}
	}

	// ========== PUBLIC MAIN TEST ==========
	@Test(timeout = 666)
	public void pubTest_einfuegen() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<Integer> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(COMP_INTEGER);
		zdvpws.einfuegen(4711);
		DoppeltVerketteterKnoten<Integer> theKopf = zdvpws.kopf;
		assertNotNull("Failed.", theKopf);
		assertNotNull("Failed.", theKopf.holeWert());
		assertEquals("Failed.", 4711, theKopf.holeWert().intValue());
		assertSame("Failed.", theKopf, theKopf.holeNachfolger());
		assertSame("Failed.", theKopf, theKopf.holeVorgaenger());
	}

	@Test(timeout = 666)
	public void pubTest_wichtigstenEntfernen() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<String> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(COMP_STRING_LEX);
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("Doe");
		DoppeltVerketteterKnoten<String> theKopf = zdvpws.kopf;
		assertNotNull("Failed.", theKopf);
		assertEquals("Failed.", "John", theKopf.holeWert());
		assertEquals("Failed.", "John", zdvpws.wichtigstenEntfernen());
	}

	@Test(timeout = 666, expected = NoSuchElementException.class)
	public void pubTest_unwichtigstenEntfernen() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<?> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(COMP_MAGIC);
		zdvpws.einfuegen(null);
		zdvpws.unwichtigstenEntfernen();
		zdvpws.unwichtigstenEntfernen();
		fail("Failed.");
	}

	@Test(timeout = 666)
	public void pubTest_wichtigstenEntfernenComparatorOfWertTyp() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<String> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(COMP_STRING_LEX);
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("Doe");
		assertEquals("Failed.", "Doe", zdvpws.wichtigstenEntfernen(COMP_STRING_ANTI_LEX));
	}

	@Test(timeout = 666, expected = NoSuchElementException.class)
	public void pubTest_unwichtigstenEntfernenComparatorOfWertTyp() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<Boolean> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(COMP_BOOL_ONE);
		zdvpws.einfuegen(null);
		zdvpws.unwichtigstenEntfernen(COMP_BOOL_ONE);
		zdvpws.unwichtigstenEntfernen(COMP_BOOL_ONE);
		fail("Failed.");
	}
}