/**
 * Classes implementing this interface are objects that can be drawn on a PixelPainter.
 */
public interface Drawable {
	/**
	 * Set the PixelPainter which is used when drawing this Drawable.
	 * 
	 * @param painter
	 *            the PixelPainter to use.
	 */
	void setPainter(PixelPainter painter);

	/**
	 * Return the PixelPainter which is used (null when it is not set yet).
	 * 
	 * @return The PixelPainter which is used in draw()
	 */
	PixelPainter getPainter();

	/**
	 * Draw this Drawable on a PixelPainter.
	 */
	void draw();
}