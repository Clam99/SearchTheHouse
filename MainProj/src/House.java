import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

public class House {

	public void start() {
        try {
	    Display.setDisplayMode(new DisplayMode(1000,700));
	    Display.create();
	} catch (LWJGLException e) {
	    e.printStackTrace();
	    System.exit(0);
		}
	
        
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	gluPerspective(45f, 10/7f, 3f, -3f);
	gluLookAt(7, 40, 10, 5, 0, 5, 0, 1, 0);
	glMatrixMode(GL_MODELVIEW);
	glEnable(GL_DEPTH_TEST);
	
	ArrayList<Wall> walls = new ArrayList<Wall>(20);
	walls.add(new xyWall(17,3,1,1, false));
	walls.add(new xyWall(4,3,1,5, true));
	walls.add(new xyWall(5,3,5,5, true));
	walls.add(new xyWall(3,3,10,5, false));
	walls.add(new xyWall(5,3,13,5, true));
	walls.add(new xyWall(5,3,5,8, true));
	walls.add(new xyWall(3,3,10,8, false));
	walls.add(new xyWall(5,3,13,8, true));
	walls.add(new xyWall(4,3,1,12, true));
	walls.add(new xyWall(13,3,5,12, false));
	walls.add(new zyWall(11,3,1,1, false));
	walls.add(new zyWall(4,3,5,1, false));
	walls.add(new zyWall(4,3,10,1, true));
	walls.add(new zyWall(4,3,13,1, true));
	walls.add(new zyWall(11,3,18,1, false));
	walls.add(new zyWall(3,3,10,5, true));
	walls.add(new zyWall(3,3,13,5, true));
	walls.add(new zyWall(4,3,5,8, false));
	walls.add(new zyWall(4,3,10,8, false));
	walls.add(new zyWall(4,3,13,8, true));
	//xyWall out=new xyWall(17, 3, 1,1, false);
	
	//System.out.println(out.testCollision(2.5f, 1));
	int x=0;
	while (!Display.isCloseRequested()) {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	
		glColor3f(1,1,1);
		for (Wall wall : walls){
			wall.draw();
			x++;
		}
		glColor3f(1,.2f,.2f);
		glBegin(GL_QUADS);
		glVertex3f(1,0,1);
		glVertex3f(18,0,1);
		glVertex3f(18,0,12);
		glVertex3f(1,0,12);
		glEnd();
		Display.update();
	}
	Display.destroy();
	}
	
	public static void main(String[] argv) {
        House test = new House();
        test.start();
    }
}
	

