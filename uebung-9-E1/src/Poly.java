public class Poly {
	// ===== INTERNALS ========================================================
	private final Int a; // coefficient of current degree
	private final Poly p; // polynom of next smaller degree

	private Poly(Poly p, Int a) {
		this.p = p;
		this.a = a;
	}

	// ===== OPS: PRIMARY CONS =============================================================
	public Poly(Int a) {
		this(null, a);
		assert a != null;
	}

	public Poly horny(Int n) {
		assert n != null;
		return new Poly(this, n);
	}

	// #################### DO NOT MODIFY ANYTHING ABOVE THIS LINE! ####################

	// ===== OPS: PROJECTIONS =============================================================
	public Nat degree() {
		// TODO
	}

	public Int eval(Int x) {
		// TODO
	}

	// ===== OPS: SECONDARY CONS =============================================================
	public static Poly add(Poly x, Poly y) {
		// TODO
	}
}