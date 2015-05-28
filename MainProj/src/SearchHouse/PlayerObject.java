package SearchHouse;

public class PlayerObject {//Represents an object that can be interacted with by the user
    private float x, y, z, size;
    protected boolean wasFound = false;//Has the user found this yet?
    protected boolean isDisplayed = true;//Should the object be displayed?
    public float r,g,b;//color of object

    public boolean isDisplayed() {
        return isDisplayed;
    }

    public PlayerObject(float xp, float yp, float zp, float r, float g, float b, float size) {
        x = xp;
        y = yp;
        z = zp;
        this.size = size;//size of cube that represents the object
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

    public boolean isFound() {//If the user has picked up the object
        return wasFound;
    }

    public float getSize() {//graphic scaling of object

        return size;
    }

    public void find(PlayerLogic l) {
    }
}
