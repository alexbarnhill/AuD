public abstract class Sign {
	// ===== INTERNALS ========================================================
	private static final Sign plus = new Sign() {
	}; // internal marker object for positive sign
	private static final Sign minus = new Sign() {
	}; // internal marker object for negative sign

	private Sign() { // internal constructor
	}

	// ===== OPS: PRIMARY CONS =============================================================
	public static Sign plus() {
		return plus;
	}

	public static Sign minus() {
		return minus;
	}

	// ===== HMI ==============================================================
	@Override
	public String toString() {
		return this == plus() ? "+" : "-";
	}
}