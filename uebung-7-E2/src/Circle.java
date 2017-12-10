public class Circle extends OrigCircle {
	private int dx;
	private int dy;
	private ShiftPainter painter;
	
	public Circle(int x, int y, int r, Color color) {
		super(r, color);
		this.dx = x;
		this.dy = y;
	}
	
	@Override
	public void setPainter(PixelPainter painter) {
		this.painter = new ShiftPainter(dx, dy, painter);
		
	}

	@Override
	public PixelPainter getPainter() {
		return this.painter;
	}

	@Override
	public void draw() {
		super.setPainter(this.painter);
		super.draw();
	}
}
