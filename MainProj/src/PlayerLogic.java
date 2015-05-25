/**
 * Created by samnoyes on 5/20/15.
 */
public class PlayerLogic {
    private float vx,vy,vz, x,y,z;//coordinates of the player
    private double rotate,viewAngle;

    public PlayerLogic() {
        y = 5;
        x = 0;
        z = 0;
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
    public void updatePosition() {
        x+=vx*Math.cos(getRotation());
        z-=vx*Math.sin(getRotation());

        x+=vz*Math.sin(getRotation());
        z+=vz*Math.cos(getRotation());

        y+=vy;

        vy = 0;
        vx = 0;
        vz = 0;
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
        boolean closeEnough = isWithinUnitsOf(20,new Vector(a,b,c),new Vector(x,y,z));
        Vector toObj = new Vector(a-x,b-y,c-z);
        boolean isFacing = isWithinUnitsOf(5,new Vector(x,y,z).addVector(toObj.projectOnto(getFacingVector())),new Vector(x,y,z).addVector(toObj));
        return closeEnough && isFacing;
    }
    public boolean isWithinUnitsOf(float units, Vector v, Vector v2) {
        return (Math.sqrt((v.getX()-v2.getX())*(v.getX()-v2.getX())+(v.getY()-v2.getY())*(v.getY()-v2.getY())+(v.getZ()-v2.getZ())*(v.getZ()-v2.getZ()))<units);
    }
    public Vector getFacingVector() {
        return new Vector((float)Math.sin(getRotation()),(float)Math.sin(viewAngle),(float)Math.cos(getRotation()));
    }
}
