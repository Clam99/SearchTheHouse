package SearchHouse;

public class Keys extends DisappearingObject {   //A Key object unlocks a wall (by adding a door to it) when it is collected
    Wall wall;//The wall to unlock
    public Keys(Wall wall, float xp, float yp, float zp, float size) {
        super(xp,yp,zp,size);
        this.wall = wall;
        isDisplayed = true;
        this.wall.close();
    }

    public void find(PlayerLogic l) {
        super.find(l);
        wall.open();
    }
}
