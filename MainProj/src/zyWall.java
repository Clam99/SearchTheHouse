import static org.lwjgl.opengl.GL11.*;

public class zyWall implements Wall {
	
	private float z,y,posx,posz;
	private boolean door;
	public zyWall(float width, float height, float posx, float posz, boolean door){
		z=width;
		y=height;
		this.door=door;
		this.posx=posx;
		this.posz=posz;
	}
	
	public float getPosX() {
		return posx;
	}
	
	public float getPosZ() {
		return posz;
	}
	
	public void open() {
		door=true;
	}
	
	public void draw() {
	
		if(!door){
			glBegin(GL_QUADS);
			glVertex3f(posx, 0f, posz);
			glVertex3f(posx, y, posz);
			glVertex3f(posx, y, posz+z);
			glVertex3f(posx, 0f, posz+z);
			}
			else{
			glBegin(GL_QUADS);
			glVertex3f(posx, 0f, posz);
			glVertex3f(posx, y, posz);
			glVertex3f(posx, y, posz+z/3);
			glVertex3f(posx, 0, posz+z/3);
			glEnd();
			glBegin(GL_QUADS);
			glVertex3f(posx, y, posz+z/3);
			glVertex3f(posx, y, posz+2*z/3);
			glVertex3f(posx, 3*y/4, posz+2*z/3);
			glVertex3f(posx, 3*y/4, posz+z/3);
			glEnd();
			glBegin(GL_QUADS);
			glVertex3f(posx, y, posz+2*z/3);
			glVertex3f(posx, y, posz+z);
			glVertex3f(posx, 0, posz+z);
			glVertex3f(posx, 0, posz+2*z/3);
			}
			glEnd();
	}
		
	public boolean testCollision(float cx, float cz) {
		if ((cz>=posz && cz<=(posz+z)) && cx<=posx+.5 && cx>posx-.5) {
			if (door && cz>(posz+z/3) && cz<(posz+2*z/3)) return false;
			else return true;
		}
		else return false;
	}

	@Override
	public Vector getSurfaceVector() {
		return new Vector(0,0,1);
	}

	@Override
	public Vector getNormalVector() {
		return new Vector(1,0,0);
	}
}





