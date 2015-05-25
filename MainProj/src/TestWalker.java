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

            boolean forward = Keyboard.isKeyDown(Keyboard.KEY_W);
            boolean back = Keyboard.isKeyDown(Keyboard.KEY_S);
            boolean moveLeft = Keyboard.isKeyDown(Keyboard.KEY_A);
            boolean moveRight = Keyboard.isKeyDown(Keyboard.KEY_D);
            boolean up = Keyboard.isKeyDown(Keyboard.KEY_UP);
            boolean down = Keyboard.isKeyDown(Keyboard.KEY_DOWN);
            boolean right = Keyboard.isKeyDown(Keyboard.KEY_RIGHT);
            boolean left = Keyboard.isKeyDown(Keyboard.KEY_LEFT);
            if (moveLeft) l.setVx(l.getVx() + .1f);
            if (moveRight) l.setVx(l.getVx() - .1f);
            if (forward) l.setVz(l.getVz() + .1f);
            if (back) l.setVz(l.getVz() - .1f);
            if (left) {
                //l.setVx(l.getVx() - .03f);
                l.setRotation(l.getRotation()+.005);
            }
            if (right) {
                //l.setVx(l.getVx() + .03f);
                l.setRotation(l.getRotation()-.005);
            }
            if (up && l.getViewAngle()<45*(Math.PI/180)) l.setViewAngle(l.getViewAngle()+.005);
            if (down && l.getViewAngle()>-45*(Math.PI/180)) l.setViewAngle(l.getViewAngle()-.005);
            l.updatePosition();
            updateGLU(l);
            drawFloor();
            Display.update();
        }
        Display.destroy();
    }
    public void updateGLU(PlayerLogic l) {
        glLoadIdentity();
        glMatrixMode(GL_PROJECTION);
        gluPerspective(30f, 1.0f, .1f, 90f);
        gluLookAt(l.getX(),l.getY(),l.getZ(),(float)(l.getX()+Math.sin(l.getRotation())),l.getY()+(float)Math.tan(l.getViewAngle()),(float)(l.getZ()+Math.cos(l.getRotation())),0,1,0);
    }
    public void drawFloor() {
        int rows = 20;
        int cols = 20;
        int width = 20;
        for (int i = -rows/2; i<rows/2;i++) {
            for (int j = -cols/2; j<cols/2;j++) {
                drawSquare(width,width*j,0,width*i, ((float)(j)/(float)cols)*(float)255, ((float)(i)/(float)rows)*(float)255,1);
            }
        }
    }
    public void drawSquare(float width, float x,float y, float z, float r, float g, float b) {
        glBegin(GL_QUADS);
        glColor3d(r,g,b);
        glVertex3d(x-(width/2),y,z-(width/2));
        glVertex3d(x-(width/2),y,z+(width/2));
        glVertex3d(x+(width/2),y,z+(width/2));
        glVertex3d(x+(width/2),y,z-(width/2));
        glEnd();
    }
}
