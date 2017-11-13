public class FunctionPlotter {
	public static char[][] plottingArea;

	/**
	 * Initializes the plottingArea. After this method, plottingArea will have width lines and height columns and will be filled with space characters.
	 *
	 * Note that when printing the plottingArea with the printPlottingArea method below: plottingArea[0][0] will end up on the bottom left, plottingArea[width][0] will end up on the bottom right, plottingArea[0][height] will end up on the top left, plottingArea[width][height] will end up on the top right of the screen.
	 *
	 * @param width
	 *            The width of the new plottingArea
	 * @param height
	 *            the height of the new plottingArea.
	 */
	public static void newPlottingArea(int width, int height) {
		plottingArea = new char[width][height];
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				plottingArea[i][j] = ' ';
			}
		}
		
	}

	/**
	 * Plots the character c into the plottingArea, at the specified position.
	 * 
	 * If the coordinates are outside the plottingArea, this function does nothing.
	 * 
	 * @param x
	 *            x coordinate of the specified position
	 * @param y
	 *            y coordinate of the specified position
	 * @param c
	 *            the character to plot
	 * @return true if the character was placed, false otherwise
	 */
	public static boolean plotChar(int x, int y, char c) {
		boolean success = false;
		if((x < plottingArea.length) && (y < plottingArea[0].length)) {
			plottingArea[x][y] = c;
			success = true;
		}
		
		
		return success;
	}

	/**
	 * Draws a horizontal line from (xStart, y) to (xEnd, y) (both end points included), using the character c.
	 * 
	 * @param xStart
	 *            x coordinate of start point of line.
	 * @param xEnd
	 *            x coordinate of end point of line
	 * @param y
	 *            y coordinate of line
	 * @param c
	 *            character to draw the line with
	 */
	public static void plotHorizontalLine(int xStart, int xEnd, int y, char c) {
		for(int i = xStart; i <= xEnd; i++) {
			plottingArea[i][y] = c;
		}
		
	}

	/**
	 * Draws a vertical line from (x, yStart) to (x, yEnd) (both end points included), using the character c.
	 * 
	 * @param x
	 *            x coordinate of line
	 * @param yStart
	 *            y coordinate of start point of line.
	 * @param yEnd
	 *            y coordinate of end point of line
	 * @param c
	 *            character to draw the line with
	 */
	public static void plotVerticalLine(int x, int yStart, int yEnd, char c) {
		for(int i = yStart; i <= yEnd; i++) {
			plottingArea[x][i] = c;
		}
	}

	/**
	 * Plots a box such that (xStart, yStart) is the lower left corner of the box, and (xEnd, yEnd) is the upper right corner of the box.
	 * 
	 * The vertical lines of the box are drawn using '|', the horizontal lines are drawn using '-', and the corners are drawn using '+'.
	 * 
	 * @param xStart
	 *            x coordinate of the lower left corner of the box
	 * @param yStart
	 *            y coordinate of the lower left corner of the box
	 * @param xEnd
	 *            x coordinate of the upper right corner of the box
	 * @param yEnd
	 *            y coordinate of the upper right corner of the box
	 */
	public static void plotBox(int xStart, int yStart, int xEnd, int yEnd) {
		for(int x = xStart; x <= xEnd; x++) {
			if(x == xStart || x == xEnd) {
				plottingArea[x][yStart] = '+';
				plottingArea[x][yEnd] = '+';
			} else {
				plottingArea[x][yStart] = '-';
				plottingArea[x][yEnd] = '-';
			}
		}
		for(int y = yStart + 1; y < yEnd; y++) {
			plottingArea[xStart][y] = '|';
			plottingArea[xEnd][y] = '|';
		}
		
	}

	/**
	 * Writes a string to the plottingArea. The first character of the string is put to the coordinates (x, y), the rest of the string is written horizontally starting from there and from left to right.
	 * 
	 * @param x
	 *            x coordinate of the start of the string
	 * @param y
	 *            y coordinate of the start of the string
	 * @param s
	 *            the string to write
	 */
	public static void plotString(int x, int y, String s) {
		for(int i = 0; i < s.length(); i++) {
			plottingArea[x+i][y] = s.charAt(i);
		}
		
		
	}

	/**
	 * This is the function you are supposed to plot
	 * 
	 * @param x
	 *            the function's parameter
	 * @return some integer...
	 */
	public static int functionToPlot(int x) {
		//return (int) Math.round(Math.pow(x, 2) * Math.sin(x));
		return (int) Math.round(10 * Math.sin(0.3 * x));
	}

	/**
	 * This function plots the function "functionToPlot" to the plottingArea. For each x between xStart and xEnd (included), it calculates y = functionToPlot(x).
	 * 
	 * As the origin of the coordinate system of the plottingArea is not the same as the origin of the array in the plottingArea itself, this function also requires the coordinates of the origin of the plottingArea.
	 * 
	 * @param xStart
	 *            the start of the interval of integers for which we plot function values
	 * @param xEnd
	 *            the end of the interval of integers for which we plot function values
	 * @param xOrigin
	 *            x coordinate of the origin of the coordinate system in the plottingArea
	 * @param yOrigin
	 *            y coordinate of the origin of the coordinate system in the plottingArea
	 */
	public static void plotFunction(int xStart, int xEnd, int xOrigin, int yOrigin) {
		int fx = 0;
		for(int x = xStart; x <= xEnd; x++) {
			fx = functionToPlot(x);
			plottingArea[xOrigin + x][yOrigin + fx] = '.';
		}
	}

	/**
	 * This function prints the contents of the plottingArea to the standard output.
	 */
	public static void printPlottingArea() {
		for (int y = plottingArea[0].length - 1; y >= 0; y--) {
			for (int x = 0; x < plottingArea.length; x++) {
				System.out.print(plottingArea[x][y]);
			}
			System.out.println();
		}
	}
}