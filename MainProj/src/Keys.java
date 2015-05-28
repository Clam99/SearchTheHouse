public class Keys extends DisappearingObject {
    Wall wall;
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
