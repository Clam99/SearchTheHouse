package SearchHouse;

public class StringTeleporter extends Teleporter {//A teleporter that needs a code to be entered in order for it to work
    String code;
    TargetDelegate target;

    //this teleporter requires a password to work
    public StringTeleporter(float xp, float zp, float tx, float tz, float size, String code, TargetDelegate t) {
        super(xp, zp, tx, tz, size);
        this.code = code;
        target = t;
    }

    @Override
    public void find(PlayerLogic l) {
        target.receiveAction("enter string");
    }
}
