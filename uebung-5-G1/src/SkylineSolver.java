import java.util.Arrays;

public class SkylineSolver {
	
	public static int area(int[] skyline) {
		int totalArea = 0;
		for(int i = 0; i < skyline.length - 2; i += 2) {
			int width = skyline[i + 2] - skyline[i];
			int height = skyline[i+1];
			totalArea += width * height;
		}
		return totalArea;
	}
	
	public static int[][] divide(int[][] orig, int num, boolean isLeft) {
		
		if(isLeft == true) {
			int[][] left = new int[num][3];
			for(int i = 0; i < num; i++) {
				left[i][0] = orig[i][0];
				left[i][1] = orig[i][1];
				left[i][2] = orig[i][2];
			}
			
			return left;
		} else {
			int[][] right = new int[orig.length - num][3];
			int counter = 0;
			for(int i = num; i < orig.length; i++) {
				right[counter][0] = orig[i][0];
				right[counter][1] = orig[i][1];
				right[counter][2] = orig[i][2];
				counter++;
			}
			
			return right;
		}
	}
	
	private static int[] copyAndEnd(int[] array) {
		int[] ended = new int[array.length + 1];
		for(int i = 0; i < array.length; i++) {
			ended[i] = array[i];
		}
		ended[ended.length - 1] = 0;
		return ended;
	}
	
	public static int[] conquer(SkylineSolverHelper ssh, int[][] b) {
		if(b.length == 1) {
			return copyAndEnd(b[0]);
		}
		if(b.length == 2) {
			return ssh.merge(copyAndEnd(b[0]), copyAndEnd(b[1]) );
		} else {
			int firstHalf = 0;
			int secondHalf = 0;
			if(b.length % 2 == 0) {
				firstHalf = b.length / 2;
				secondHalf = b.length / 2;
			} else {
				firstHalf = b.length + 1 / 2;
				secondHalf = b.length - firstHalf;
			}
			int[][] one = divide(b, firstHalf, true);
			int[][] two = divide(b, secondHalf, false);
			int[] result = ssh.merge(conquer(ssh, one), conquer(ssh, two));
			return result;
		}
	}
}
