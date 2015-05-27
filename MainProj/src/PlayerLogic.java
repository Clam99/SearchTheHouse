package lwgl2;

import org.lwjgl.input.Keyboard;

/**
 * Created by samnoyes on 5/20/15.
 */
public class PlayerLogic {
    private float vx,vy,vz, x,y,z;//coordinates of the player
    private double rotate=Math.PI,viewAngle;
    private boolean didPrint = false;
    protected boolean wasFound = false;
    protected boolean isDisplayed = true;

    public PlayerLogic() {
        y = 1.5f;
        x = 12.5f;
        z = 15.5f;
        vx = 0;
        vy = 0;
    }

    public void setVy(float y) {
        vy=y;
    }
    public float getVy() {
        return vy;
    }
    public void setVx(float x) {
        vx=x;
    }
    public float getVx() {
        return vx;
    }
    public void setVz(float x) {
        vz=x;
    }
    public float getVz() {
        return vz;
    }
    public void setX(float x) {
        this.x=x;
    }
    public void setY(float y) {
        this.y=y;
    }
    public void setZ(float z) {
        this.z=z;
    }
    public void setPos(float x, float y, float z) {
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public void updatePosition() {
        x+=vx*Math.cos(getRotation());
        z-=vx*Math.sin(getRotation());

        x+=vz*Math.sin(getRotation());
        z+=vz*Math.cos(getRotation());

        y+=vy;
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

    public void setRotation(double r) {
        rotate = r;
    }
    public double getRotation() {
        return rotate;
    }
    public double getViewAngle() {
        return viewAngle;
    }
    public void setViewAngle(double v) {
        viewAngle = v;
    }
    public boolean isLookingAt(float a, float b, float c) {
        boolean closeEnough = isWithinUnitsOf(3,new Vector(a,b,c),new Vector(x,y,z));
        Vector toObj = new Vector(a,b,c).subtractVector(new Vector(x,y,z));
        boolean isFacing = isWithinUnitsOf(2,   new Vector(x,y,z).addVector(toObj.projectOnto(getFacingVector()))   ,   new Vector(a,b,c));

        if (Keyboard.isKeyDown(Keyboard.KEY_0) && !didPrint) {
            System.out.println("Current Location: " + new Vector(x,y,z));
            System.out.println("Object Location: " + new Vector(a,b,c));
            System.out.println("Vector from camera to object: " + toObj);
            System.out.println("Looking at vector: " + getFacingVector());
            System.out.println("Projected vector: " + toObj.projectOnto(getFacingVector()));
            System.out.println();
            didPrint = true;
        }
        else if (!Keyboard.isKeyDown(Keyboard.KEY_0)) {
            didPrint = false;
        }

        return isFacing && closeEnough;
    }
    public boolean isWithinUnitsOf(float units, Vector v, Vector v2) {
        return (v.subtractVector(v2).getMagnitude()<units);

    }
    public Vector getFacingVector() {//this definitely works
        return new Vector((float)Math.sin(getRotation()),(float)Math.tan(getViewAngle()),(float)Math.cos(getRotation()));

    }
}
