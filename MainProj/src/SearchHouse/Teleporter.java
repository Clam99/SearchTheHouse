package SearchHouse;

public class Teleporter extends PlayerObject {//Move the player from (xp,y,zp) to (tx, y, tz)
    private float tx,tz;

    public Teleporter(float xp, float zp, float tx, float tz, float size) {
        super(xp, 0, zp, 0, 0, 1, size);
        this.tx = tx;
        this.tz = tz;
    }

    public void find(PlayerLogic l) {
        //super.find(l);
        l.setX(tx);
        l.setZ(tz);
    }

    public float getTx() {
        return tx;
    }

    public float getTz() {
        return tz;
    }
}
