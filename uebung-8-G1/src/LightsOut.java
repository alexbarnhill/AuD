public class LightsOut {
	private int[][] field;
	private long state;
	private long mask;
	private int rows;
	private int cols;
	private long solved;
	private long current;
	private int depth = 2;
	private ZahlenFolgenMerker zf;
	private MerkerFuerLightsOutLoesungsVersuche merk;
	private ZahlenMerker zm;
	
	private long stateTrim(long bits, long state) {
		long mask = (1L << bits) - 1;
		return state & mask;
	}
	public LightsOut(int cols, int rows, long state, long mask) {
		if(rows <= 0 || cols <= 0) {
			throw new IllegalArgumentException();
		}
		this.rows = rows;
		this.cols = cols;
		this.field = new int[rows][cols];
		this.state = stateTrim(rows * cols, state);
		this.solved = state;
		for(int i = 0; i < rows * cols; i++) {
			if(BitOps.isSet(mask, i)) {
				this.state = BitOps.clear(this.state, i);
				this.solved = BitOps.clear(this.solved, i);
			} else {
				this.solved = BitOps.set(this.solved, i);
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
		this.zf = new ZahlenFolgenMerker();
		this.merk = getInitialToggles(this.current);
		this.zm = new ZahlenMerker();

	}
	
	public long getState() {
		return this.state;
	}
	
	public void toggle(int col, int row) {
		if(col >= this.cols || row >= this.rows || col < 0 || row < 0) {
			throw new IllegalArgumentException("Cannot Toggle Outisde of the field");
		}
		int pos = this.field.length * row + col;
		
		if(!BitOps.isSet(this.mask, pos)) {
			int over = this.field.length * (row - 1) + col;
			int under = this.field.length * (row + 1) + col;
			int left = this.field.length * (row) + (col - 1);
			int right = this.field.length * row + (col + 1);
			if(!BitOps.isSet(this.mask, pos)) {
				this.state = BitOps.flip(this.getState(), pos);
			}
			if(!BitOps.isSet(this.mask, over) && (row - 1) >= 0) {
				this.state = BitOps.flip(this.getState(), over);
			}
			if(!BitOps.isSet(this.mask, right) && (col + 1) <= this.cols - 1) {
				this.state = BitOps.flip(this.getState(), right);
			}
			if(!BitOps.isSet(this.mask, under) && (row + 1) <= this.rows - 1) {
				this.state = BitOps.flip(this.getState(), under);
			}
			if(!BitOps.isSet(this.mask, left) && (col - 1) >= 0) {
				this.state = BitOps.flip(this.getState(), left);
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
	
	private void printIntegerArray(Integer[] a) {
		for(int i = 0; i < a.length; i++) {
			System.out.printf("%s ", a[i]);
		}
		System.out.println();
	}
	
	public ZahlenFolgenMerker solve() {
		long[] prev = this.merk.gibMirAlleZustaende();
		if(this.merk.verrateMirDieSchaltfolgeZum(0) != null) {
			return this.merk.verrateMirDieSchaltfolgeZum(0);
		}
		
		// The idea is simple...
		// Go through the old states
		// For every old state, get the Folge for that state
		// For every entity on the board, add it to the previous state
		// See if this new state is correct
		// At any rate add the new state and the Folge to the List
		
		for(int i = 0; i < prev.length; i++) {
			long prevState = prev[i];
			ZahlenFolgenMerker prevFolge = this.merk.verrateMirDieSchaltfolgeZum(prevState);
			for(int j = 0; j < (this.cols * this.rows); j++) {
				if(!BitOps.isSet(this.mask, j)) {
					ZahlenFolgenMerker newFolge = prevFolge.machMirEineKopieDavon();
					long newState = toggleSet(j, prevState);
					System.out.println(newState);
					newFolge.ergaenze(j);
					if(newState == 0) {
						return newFolge;
					}
					this.merk.merkeDir(newState, newFolge);
					prevState = toggleSet(j, prevState);
				}
			}
		}
		return this.merk.verrateMirDieSchaltfolgeZum(0);
	}
	
}
