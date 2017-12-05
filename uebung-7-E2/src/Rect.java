public class Rect implements Drawable{
	private int x;
	private int y;
	private int height;
	private int width;
	private Color color;
	
	private PixelPainter painter;
	
	public Rect(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	@Override
	public void setPainter(PixelPainter painter) {
		this.painter = painter;
		
	}

	@Override
	public PixelPainter getPainter() {
		return painter;
	}

	@Override
	public void draw() {
		PixelPainter painter = this.getPainter();
		for(int i = this.x; i < this.x + this.width; i++) {
			
			for(int j = this.y; j < this.y + this.height; j++) {	
				
				painter.set(i, j, this.color);
			}
		}
		
	}

}
