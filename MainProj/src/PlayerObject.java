public class PlayerObject {
    private float x, y, z, size;
    protected boolean wasFound = false;
    protected boolean isDisplayed = true;
    public float r,g,b;

    public boolean isDisplayed() {
        return isDisplayed;
    }

    public PlayerObject(float xp, float yp, float zp, float r, float g, float b, float size) {
        x = xp;
        y = yp;
        z = zp;
        this.size = size;
        this.r=r;
        this.g=g;
        this.b=b;
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

    public boolean find(PlayerLogic l) {
    	return false;
    }
}
