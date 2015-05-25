/**
 * Created by smurphy on 5/24/15.
 */
public class Vector {
    private float x,y,z;
    public Vector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public float getMagnitude() {
        return (float)Math.sqrt(x*x+y*y*z*z);
    }
    public Vector projectOnto(Vector v) {
        return multiplyVector(dotProduct(v)/(getMagnitude()*getMagnitude()));
    }
    public float dotProduct(Vector v) {
        return a*x+b*y+c*z;
    }
    public Vector multiplyVector(float a) {
        return new Vector(x*a,y*a,z*a);
    }
    public Vector divideVector(float a) {
        return new Vector(x / a, y / a, z / a);
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getZ() {
        return z;
    }
}
