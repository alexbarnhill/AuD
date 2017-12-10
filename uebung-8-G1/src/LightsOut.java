public class LightsOut {
	private int[][] field;
	private long state;
	private long mask;
	private int rows;
	private int cols;
	
	private long stateTrim(long bits, long state) {
		long mask = 1;
		for(int i = 0; i < bits; i++) {
			mask = (mask << 1) | 1;
		}
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
		this.mask = mask;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				int bit = (int) (state >> (i + j) & 1);
				field[i][j] = bit;
			}
		}
		printField();
	}
	
	private void printField() {
		for(int i = 0; i < this.rows; i++) {
			for(int j = 0; j < this.cols; j++) {
				System.out.printf("| %s |", field[i][j]);
			}
			System.out.println();
		}
	}
	
	public long getState() {
		return 0;
	}
	
	public void toggle(int col, int row) {
		
	}
	
	public ZahlenFolgenMerker solve() {
		
		return new ZahlenFolgenMerker();
	}
	
	
	
}
