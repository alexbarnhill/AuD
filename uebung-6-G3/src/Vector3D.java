public class Vector3D {
	private double x;
	private double y;
	private double z;

	Vector3D() {
		x = 0;
		y = 0;
		z = 0;
	}

	Vector3D(double x, double y, double z) {
		setAll(x, y, z);
	}

	Vector3D(Vector3D v) {
		setAll(v.x, v.y, v.z);
	}

	void setAll(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	void add(Vector3D other) {
		this.x += other.x;
		this.y += other.y;
		this.z += other.z;
	}

	void mul(Vector3D other) {
		this.x *= other.x;
		this.y *= other.y;
		this.z *= other.z;
	}

	double get(int i) {
		switch (i) {
		case 0:
			return x;
		case 1:
			return y;
		case 2:
			return z;
		default:
			throw new IndexOutOfBoundsException();
		}
	}

	void set(int i, double value) {
		switch (i) {
		case 0:
			x = value;
			break;
		case 1:
			y = value;
			break;
		case 2:
			z = value;
			break;
		default:
			throw new IndexOutOfBoundsException();
		}
	}
}