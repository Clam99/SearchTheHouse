/**
 * Created by smurphy on 5/26/15.
 */
public class PlayerObject {
    private float x,y,z;
    private float size;
    protected boolean wasFound = false;
    protected boolean isDisplayed = true;

    public boolean isDisplayed() {
        return isDisplayed;
    }

    public PlayerObject(float xp, float yp, float zp, float size) {
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

    public float getSize() {

        return size;
    }

    public void find(PlayerLogic l) {
    }
}
