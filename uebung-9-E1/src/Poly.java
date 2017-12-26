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
		if(this.p == null) {
			return Nat.zero();
		} else {
			return Nat.add(Nat.succ(Nat.zero()), this.p.degree());
		}
	}

	public Int eval(Int x) {
		Nat totalDegree = this.degree();
		Nat degCount = this.degree();
		Int result = Int.nat2int(Sign.plus(), Nat.zero());
		Poly start = this;
		
		while(start.p != null) {
			degCount = Nat.sub(totalDegree, start.degree());
			Int pow = x;
			while(Nat.sub(degCount, Nat.succ(Nat.zero())) != Nat.zero()) {
				pow = Int.mul(pow, x);
				degCount = Nat.sub(degCount, Nat.succ(Nat.zero()));
			}
			
			if(degCount != Nat.zero()) {
				result = Int.add(result, Int.mul(pow, start.a));
			} else {
				result = Int.add(result, start.a);
			}
			
			start = start.p;
		}
		
		degCount = Nat.sub(totalDegree, start.degree());
		Int pow = x;
		while(Nat.sub(degCount, Nat.succ(Nat.zero())) != Nat.zero()) {
			pow = Int.mul(pow, x);
			degCount = Nat.sub(degCount, Nat.succ(Nat.zero()));
		}
		
		if(degCount != Nat.zero()) {
			result = Int.add(result, Int.mul(pow, start.a));
		} else {
			result = Int.add(result, start.a);
		}
		return result;
		

	}

	// ===== OPS: SECONDARY CONS =============================================================
	public static Poly add(Poly x, Poly y) {
		//If we're at the end, but the two pols have the same length
		if(x != null && y != null && x.p == null && y.p == null) {
			return new Poly(Int.add(x.a, y.a));
		  
		  // else if x is longer than y and is not yet at the end	
		} else if(x != null && y == null) {
			if(x.p != null) {
				return add(x.p, null).horny(x.a);
			} else {
				return new Poly(x.a);
			}
			
			// else if y is longer than x and y is not yet at the end
		} else if(x == null && y != null) {
			if(y.p != null) {
				return add(null, y.p).horny(y.a);
			} else {
				return new Poly(y.a);
			}
		} else {
			return add(x.p, y.p).horny(Int.add(x.a, y.a));
		}
	}
}