import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class DPLLSolverPublicTest {
	// ========== SYSTEM ==========
	public static final String EX_NAME_propagateUnitClauses = "Solver.propagateUnitClauses";
	public static final String EX_NAME_assignPureLiterals = "Solver.assignPureLiterals";
	public static final String EX_NAME_chooseVariable = "Solver.chooseVariable";
	public static final String EX_NAME_solve = "Solver.solve";

	// ========== MOCK ==========
	protected static final class DPLLSolverCountingMock extends DPLLSolver {
		protected long propagateUnitClausesCallCounter;
		protected long assignPureLiteralsCallCounter;
		protected long chooseVariableCallCounter;
		protected long solveCallCounter;

		DPLLSolverCountingMock(final int numVars) {
			super(numVars);
		}

		@Override
		public void propagateUnitClauses(final List<List<Literal>> formula, final Map<Integer, Boolean> assignments) {
			this.propagateUnitClausesCallCounter += 1;
			super.propagateUnitClauses(formula, assignments);
		}

		@Override
		public void assignPureLiterals(final List<List<Literal>> formula, final Map<Integer, Boolean> assignments) {
			this.assignPureLiteralsCallCounter += 1;
			super.assignPureLiterals(formula, assignments);
		}

		@Override
		public int chooseVariable(final Map<Integer, Boolean> assignments) {
			this.chooseVariableCallCounter += 1;
			return super.chooseVariable(assignments);
		}

		@Override
		public boolean solve(final List<List<Literal>> formula, final Map<Integer, Boolean> assignments) {
			this.solveCallCounter += 1;
			return super.solve(formula, assignments);
		}
	}

	// ============================== PUBLIC TEST ==============================
	@Test(timeout = 1000)
	public void pubTest__propagateUnitClauses__simple() {
		// (v0) & (v1 | !v2) & (!v1 | v2) ==> (v1 | !v2) & (!v1 | v2)
		final List<List<Literal>> formula = new LinkedList<>();
		formula.add(mkClause(new Literal(0, false)));
		formula.add(mkClause(new Literal(1, false), new Literal(2, true)));
		formula.add(mkClause(new Literal(1, true), new Literal(2, false)));
		String formulaString = formulaToString(formula);
		final List<List<Literal>> savedFormula = Solver.deepCopy(formula);
		final Map<Integer, Boolean> assignments = new HashMap<>();
		final Solver solver = new DPLLSolverCountingMock(3);
		solver.propagateUnitClauses(formula, assignments);
		assertNotEquals(DPLLSolverPublicTest.EX_NAME_propagateUnitClauses + " on \"" + formulaString + "\" should modify the formula", savedFormula, formula);
		assertEquals(DPLLSolverPublicTest.EX_NAME_propagateUnitClauses + " on \"" + formulaString + "\" should remove exactly one clause", 1, savedFormula.size() - formula.size());
		assertTrue(DPLLSolverPublicTest.EX_NAME_propagateUnitClauses + " on \"" + formulaString + "\" should keep clause (v1 | !v2)", formula.contains(mkClause(new Literal(1, false), new Literal(2, true))));
		assertTrue(DPLLSolverPublicTest.EX_NAME_propagateUnitClauses + " on \"" + formulaString + "\" should keep clause (!v1 | v2)", formula.contains(mkClause(new Literal(1, true), new Literal(2, false))));
		assertTrue(DPLLSolverPublicTest.EX_NAME_propagateUnitClauses + " on \"" + formulaString + "\" should assign variable 0", assignments.containsKey(0));
		assertTrue(DPLLSolverPublicTest.EX_NAME_propagateUnitClauses + " on \"" + formulaString + "\" assigned wrong value", assignments.get(0));
	}

	@Test(timeout = 1000)
	public void pubTest__propagateUnitClauses__exampleFromExerciseSheet() {
		// (v0) & (!v0 | v1 | !v2) & (v0 | !v1) & (!v1 | v2) ==> (v1 | !v2) & (!v1 | v2)
		final List<List<Literal>> formula = new LinkedList<>();
		formula.add(mkClause(new Literal(0, false)));
		formula.add(mkClause(new Literal(0, true), new Literal(1, false), new Literal(2, true)));
		formula.add(mkClause(new Literal(0, false), new Literal(1, true)));
		formula.add(mkClause(new Literal(1, true), new Literal(2, false)));
		String formulaString = formulaToString(formula);
		final List<List<Literal>> savedFormula = Solver.deepCopy(formula);
		final Map<Integer, Boolean> assignments = new HashMap<>();
		final DPLLSolverCountingMock solver = new DPLLSolverCountingMock(3);
		solver.propagateUnitClauses(formula, assignments);
		assertNotEquals(DPLLSolverPublicTest.EX_NAME_propagateUnitClauses + " on \"" + formulaString + "\" should modify the formula", savedFormula, formula);
		assertEquals(DPLLSolverPublicTest.EX_NAME_propagateUnitClauses + " on \"" + formulaString + "\" should remove exactly two clauses", 2, savedFormula.size() - formula.size());
		assertTrue(DPLLSolverPublicTest.EX_NAME_propagateUnitClauses + " on \"" + formulaString + "\" should keep clause (v1 | !v2)", formula.contains(mkClause(new Literal(1, false), new Literal(2, true))));
		assertTrue(DPLLSolverPublicTest.EX_NAME_propagateUnitClauses + " on \"" + formulaString + "\" should keep clause (!v1 | v2)", formula.contains(mkClause(new Literal(1, true), new Literal(2, false))));
		assertTrue(DPLLSolverPublicTest.EX_NAME_propagateUnitClauses + " on \"" + formulaString + "\" should assign variable 0", assignments.containsKey(0));
		assertTrue(DPLLSolverPublicTest.EX_NAME_propagateUnitClauses + " on \"" + formulaString + "\" assigned wrong value", assignments.get(0));
		assertEquals(DPLLSolverPublicTest.EX_NAME_propagateUnitClauses + " on \"" + formulaString + "\" should not call other methods from super-class", 0, solver.assignPureLiteralsCallCounter + solver.chooseVariableCallCounter + solver.solveCallCounter);
	}

	// ----------------------------------------------------------------------

	@Test(timeout = 1000)
	public void pubTest__assignPureLiterals() {
		// (!v0 | v1) & (!v1 | !v2) & (v1 | v2)
		final List<List<Literal>> formula = new LinkedList<>();
		formula.add(mkClause(new Literal(0, true), new Literal(1, false)));
		formula.add(mkClause(new Literal(1, true), new Literal(2, true)));
		formula.add(mkClause(new Literal(1, false), new Literal(2, false)));
		String formulaString = formulaToString(formula);
		final List<List<Literal>> savedFormula = Solver.deepCopy(formula);
		final Map<Integer, Boolean> assignments = new HashMap<>();
		final DPLLSolver solver = new DPLLSolver(3);
		solver.assignPureLiterals(formula, assignments);
		assertNotEquals(DPLLSolverPublicTest.EX_NAME_assignPureLiterals + " on \"" + formulaString + "\" should modify the formula", savedFormula, formula);
		assertEquals(DPLLSolverPublicTest.EX_NAME_assignPureLiterals + " on \"" + formulaString + "\" should remove exactly one clause", 1, savedFormula.size() - formula.size());
		assertTrue(DPLLSolverPublicTest.EX_NAME_assignPureLiterals + " on \"" + formulaString + "\" should keep clause (!v1 | !v2)", formula.contains(mkClause(new Literal(1, true), new Literal(2, true))));
		assertTrue(DPLLSolverPublicTest.EX_NAME_assignPureLiterals + " on \"" + formulaString + "\" should keep clause (v1 | v2)", formula.contains(mkClause(new Literal(1, false), new Literal(2, false))));
		assertTrue(DPLLSolverPublicTest.EX_NAME_assignPureLiterals + " on \"" + formulaString + "\" should assign variable 0", assignments.containsKey(0));
		assertFalse(DPLLSolverPublicTest.EX_NAME_assignPureLiterals + " on \"" + formulaString + "\" assigned wrong value", assignments.get(0));
	}

	// ----------------------------------------------------------------------

	@Test(timeout = 500)
	public void pubTest__chooseVariable__empty() {
		assertEquals(DPLLSolverPublicTest.EX_NAME_chooseVariable + "() should return 0", 0, new DPLLSolver(3).chooseVariable(new HashMap<Integer, Boolean>()));
	}

	// ----------------------------------------------------------------------

	@Test(timeout = 1000)
	public void pubTest__solve__exampleFromExerciseSheet() {
		// (v0 | !v2) & (!v0 | !v1) & (!v0 | v2) & (v1 | v2)
		final List<List<Literal>> formula = new LinkedList<>();
		formula.add(mkClause(new Literal(0, false), new Literal(2, true)));
		formula.add(mkClause(new Literal(0, true), new Literal(1, true)));
		formula.add(mkClause(new Literal(0, true), new Literal(2, false)));
		formula.add(mkClause(new Literal(1, false), new Literal(2, false)));
		String formulaString = formulaToString(formula);
		final Map<Integer, Boolean> assignments = new HashMap<>();
		final DPLLSolver solver = new DPLLSolver(3);
		boolean isSatisfiable = solver.solve(formula, assignments);
		assertTrue(DPLLSolverPublicTest.EX_NAME_solve + " on \"" + formulaString + "\" returned wrong result", isSatisfiable);
		assertTrue("No assignment for variable 0 found", assignments.containsKey(0));
		assertTrue("No assignment for variable 1 found", assignments.containsKey(1));
		assertTrue("No assignment for variable 2 found", assignments.containsKey(2));
		if (assignments.get(0)) {
			assertFalse("Assignment for variable 1 is wrong", assignments.get(1));
			assertTrue("Assignment for variable 2 is wrong", assignments.get(2));
		} else {
			assertTrue("Assignment for variable 1 is wrong", assignments.get(1));
			assertFalse("Assignment for variable 2 is wrong", assignments.get(2));
		}
	}

	// ========== HELPER ==========
	protected static final List<Literal> mkClause(final Literal... literals) {
		return new LinkedList<>(Arrays.asList(literals));
	}

	protected static final String formulaToString(final List<List<Literal>> formula) {
		final StringBuilder result = new StringBuilder();
		boolean first = true;
		for (final List<Literal> clause : formula) {
			if (!first) {
				result.append(" \u2227 "); // and, &
			}
			first = false;
			result.append('(');
			boolean firstLit = true;
			for (final Literal literal : clause) {
				if (!firstLit) {
					result.append(" \u2228 "); // or, |
				}
				firstLit = false;
				result.append(literal);
			}
			result.append(')');
		}
		return result.toString();
	}
}