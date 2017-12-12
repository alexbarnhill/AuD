public class SchiffVersenkenZweigueberdeckungsTest {
	
	@Test
	public void con_test_1() {
		new SchiffVersenken(10, 10, 5);
	}
	
	@Test
	public void con_test_2() {
		new SchiffVersenken(1, 2, 5);
	}
	
	@Test
	public void con_test_3() {
		new SchiffVersenken(2, 1, 5);
	}
	
	@Test
	public void con_test_4() {
		new SchiffVersenken(1, 1, 5);
	}
	
	@Test
	public void con_test_5() {
		new SchiffVersenken(1, 1, 1);
	}
	
	@Test
	public void con_test_6() {
		new SchiffVersenken(2, 1, 1);
	}
	
	@Test
	public void con_test_7() {
		new SchiffVersenken(1, 2, 1);
	}
	
	@Test
	public void con_test_8() {
		new SchiffVersenken(2, 2, 1);
	}
	
	
	@Test
	public void positioniereSchiff() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 5);
		boolean result = s1.positioniereSchiff(2, 2, 2, false);
		Assert.assertEquals("Ship placed incorrectly!", true, result);
	}
	
	@Test
	public void positioniereSchiff_vert() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 5);
		boolean result = s1.positioniereSchiff(2, 2, 2, true);
		Assert.assertEquals("Ship placed incorrectly!", true, result);
	}
	
	@Test
	public void spielzugTest() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 5);
		s1.positioniereSchiff(2, 2, 2, false);
		int result2 = s1.spielzugAusfuehren(2, 2);
		Assert.assertEquals("Should have been registered as a hit", 2, result2);
		
	}
	
	@Test
	public void spielzugTest_miss() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 5);
		s1.positioniereSchiff(2, 2, 2, false);
		int result2 = s1.spielzugAusfuehren(1, 1);
		Assert.assertEquals("Should have been registered as a miss", 1, result2);
		
	}
	
	@Test
	public void spielzugTest_loss() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 2);
		s1.positioniereSchiff(2, 2, 2, false);
		s1.spielzugAusfuehren(1, 1);
		int result2 = s1.spielzugAusfuehren(1, 2);
		Assert.assertEquals("You should have lost", -1, result2);
		
	}
	
	@Test
	public void spielzugTest_won() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 2);
		s1.positioniereSchiff(2, 2, 2, false);
		s1.spielzugAusfuehren(2, 2);
		int result2 = s1.spielzugAusfuehren(3, 2);
		Assert.assertEquals("You should have won!", -2, result2);
		
	}
	
	@Test
	public void spielzugTest_offBase() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 2);
		s1.positioniereSchiff(2, 2, 2, false);
		int result2 = s1.spielzugAusfuehren(-1, 2);
		Assert.assertEquals("You shouldn't have shot there", 0, result2);
		
	}
	
	@Test
	public void positioniereShiff_tooLong() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 2);
		boolean result = s1.positioniereSchiff(2, 2, 20, false);
		Assert.assertEquals("Your boat was too big", false, result);
		
	}

	@Test
	public void positioniereShiff_tooTall() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 2);
		boolean result = s1.positioniereSchiff(2, 2, 20, true);
		Assert.assertEquals("Your boat was too big", false, result);
		
	}
	
	@Test
	public void positioniereShiff_WrongPlacement_1() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 2);
		boolean result = s1.positioniereSchiff(20, 2, 2, true);
		Assert.assertEquals("Your boat was not in the water", false, result);
		
	}
	
	@Test
	public void positioniereShiff_WrongPlacement_2() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 2);
		boolean result = s1.positioniereSchiff(2, 20, 2, true);
		Assert.assertEquals("Your boat was not in the water", false, result);
		
	}
	
	@Test
	public void positioniereShiff_WrongPlacement_3() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 2);
		boolean result = s1.positioniereSchiff(-2, 2, 2, true);
		Assert.assertEquals("Your boat was not in the water", false, result);
		
	}
	
	@Test
	public void positioniereShiff_WrongPlacement_4() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 2);
		boolean result = s1.positioniereSchiff(2, -2, 2, true);
		Assert.assertEquals("Your boat was not in the water", false, result);
		
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
	
	@Test
	public void feldAusgebenTest_2() {
		SchiffVersenken s1 = new SchiffVersenken(10, 10, 5);
		s1.neuesSpielfeld();
		s1.feldAusgeben(false);
	}
}
