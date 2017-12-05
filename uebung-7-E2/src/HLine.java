public class HLine implements Drawable{
	private int x0;
	private int x1;
	private int y;
	private Color color;
	
	private PixelPainter painter;
	
	public HLine(int x0, int x1, int y, Color color) {
		this.x0 = x0;
		this.x1 = x1;
		this.y = y;
		this.color = color;
	}
	

	
	public void setPainter(PixelPainter painter) {
		this.painter = painter;
	}

	
	public PixelPainter getPainter() {
		return this.painter;
	}

	public void draw() {
		PixelPainter painter = this.getPainter();
		for(int i = x0; i <= x1; i++) {
			painter.set(i, this.y, this.color);
			
		}
		
	}
}