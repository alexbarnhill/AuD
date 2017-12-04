/**
 * Interface for painting colored pixels. The origin (0,0) for this interface is the upper left corner.
 */
public interface PixelPainter {
	/**
	 * Sets the pixel at (x, y) to color. Do not call this method with coordinates outside the range given by getMinX()/getMaxX() resp. getMinY()/getMaxY()!
	 * 
	 * @param x
	 *            x-coordinate of the pixel to set.
	 * @param y
	 *            y-coordinate of the pixel to set.
	 * @param color
	 *            color The Pixel Should be set to.
	 */
	void set(int x, int y, Color color);

	/**
	 * Set all pixels to black.
	 */
	void clear();

	/**
	 * Get the lowest allowed x value for the draw function
	 * 
	 * @return the lowest allowed x value for the draw function
	 */
	int getMinX();

	/**
	 * Get the highest allowed x value for the draw function
	 * 
	 * @return the highest allowed x value for the draw function
	 */
	int getMaxX();

	/**
	 * Get the lowest allowed y value for the draw function
	 * 
	 * @return the lowest allowed y value for the draw function
	 */
	int getMinY();

	/**
	 * Get the highest allowed y value for the draw function
	 * 
	 * @return the highest allowed y value for the draw function
	 */
	int getMaxY();
}