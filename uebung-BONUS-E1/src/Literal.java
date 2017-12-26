public class Literal {
	private final int variableIndex;
	private final boolean negated;

	public Literal(final int variableIndex, final boolean negated) {
		this.variableIndex = variableIndex;
		this.negated = negated;
	}

	public int getVariableIndex() {
		return this.variableIndex;
	}

	public boolean isNegated() {
		return this.negated;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Literal) {
			final Literal other = (Literal) obj;
			return (this.variableIndex == other.getVariableIndex()) && (this.negated == other.isNegated());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.negated ? ~this.variableIndex : this.variableIndex;
	}

	@Override
	public String toString() {
		if (this.negated) {
			return "\u00AC" + "v" + this.variableIndex;
		}
		return "v" + this.variableIndex;
	}
}