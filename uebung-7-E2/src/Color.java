import java.lang.IllegalArgumentException;

/**
 * Class for colors in RGB scheme.
 */
public class Color implements Cloneable {
	/**
	 * Predefined color red.
	 */
	public static final Color RED = new Color(255, 0, 0);
	/**
	 * Predefined color green.
	 */
	public static final Color GREEN = new Color(0, 255, 0);
	/**
	 * Predefined color blue.
	 */
	public static final Color BLUE = new Color(0, 0, 255);
	/**
	 * Predefined color yellow.
	 */
	public static final Color YELLOW = new Color(255, 255, 0);
	/**
	 * Predefined color black.
	 */
	public static final Color BLACK = new Color(0, 0, 0);
	/**
	 * Predefined color white.
	 */
	public static final Color WHITE = new Color(255, 255, 255);

	private final int red;
	private final int green;
	private final int blue;

	/**
	 * Create a custom Color. The values must be in the range [0, 255].
	 */
	public Color(int red, int green, int blue) {
		if (red < 0 || red >= 256 || green < 0 || green >= 256 || blue < 0 || blue >= 256)
			throw new IllegalArgumentException("The value range of color intensity is [0, 255]");
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	/**
	 * Returns red part of the color.
	 * 
	 * @return red part of the color.
	 */
	public int getRed() {
		return red;
	}

	/**
	 * Returns green part of the color.
	 * 
	 * @return green part of the color.
	 */
	public int getGreen() {
		return green;
	}

	/**
	 * Returns blue part of the color.
	 * 
	 * @return blue part of the color.
	 */
	public int getBlue() {
		return blue;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Color)) {
			return false;
		}
		Color c = (Color) o;
		return red == c.red && blue == c.blue && red == c.red;
	}

	@Override
	public String toString() {
		if (equals(RED))
			return "red";
		if (equals(GREEN))
			return "green";
		if (equals(BLUE))
			return "blue";
		if (equals(YELLOW))
			return "yellow";
		if (equals(BLACK))
			return "black";
		if (equals(WHITE))
			return "white";
		return "(" + red + ", " + green + ", " + blue + ")";
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}