package SearchHouse;

public class PlayerLogic {//Keeps track of the game's logic
    private float vx,vy,vz, x,y,z;//coordinates of the player and movement velocities.
                                  //NOTE:vx if really just the side to side movement and vz is the forward movement.
                                  //They are not necessarily in the x or z direction since it depends on the user's rotation.

    private double rotate=Math.PI,viewAngle;//rotate is the amount that the user is currently rotated about the y axis, and viewAngle is the amount that the user is rotated up and down

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
    public void updatePosition() {//Move the player based on velocities
        //Calculate how to move the player based on his forward and side to side movement and rotation
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
    public boolean isLookingAt(float a, float b, float c) {//Check if user is close to and is facing a certain point
        boolean closeEnough = isWithinUnitsOf(3,new Vector(a,b,c),new Vector(x,y,z));
        Vector toObj = new Vector(a,b,c).subtractVector(new Vector(x,y,z));
        boolean isFacing = isWithinUnitsOf(2,   new Vector(x,y,z).addVector(toObj.projectOnto(getFacingVector()))   ,   new Vector(a,b,c));
        return isFacing && closeEnough;
    }
    public boolean isWithinUnitsOf(float units, Vector v, Vector v2) {//The Vectors represent points
        return (v.subtractVector(v2).getMagnitude()<units);

    }
    public Vector getFacingVector() {//get vector of where the user is facing
        return new Vector((float)Math.sin(getRotation()),(float)Math.tan(getViewAngle()),(float)Math.cos(getRotation()));

    }
}
