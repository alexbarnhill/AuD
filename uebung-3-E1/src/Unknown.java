public class Unknown {
	public static int unknown(int value) {
  		int p = 10;
  		int d = 1;
  		while(p <= value) {
  			p *= 0xA;
  			d++;
  		}
  		
  		return d;
	}
}
