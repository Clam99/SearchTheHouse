/**
 * Created by smurphy on 5/26/15.
 */
public class Teleporter extends PlayerObject {
    float tx,ty,tz;

    public Teleporter(float xp, float yp, float zp, float tx, float ty, float tz, float size) {
        super(xp, yp, zp, size);
        this.tx = tx;
        this.ty = ty;
        this.tz = tz;
    }

    public void find(PlayerLogic l) {
        //super.find(l);
        l.setX(tx);
        l.setZ(tz);
    }
}
