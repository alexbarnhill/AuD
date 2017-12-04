import java.util.ArrayList;

class OrigCircle implements Drawable {
	private PixelPainter painter;
	private ArrayList<HLine> lines;

	public OrigCircle(int r, Color color) {
		this.lines = new ArrayList<HLine>();
		int x = r, y = 0, e = 0;
		lines.add(new HLine(-x, x, y, color));
		lines.add(new HLine(-y, y, -x, color));
		lines.add(new HLine(-y, y, x, color));
		while (x > y) {
			e -= (y++ << 1) + 1;
			e += e >= 0 ? 0 : (x-- << 1) + 1;
			lines.add(new HLine(-x, x, y, color));
			lines.add(new HLine(-x, x, -y, color));
			lines.add(new HLine(-y, y, x, color));
			lines.add(new HLine(-y, y, -x, color));
		}
	}

	@Override
	public void setPainter(PixelPainter painter) {
		this.painter = painter;
		for (HLine line : lines) {
			line.setPainter(painter);
		}
	}

	@Override
	public PixelPainter getPainter() {
		return painter;
	}

	@Override
	public void draw() {
		for (HLine line : lines) {
			line.draw();
		}
	}
}