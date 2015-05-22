import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.awt.event.KeyListener;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.util.glu.GLU.gluLookAt;
import static org.lwjgl.util.glu.GLU.gluPerspective;

/**
 * Created by samnoyes on 5/14/15.
 */

public class TestWalker {

float rotated = 0;

    public TestWalker()  {
        try {
            start();
        }
        catch (LWJGLException e) {
            e.printStackTrace();
        }
    }
    public void start() throws org.lwjgl.LWJGLException {
        PlayerLogic l = new PlayerLogic();
        Display.setDisplayMode(new DisplayMode(800, 800));
        Display.create();
        glMatrixMode(GL_PROJECTION);
        gluPerspective(50f, 1.0f, -1000f, 1000f);
        gluLookAt(0,0,10,0,0,0,0,1,0);
        glEnable(GL_DEPTH_TEST);
        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            boolean forward = Keyboard.isKeyDown(Keyboard.KEY_UP);
            boolean back = Keyboard.isKeyDown(Keyboard.KEY_DOWN);
            boolean right = Keyboard.isKeyDown(Keyboard.KEY_RIGHT);
            boolean left = Keyboard.isKeyDown(Keyboard.KEY_LEFT);
            float maxV = .25f;
            if (forward && l.getVy()>-maxV) l.setVy(l.getVy() - .03f);
            if (back && l.getVy()<maxV) l.setVy(l.getVy() + .03f);
            if (left && l.getVx()>-maxV) { l.setVx(l.getVx() - .03f); }
            if (right && l.getVx()<maxV) { l.setVx(l.getVx() + .03f); }
            l.updatePosition();
            updateGLU(l);
            drawFloor();
            Display.update();
        }
        Display.destroy();
    }
    public void updateGLU(PlayerLogic l) {
        //System.out.println(l.x + ", " + l.y);
        glLoadIdentity();
        glMatrixMode(GL_PROJECTION);
        gluPerspective(30f, 1.0f, 10f, -10f);
        gluLookAt(l.x,5,l.y,l.x,5,l.y-10,0,1,0);
       // gluLookAt(l.x,100,l.y,0,0,0,0,1,0);
    }
    public void drawFloor() {
        int rows = 20;
        int cols = 20;
        int width = 20;
        for (int i = -rows/2; i<rows/2;i++) {
            for (int j = -cols/2; j<cols/2;j++) {
                drawSquare(width,width*j,0,width*i, ((float)j/(float)cols)*(float)255, ((float)i/(float)rows)*(float)255,1);
            }
        }
    }
    public void drawSquare(float width, float x,float y, float z, float r, float g, float b) {
        glBegin(GL_QUADS);
        glColor3d(r,g,b);
        glVertex3d(x-(width/2),y,z-(width/2));
        glVertex3d(x-(width/2),0,z+(width/2));
        glVertex3d(x+(width/2),0,z+(width/2));
        glVertex3d(x+(width/2),0,z-(width/2));
        glEnd();
    }
}
