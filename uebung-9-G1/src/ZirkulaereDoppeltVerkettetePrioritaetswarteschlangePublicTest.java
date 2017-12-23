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
		public int compare(ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<?> o1,
				ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<?> o2) {
			return 0;
		}
	};
	private static final Comparator<Boolean> COMP_BOOL_ONE = new Comparator<Boolean>() {
		@Override
		public int compare(Boolean o1, Boolean o2) {
			return 1;
		}
	};

	// ========== check intestines of
	// ZirkulaereDoppeltVerkettetePrioritaetswarteschlange... ==========
	// @AuD-STUDENT: DO NOT USE REFLECTION IN YOUR OWN SUBMISSION! BITTE KEINE
	// REFLECTION IN DER EIGENEN ABGABE VERWENDEN! => "0 Punkte"!
	@Test(timeout = 666)
	public void pubTest_intestines() {
		@SuppressWarnings("rawtypes") // DO NOT USE THAT IN YOUR OWN SUBMISSION!
		Class<ZirkulaereDoppeltVerkettetePrioritaetswarteschlange> zdvpwsClass = ZirkulaereDoppeltVerkettetePrioritaetswarteschlange.class;
		@SuppressWarnings("rawtypes") // DO NOT USE THAT IN YOUR OWN SUBMISSION!
		Class<AbstrakteZirkulaereDoppeltVerkettetePrioritaetswarteschlange> azdvpwsClass = AbstrakteZirkulaereDoppeltVerkettetePrioritaetswarteschlange.class;
		assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal super class.", azdvpwsClass,
				zdvpwsClass.getSuperclass());
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal interfaces.", 0,
				zdvpwsClass.getInterfaces().length);
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal inner annotations.", 0,
				zdvpwsClass.getDeclaredAnnotations().length);
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal inner classes.", 0,
				zdvpwsClass.getDeclaredClasses().length);
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal number of generic types.", 1,
				zdvpwsClass.getTypeParameters().length);
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal number of bounds for your generic type.", 1,
				zdvpwsClass.getTypeParameters()[0].getBounds().length);
		assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal bounds for your generic type.", Object.class,
				zdvpwsClass.getTypeParameters()[0].getBounds()[0]);
		Field[] zdvpwsFields = zdvpwsClass.getDeclaredFields();
		for (Field field : zdvpwsFields) {
			if (!(field.getName().equals("$assertionsDisabled") && field.getType() == boolean.class)) { // damn java
																										// hack
				fail("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal attribute (field: " + field + ").");
			}
		}
	}

	// ========== PUBLIC MAIN TEST ==========
	@Test(timeout = 666)
	public void pubTest_einfuegen() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<Integer> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_INTEGER);
		zdvpws.einfuegen(4711);
		DoppeltVerketteterKnoten<Integer> theKopf = zdvpws.kopf;
		assertNotNull("Failed.", theKopf);
		assertNotNull("Failed.", theKopf.holeWert());
		assertEquals("Failed.", 4711, theKopf.holeWert().intValue());
		assertSame("Failed.", theKopf, theKopf.holeNachfolger());
		assertSame("Failed.", theKopf, theKopf.holeVorgaenger());
	}

	@Test(timeout = 666)
	public void pubTest_einfuegen_2() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<Integer> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_INTEGER);
		zdvpws.einfuegen(5);
		zdvpws.einfuegen(10);
		zdvpws.einfuegen(8);
		zdvpws.einfuegen(9);
		zdvpws.einfuegen(7);
		zdvpws.einfuegen(4);
		DoppeltVerketteterKnoten<Integer> theKopf = zdvpws.kopf;
		assertNotNull("Failed.", theKopf);
		assertNotNull("Failed.", theKopf.holeWert());
		assertEquals("Failed.", 10, theKopf.holeWert().intValue());
		assertEquals("End is incorrect", 4, theKopf.holeVorgaenger().holeWert().intValue());
	}

	@Test(timeout = 666)
	public void pubTest_einfuegen_3() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<Integer> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_INTEGER);
		zdvpws.einfuegen(5);
		zdvpws.einfuegen(10);
		zdvpws.einfuegen(8);
		zdvpws.einfuegen(9);
		zdvpws.einfuegen(7);
		zdvpws.einfuegen(4);
		DoppeltVerketteterKnoten<Integer> theKopf = zdvpws.kopf;
		assertNotNull("Failed.", theKopf);
		assertNotNull("Failed.", theKopf.holeWert());
		assertEquals("Failed.", 10, theKopf.holeWert().intValue());
		assertEquals("End is incorrect", 4, theKopf.holeVorgaenger().holeWert().intValue());
		assertEquals("Second Last is incorrect", 5, theKopf.holeVorgaenger().holeVorgaenger().holeWert().intValue());

	}

	@Test(timeout = 666)
	public void pubTest_einfuegen_4() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<Integer> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_INTEGER);
		zdvpws.einfuegen(5);
		zdvpws.einfuegen(10);
		zdvpws.einfuegen(8);
		zdvpws.einfuegen(9);
		zdvpws.einfuegen(7);
		zdvpws.einfuegen(4);
		DoppeltVerketteterKnoten<Integer> theKopf = zdvpws.kopf;
		assertNotNull("Failed.", theKopf);
		assertNotNull("Failed.", theKopf.holeWert());
		assertEquals("Failed.", 10, theKopf.holeWert().intValue());
		assertEquals("End is incorrect", 4, theKopf.holeVorgaenger().holeWert().intValue());
		assertEquals("Second Last is incorrect", 5, theKopf.holeVorgaenger().holeVorgaenger().holeWert().intValue());
		assertEquals("Second is incorrect", 9, theKopf.holeNachfolger().holeWert().intValue());
	}

	@Test(timeout = 666)
	public void pubTest_einfuegen_5() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<Integer> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_INTEGER);
		zdvpws.einfuegen(5);
		zdvpws.einfuegen(10);
		zdvpws.einfuegen(8);
		zdvpws.einfuegen(9);
		zdvpws.einfuegen(7);
		zdvpws.einfuegen(10);
		DoppeltVerketteterKnoten<Integer> theKopf = zdvpws.kopf;
		assertNotNull("Failed.", theKopf);
		assertNotNull("Failed.", theKopf.holeWert());
		assertEquals("Failed.", 10, theKopf.holeWert().intValue());
		assertEquals("Last is incorrect", 5, theKopf.holeVorgaenger().holeWert().intValue());
		assertEquals("Second is incorrect", 10, theKopf.holeNachfolger().holeWert().intValue());
	}

	@Test(timeout = 666)
	public void pubTest_einfuegen_6() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<Integer> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_INTEGER);
		zdvpws.einfuegen(5);
		zdvpws.einfuegen(10);
		zdvpws.einfuegen(8);
		zdvpws.einfuegen(9);
		zdvpws.einfuegen(7);
		zdvpws.einfuegen(10);
		DoppeltVerketteterKnoten<Integer> theKopf = zdvpws.kopf;
		assertNotNull("Failed.", theKopf);
		assertNotNull("Failed.", theKopf.holeWert());
		assertEquals("Failed.", 10, theKopf.holeWert().intValue());
		assertEquals("Last is incorrect", 5, theKopf.holeVorgaenger().holeWert().intValue());
		assertEquals("Second is incorrect", 10, theKopf.holeNachfolger().holeWert().intValue());
		assertEquals("Third is incorrect", 9, theKopf.holeNachfolger().holeNachfolger().holeWert().intValue());
		assertEquals("Fourth is incorrect", 8,
				theKopf.holeNachfolger().holeNachfolger().holeNachfolger().holeWert().intValue());
		assertEquals("Fifth is incorrect", 7,
				theKopf.holeNachfolger().holeNachfolger().holeNachfolger().holeNachfolger().holeWert().intValue());
	}

	@Test(timeout = 666)
	public void pubTest_einfuegen_7() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<Integer> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_INTEGER);
		zdvpws.einfuegen(5);
		zdvpws.einfuegen(10);
		zdvpws.einfuegen(8);
		zdvpws.einfuegen(9);
		zdvpws.einfuegen(7);
		zdvpws.einfuegen(10);
		DoppeltVerketteterKnoten<Integer> theKopf = zdvpws.kopf;
		assertNotNull("Failed.", theKopf);
		assertNotNull("Failed.", theKopf.holeWert());
		assertEquals("Failed.", 10, theKopf.holeWert().intValue());
		assertEquals("Last is incorrect", 5, theKopf.holeVorgaenger().holeWert().intValue());
		assertEquals("Second Last is incorrect", 7, theKopf.holeVorgaenger().holeVorgaenger().holeWert().intValue());
		assertEquals("Third Last is incorrect", 8,
				theKopf.holeVorgaenger().holeVorgaenger().holeVorgaenger().holeWert().intValue());
		assertEquals("Fourth Last is incorrect", 9,
				theKopf.holeVorgaenger().holeVorgaenger().holeVorgaenger().holeVorgaenger().holeWert().intValue());
		assertEquals("Fifth Last is incorrect", 10, theKopf.holeVorgaenger().holeVorgaenger().holeVorgaenger()
				.holeVorgaenger().holeVorgaenger().holeWert().intValue());
	}

	@Test(timeout = 666)
	public void pubTest_einfuegen_8() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<Integer> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_INTEGER);
		zdvpws.einfuegen(5);
		zdvpws.einfuegen(10);
		zdvpws.einfuegen(10);
		zdvpws.einfuegen(10);
		zdvpws.einfuegen(7);
		zdvpws.einfuegen(10);
		DoppeltVerketteterKnoten<Integer> theKopf = zdvpws.kopf;
		assertNotNull("Failed.", theKopf);
		assertNotNull("Failed.", theKopf.holeWert());
		assertEquals("Failed.", 10, theKopf.holeWert().intValue());
		assertEquals("Last is incorrect", 5, theKopf.holeVorgaenger().holeWert().intValue());
		assertEquals("Second Last is incorrect", 7, theKopf.holeVorgaenger().holeVorgaenger().holeWert().intValue());
		assertEquals("Third Last is incorrect", 10,
				theKopf.holeVorgaenger().holeVorgaenger().holeVorgaenger().holeWert().intValue());
		assertEquals("Fourth Last is incorrect", 10,
				theKopf.holeVorgaenger().holeVorgaenger().holeVorgaenger().holeVorgaenger().holeWert().intValue());
		assertEquals("Fifth Last is incorrect", 10, theKopf.holeVorgaenger().holeVorgaenger().holeVorgaenger()
				.holeVorgaenger().holeVorgaenger().holeWert().intValue());
	}

	@Test(timeout = 666)
	public void pubTest_wichtigstenEntfernen() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<String> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_STRING_LEX);
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("Doe");
		DoppeltVerketteterKnoten<String> theKopf = zdvpws.kopf;
		assertNotNull("Failed.", theKopf);
		assertEquals("Failed.", "John", theKopf.holeWert());
		assertEquals("Failed.", "John", zdvpws.wichtigstenEntfernen());
	}

	@Test(timeout = 666)
	public void pubTest_wichtigstenEntfernen_2() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<Integer> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_INTEGER);
		zdvpws.einfuegen(5);
		zdvpws.einfuegen(6);
		zdvpws.einfuegen(8);
		zdvpws.einfuegen(9);
		zdvpws.einfuegen(10);
		DoppeltVerketteterKnoten<Integer> theKopf = zdvpws.kopf;
		assertNotNull("Failed.", theKopf);
		assertEquals("Failed.", new Integer(10), theKopf.holeWert());
		assertEquals("Failed.", new Integer(10), zdvpws.wichtigstenEntfernen());
	}

	@Test(timeout = 666)
	public void pubTest_wichtigstenEntfernen_4() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<Integer> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_INTEGER);
		zdvpws.einfuegen(5);
		zdvpws.einfuegen(6);
		zdvpws.einfuegen(8);
		zdvpws.einfuegen(9);
		zdvpws.einfuegen(10);
		DoppeltVerketteterKnoten<Integer> theKopf = zdvpws.kopf;
		assertNotNull("Failed.", theKopf);
		assertEquals("Failed.", new Integer(10), theKopf.holeWert());
		assertEquals("Failed.", new Integer(10), zdvpws.wichtigstenEntfernen());
		assertEquals("The head wasn't replaced", new Integer(9), zdvpws.kopf.holeWert());
	}

	@Test(timeout = 666)
	public void pubTest_wichtigstenEntfernen_3() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<Integer> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_INTEGER);
		zdvpws.einfuegen(5);
		zdvpws.einfuegen(10);
		zdvpws.einfuegen(8);
		zdvpws.einfuegen(9);
		zdvpws.einfuegen(7);
		DoppeltVerketteterKnoten<Integer> theKopf = zdvpws.kopf;
		assertNotNull("Failed.", theKopf);
		assertEquals("Failed.", new Integer(10), theKopf.holeWert());
		assertEquals("Failed.", new Integer(10), zdvpws.wichtigstenEntfernen());
	}

	@Test(timeout = 666)
	public void pubTest_wichtigstenEntfernen_5() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<Integer> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_INTEGER);
		zdvpws.einfuegen(10);
		DoppeltVerketteterKnoten<Integer> theKopf = zdvpws.kopf;
		assertNotNull("Failed.", theKopf);
		assertEquals("Failed.", 10, theKopf.holeWert().intValue());
		assertEquals("Failed.", 10, zdvpws.wichtigstenEntfernen().intValue());
		assertNull("Should be empty", zdvpws.kopf);
	}

	@Test(timeout = 666)
	public void pubTest_unwichtigstenEntfernen_2() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<Integer> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_INTEGER);
		zdvpws.einfuegen(5);
		zdvpws.einfuegen(10);
		zdvpws.einfuegen(8);
		zdvpws.einfuegen(9);
		zdvpws.einfuegen(7);
		zdvpws.einfuegen(4);
		DoppeltVerketteterKnoten<Integer> theKopf = zdvpws.kopf;
		assertNotNull("Failed.", theKopf);
		assertEquals("Failed.", new Integer(4), theKopf.holeVorgaenger().holeWert());
		assertEquals("Failed.", new Integer(4), zdvpws.unwichtigstenEntfernen());
	}

	@Test(timeout = 666)
	public void pubTest_unwichtigstenEntfernen_3() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<Integer> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_INTEGER);
		zdvpws.einfuegen(5);
		zdvpws.einfuegen(10);
		zdvpws.einfuegen(8);
		zdvpws.einfuegen(9);
		zdvpws.einfuegen(10);
		zdvpws.einfuegen(7);
		zdvpws.einfuegen(4);
		DoppeltVerketteterKnoten<Integer> theKopf = zdvpws.kopf;
		assertNotNull("Failed.", theKopf);
		assertEquals("Failed.", new Integer(4), theKopf.holeVorgaenger().holeWert());
		assertEquals("Failed.", new Integer(4), zdvpws.unwichtigstenEntfernen());
	}

	@Test(timeout = 666)
	public void pubTest_unwichtigstenEntfernen_4() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<Integer> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_INTEGER);
		zdvpws.einfuegen(5);
		zdvpws.einfuegen(10);
		zdvpws.einfuegen(8);
		zdvpws.einfuegen(9);
		zdvpws.einfuegen(10);
		zdvpws.einfuegen(7);
		zdvpws.einfuegen(4);
		zdvpws.einfuegen(4);
		DoppeltVerketteterKnoten<Integer> theKopf = zdvpws.kopf;
		assertNotNull("Failed.", theKopf);
		assertEquals("Failed.", new Integer(4), theKopf.holeVorgaenger().holeWert());
		assertEquals("Failed.", new Integer(4), zdvpws.unwichtigstenEntfernen());
	}

	@Test(timeout = 666)
	public void pubTest_unwichtigstenEntfernen_6() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<Integer> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_INTEGER);
		zdvpws.einfuegen(5);
		zdvpws.einfuegen(10);
		zdvpws.einfuegen(8);
		zdvpws.einfuegen(9);
		zdvpws.einfuegen(10);
		zdvpws.einfuegen(7);
		zdvpws.einfuegen(4);
		DoppeltVerketteterKnoten<Integer> theKopf = zdvpws.kopf;
		assertNotNull("Failed.", theKopf);
		assertEquals("Failed.", new Integer(4), theKopf.holeVorgaenger().holeWert());
		assertEquals("Failed.", new Integer(4), zdvpws.unwichtigstenEntfernen());
		assertEquals("The tail wasn't replaced", 5, theKopf.holeVorgaenger().holeWert().intValue());
	}

	@Test(timeout = 666)
	public void pubTest_unwichtigstenEntfernen_7() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<Integer> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_INTEGER);
		zdvpws.einfuegen(5);
		DoppeltVerketteterKnoten<Integer> theKopf = zdvpws.kopf;
		assertNotNull("Failed.", theKopf);
		assertEquals("Failed.", 5, theKopf.holeVorgaenger().holeWert().intValue());
		assertEquals("Failed.", 5, zdvpws.unwichtigstenEntfernen().intValue());
		assertNull("Should be empty", zdvpws.kopf);
	}

	@Test(timeout = 666)
	public void pubTest_unwichtigstenEntfernen_8() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<Integer> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_INTEGER);
		zdvpws.einfuegen(5);
		zdvpws.einfuegen(10);
		DoppeltVerketteterKnoten<Integer> theKopf = zdvpws.kopf;
		assertNotNull("Failed.", theKopf);
		assertEquals("Failed.", new Integer(5), theKopf.holeVorgaenger().holeWert());
		assertEquals("Failed.", new Integer(5), zdvpws.unwichtigstenEntfernen());
		assertEquals("The tail wasn't replaced", 10, theKopf.holeVorgaenger().holeWert().intValue());
	}

	@Test(timeout = 666, expected = NoSuchElementException.class)
	public void pubTest_unwichtigstenEntfernen() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<?> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_MAGIC);
		zdvpws.einfuegen(null);
		zdvpws.unwichtigstenEntfernen();
		zdvpws.unwichtigstenEntfernen();
		fail("Failed.");
	}

	@Test(timeout = 666)
	public void pubTest_wichtigstenEntfernenComparatorOfWertTyp() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<String> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_STRING_LEX);
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("Doe");
		assertEquals("Failed.", "Doe", zdvpws.wichtigstenEntfernen(COMP_STRING_ANTI_LEX));
	}

	@Test(timeout = 666)
	public void pubTest_wichtigstenEntfernenComparatorOfWertTyp_2() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<String> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_STRING_LEX);
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("Doe");
		zdvpws.einfuegen("Apple");
		assertEquals("Failed.", "Apple", zdvpws.wichtigstenEntfernen(COMP_STRING_ANTI_LEX));
	}

	@Test(timeout = 666)
	public void pubTest_wichtigstenEntfernenComparatorOfWertTyp_3() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<String> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_STRING_LEX);
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("Doe");
		zdvpws.einfuegen("Apple");
		zdvpws.einfuegen("Banana");
		assertEquals("Failed.", "Apple", zdvpws.wichtigstenEntfernen(COMP_STRING_ANTI_LEX));
	}

	@Test(timeout = 666)
	public void pubTest_wichtigstenEntfernenComparatorOfWertTyp_4() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<String> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_STRING_LEX);
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("Doe");
		zdvpws.einfuegen("Apple");
		zdvpws.einfuegen("Banana");
		zdvpws.einfuegen("Grape");
		assertEquals("Failed.", "Apple", zdvpws.wichtigstenEntfernen(COMP_STRING_ANTI_LEX));
	}

	@Test(timeout = 666)
	public void pubTest_wichtigstenEntfernenComparatorOfWertTyp_5() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<String> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_STRING_LEX);
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("Doe");
		zdvpws.einfuegen("Banana");
		zdvpws.einfuegen("Grape");
		assertEquals("Failed.", "Banana", zdvpws.wichtigstenEntfernen(COMP_STRING_ANTI_LEX));
	}
	
	@Test(timeout = 666)
	public void pubTest_wichtigstenEntfernenComparatorOfWertTyp_6() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<String> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_STRING_LEX);
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("Doe");
		zdvpws.einfuegen("Banana");
		zdvpws.einfuegen("Grape");
		assertEquals("Failed.", "John", zdvpws.wichtigstenEntfernen());
	}
	
	@Test(timeout = 666)
	public void pubTest_wichtigstenEntfernenComparatorOfWertTyp_7() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<String> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_STRING_ANTI_LEX);
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("Doe");
		zdvpws.einfuegen("Banana");
		zdvpws.einfuegen("Grape");
		assertEquals("Failed.", "John", zdvpws.wichtigstenEntfernen(COMP_STRING_LEX));
	}
	
	@Test(timeout = 666)
	public void pubTest_wichtigstenEntfernenComparatorOfWertTyp_8() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<String> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_STRING_ANTI_LEX);
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("John");
		assertEquals("Failed.", "John", zdvpws.wichtigstenEntfernen(COMP_STRING_LEX));
	}
	
	@Test(timeout = 666)
	public void pubTest_wichtigstenEntfernenComparatorOfWertTyp_9() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<String> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_STRING_ANTI_LEX);
		zdvpws.einfuegen("Apple");
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("John");
		assertEquals("Failed.", "John", zdvpws.wichtigstenEntfernen(COMP_STRING_LEX));
	}
	
	@Test
	public void pubTest_wichtigstenEntfernenComparatorOfWertTyp_10() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<String> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_STRING_ANTI_LEX);
		zdvpws.einfuegen("Apple");
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("John");
		assertEquals("Failed.", "Apple", zdvpws.wichtigstenEntfernen(COMP_STRING_ANTI_LEX));
	}
	
	@Test
	public void pubTest_wichtigstenEntfernenComparatorOfWertTyp_11() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<String> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_STRING_ANTI_LEX);
		zdvpws.einfuegen("Apple");
		assertEquals("Failed.", "Apple", zdvpws.wichtigstenEntfernen(COMP_STRING_ANTI_LEX));
		assertNull("List should now be empty", zdvpws.kopf);
	}

	@Test(timeout = 666, expected = NoSuchElementException.class)
	public void pubTest_unwichtigstenEntfernenComparatorOfWertTyp() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<Boolean> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_BOOL_ONE);
		zdvpws.einfuegen(null);
		zdvpws.unwichtigstenEntfernen(COMP_BOOL_ONE);
		zdvpws.unwichtigstenEntfernen(COMP_BOOL_ONE);
		fail("Failed.");
	}
	
	@Test(timeout = 666)
	public void pubTest_unwichtigstenEntfernenComparatorOfWertTyp_2() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<String> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_STRING_LEX);
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("Doe");
		zdvpws.einfuegen("Banana");
		zdvpws.einfuegen("Grape");
		assertEquals("Failed.", "John", zdvpws.unwichtigstenEntfernen(COMP_STRING_ANTI_LEX));
	}
	
	@Test
	public void pubTest_unwichtigstenEntfernenComparatorOfWertTyp_3() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<String> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_STRING_ANTI_LEX);
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("Doe");
		zdvpws.einfuegen("Banana");
		zdvpws.einfuegen("Grape");
		assertEquals("Failed.", "John", zdvpws.unwichtigstenEntfernen(COMP_STRING_ANTI_LEX));
	}
	
	@Test
	public void pubTest_unwichtigstenEntfernenComparatorOfWertTyp_4() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<String> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_STRING_ANTI_LEX);
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("Doe");
		zdvpws.einfuegen("Banana");
		zdvpws.einfuegen("Grape");
		zdvpws.einfuegen("Steve");
		assertEquals("Failed.", "Steve", zdvpws.unwichtigstenEntfernen(COMP_STRING_ANTI_LEX));
	}
	
	@Test
	public void pubTest_unwichtigstenEntfernenComparatorOfWertTyp_5() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<String> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_STRING_LEX);
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("Doe");
		zdvpws.einfuegen("Banana");
		zdvpws.einfuegen("Grape");
		zdvpws.einfuegen("Steve");
		assertEquals("Failed.", "Banana", zdvpws.unwichtigstenEntfernen(COMP_STRING_LEX));
	}
	
	@Test
	public void pubTest_unwichtigstenEntfernenComparatorOfWertTyp_6() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<String> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_STRING_LEX);
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("John");
		zdvpws.einfuegen("John");
		assertEquals("Failed.", "John", zdvpws.unwichtigstenEntfernen(COMP_STRING_LEX));
	}
	
	@Test
	public void pubTest_unwichtigstenEntfernenComparatorOfWertTyp_7() {
		ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<String> zdvpws = new ZirkulaereDoppeltVerkettetePrioritaetswarteschlange<>(
				COMP_STRING_LEX);
		zdvpws.einfuegen("John");
		assertEquals("Failed.", "John", zdvpws.unwichtigstenEntfernen(COMP_STRING_LEX));
		assertNull("List should be empty", zdvpws.kopf);
	}
}