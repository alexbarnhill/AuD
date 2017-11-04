import static org.junit.Assert.*;
import org.junit.*;

public class FunctionPlotterPublicTest {
	// --------------- PUBLIC TESTS ------------------
	@Test(timeout = 100)
	public void pubTest_newPlottingArea_80x30() {
		int width = 80, height = 30;
		FunctionPlotter.newPlottingArea(width, height);
		assertNotNull(FunctionPlotter.plottingArea);
		assertTrue(FunctionPlotter.plottingArea.length == width);
		for (int i = 0; i < width; i++) {
			assertNotNull(FunctionPlotter.plottingArea[i]);
			assertTrue(FunctionPlotter.plottingArea[i].length == height);
			for (int j = 0; j < height; j++) {
				assertEquals("array content wrong at (" + i + "," + j + ")", ' ', FunctionPlotter.plottingArea[i][j]);
			}
		}
	}

	// --------------- PUBLIC TESTS ------------------
	@Test(timeout = 1000)
	public void pubTest_plotChar() {
		int width = 80, height = 30;
		FunctionPlotter.newPlottingArea(width, height);
		assertNotNull(FunctionPlotter.plottingArea);
		char[][] chars = { { 79, 12, '>' }, { 12, 12, '+' }, { 12, 17, '+' }, { 14, 17, '5' }, { 17, 12, '+' }, { 17, 13, '5' }, { 14, 26, 'y' }, { 79, 13, 'x' } };
		for (char[] cs : chars) {
			assertTrue(FunctionPlotter.plotChar(cs[0], cs[1], cs[2]));
		}
		assertFalse("returned value is wrong.", FunctionPlotter.plotChar(81, 31, '!'));
		for (int i = 0; i < width; i++) {
			assertNotNull(FunctionPlotter.plottingArea[i]);
			assertTrue(FunctionPlotter.plottingArea[i].length == height);
			for (int j = 0; j < height; j++) {
				boolean found = false;
				for (char[] cs : chars) {
					if (i == cs[0] && j == cs[1]) {
						assertEquals("array content wrong at (" + i + "," + j + ")", cs[2], FunctionPlotter.plottingArea[i][j]);
						found = true;
					}
				}
				if (!found) {
					assertEquals("array content wrong at (" + i + "," + j + ")", ' ', FunctionPlotter.plottingArea[i][j]);
				}
			}
		}
	}

	// --------------- PUBLIC TESTS ------------------
	@Test(timeout = 1000)
	public void pubTest_plotHorizontalLine_Xaxis_at_y12() {
		int width = 80, height = 30;
		FunctionPlotter.newPlottingArea(width, height);
		assertNotNull(FunctionPlotter.plottingArea);
		FunctionPlotter.plotHorizontalLine(0, width - 1, 12, '-');
		for (int i = 0; i < width; i++) {
			assertNotNull(FunctionPlotter.plottingArea[i]);
			assertTrue(FunctionPlotter.plottingArea[i].length == height);
			for (int j = 0; j < height; j++) {
				if (j == 12) {
					assertEquals("array content wrong at (" + i + "," + j + ")", '-', FunctionPlotter.plottingArea[i][j]);
				} else {
					assertEquals("array content wrong at (" + i + "," + j + ")", ' ', FunctionPlotter.plottingArea[i][j]);
				}
			}
		}
	}

	// --------------- PUBLIC TESTS ------------------
	@Test(timeout = 100)
	public void pubTest_plotVerticalLine_Yaxis_at_x12_from0to25() {
		int width = 80, height = 30;
		FunctionPlotter.newPlottingArea(width, height);
		assertNotNull(FunctionPlotter.plottingArea);
		FunctionPlotter.plotVerticalLine(12, 0, 25, '|');
		for (int i = 0; i < width; i++) {
			assertNotNull(FunctionPlotter.plottingArea[i]);
			assertTrue(FunctionPlotter.plottingArea[i].length == height);
			for (int j = 0; j < height; j++) {
				if (i == 12 && j <= 25) {
					assertEquals("array content wrong at (" + i + "," + j + ")", '|', FunctionPlotter.plottingArea[i][j]);
				} else {
					assertEquals("array content wrong at (" + i + "," + j + ")", ' ', FunctionPlotter.plottingArea[i][j]);
				}
			}
		}
	}

	// --------------- PUBLIC TESTS ------------------
	@Test(timeout = 1000)
	public void pubTest_plotBox() {
		FunctionPlotter.newPlottingArea(80, 30);
		FunctionPlotter.plotBox(56, 25, 79, 27);
		for (int[] ij : new int[][] { { 56, 25 }, { 56, 27 }, { 79, 25 }, { 79, 27 } }) {
			assertEquals("array content wrong at (" + ij[0] + "," + ij[1] + ")", '+', FunctionPlotter.plottingArea[ij[0]][ij[1]]);
		}
		for (int i = 57; i < 79; i++) {
			assertEquals("array content wrong at (" + i + "," + 25 + ")", '-', FunctionPlotter.plottingArea[i][25]);
			assertEquals("array content wrong at (" + i + "," + 25 + ")", '-', FunctionPlotter.plottingArea[i][27]);
		}
		for (int j = 26; j < 27; j++) {
			assertEquals("array content wrong at (" + 56 + "," + j + ")", '|', FunctionPlotter.plottingArea[56][j]);
			assertEquals("array content wrong at (" + 79 + "," + j + ")", '|', FunctionPlotter.plottingArea[79][j]);
		}
	}

	// --------------- PUBLIC TESTS ------------------
	@Test(timeout = 1000)
	public void pubTest_plotString() {
		FunctionPlotter.newPlottingArea(80, 30);
		final String func = "f(x) = 10*sin(0.3*x)";
		FunctionPlotter.plotString(58, 26, func);
		for (int x = 58, i = 0; i < func.length(); x++, i++) {
			assertTrue(FunctionPlotter.plottingArea[x][26] == func.charAt(i));
		}
	}

	@Test(timeout = 1000)
	public void pubTest_plotFunction_with_coordinateSystem_and_legend() {
		FunctionPlotter.newPlottingArea(80, 30);
		FunctionPlotter.plotBox(56, 25, 79, 27);
		FunctionPlotter.plotString(58, 26, "f(x) = 10*sin(0.3*x)");
		FunctionPlotter.plotVerticalLine(12, 0, 25, '|');
		FunctionPlotter.plotChar(12, 26, '^');
		FunctionPlotter.plotHorizontalLine(0, 78, 12, '-');
		FunctionPlotter.plotChar(79, 12, '>');
		FunctionPlotter.plotChar(12, 17, '+');
		FunctionPlotter.plotChar(14, 17, '5');
		FunctionPlotter.plotChar(17, 12, '+');
		FunctionPlotter.plotChar(17, 13, '5');
		FunctionPlotter.plotChar(14, 26, 'y');
		FunctionPlotter.plotChar(79, 13, 'x');
		FunctionPlotter.plotFunction(-10, +60, 12, 12);
		assertAreasSame();
	}

	// ------------------------------------------------------
	private static char[][] expected = { //
			"            -                 ".toCharArray(), //
			"            -                 ".toCharArray(), //
			"           .-                 ".toCharArray(), //
			"        .   -                 ".toCharArray(), //
			"     .      -                 ".toCharArray(), //
			"   .        -                 ".toCharArray(), //
			"  .         -                 ".toCharArray(), //
			"  .         -                 ".toCharArray(), //
			"   .        -                 ".toCharArray(), //
			"    .       -                 ".toCharArray(), //
			"      .     -                 ".toCharArray(), //
			"         .  -                 ".toCharArray(), //
			"||||||||||||.||||+||||||||^   ".toCharArray(), //
			"            -  .              ".toCharArray(), //
			"            -    5.       y   ".toCharArray(), //
			"            -       .         ".toCharArray(), //
			"            -        .        ".toCharArray(), //
			"            +5        .       ".toCharArray(), //
			"            -         .       ".toCharArray(), //
			"            -        .        ".toCharArray(), //
			"            -      .          ".toCharArray(), //
			"            -   .             ".toCharArray(), //
			"            -.                ".toCharArray(), //
			"          . -                 ".toCharArray(), //
			"        .   -                 ".toCharArray(), //
			"     .      -                 ".toCharArray(), //
			"   .        -                 ".toCharArray(), //
			"  .         -                 ".toCharArray(), //
			"  .         -                 ".toCharArray(), //
			"   .        -                 ".toCharArray(), //
			"    .       -                 ".toCharArray(), //
			"      .     -                 ".toCharArray(), //
			"         .  -                 ".toCharArray(), //
			"            .                 ".toCharArray(), //
			"            -  .              ".toCharArray(), //
			"            -     .           ".toCharArray(), //
			"            -       .         ".toCharArray(), //
			"            -        .        ".toCharArray(), //
			"            -         .       ".toCharArray(), //
			"            -         .       ".toCharArray(), //
			"            -        .        ".toCharArray(), //
			"            -      .          ".toCharArray(), //
			"            -   .             ".toCharArray(), //
			"            -.                ".toCharArray(), //
			"          . -                 ".toCharArray(), //
			"       .    -                 ".toCharArray(), //
			"     .      -                 ".toCharArray(), //
			"   .        -                 ".toCharArray(), //
			"  .         -                 ".toCharArray(), //
			"  .         -                 ".toCharArray(), //
			"   .        -                 ".toCharArray(), //
			"    .       -                 ".toCharArray(), //
			"       .    -                 ".toCharArray(), //
			"         .  -                 ".toCharArray(), //
			"            .                 ".toCharArray(), //
			"            -  .              ".toCharArray(), //
			"            -     .      +|+  ".toCharArray(), //
			"            -       .    - -  ".toCharArray(), //
			"            -        .   -f-  ".toCharArray(), //
			"            -         .  -(-  ".toCharArray(), //
			"            -         .  -x-  ".toCharArray(), //
			"            -       .    -)-  ".toCharArray(), //
			"            -      .     - -  ".toCharArray(), //
			"            -   .        -=-  ".toCharArray(), //
			"            -.           - -  ".toCharArray(), //
			"          . -            -1-  ".toCharArray(), //
			"       .    -            -0-  ".toCharArray(), //
			"     .      -            -*-  ".toCharArray(), //
			"   .        -            -s-  ".toCharArray(), //
			"  .         -            -i-  ".toCharArray(), //
			"  .         -            -n-  ".toCharArray(), //
			"   .        -            -(-  ".toCharArray(), //
			"    .       -            -0-  ".toCharArray(), //
			"            -            -.-  ".toCharArray(), //
			"            -            -3-  ".toCharArray(), //
			"            -            -*-  ".toCharArray(), //
			"            -            -x-  ".toCharArray(), //
			"            -            -)-  ".toCharArray(), //
			"            -            - -  ".toCharArray(), //
			"            >x           +|+  ".toCharArray(), //
	};

	private static void assertAreasSame() {
		assertTrue("array size wrong scratchpad " + FunctionPlotter.plottingArea.length + " myPlotArea " + expected.length, FunctionPlotter.plottingArea.length == expected.length);
		for (int i = 0; i < expected.length; i++) {
			assertTrue("array size wrong", FunctionPlotter.plottingArea[i].length == expected[i].length);
			for (int j = 0; j < expected[i].length; j++) {
				assertTrue(java.util.Arrays.deepToString(FunctionPlotter.plottingArea) + "array content wrong at (" + i + "," + j + ") expected '" + expected[i][j] + "' found '" + FunctionPlotter.plottingArea[i][j] + "' '", FunctionPlotter.plottingArea[i][j] == expected[i][j]);
			}
		}
	}

	// ========== main ==========
	// nothing to do ;) - please do nothing here:
	public static void main(String args[]) {
		// to compile on command line: javac -cp .:/usr/share/java/junit4.jar *.java
		// to run on command line: java -cp .:/usr/share/java/junit4.jar <nameOfThisClass>

		// starts junit runner - don't try to understand!
		org.junit.runner.JUnitCore.main(new Object() {
		}.getClass().getEnclosingClass().getSimpleName());
	}
}