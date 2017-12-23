public final class Int {
	// ===== INTERNALS ========================================================
	private final Sign sign;
	private final Nat nat;

	private Int(Sign sign, Nat nat) { // internal constructor
		this.sign = sign;
		this.nat = nat;
	}

	// #################### DO NOT MODIFY ANYTHING ABOVE THIS LINE! ####################

	// ===== OPS: PRIMARY CONS =============================================================
	// TODO: nat2int

	// ===== OPS: PROJECTIONS =============================================================
	// TODO: sign, nat

	// ===== OPS: SECONDARY CONS =============================================================
	// TODO: uminus
	// TODO: add,sub,mul,div

	// #################### DO NOT MODIFY ANYTHING BELOW THIS LINE! ####################

	// ===== HMI ==============================================================
	@Override
	public String toString() {
		return String.format("(%1$s%2$s)", sign(this), nat(this));
	}
}