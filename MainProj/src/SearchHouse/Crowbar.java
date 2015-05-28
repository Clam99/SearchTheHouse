package SearchHouse;

public class Crowbar extends PlayerObject {//The crowbar is the last object to find, and once found, the game is over.
    TargetDelegate target;

    public Crowbar(float xp, float yp, float zp, float size, TargetDelegate t) {
        super(xp,yp,zp,0,0,0,size);
        target = t;
    }
    
    public void find(PlayerLogic l) {
        wasFound = true;
        isDisplayed = false;
        target.receiveAction("end"); //sends info to SearchHouse.TestWalker
    }
}
