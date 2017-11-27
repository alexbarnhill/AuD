import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Matrix3x3MainTestGUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 480;
	private static final int HEIGHT = 480;
	private static final float CAM_Z = -5;
	private static final int STEP_TIME = 25;
	private static final float STEP_ALPHA = (float) (Math.PI / 128.0);
	private static final float STEP_BETA = STEP_ALPHA / 2;

	// =========================================================================
	private static final class Box {
		private final Vector3D[] vertices = { //
				new Vector3D(-1, -1, +1), new Vector3D(+1, -1, +1), new Vector3D(+1, +1, +1), new Vector3D(-1, +1, +1), // 4 "front" vertices
				new Vector3D(-1, -1, -1), new Vector3D(+1, -1, -1), new Vector3D(+1, +1, -1), new Vector3D(-1, +1, -1) // 4 "back" vertices
		};
		private final int[] edges = { //
				0, 1, 1, 2, 2, 3, 3, 0, // 4 "front" edges
				4, 5, 5, 6, 6, 7, 7, 4, // 4 "back" edges
				0, 4, 1, 5, 2, 6, 3, 7// 4 edges between "front" and "back"
		};

		private Box() {
		}

		private void draw(Graphics g, Matrix3x3 mt) {
			Vector3D[] tv = new Vector3D[vertices.length];
			for (int i = 0; i < vertices.length; ++i) {
				tv[i] = mt.mulVec(vertices[i]);
			}
			for (int i = 0; i < edges.length - 1;) {
				drawLine(g, tv[edges[i++]], tv[edges[i++]]);
			}
		}

		private void drawLine(Graphics g, Vector3D a, Vector3D b) {
			Vector3D ta = new Vector3D(a);
			Vector3D tb = new Vector3D(b);
			ta.set(2, ta.get(2) - CAM_Z);
			ta.set(1, HEIGHT * ta.get(1) / ta.get(2) + HEIGHT / 2);
			ta.set(0, WIDTH * ta.get(0) / ta.get(2) + WIDTH / 2);
			tb.set(2, tb.get(2) - CAM_Z);
			tb.set(1, HEIGHT * tb.get(1) / tb.get(2) + HEIGHT / 2);
			tb.set(0, WIDTH * tb.get(0) / tb.get(2) + WIDTH / 2);
			g.drawLine((int) ta.get(0), (int) ta.get(1), (int) tb.get(0), (int) tb.get(1));
		}
	}

	// =========================================================================

	private final Box box = new Box();
	private float alpha = 0;
	private float beta = 0;

	// =========================================================================

	private Matrix3x3MainTestGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.WHITE);
		setTitle("AuD is fun!");
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Matrix3x3 mt = new Matrix3x3();
		mt.toIdentity();
		Matrix3x3 rotX = new Matrix3x3();
		rotX.setRotX(alpha);
		Matrix3x3 rotY = new Matrix3x3();
		rotY.setRotY(beta);
		mt.mutMul(rotY);
		mt.mutMul(rotX);
		box.draw(g, mt);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		alpha += STEP_ALPHA;
		if (alpha > 2 * Math.PI) {
			alpha -= 2 * Math.PI;
		}
		beta += STEP_BETA;
		if (beta > 2 * Math.PI) {
			beta -= 2 * Math.PI;
		}
		repaint();
	}

	// =========================================================================

	public static void main(String arg[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable t) {
			// ok - ugly is fine too...
		}
		final Matrix3x3MainTestGUI frame = new Matrix3x3MainTestGUI();
		final Timer timer = new Timer(STEP_TIME, frame);
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			public void uncaughtException(Thread t, Throwable e) {
				timer.stop();
				e.printStackTrace();
				System.exit(-1);
			}
		});
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		timer.start();
	}
}