public class Ausnehmend {
	public static void fa(int va, int vb) throws Ball {
		 try {
			 System.out.println("fa : 0 .");
			 if (va > vb) {
				 System.out.println("fa: 1.");
				 throw new Discus("");
			 } else {
				 System.out.println("fa : 2. ");
				 fb(Math.random() < 0.5 ? null : Math.random() < 0.5 ? "" : "Frisbee");
				 System.out.println("fa: 3.");
			 }
		 } catch (Discus discus) {
			 System.out.println(" fa: E0 − " + discus.getClass().getName());
			 throw discus;
		 } catch (Frisbee frisbee) {
			 System.out.println("fa: E1 − " + frisbee.getClass().getName());
			 throw frisbee;
		 } catch (Ball ball) {
			 System.out.println(" fa : E2 − " + ball.getClass().getName());
		 } finally {
			 System.out.println(" fa : F");
		 }
	}

	 public static void fb(String va) throws Frisbee, Ball {
		 try {
			 System.out.println("fb : 0 .");
			 if (va.length() > 0) {
				 System.out.println(" fb : 1 . ");
				 throw new Ball();
			 } else {
				 System.out.println("fb : 2 . ");
				 fa((int)(100 * Math.random()), (int)(100 * Math.random()));
	    	System.out.println("fb : 3 . ");
			 }
		 } finally {
			 System.out.println(" fb : F ");
		 }
	 }

 	public static void main(String[] args) {
 		try {
 			System.out.println("m: 0.");
 			int va = Integer.parseInt(args[0]), vb = Integer.parseInt(args[1]);
 			fa(va, vb);
 			System.out.println("m: 1.");
		 } catch (Ball ball) {
			 System.out.println("m: E0 − " + ball.getClass().getName());
		 } finally {
			 System.out.println("m: F ");
		 }
 	}
}

class Frisbee extends RuntimeException {}

class Discus extends Frisbee {
	public Discus(int va) throws Frisbee, Ball {}

	public Discus(String va) throws Frisbee, Ball {
		throw Math.random() < 0.5 ? new Discus(Math.random() < 0.5 ? 1 : 2) : new Frisbee();
	}
}

class Ball extends Throwable {}