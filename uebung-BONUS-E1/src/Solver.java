import java.util.*;

public abstract class Solver {
	protected final int numVars;

	public Solver(final int numVars) {
		this.numVars = numVars;
	}

	/**
	 * Performs unit propagation on the specified formula. The formula is modified in place. The assigned variables are also stored in the respective map.
	 */
	public abstract void propagateUnitClauses(final List<List<Literal>> formula, final Map<Integer, Boolean> assignments);

	/**
	 * Assigns pure literals in the specified formula. The formula is modified in place. The assigned variables are also stored in the respective map.
	 */
	public abstract void assignPureLiterals(final List<List<Literal>> formula, final Map<Integer, Boolean> assignments);

	/**
	 * Chooses a variable for the next recursive call.
	 * 
	 * @return the index of the first variable that has no assignment yet (smallest index!)
	 */
	public abstract int chooseVariable(final Map<Integer, Boolean> assignments);

	/**
	 * Solves the specified formula using the DPLL algorithm. If the formula is satisfiable, then a satisfying assigment of the variables is stored in {@code assignments}.
	 * 
	 * @return {@code true} if the formula is satisfiable
	 */
	public abstract boolean solve(final List<List<Literal>> formula, final Map<Integer, Boolean> assignments);

	/**
	 * Returns a deep copy of {@code formula}. (The {@code Literal} elements themselves are not copied.)
	 * 
	 * @param formula
	 *            - the list of lists of {@code Literals} to be structurally cloned in full depth (except for the {@code Literal}s)
	 * @return a deep clone of {@code formula}
	 */
	public static final List<List<Literal>> deepCopy(final List<List<Literal>> formula) {
		final List<List<Literal>> result = new LinkedList<>();
		for (final List<Literal> clause : formula) {
			result.add(new LinkedList<>(clause));
		}
		return result;
	}
}