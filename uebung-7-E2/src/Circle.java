public class Circle extends OrigCircle {
	private int x;
	private int y;
	private int r;
	private Color color;
	
	private PixelPainter painter;
	
	public Circle(int x, int y, int r, Color color) {
		super(r, color);
		this.x = x;
		this.y = y;
		this.r = r;
		this.color = color;
	}
	
	public void setPainter(int dx, int dy, PixelPainter painter) {
		this.painter = new ShiftPainter(dx, dy, painter);
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
		
	}
}
