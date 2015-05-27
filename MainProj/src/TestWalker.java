package lwgl2;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.ArrayList;
import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

/**
 * Created by samnoyes on 5/14/15.
 */

public class TestWalker {
    boolean isLookingAtOrigin;
    private TrueTypeFont font, font2;

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
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glMatrixMode(GL_PROJECTION);
        gluPerspective(50f, 1.0f, -1000f, 1000f);
        gluLookAt(0,0,10,0,0,0,0,1,0);
        glEnable(GL_DEPTH_TEST);
        Wall entrance = new xyWall(4,3,1,12, false);
        ArrayList<Wall> walls = new ArrayList<Wall>(20);
    	walls.add(new xyWall(17,3,1,1, false));
    	walls.add(new xyWall(4,3,1,5, true));
    	walls.add(new xyWall(5,3,5,5, true));
    	walls.add(new xyWall(3,3,10,5, false));
    	walls.add(new xyWall(5,3,13,5, true));
    	walls.add(new xyWall(5,3,5,8, true));
    	walls.add(new xyWall(3,3,10,8, false));
    	walls.add(new xyWall(5,3,13,8, true));
    	walls.add(entrance);
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
    	
    	Font awtFont = new Font("Times New Roman", Font.BOLD, 50);
		font = new TrueTypeFont(awtFont, false);
		
    	ArrayList<PlayerObject> objects = new ArrayList<PlayerObject>();
    	objects.add(new PlayerObject(ObjectType.KEYS,0,0,0,.5f));
    	
        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            updateGLU(l);

            boolean forward = Keyboard.isKeyDown(Keyboard.KEY_W);
            boolean back = Keyboard.isKeyDown(Keyboard.KEY_S);
            boolean moveLeft = Keyboard.isKeyDown(Keyboard.KEY_A);
            boolean moveRight = Keyboard.isKeyDown(Keyboard.KEY_D);
            boolean up = Keyboard.isKeyDown(Keyboard.KEY_UP);
            boolean down = Keyboard.isKeyDown(Keyboard.KEY_DOWN);
            boolean right = Keyboard.isKeyDown(Keyboard.KEY_RIGHT);
            boolean left = Keyboard.isKeyDown(Keyboard.KEY_LEFT);
            boolean space = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
            if (moveLeft) l.setVx(l.getVx() + .01f);
            if (moveRight) l.setVx(l.getVx() - .01f);
            if (forward) l.setVz(l.getVz() + .01f);
            if (back) l.setVz(l.getVz() - .01f);
            if (left) {
                l.setRotation(l.getRotation()+.003);
            }
            if (right) {
                l.setRotation(l.getRotation()-.003);
            }
            if (up && l.getViewAngle()<45*(Math.PI/180)) l.setViewAngle(l.getViewAngle()+.003);
            if (down && l.getViewAngle()>-45*(Math.PI/180)) l.setViewAngle(l.getViewAngle()-.003);
            if (l.isLookingAt(0,0,0) != isLookingAtOrigin) {
                isLookingAtOrigin = !isLookingAtOrigin;
                System.out.println(l.isLookingAt(0,0,0));
            }
            
            if (space) {
                for (PlayerObject obj : objects) {
                    if (l.isLookingAt(obj.getX(), obj.getY(), obj.getZ())) {
                        obj.find();
                        entrance.open();
                    }
                }
            }

            for (Wall wall: walls) {
            	if(wall.testCollision(l.getX(), l.getZ())) {
            		if (moveRight) l.setVx(l.getVx()+.1f);
            		if (moveLeft) l.setVx(l.getVx()-.1f);
            		if (forward) l.setVz(l.getVz()-.1f);
            		if (back) l.setVz(l.getVz()+.1f);            	}
            }

            for (PlayerObject obj: objects) {
                if (!obj.isFound()) { drawCube(obj.getX(),obj.getY(),obj.getZ(), obj.getSize());}
            }
            
            l.updatePosition();
            glColor3f(1,1,1);
    		for (Wall wall : walls){
    			glColor3f(wall.getPosX()/20, 1, wall.getPosZ()/20);
    			wall.draw();
    		}
    		glColor3f(1,.2f,.2f);
    		glBegin(GL_QUADS);
    		glVertex3f(1,0,1);
    		glVertex3f(18,0,1);
    		glVertex3f(18,0,12);
    		glVertex3f(1,0,12);
    		glEnd();
    		glColor3f(1,1,1);
    		glBegin(GL_QUADS);
    		glVertex3f(1,0,1);
    		glVertex3f(-1,0,1);
    		glVertex3f(-1,0,-1);
    		glVertex3f(1,0,-1);
    		glEnd();
    		
    		make2D();
    		Color.white.bind();
            font.drawString(200, 200, "Test", Color.yellow);
            
            Display.update();
        }
        Display.destroy();
    }
    public void updateGLU(PlayerLogic l) {
    	glDisable(GL_BLEND);
        glMatrixMode(GL_PROJECTION);
    	glLoadIdentity();
        glMatrixMode(GL_PROJECTION);
        gluPerspective(50f, 1.0f, .1f, 90f);
        gluLookAt(l.getX(),l.getY(),l.getZ(),(float)(l.getX()+Math.sin(l.getRotation())),l.getY()+(float)Math.tan(l.getViewAngle()),(float)(l.getZ()+Math.cos(l.getRotation())),0,1,0);
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
    
    protected static void make2D() {
        glEnable(GL_BLEND);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0.0f, Display.getWidth(), Display.getHeight(), 0.0f, 0.0f, 1.0f);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }
    
    public void drawCube(float x, float y, float z, float size) {
        glBegin(GL_QUAD_STRIP);
        glVertex3d(x-size/2,y+size/2, z+size/2);
        glVertex3d(x-size/2,y-size/2, z+size/2);
        glVertex3d(x+size/2,y+size/2, z+size/2);
        glVertex3d(x+size/2,y-size/2, z+size/2);
        glVertex3d(x+size/2,y+size/2, z-size/2);
        glVertex3d(x+size/2,y-size/2, z-size/2);
        glVertex3d(x-size/2,y+size/2, z-size/2);
        glVertex3d(x-size/2,y-size/2, z-size/2);
        glVertex3d(x-size/2,y+size/2, z+size/2);
        glVertex3d(x-size/2,y-size/2, z+size/2);
        glEnd();
    }
}
