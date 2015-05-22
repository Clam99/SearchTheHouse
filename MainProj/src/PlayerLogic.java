/**
 * Created by samnoyes on 5/20/15.
 */
public class PlayerLogic {
    float vx,vy,x,y;

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
        x+=vx;
        y+=vy;
        vy*=.99;//friction/resistance
        vx*=.99;
    }
}
