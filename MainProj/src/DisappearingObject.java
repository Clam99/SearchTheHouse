public class DisappearingObject extends PlayerObject {
	public final float r=1, g=1, b=0;

    public DisappearingObject(float xp, float yp, float zp, float size) {
        super(xp,yp,zp,1,1,0,size);
    }

    public void find(PlayerLogic l) {
        wasFound = true;
        isDisplayed = false;
    }
}
