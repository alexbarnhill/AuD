public class Path implements Drawable{
	private int x;
	private int y;
	private int[] path;
	private Color color;
	
	private PixelPainter painter;
	
	public Path(int x, int y, int[] path, Color color) {
		this.x = x;
		this.y = y;
		this.path = path;
		this.color = color;
	}

	@Override
	public void setPainter(PixelPainter painter) {
		this.painter = painter;
	}

	@Override
	public PixelPainter getPainter() {
		return this.painter;
	}

	@Override
	public void draw() {
		PixelPainter painter = this.getPainter();
		int currentX = this.x;
		int currentY = this.y;
		painter.set(currentX, currentY, this.color);
		for(int i = 0; i < this.path.length; i++) {
			// Calculate new x and y
			if(path[i] <= 3 && path[i] != 0) {
				currentY = currentY - 1;
			} else if(path[i] > 4) {
				currentY = currentY + 1;
			}
			
			if(path[i] == 3 || path[i] == 4 || path[i] == 5) {
				currentX--;
			} else if(path[i] == 1 || path[i] == 0 || path[i] == 7) {
				currentX++;
			}
			if(currentX >= painter.getMinX() && currentX <= painter.getMaxX() && currentY >= painter.getMinX() && currentY <= painter.getMaxY()) {
				try {
					painter.set(currentX, currentY, this.color);
				} catch(ArrayIndexOutOfBoundsException e ) {
					System.err.println(e.getMessage());
				}
				
			}
			
			
		}
	}
}
