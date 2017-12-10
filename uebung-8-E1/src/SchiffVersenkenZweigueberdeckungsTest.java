public class SchiffVersenkenZweigueberdeckungsTest {
	@Test
	public void positioniereSchiffTestOffGrid() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 5);
		boolean result = s1.positioniereSchiff(-1, -1, 4, true);
		Assert.assertEquals("Should return false!", false, result);
	}
	
	@Test
	public void positioniereSchiffTestTooTall() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 5);
		boolean result = s1.positioniereSchiff(4, 5, 20, true);
		Assert.assertEquals("Should return false!", false, result);
	}
	
	@Test
	public void positioniereSchiffTestTooWide() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 5);
		boolean result = s1.positioniereSchiff(4, 5, 20, false);
		Assert.assertEquals("Should return false!", false, result);
	}
	
	@Test
	public void positioniereSchiffTestSuccessfulPlacement() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 5);
		boolean result = s1.positioniereSchiff(4, 5, 4, false);
		Assert.assertEquals("Should be able to be placed", true, result);
	}
	
	@Test
	public void spielzugAusfuehrenOffGrid() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 5);
		int result = s1.spielzugAusfuehren(-1, 10);
		Assert.assertEquals("Placement off grid registered incorrectly!", 0, result);
	}
	
	@Test
	public void spielzugAusfuehrenMultipleMisses() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 5);
		s1.positioniereSchiff(2, 4, 4, false);
		int result1 = s1.spielzugAusfuehren(1, 2);
		Assert.assertEquals("Placement off grid registered incorrectly!", 1, result1);
		int result2 = s1.spielzugAusfuehren(1, 3);
		Assert.assertEquals("Placement off grid registered incorrectly!", 1, result2);
		int result3 = s1.spielzugAusfuehren(1, 4);
		Assert.assertEquals("Placement off grid registered incorrectly!", 1, result3);
		int result4 = s1.spielzugAusfuehren(1, 5);
		Assert.assertEquals("Placement off grid registered incorrectly!", 1, result4);
		int result5 = s1.spielzugAusfuehren(1, 6);
		Assert.assertEquals("Placement off grid registered incorrectly! You should have lost by now.", -1, result5);
	}
	
	@Test
	public void spielzugAusfuehrenMultipleHits() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 5);
		s1.positioniereSchiff(2, 4, 4, false);
		int result1 = s1.spielzugAusfuehren(2, 4);
		Assert.assertEquals("Hit placement registered incorrectly!", 2, result1);
		int result2 = s1.spielzugAusfuehren(2, 5);
		Assert.assertEquals("Hit placement incorrectly!", 2, result2);
		int result3 = s1.spielzugAusfuehren(2, 6);
		Assert.assertEquals("Hit placement incorrectly!", 2, result3);
		int result4 = s1.spielzugAusfuehren(2, 7);
		Assert.assertEquals("Hit placement incorrectly! You should have won by now.", -2, result4);
	}
	
	@Test
	public void neuesSpielfeldTest() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 5);
		s1.neuesSpielfeld();
	}
	
	@Test
	public void feldAusgebenTest() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 5);
		s1.neuesSpielfeld();
		s1.feldAusgeben(true);
	}
}
