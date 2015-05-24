/**
 * Created by samnoyes on 5/20/15.
 */
public class PlayerLogic {
    private float vx,vy,x,y;
    private double rotate,viewAngle;

    public PlayerLogic() {
        y = 0;
        x = 0;
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
    public void updatePosition() {
        x+=vy*(float)Math.sin(getRotation());
        y+=vy*(float)Math.cos(getRotation());

        x+=vx*(float)Math.cos(getRotation());
        y-=vx*(float)Math.sin(getRotation());

        vy = 0;
        vx = 0;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
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
}
