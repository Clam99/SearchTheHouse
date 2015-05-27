/**
 * Created by smurphy on 5/26/15.
 */
public class DisappearingObject extends PlayerObject {

    public DisappearingObject(float xp, float yp, float zp, float size) {
        super(xp,yp,zp,size);
    }

    public void find() {
        wasFound = true;
        isDisplayed = false;
    }
}
