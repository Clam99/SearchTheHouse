/**
 * Created by smurphy on 5/26/15.
 */
public class Keys extends DisappearingObject {
    Wall wall;
    public Keys(Wall wall, float xp, float yp, float zp, float size) {
        super(xp,yp,zp,size);
        this.wall = wall;
        isDisplayed = true;
        this.wall.close();
    }

    @Override
    public void find() {
        super.find();
        wall.open();
    }
}
