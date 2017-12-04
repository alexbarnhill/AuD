public class Matrix3x3 {
	Vector3D[] m = {new Vector3D(), new Vector3D(), new Vector3D()};
	
	public double get(int row, int col) {
		return m[col].get(row);
	}
	
	public void set(int row, int col, double entry) {
		m[col].set(row, entry);
	}
	
	public void toIdentity() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(i == j) {
					this.set(i, i, 1);
				} else {
					this.set(i, j, 0);
				}
			}
		}
	}
	
	public Matrix3x3 deepCopyFrom(Matrix3x3 q) {
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				this.set(i, j, q.get(i, j));
			}
		}
		
		return this;
	}
	
	public  void setRotX( double winkel) {
		toIdentity();
		set (1, 1, Math.cos(winkel));
		set (2, 1, Math.sin(winkel));
		set (1, 2, Math.sin(-winkel));
		set (2, 2, Math.cos(winkel));
		
		
		
	}
	
	
	public  void setRotY(double winkel) {
		toIdentity();
		set (0, 0, Math.cos(winkel));
		set (2, 0, Math.sin(-winkel));
		set (0, 2, Math.sin(winkel));
		set (2, 2, Math.cos(winkel));
		
		
		
		
	}
	
	public  void setRotZ(double winkel) {
		toIdentity();
		set (0, 0, Math.cos(winkel));
		set (1, 0, Math.sin(winkel));
		set (0, 1, Math.sin(-winkel));
		set (1, 1, Math.cos(winkel));
		
		
	}
	
	public Matrix3x3 immutMul(Matrix3x3 b) {
		Matrix3x3 mul = new Matrix3x3();
		mul.set(0, 0, ((this.get(0, 0) * b.get(0, 0)) + (this.get(0, 1) * b.get(1, 0)) + (this.get(0, 2) * b.get(2, 0))));
		mul.set(0, 1, ((this.get(0, 0) * b.get(0, 1)) + (this.get(0, 1) * b.get(1, 1)) + (this.get(0, 2) * b.get(2, 1))));
		mul.set(0, 2, ((this.get(0, 0) * b.get(0, 2)) + (this.get(0, 1) * b.get(1, 2)) + (this.get(0, 2) * b.get(2, 2))));

		mul.set(1, 0, ((this.get(1, 0) * b.get(0, 0)) + (this.get(1, 1) * b.get(1, 0)) + (this.get(1, 2) * b.get(2, 0))));
		mul.set(1, 1, ((this.get(1, 0) * b.get(0, 1)) + (this.get(1, 1) * b.get(1, 1)) + (this.get(1, 2) * b.get(2, 1))));
		mul.set(1, 2, ((this.get(1, 0) * b.get(0, 2)) + (this.get(1, 1) * b.get(1, 2)) + (this.get(1, 2) * b.get(2, 2))));
		
		mul.set(2, 0, ((this.get(2, 0) * b.get(0, 0)) + (this.get(2, 1) * b.get(1, 0)) + (this.get(2, 2) * b.get(2, 0))));
		mul.set(2, 1, ((this.get(2, 0) * b.get(0, 1)) + (this.get(2, 1) * b.get(1, 1)) + (this.get(2, 2) * b.get(2, 1))));
		mul.set(2, 2, ((this.get(2, 0) * b.get(0, 2)) + (this.get(2, 1) * b.get(1, 2)) + (this.get(2, 2) * b.get(2, 2))));
		return mul;
	}
	
	public void mutMul(Matrix3x3 b) {
		deepCopyFrom(immutMul(b));
	}
	
	public Vector3D mulVec(Vector3D vec) {
		Vector3D mul = new Vector3D();
		mul.set(0, (this.get(0, 0) * vec.get(0) + (this.get(0, 1) * vec.get(1)) + (this.get(0, 2) * vec.get(2))) );
		mul.set(1, (this.get(1, 0) * vec.get(0) + (this.get(1, 1) * vec.get(1)) + (this.get(1, 2) * vec.get(2))) );
		mul.set(2, (this.get(2, 0) * vec.get(0) + (this.get(2, 1) * vec.get(1)) + (this.get(2, 2) * vec.get(2))) );
		return mul;
	}
	
	
}
