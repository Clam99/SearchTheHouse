public class Teleporter extends PlayerObject {
    private float tx,tz;

    public Teleporter(float xp, float zp, float tx, float tz, float size) {
        super(xp, 0, zp, 0, 0, 1, size);
        this.tx = tx;
        this.tz = tz;
    }

    public boolean find(PlayerLogic l) {
        //super.find(l);
        l.setX(tx);
        l.setZ(tz);
        return false;
    }
}
