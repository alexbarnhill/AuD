
// ***** DO NOT USE, CHANGE OR TRY TO UNDERSTAND THE CODE BELOW ***** //
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * <p>
 * This class contains the front-end (GUI) for gem puzzles.
 * </p>
 * <p>
 * For a general description of the game please refer to: <a href="http://en.wikipedia.org/wiki/15_puzzle">Gem Puzzle</a>
 * </p>
 * 
 * @author John Doe
 * @version 1.1, 12/08/17
 */
public final class GemPuzzle {
	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable t) {
		}
	}

	private GemPuzzle() {
	}

	/**
	 * Let the games begin (here)!
	 * 
	 * @param args
	 *            command line arguments, possibly denoting the size of the puzzle
	 *            <ul>
	 *            <li>if more than one argument is passed, then the first is expected to be the height and the second to be the width of the gem puzzle</li>
	 *            <li>if exactly one argument is passed, then it is expected to be both width and height (equal) of the puzzle</li>
	 *            <li>if no argument is passed or the values are outside legal ranges, then the size of the puzzle defaults to 3x3</li>
	 *            </ul>
	 */
	public static void main(String[] args) {
		new GemPuzzleInner(args);
	}

	private static final class GemPuzzleInner extends JFrame implements ActionListener, KeyListener, Runnable {
		private static final long serialVersionUID = 666L;
		private static final String FRAME_TITLE = "AuD - Gem Puzzle";
		private static final Border RAISED = new EtchedBorder(EtchedBorder.RAISED);
		private static final Border LOWERED = new EtchedBorder(EtchedBorder.LOWERED);
		private static final Color OK = new Color(255, 224, 224);
		private static final Color FAIL = new Color(224, 255, 224);

		private int rows, cols, moves;
		private int[][] matrix;
		private JLabel[][] matrixGUI;
		private JPanel controlPanel;
		private JPanel matrixPanel;
		private JButton restart;
		private JButton hint;
		private JButton auto;
		private JButton quit;

		private GemPuzzleInner(String[] args) {
			super(FRAME_TITLE);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			try {
				rows = cols = 3;
				rows = Integer.parseInt(args[0]);
				if (rows < 2 || rows > 45) {
					rows = 3;
				}
				cols = rows;
				cols = Integer.parseInt(args[1]);
				if (cols < 2 || cols > 45) {
					cols = 3;
				}
			} catch (Throwable t) {
			}
			createGUI();
			startNewGame();
			pack();
			setVisible(true);
		}

		private void createGUI() {
			controlPanel = new JPanel();
			controlPanel.setLayout(new GridLayout(1, 4));
			restart = new JButton("New", UIManager.getIcon("FileView.fileIcon"));
			restart.setFocusable(false);
			restart.addActionListener(this);
			controlPanel.add(restart);
			hint = new JButton("Tip?", UIManager.getIcon("OptionPane.questionIcon"));
			hint.setFocusable(false);
			hint.addActionListener(this);
			controlPanel.add(hint);
			auto = new JButton("Auto!", UIManager.getIcon("FileView.computerIcon"));
			auto.setFocusable(false);
			auto.addActionListener(this);
			controlPanel.add(auto);
			quit = new JButton("Quit", UIManager.getIcon("OptionPane.errorIcon"));
			quit.setFocusable(false);
			quit.addActionListener(this);
			controlPanel.add(quit);
			matrixPanel = new JPanel();
			Font f;
			int width = 666, height = 666;
			if (rows < cols) {
				height = width * rows / cols;
			} else {
				width = height * cols / rows;
			}
			matrixPanel.setPreferredSize(new Dimension(width, height));
			matrixPanel.setLayout(new GridLayout(rows, cols));
			matrixGUI = new JLabel[rows][cols];
			for (int r = 0; r < rows; r++) {
				for (int c = 0; c < cols; c++) {
					matrixGUI[r][c] = new JLabel("", JLabel.CENTER);
					matrixGUI[r][c].setOpaque(true);
					f = matrixGUI[r][c].getFont();
					f = f.deriveFont(Font.BOLD, height / rows / 2);
					matrixGUI[r][c].setFont(f);
					matrixPanel.add(matrixGUI[r][c]);
				}
			}
			Box box = new Box(BoxLayout.Y_AXIS);
			box.add(controlPanel);
			box.add(matrixPanel);
			getContentPane().add(box);
			addKeyListener(this);
		}

		private void startNewGame() {
			moves = 0;
			matrix = GemPuzzleLogic.init(rows, cols);
			for (int i = 0; i < 10 * rows * cols; i++) {
				GemPuzzleLogic.shuffle(matrix);
			}
			paintMatrix();
		}

		private void paintMatrix() {
			for (int r = 0; r < rows; r++) {
				for (int c = 0; c < cols; c++) {
					if (matrix[r][c] != 0) {
						matrixGUI[r][c].setText("" + matrix[r][c]);
						if (matrix[r][c] == (r * cols + c + 1)) {
							matrixGUI[r][c].setBackground(FAIL);
						} else {
							matrixGUI[r][c].setBackground(OK);
						}
						matrixGUI[r][c].setBorder(LOWERED);
					} else {
						matrixGUI[r][c].setText("");
						matrixGUI[r][c].setBackground(Color.DARK_GRAY);
						matrixGUI[r][c].setBorder(RAISED);
					}
				}
			}
			getRootPane().invalidate();
			setTitle(FRAME_TITLE + " - moves: " + moves);
		}

		@Override
		public synchronized void actionPerformed(ActionEvent e) {
			if (e != null && e.getSource() == restart) {
				startNewGame();
			} else if (e != null && e.getSource() == hint) {
				restart.setEnabled(false);
				hint.setEnabled(false);
				auto.setEnabled(false);
				quit.setEnabled(false);
				Direction[] directions = GemPuzzleLogic.autoComplete(matrix);
				if (directions.length > 0) {
					move(directions[0]);
				}
				restart.setEnabled(true);
				hint.setEnabled(true);
				auto.setEnabled(true);
				quit.setEnabled(true);
			} else if (e != null && e.getSource() == auto) {
				restart.setEnabled(false);
				hint.setEnabled(false);
				auto.setEnabled(false);
				quit.setEnabled(false);
				new Thread(this).start();
			} else if (e != null && e.getSource() == quit) {
				System.exit(0);
			}
		}

		@Override
		public synchronized void keyReleased(KeyEvent e) {
			if (!auto.isEnabled()) {
				return;
			}
			if (e != null && e.getSource() == this) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					move(Direction.UP);
					break;
				case KeyEvent.VK_DOWN:
					move(Direction.DOWN);
					break;
				case KeyEvent.VK_LEFT:
					move(Direction.LEFT);
					break;
				case KeyEvent.VK_RIGHT:
					move(Direction.RIGHT);
					break;
				}
				if (GemPuzzleLogic.isGameOver(matrix)) {
					JOptionPane.showMessageDialog(this, "Congratulations!\nYou solved this puzzle.", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
					startNewGame();
				}
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// just ignore
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// just ignore
		}

		@Override
		public void run() {
			Direction[] directions = GemPuzzleLogic.autoComplete(matrix);
			for (int d = 0; d < directions.length; d++) {
				try {
					Thread.sleep(d == 0 ? 0 : 666);
				} catch (Throwable t) {
				}
				move(directions[d]);
			}
			restart.setEnabled(true);
			hint.setEnabled(true);
			auto.setEnabled(true);
			quit.setEnabled(true);
		}

		private void move(Direction direction) {
			moves += GemPuzzleLogic.moveGap(matrix, direction) ? 1 : 0;
			paintMatrix();
		}
	}
}