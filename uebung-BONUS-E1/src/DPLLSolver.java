import java.util.*;

public class DPLLSolver extends Solver {
    private int last = 0;
	public DPLLSolver(int numVars) {
		super(numVars);
	}


	
	private List<Literal> getShortest(List<List<Literal>> formula) {
		List<Literal> shortest = formula.get(0);
		for (List <Literal> next : formula) {
			if (next.size() < shortest.size()) {
				shortest = next;
			}

		}
		
		return shortest;
	}

	private void removeWithAssignments(List<List<Literal>> formula, Map<Integer, Boolean> assignments) {
	    List<List<Literal>> toRemove = new LinkedList <>();
        for (Iterator<List <Literal>>  i = formula.iterator(); i.hasNext();) {
            List<Literal> minTerm = i.next();
            for (Iterator <Literal> j = minTerm.iterator(); j.hasNext(); ) {
                Literal lit = j.next();
                if (assignments.containsKey(lit.getVariableIndex())) {
                    if (lit.isNegated() && !assignments.get(lit.getVariableIndex())) {
                        toRemove.add(minTerm);
                    } else if (!lit.isNegated() && assignments.get(lit.getVariableIndex())) {
                        toRemove.add(minTerm);
                    } else if (lit.isNegated() && assignments.get(lit.getVariableIndex())) {
                        j.remove();
                    } else if (!lit.isNegated() && !assignments.get(lit.getVariableIndex())) {
                        j.remove();
                    }
                }
            }
        }

        formula.removeAll(toRemove);
    }


	@Override
	public void propagateUnitClauses(List<List<Literal>> formula, Map<Integer, Boolean> assignments) {
		List<Literal> shortest = getShortest(formula);
		if(shortest.size() == 1) {
            Literal sing = shortest.get(0);
            if (sing.isNegated()) {
                assignments.put(sing.getVariableIndex(), false);
            } else {
                assignments.put(sing.getVariableIndex(), true);
            }
            formula.remove(shortest);

            removeWithAssignments(formula, assignments);


        }
	}

	@Override
	public void assignPureLiterals(List<List<Literal>> formula, Map<Integer, Boolean> assignments) {
        Map<Integer, Boolean> toKeep = new HashMap<Integer, Boolean>();
        Map<Integer, Boolean> hasBeenProcessed = new HashMap<Integer, Boolean>();
        for (List <Literal> minTerm : formula) {
            for (Iterator <Literal> j = minTerm.iterator(); j.hasNext(); ) {
                Literal lit = j.next();
                if(toKeep.containsKey(lit.getVariableIndex())) {
                    if(toKeep.get(lit.getVariableIndex()) != lit.isNegated()) {
                        toKeep.remove(lit.getVariableIndex());
                    }
                } else {
                    if(!hasBeenProcessed.containsKey(lit.getVariableIndex())) {
                        toKeep.put(lit.getVariableIndex(), lit.isNegated());
                    }

                    hasBeenProcessed.put(lit.getVariableIndex(), lit.isNegated());
                }
            }
        }
		
		for(Integer key : toKeep.keySet()) {
            assignments.put(key, !toKeep.get(key));
        }

        removeWithAssignments(formula, assignments);

	}

	@Override
	public int chooseVariable(Map<Integer, Boolean> assignments) {
		int var = 0;
        if(assignments.containsKey(numVars - 1)) {
            return last++;
        } else {
            for(int i = 0; i < assignments.size(); i++) {
                if(!assignments.containsKey(i+1)) {
                    var = i + 1;
                }
            }
        }

		return var;
	}

	private boolean isSolved(List<List<Literal>> formula, Map<Integer, Boolean> assignments) {
		if(numVars > assignments.keySet().size()) {
			return false;
		}

		for (List<Literal> minTerm : formula) {
			boolean minTermResult = false;
			for (Literal lit : minTerm) {
				boolean testedLit = lit.isNegated() ? !assignments.get(lit.getVariableIndex()) : assignments.get(lit.getVariableIndex());
				if (testedLit) {
					minTermResult = true;
					break;
				}
			}
			if (!minTermResult) {
				return false;
			}

		}
		
		return true;
	}
	
	private boolean containsEmptyClause(List<List<Literal>> formula) {
		boolean containsEmptyClause = false;
		for (List<Literal> clause : formula) {
			if (clause.size() == 0) {
				containsEmptyClause = true;
			}
		}
		
		return containsEmptyClause;
	}

	@Override
	public boolean solve(List<List<Literal>> formula, Map<Integer, Boolean> assignments) {
		if(isSolved(formula, assignments)) {
			return true;
		}
		
		if(containsEmptyClause(formula)) {
			return false;
		}

		propagateUnitClauses(formula, assignments);
		assignPureLiterals(formula, assignments);
		int var = chooseVariable(assignments);
        if(var >= numVars) {
            last = 0;
        }
        System.out.println(var);
        System.out.println(assignments);
		assignments.put(var, true);

		if(solve(formula, assignments)) {
		    return true;
        }

        assignments.put(var, false);

		if(solve(formula, assignments)) {
		    return true;
        }

		return false;
	}

}
