import org.lwjgl.LWJGLException;
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
            Display.setDisplayMode(new DisplayMode(800, 800));
            Display.create();
            gluPerspective(80f, 1.0f, -100f, 1800f);
            gluLookAt(-500,0,500,0,0,0,0,1,0);
            glEnable(GL_DEPTH_TEST);

        }
}
