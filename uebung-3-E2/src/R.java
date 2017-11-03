public class R {
	public static int[] r(int[] content) {
		int length = content.length;
		int[] result = new int[length];
		int cur = 0;
		while (cur < length) {
			int ra = 0;
			int j = 0; //length == 3 ---- j == 1; cur == 0
			// 2) j == 2
			while (j < length) {
				if (j == cur) {
					j = j + 1; // j == 1
					continue;
				}
				//content[j] == 2, content[cur] == 5
				//content[j] == 3, content[cur] == 5
				if (content[j] < content[cur]) {
					ra = ra + 1;
					//ra == 1
					//ra == 2
				}
				// 1) cur == 0; j == 1; ra == 1; result == []
				// 2) cur == 0; j == 2; ra == 2; result == []
				// 1) --> cur j ra result <--
				j = j + 1;
			}
			
			result[cur] = ra;
			cur = cur + 1;
			// 2) cur == 1; j == 2; ra == 2; result == [2]
			// 2) --> cur j ra result <--
		}
		return result;
	}

	public static void main(String[] args) {
		int[] test = { 5, 2, 3 };
		int[] result = r(test);
		for (int i = 0; i < test.length; i++) {
			System.out.println(test[i] + " " + result[i]);
		}
	}
}