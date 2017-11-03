public class Declarations {
	// TODO: declare a constant named "AUD_EXAM_GRADES" with a value of -10131720232730333740.4347 * 10^(-50)

	public static final double AUD_EXAM_GRADES = -10131720232730333740.4347e-50;

	public enum Tutors {
		athanassios, janina, fabian, anatoliy, eva, oliver, christian, adrian, sara, tom, immanuel, johannes, andreas, yannik,
		stefan, benedikt, daniel, julius, rebekka
	}
	// TODO: declare an enumeration named "Tutors" containing the first (only the first ONE!)
	// given names (e.g. "John" from "John Doe") of all 22 tutors of the AuD winter term 2017/18:
	// https://www2.cs.fau.de/teaching/WS2017/AuD/uebungen/index.html
	// IMPORTANT: Although Java coding conventions advise you to write enum constants in CAPITAL_LETTERS,
	// please use simple CamelCase for the names here!!

	public static char[] lowerCaseLetters() {
		char[] letters = new char[26];
		int counter = 0;
		for (char i = 97; i <= (97 + 25); i++) {
			letters[counter] = i;
			counter++;
		}
		return letters;
		// TODO: declare, fill and return an 1-dimensional
		// array containing the ASCII-letters a to z (lower case letters, ASCII 97(10) to 0x7A)

	}

	public static char[][] aFewChars() {
		char[][] container = new char[4][26];

		int counter = 0;
		for (char j = 65; j <= (90); j++) {
			System.out.print(j);
			container[0][counter] = j;
			counter++;
		}

		counter = 0;
		for (char i = 97; i <= (122); i++) {
			container[1][counter] = i;
			counter++;
		}

		counter = 0;
		for (char i = 0; i <= 25; i++) {
			container[2][i] = i;
			counter++;
		}

		counter = 0;
		for (char i = 0; i < 3; i++) {
			if (i < 2) {
				for (char j = 48; j <=57; j++) {
					container[3][counter] = j;
					counter++;
				}
			} else {
				for (char j = 48; j <= 53; j++) {
					container[3][counter] = j;
					counter++;
				}
			}

		}

		// TODO: declare, fill and return a 2-dimensional array containing
		// 0) the ASCII-letters A to Z (capital letters) in the first row,
		// 1) the ASCII-letters a to z (lower case letters) in the second row,
		// 2) the values (!) 0 to 25 in the third row,
		// 3) the ASCII-characters (!) 0 to 9, than 0 to 9 again and finally 0 to 5 in the fourth row
		return container;

	}

	// TODO: declare a constant String named "DINGBATS" containing the
	// Dingbats arrow symbols from [2798] to [27BE]
	// with a horizontal tabulator \t between groups of seven symbols each
	// (e.g. after [279E], after [27A5], ...)
	// and finally followed by a line feed \n
	// (no spaces or other symbols than those above!!)

	public static String DINGBATS = "\u2798\u2799\u279A\u279B\u279C\u279D\u279E\t\u279F\u27A0\u27A1\u27A2\u27A3\u27A4\u27A5\t\u27A6\u27A7\u27A8\u27A9\u27AA\u27AB\u27AC\t\u27AD\u27AE\u27AF\u27B0\u27B1\u27B2\u27B3\t\u27B4\u27B5\u27B6\u27B7\u27B8\u27B9\u27BA\t\u27BB\u27BC\u27BC\u27BD\u27BE\n";

	public static String[][][] theHyperCube() {
		// TODO: declare, fill and return a 3-dimensional array of size 3x3x3 where
		// each cell contains its coordinates encoded as String (e.g. cube[2][1][0] ==
		// "210")
		String[][][] cube = new String[3][3][3];
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				for (int k = 0; k <= 2; k++) {
					String coordinates = i + "" + j + "" + k;
					cube[i][j][k] = coordinates;
				}
			}
		}
		return cube;
	}
}
