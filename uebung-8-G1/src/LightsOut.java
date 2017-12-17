public class LightsOut {
	private int[][] field;
	private long state;
	private long mask;
	private int rows;
	private int cols;
	private long current;
	private MerkerFuerLightsOutLoesungsVersuche merk;

	
	private long stateTrim(long bits, long state) {
		long bitMask;
		bitMask = ((1L << bits) - (bits >> 6)) - 1L;
		return state & bitMask;
	}
	
	public LightsOut(int cols, int rows, long state, long mask) {
		if(rows <= 0 || cols <= 0 || (rows * cols > 64)) {
			throw new IllegalArgumentException();
		}
		this.rows = rows;
		this.cols = cols;
		this.field = new int[rows][cols];
		this.state = stateTrim(rows * cols, state);
		for(int i = 0; i < rows * cols; i++) {
			if(BitOps.isSet(mask, i)) {
				this.state = BitOps.clear(this.state, i);

			} 
		}
		this.mask = mask;
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				int bit = (int) (this.state >> (i + j) & 1);
				field[i][j] = bit;
			}
		}
		
		
		this.current = this.state;
		this.merk = getInitialToggles(this.current);

	}
	
	public long getState() {
		return this.state;
	}
	
	public void toggle(int col, int row) {
		if(col >= this.cols || row >= this.rows || col < 0 || row < 0) {
			throw new IllegalArgumentException("Cannot Toggle Outisde of the field");
		}
		int pos = col + (row * this.cols);
		if(!BitOps.isSet(this.mask, pos)) {
			int right = pos + 1;
			int left = pos - 1;
			int above = pos - this.cols;
			int below = pos + this.cols;
			if(pos >= 0 && pos < (this.cols * this.rows)) {
				this.state = BitOps.flip(this.state, pos);
			}
			
			if(left >= 0 && (left / this.cols) == (pos / this.cols) && !BitOps.isSet(mask, left)) {
				this.state = BitOps.flip(this.state, left);
			}
			
			if(right < (this.cols * this.rows) && (right / this.cols) == (pos / this.cols) && !BitOps.isSet(mask, right)) {
				this.state = BitOps.flip(this.state, right);
			}
			
			if(above >= 0  && !BitOps.isSet(mask, above)) {
				this.state = BitOps.flip(this.state, above);
			}
			
			if(below < (this.cols * this.rows) && !BitOps.isSet(mask, below)) {
				this.state = BitOps.flip(this.state, below);
			}
		}
		
		


	}
	
	public long toggleSet(int s, long set) {
		long newSet = set;
		int col = s % this.cols;
		int row = s / this.cols;
		if(col >= this.cols || row >= this.rows || col < 0 || row < 0) {
			throw new IllegalArgumentException("Cannot Toggle Outisde of the field");
		}
		if(!BitOps.isSet(this.mask, s)) {
			int right = s + 1;
			int left = s - 1;
			int above = s - this.cols;
			int below = s + this.cols;
			
			if(s >= 0 && s < (this.cols * this.rows)) {
				newSet = BitOps.flip(newSet, s);
			}
			
			if(left >= 0 && (left / this.cols) == (s / this.cols) && !BitOps.isSet(mask, left)) {
				newSet = BitOps.flip(newSet, left);
			}
			
			if(right < (this.cols * this.rows) && (right / this.cols) == (s / this.cols) && !BitOps.isSet(mask, right)) {
				newSet = BitOps.flip(newSet, right);
			}
			
			if(above >= 0  && !BitOps.isSet(mask, above)) {
				newSet = BitOps.flip(newSet, above);
			}
			
			if(below < (this.cols * this.rows) && !BitOps.isSet(mask, below)) {
				newSet = BitOps.flip(newSet, below);
			}
		}
		return newSet;


	}
	
	public MerkerFuerLightsOutLoesungsVersuche getInitialToggles(long start) {
		MerkerFuerLightsOutLoesungsVersuche merker = new MerkerFuerLightsOutLoesungsVersuche();
		ZahlenFolgenMerker z;
		for(int i = 0; i < ((this.rows * this.cols)); i++) {
			if(!BitOps.isSet(this.mask, i)) {
				long newState = this.toggleSet(i, start);
				z = new ZahlenFolgenMerker();
				z.ergaenze(i);
				merker.merkeDir(newState, z);

			}

			
		}
		return merker;
	}
	
	
	private boolean isNotIn(Integer[] a, int index) {
		boolean result = true;
		for(int i = 0; i < a.length; i++) {
			if(a[i] == index) {
				return false;
			}
		}
		return result;
	}
	
	public ZahlenFolgenMerker solve() {
		
		if(this.merk.verrateMirDieSchaltfolgeZum(0) != null) {
			return this.merk.verrateMirDieSchaltfolgeZum(0);
		}

		for(int depth = 2; depth < this.cols * rows; depth++) {
			long[] prev = this.merk.gibMirAlleZustaende();
			for(int i = 0; i < prev.length; i++) {
				long prevState = prev[i];
				ZahlenFolgenMerker prevFolge = this.merk.verrateMirDieSchaltfolgeZum(prevState);
				for(int j = 0; j < (this.cols * this.rows); j++) {
					if(!BitOps.isSet(this.mask, j) && BitOps.isSet(prevState, j) && this.merk.verrateMirDieSchaltfolgeZum(toggleSet(j, prevState)) == null) {
						if(prevFolge.gibtMirAlle().length == (depth - 1) && isNotIn(prevFolge.gibtMirAlle(), j)) {
							ZahlenFolgenMerker newFolge = prevFolge.machMirEineKopieDavon();
							long newState = toggleSet(j, prevState);
							newFolge.ergaenze(j);
							if(newState == 0) {
								return newFolge;
							}
							this.merk.merkeDir(newState, newFolge);
						}
					}
				}
				
			}
		}
		return new ZahlenFolgenMerker();
	}
	
}
