/**
 * Created by smurphy on 5/26/15.
 */
public class PlayerObject {
    private ObjectType type;
    private float x,y,z;
    private float size;
    private boolean wasFound = false;

    public PlayerObject(ObjectType t, float xp, float yp, float zp, float size) {
        type = t;
        x = xp;
        y = yp;
        z = zp;
        this.size = size;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public boolean isFound() {
        return wasFound;
    }

    public ObjectType getType() {
        return type;
    }

    public float getSize() {

        return size;
    }

    public void find() {
        wasFound = true;
    }
}
