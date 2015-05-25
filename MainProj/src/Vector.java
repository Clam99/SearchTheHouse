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
        return (float)Math.sqrt(x*x+y*y+z*z);
    }

    public Vector projectOnto(Vector v) {
        return v.scaleVector(dotProduct(v)/(v.getMagnitude()*v.getMagnitude()));
    }

    public float dotProduct(Vector v) {
        return v.getX()*x + v.getY()*y + v.getZ()*z;
    }

    public Vector scaleVector(float a) {
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
    public Vector addVector(Vector v) {
        return new Vector(x+v.getX(),y+v.getY(),z+v.getZ());
    }
    public Vector subtractVector(Vector v) { return new Vector(x-v.getX(),y-v.getY(),z-v.getZ()); }
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
