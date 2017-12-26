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
	public static Int nat2int(Sign sgn, Nat nat) {
	
		return new Int(sgn, nat);
	}

	// ===== OPS: PROJECTIONS =============================================================
	public static Sign sign(Int num) {
		return num.sign;
	}
	
	public static Nat nat(Int num) {
		return num.nat;
	}
	
	
	// ===== OPS: SECONDARY CONS =============================================================

	public static Int add(Int a, Int b) {
		Sign sgn = Sign.plus();
		if(sign(a) == Sign.minus() && sign(b) == Sign.minus()) {
			sgn = Sign.minus();
		} else if(sign(a) == Sign.plus() && sign(b) == Sign.minus()) {
			Int c = nat2int(Sign.plus(), nat(b));
			return Int.sub(a, c);
		} else if(sign(a) == Sign.minus() && sign(b) == Sign.plus()) {
			Int c = nat2int(Sign.plus(), nat(a));
			return Int.sub(b, c);
		}
		
		Nat nat = Nat.add(nat(a), nat(b));
		if(nat == Nat.zero()) {
			sgn = Sign.plus();
		}
		return nat2int(sgn, nat);
	}
	
	public static Int sub(Int a, Int b) {
		Sign sgn = Sign.plus();
		Nat nat = Nat.sub(nat(a), nat(b));
		if((sign(a) == Sign.plus() && sign(b) == Sign.minus()) || (sign(a) == Sign.minus() && sign(b) == Sign.minus())) {
			Int c = nat2int(Sign.plus(), nat(b));
			return Int.add(a, c);
		} else if(sign(a) == Sign.minus() && sign(b) == Sign.plus()) {
			sgn = Sign.minus();
			nat = Nat.add(nat(a), nat(b));
		} else if(sign(a) == Sign.plus() && sign(b) == Sign.plus()) {
			if(Nat.sub(nat(a), nat(b)) == Nat.zero() && nat(a) != nat(b)) {
				sgn = Sign.minus();
				nat = Nat.sub(nat(b), nat(a));
			}
		}
		
		if(nat == Nat.zero()) {
			sgn = Sign.plus();
		}
		
		return nat2int(sgn, nat);
	}
	
	public static Int mul(Int a, Int b) {
		Sign sgn = Sign.plus();
		if(nat(a) == Nat.zero() || nat(b) == Nat.zero()) {
			return nat2int(Sign.plus(), Nat.zero());
		}
		if((sign(a) == Sign.minus() && sign(b) == Sign.plus()) || (sign(a) == Sign.plus() && sign(b) == Sign.minus())) {
			sgn = Sign.minus();
		}
		
		return nat2int(sgn, Nat.mul(nat(a), nat(b)));
	}
	
	public static Int div(Int a, Int b) {
		Sign sgn = Sign.plus();
		if(nat(a) == Nat.zero() || nat(b) == Nat.zero()) {
			return nat2int(Sign.plus(), Nat.zero());
		}
		if((sign(a) == Sign.minus() && sign(b) == Sign.plus()) || (sign(a) == Sign.plus() && sign(b) == Sign.minus())) {
			sgn = Sign.minus();
		}
		return nat2int(sgn, Nat.div(nat(a), nat(b)));
	}
	
	public static Int uminus(Int a) {
		if(sign(a) == Sign.plus()) {
			return nat2int(Sign.minus(), nat(a));
		} 
		
		return nat2int(Sign.plus(), nat(a));
	}

	// #################### DO NOT MODIFY ANYTHING BELOW THIS LINE! ####################

	// ===== HMI ==============================================================
	@Override
	public String toString() {
		return String.format("(%1$s%2$s)", sign(this), nat(this));
	}
}