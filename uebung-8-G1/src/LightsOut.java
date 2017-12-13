public class LightsOut {
	private int[][] field;
	private long state;
	private long mask;
	private int rows;
	private int cols;
	private long solved;
	private long current;
	private int depth = 1;
	private ZahlenFolgenMerker zf;
	private MerkerFuerLightsOutLoesungsVersuche merk;
	private ZahlenMerker zm;
	
	private long stateTrim(long bits, long state) {
		long mask = (1L << bits) - 1;
		return state & mask;
	}
	public LightsOut(int rows, int cols, long state, long mask) {
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
		this.merk = new MerkerFuerLightsOutLoesungsVersuche();
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
	
	public long toggleSet(int s, long set) {
		int col = s % this.cols;
		int row = s / this.cols;
		if(col >= this.cols || row >= this.rows || col < 0 || row < 0) {
			throw new IllegalArgumentException("Cannot Toggle Outisde of the field");
		}
		int pos = this.field.length * row + col;
		int over = this.field.length * (row - 1) + col;
		int under = this.field.length * (row + 1) + col;
		int left = this.field.length * (row) + (col - 1);
		int right = this.field.length * row + (col + 1);
		if(!BitOps.isSet(this.mask, pos)) {
			set = BitOps.flip(set, pos);
		}
		if(!BitOps.isSet(this.mask, over) && (row - 1) >= 0) {
			set = BitOps.flip(set, over);
		}
		if(!BitOps.isSet(this.mask, right) && (col + 1) <= this.cols - 1) {
			set = BitOps.flip(set, right);
		}
		if(!BitOps.isSet(this.mask, under) && (row + 1) <= this.rows - 1) {
			set = BitOps.flip(set, under);
		}
		if(!BitOps.isSet(this.mask, left) && (col - 1) >= 0) {
			set = BitOps.flip(set, left);
		}
		
		return set;


	}
	
	public ZahlenFolgenMerker solve() {
		if(this.current == 0) {
			return this.zf;
		}
		System.out.println(this.getState());
		ZahlenFolgenMerker l = solveHelper(this.zf, 1 * (rows * cols));
		
		return this.zf;
	}
	
	private void printArray(Integer[] integers) {
		for(int i = 0; i < integers.length; i++) {
			System.out.printf("%s ", integers[i]);
		}
		System.out.println();
	}
	
	private ZahlenFolgenMerker solveHelper(ZahlenFolgenMerker z, int depth) {
		
		for(int i = 0; i < depth; i++) {
			System.out.println("Starting with state = " + this.current);
			this.current = toggleSet(i, this.current);
			System.out.printf("Togelling %s  -- Result = %s\n", i, this.current);
			z.ergaenze(i);
			if(this.current == 0) {
				System.out.printf("Solution found in %s", i);
				break;
			} else {
				this.merk.merkeDir(this.current, z);
				this.current = toggleSet(i, this.current);
				System.out.printf("Returing to state. Toggeling %s -- Result = %s \n", i, this.current);
			}
		}
		return z;
	}
	
	
}
