public class ShiftPainter implements PixelPainter{
	private int dx;
	private int dy;
	private PixelPainter painter;
	
	public ShiftPainter(int dx, int dy, PixelPainter painter) {
		this.dx = dx;
		this.dy = dy;
		this.painter = painter;
	}
	
	@Override
	public void set(int x, int y, Color color) {
		painter.set(x + dx, y + dy, color);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMinX() {
		return painter.getMinX() - dx;
	}

	@Override
	public int getMaxX() {
		return painter.getMaxX() - dx;
	}

	@Override
	public int getMinY() {
		return painter.getMinY() - dy;
	}

	@Override
	public int getMaxY() {
		return painter.getMaxX() - dx;
	}

}
