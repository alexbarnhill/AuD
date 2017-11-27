public class Scope {
 static int m(int a, int b) {
   int sum = a + b;
   // *** 1 ***
   return sum;
 }

 public static void main(String args[]) {
   args = null;
   int sum = 0;
   for (int i = 0; i < 4; i++)
     sum += i;
     // *** 4 ***
     if (sum > 3) {
       int b = 3 * 7;
       sum = m(sum, b);
     // *** 5 ***
     }
     Vec3 p = new Vec3();
     p.x = p.y = -(p.z = 1);
     float x = p.x;
     // *** 6 ***
     Vec3 q = p;
     q.y = 2;
     // *** 7 ***
     q = new Vec3();
     q.x = q.y = q.z = 2;
     q.add(p);
     // *** 8 ***
  }
}