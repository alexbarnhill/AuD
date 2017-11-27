class Vec3 {
 float x, y, z;

 void add(Vec3 b) {
   x += b.x;
   y += b.y;
   z += b.z;
   // *** 2 ***
 }

 float sum() {
   float sum = x + y + z;
   // *** 3 ***
   return sum;
 }
}