package SearchHouse;

import static org.lwjgl.opengl.GL11.*;

public class xyWall implements Wall{ //wall parallel to xy-plane
	
	private float x,y,posx,posz;
	private boolean door;
	
	public xyWall(float width, float height, float posx, float posz, boolean door){
		x=width;
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
	
	public float getWidth() {
		return x;
	}
	
	public float getHeight() {
		return y;
	}
	
	public void open() {
		door=true;
	}
	
	public void close(){
		door=false;
	}
	
	public void addX(float x) {
		posx+=x;
	}
	
	public void addZ(float z) {
		posz+=z;
	}
	
	public void draw() {
		
		if(!door){//draw without door
		glBegin(GL_QUADS);
		glVertex3f(posx, 0f, posz);
		glVertex3f(posx, y, posz);
		glVertex3f(posx+x, y, posz);
		glVertex3f(posx+x, 0f, posz);
		}
		else{//draw with door
		glBegin(GL_QUADS);
		glVertex3f(posx, 0f, posz);
		glVertex3f(posx, y, posz);
		glVertex3f(posx+x/3, y, posz);
		glVertex3f(posx+x/3, 0, posz);
		glEnd();
		glBegin(GL_QUADS);
		glVertex3f(posx+x/3, y, posz);
		glVertex3f(posx+2*x/3, y, posz);
		glVertex3f(posx+2*x/3, 3*y/4, posz);
		glVertex3f(posx+x/3, 3*y/4, posz);
		glEnd();
		glBegin(GL_QUADS);
		glVertex3f(posx+2*x/3, y, posz);
		glVertex3f(posx+x, y, posz);
		glVertex3f(posx+x, 0, posz);
		glVertex3f(posx+2*x/3, 0, posz);
		}
		glEnd();
	}
	
	public boolean testCollision(float cx, float cz) {
		if ((cx>=posx && cx<=(posx+x)) && cz<=posz+.2 && cz>posz-.2) {
			if (door && cx>(posx+x/3) && cx<(posx+2*x/3)) return false;
			else return true;
		}
		else return false;
	}
	
	public xyWall copy() {
        return new xyWall(x,y,posx,posz,door);
    }
	
	@Override
	public Vector getSurfaceVector() {
		return new Vector(1,0,0);
	}

	@Override
	public Vector getNormalVector() {
		return new Vector(0,0,1);
	}
}
