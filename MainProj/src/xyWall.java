import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

public class xyWall implements Wall{
	
	private float x,y,posx,posz;
	private boolean door;
	
	public xyWall(float width, float height, float posx, float posz, boolean door){
		x=width;
		y=height;
		this.door=door;
		this.posx=posx;
		this.posz=posz;
	}
	
	public void draw() {
		
		if(!door){
		glBegin(GL_QUADS);
		glVertex3f(posx, 0f, posz);
		glVertex3f(posx, y, posz);
		glVertex3f(posx+x, y, posz);
		glVertex3f(posx+x, 0f, posz);
		}
		else{
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
		if ((cx>=posx && cx<=(posx+x)) && cz>=(posz-1) && cz<=posz+1) {
			if (door && cx>(posx+x/3) && cx<(posx+2*x/3)) return false;
			else return true;
		}
		else return false;
	}
    @Override
     public float getPosx() {
        return posx;
    }

    @Override
    public float getPosz() {
        return posz;
    }

    @Override
    public Vector getSurfaceVector() {
        return new Vector(1,0,0);
    }
}

