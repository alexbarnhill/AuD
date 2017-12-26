import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class GemPuzzleLogicPublicTest {
	// ========== SYSTEM ==========
	public static final String EX_NAME_init = "GemPuzzleLogic.init";
	public static final String EX_NAME_shuffle = "GemPuzzleLogic.shuffle";
	public static final String EX_NAME_isGameOver = "GemPuzzleLogic.isGameOver";
	public static final String EX_NAME_moveGap = "GemPuzzleLogic.moveGap";
	public static final String EX_NAME_autoComplete = "GemPuzzleLogic.autoComplete";

	// ============================== PUBLIC TEST ==============================
	@Test(timeout = 666)
	public void pubTest__init() {
		int[][] matrixExpected = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 0 } };
		int[][] matrixStudent = GemPuzzleLogic.init(4, 4);
		assertArrayEquals(GemPuzzleLogicPublicTest.EX_NAME_init, matrixExpected, matrixStudent);
	}

	// ----------------------------------------------------------------------

	@Test(timeout = 666)
	public void pubTest__shuffle__simple() {
		int[][] matrixExpected1 = { { 1, 2, 3 }, { 4, 5, 0 }, { 7, 8, 6 } };
		int[][] matrixExpected2 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 0, 8 } };
		int[][] matrixActual = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
		GemPuzzleLogic.shuffle(matrixActual);
		assertTrue(GemPuzzleLogicPublicTest.EX_NAME_shuffle, Arrays.deepEquals(matrixExpected1, matrixActual) || Arrays.deepEquals(matrixExpected2, matrixActual));
	}

	// ----------------------------------------------------------------------

	@Test(timeout = 666)
	public void pubTest__isGameOver__simple() {
		int[][] matrixExpectedTrue = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 0 } };
		int[][] matrixActualTrue = GemPuzzleLogicPublicTest.deepCloneArray(matrixExpectedTrue);
		boolean resultActualTrue = GemPuzzleLogic.isGameOver(matrixActualTrue);
		assertArrayEquals(GemPuzzleLogicPublicTest.EX_NAME_isGameOver + " illegally modified input matrix!", matrixExpectedTrue, matrixActualTrue);
		assertTrue(GemPuzzleLogicPublicTest.EX_NAME_isGameOver + " failed to recognize a game-over situation.", resultActualTrue);
		int[][] matrixExpectedFalse = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 0, 15 } };
		int[][] matrixActualFalse = GemPuzzleLogicPublicTest.deepCloneArray(matrixExpectedFalse);
		boolean resultActualFalse = GemPuzzleLogic.isGameOver(matrixActualFalse);
		assertArrayEquals(GemPuzzleLogicPublicTest.EX_NAME_isGameOver + " illegally modified input matrix!", matrixExpectedFalse, matrixActualFalse);
		assertFalse(GemPuzzleLogicPublicTest.EX_NAME_isGameOver + " failed to recognize a non-game-over situation.", resultActualFalse);
	}

	// ----------------------------------------------------------------------

	@Test(timeout = 666)
	public void pubTest__moveGap__simple() {
		int[][] matrixInputTrue = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 0, 8 } };
		int[][] matrixExpectedTrue = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
		int[][] matrixStudentTrue = GemPuzzleLogicPublicTest.deepCloneArray(matrixInputTrue);
		boolean resultActualTrue = GemPuzzleLogic.moveGap(matrixStudentTrue, Direction.RIGHT);
		assertTrue(GemPuzzleLogicPublicTest.EX_NAME_moveGap + " should move gap and return true.", resultActualTrue);
		assertArrayEquals(GemPuzzleLogicPublicTest.EX_NAME_moveGap + " should move gap and return true.", matrixExpectedTrue, matrixStudentTrue);
		int[][] matrixExpectedFalse = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
		int[][] matrixStudentFalse = GemPuzzleLogicPublicTest.deepCloneArray(matrixExpectedFalse);
		boolean resultActualFalse = GemPuzzleLogic.moveGap(matrixStudentFalse, Direction.UP);
		assertFalse(GemPuzzleLogicPublicTest.EX_NAME_moveGap + " should not move gap.", resultActualFalse);
		assertArrayEquals(GemPuzzleLogicPublicTest.EX_NAME_moveGap + " illegally modified input matrix!", matrixExpectedFalse, matrixStudentFalse);
	}

	// ----------------------------------------------------------------------

	@Test(timeout = 666)
	public void pubTest__autoComplete__oneStep() {
		int[][] matrixExpected = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 }, { 10, 11, 9 } };
		int[][] matrixStudent = GemPuzzleLogicPublicTest.deepCloneArray(matrixExpected);
		Direction[] result = GemPuzzleLogic.autoComplete(matrixStudent);
		assertArrayEquals(GemPuzzleLogicPublicTest.EX_NAME_autoComplete + " illegally modified input matrix!", matrixExpected, matrixStudent);
		assertNotNull(GemPuzzleLogicPublicTest.EX_NAME_autoComplete + " returned null!", result);
		assertTrue(GemPuzzleLogicPublicTest.EX_NAME_autoComplete + " wrong number of moves!", result.length == 1);
		assertSame(GemPuzzleLogicPublicTest.EX_NAME_autoComplete + " returned wrong moves!", Direction.DOWN, result[0]);
	}

	@Test(timeout = 36666)
	public void pubTest__autoComplete__trickyReversed() {
		int[][] matrix = { { 0, 8, 7 }, { 6, 5, 4 }, { 3, 2, 1 } };
		GemPuzzleLogicPublicTest.check_autoComplete(matrix, 28);
	}

	protected static final void check_autoComplete(int[][] matrixExpected, int stepsExpected) {
		int[][] matrixStudent = GemPuzzleLogicPublicTest.deepCloneArray(matrixExpected);
		Direction[] directions = GemPuzzleLogic.autoComplete(matrixStudent);
		assertArrayEquals(GemPuzzleLogicPublicTest.EX_NAME_autoComplete + " illegally modified input matrix!", matrixExpected, matrixStudent);
		assertNotNull(GemPuzzleLogicPublicTest.EX_NAME_autoComplete + " returned null!", directions);
		assertEquals(GemPuzzleLogicPublicTest.EX_NAME_autoComplete + " wrong number of moves!", stepsExpected, directions.length);
		int rows = matrixExpected.length;
		int cols = matrixExpected[0].length;
		int r = 666, c = 666;
		searchGapExpected: for (r = 0; r < rows; r++) {
			for (c = 0; c < cols; c++) {
				if (matrixExpected[r][c] == 0) {
					break searchGapExpected;
				}
			}
		}
		for (Direction dir : directions) {
			switch (dir) {
			case DOWN:
				assertTrue("Move throws gap off the board!", r < rows - 1);
				matrixExpected[r][c] = matrixExpected[r + 1][c];
				matrixExpected[r + 1][c] = 0;
				r++;
				break;
			case LEFT:
				assertTrue("Move throws gap off the board!", c > 0);
				matrixExpected[r][c] = matrixExpected[r][c - 1];
				matrixExpected[r][c - 1] = 0;
				c--;
				break;
			case RIGHT:
				assertTrue("Move throws gap off the board!", c < cols - 1);
				matrixExpected[r][c] = matrixExpected[r][c + 1];
				matrixExpected[r][c + 1] = 0;
				c++;
				break;
			case UP:
				assertTrue("Move throws gap off the board!", r > 0);
				matrixExpected[r][c] = matrixExpected[r - 1][c];
				matrixExpected[r - 1][c] = 0;
				r--;
				break;
			default:
				throw new IllegalArgumentException("Ooops - this should not have happened");
			}
		}
		int[][] matrixSolved = new int[rows][cols];
		for (r = 0; r < rows; r++) {
			for (c = 0; c < cols; c++) {
				matrixSolved[r][c] = (r * cols) + c + 1;
			}
		}
		matrixSolved[rows - 1][cols - 1] = 0;
		assertArrayEquals(GemPuzzleLogicPublicTest.EX_NAME_autoComplete + " did not solve the puzzle.", matrixSolved, matrixExpected);
	}

	// ========== HELPER ==========
	protected static final int[][] deepCloneArray(int[][] matrixOrig) {
		int[][] matrixClone = new int[matrixOrig.length][];
		for (int r = 0; r < matrixOrig.length; r++) {
			matrixClone[r] = new int[matrixOrig[r].length];
			for (int c = 0; c < matrixOrig[r].length; c++) {
				matrixClone[r][c] = matrixOrig[r][c];
			}
		}
		return matrixClone;
	}
}