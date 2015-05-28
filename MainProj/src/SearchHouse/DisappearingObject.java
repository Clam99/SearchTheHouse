package SearchHouse;

public class DisappearingObject extends PlayerObject {//This object disappears when the user interacts with it such as the SearchHouse.Crowbar and the SearchHouse.Keys.

    public DisappearingObject(float xp, float yp, float zp, float size) {
        super(xp,yp,zp,1,1,0,size); //construct PlayerObjet with certain color
    }

    public void find(PlayerLogic l) {
        wasFound = true;
        isDisplayed = false;
    }
}
