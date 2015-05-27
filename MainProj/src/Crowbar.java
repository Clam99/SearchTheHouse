public class Crowbar extends PlayerObject {
    public Crowbar(float xp, float yp, float zp, float size) {
        super(xp,yp,zp,0,0,0,size);
    }
    
    public boolean find(PlayerLogic l) {
        wasFound = true;
        isDisplayed = false;
        return true;
    }
}
