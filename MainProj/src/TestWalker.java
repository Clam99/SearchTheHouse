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
    float MOVE_BY = 0.01f;
    boolean spacePressed = false;

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
    	walls.add(new xyWall(5,3,5,5, false));
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
    	walls.add(new zyWall(4,3,13,1, false));
    	walls.add(new zyWall(11,3,18,1, false));
    	walls.add(new zyWall(3,3,10,5, true));
    	walls.add(new zyWall(3,3,13,5, true));
    	walls.add(new zyWall(4,3,5,8, false));
    	walls.add(new zyWall(4,3,10,8, false));
    	walls.add(new zyWall(4,3,13,8, false));

        /*ArrayList<PlayerObject> objects = new ArrayList<PlayerObject>();
        objects.add(new PlayerObject(ObjectType.KEYS,16,0,10,.5f));
        objects.add(new PlayerObject(ObjectType.KEYS,10,0,5,.5f));
        objects.add(new PlayerObject(ObjectType.KEYS,15,0,15,.5f));
        objects.add(new PlayerObject(ObjectType.KEYS,15,0,5,.5f));*/
    	
    	Font awtFont = new Font("Times New Roman", Font.BOLD, 50);
		font = new TrueTypeFont(awtFont, false);


        Teleporter t1 = new Teleporter(0,0,0,3,1.5f,3,.5f);
        Teleporter t2 = new Teleporter(3,0,3,0,1.5f,0,.5f);


    	ArrayList<PlayerObject> objects = new ArrayList<PlayerObject>();
    	objects.add(new Keys(walls.get(8),0,0,20,.5f));
        objects.add(new Keys(walls.get(16),12,0,9,.5f));
        objects.add(new Keys(walls.get(7),8,0,9,.5f));
        objects.add(new Keys(walls.get(15),4,0,7,.5f));
        objects.add(new Keys(walls.get(3),11,0,6,.5f));
        objects.add(new Keys(walls.get(1),9,0,4,.5f));
        objects.add(new Keys(walls.get(12),11,0,2,.5f));
        objects.add(t1);
        objects.add(t2);
    	
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

            if (moveLeft) l.setVx(MOVE_BY);
            else if (moveRight) l.setVx(-MOVE_BY);
            else l.setVx(0);
            if (forward) l.setVz(MOVE_BY);
            else if (back) l.setVz(-MOVE_BY);
            else l.setVz(0);

            if (left) {
                l.setRotation(l.getRotation()+.009);
            }
            if (right) {
                l.setRotation(l.getRotation()-.009);
            }
            if (up && l.getViewAngle()<45*(Math.PI/180)) l.setViewAngle(l.getViewAngle()+.009);
            if (down && l.getViewAngle()>-45*(Math.PI/180)) l.setViewAngle(l.getViewAngle()-.009);
            if (l.isLookingAt(0,0,0) != isLookingAtOrigin) {
                isLookingAtOrigin = !isLookingAtOrigin;
                System.out.println(l.isLookingAt(0,0,0));
            }
            
            if (space && !spacePressed) {
                spacePressed = true;
                for (PlayerObject obj : objects) {
                    if (l.isLookingAt(obj.getX(), obj.getY(), obj.getZ())) {
                        obj.find(l);
                        break;
                    }
                }
            }
            else if (!space) {
                spacePressed = false;
            }

            for (Wall wall: walls) {
            	if(wall.testCollision(l.getX(), l.getZ())) {
                    if (moveRight) l.setVx(l.getVx()+.1f);
                    if (moveLeft) l.setVx(l.getVx()-.1f);
                    if (forward) l.setVz(l.getVz()-.1f);
                    if (back) l.setVz(l.getVz()+.1f); 
                    /*Vector currentMovement = new Vector(l.getVx()*(float)Math.cos(l.getRotation())+l.getVz()*(float)Math.sin(l.getRotation()),0,l.getVz()*(float)Math.cos(l.getRotation())-l.getVx()*(float)Math.sin(l.getRotation()));
                    Vector projection = currentMovement.projectOnto(wall.getSurfaceVector());
                    Vector moveBack = currentMovement.projectOnto(wall.getNormalVector()).scaleVector(-1);
                    moveBack.setMagnitude(0.1f);
                    Vector newPosition = moveBack.addVector(new Vector(l.getX(),l.getY(),l.getZ()));
                    //l.setX(newPosition.getX());
                    //l.setY(newPosition.getY());
                    //l.setZ(newPosition.getZ());
                    System.out.println("Current movement: " + currentMovement);
                    System.out.println("Wall: " + wall.getSurfaceVector());
                    System.out.println(projection);
                    if (wall.getSurfaceVector().getX()==1) {
                        l.setVx(projection.getMagnitude()*(float)Math.cos(l.getRotation()));
                        l.setVz(projection.getMagnitude()*(float)Math.sin(l.getRotation()));
                    }
                    else {
                        l.setVz(projection.getMagnitude()*(float)Math.cos(l.getRotation()));
                        l.setVx(projection.getMagnitude()*(float)Math.sin(l.getRotation()));
                    }
                    /*if (wall.getSurfaceVector().getX() == 1) {
                        if (l.getVx() > 0) {
                            l.setRotation(-3.14159/2);

                            System.out.println("90");
                        }
                        else {
                            l.setRotation(3.14159/2);

                            System.out.println("-90");
                        }

                    }
                    else {
                        if (l.getVz() > 0) {
                            l.setRotation(0);
                            System.out.println("0");
                        }
                        else {
                            l.setRotation(3.14159);
                            System.out.println("180");
                        }
                    }*/
            	}
            }

            for (PlayerObject obj: objects) {
                if (obj.isDisplayed()) { drawCube(obj.getX(),obj.getY(),obj.getZ(), obj.getSize());}
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
        gluLookAt(l.getX(),l.getY(),l.getZ(),(float)(l.getX()+Math.sin(l.getRotation())),l.getY()+(float)Math.tan(l.getViewAngle()),(float)(l.getZ() + Math.cos(l.getRotation())), 0, 1, 0);

    }

    public void drawSquare(float width, float x, float y, float z, float r, float g, float b) {
        glBegin(GL_QUADS);
        glColor3d(r, g,b);
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
