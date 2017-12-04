public class ShiftPainter implements PixelPainter{
	private int dx;
	private int dy;
	private Color colors[][];
	
	public ShiftPainter(int dx, int dy, PixelPainter painter) {
		this.dx = painter.getMinX() + dx;
		this.dy = painter.getMinY() + dy;
	}
	
	@Override
	public void set(int x, int y, Color color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMinX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxY() {
		// TODO Auto-generated method stub
		return 0;
	}

}
