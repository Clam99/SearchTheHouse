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
    boolean spacePressed = false;
    ArrayList<Wall> walls = new ArrayList<Wall>(48);
	ArrayList<Wall> house1 = new ArrayList<Wall>(48);
    ArrayList<Wall> house2 = new ArrayList<Wall>(48);
    ArrayList<Wall> house3 = new ArrayList<Wall>(48);
    ArrayList<Wall> house4 = new ArrayList<Wall>(48);

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
        String s = new String();
        Display.setDisplayMode(new DisplayMode(1200, 800));
        Display.create();
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glMatrixMode(GL_PROJECTION);
        gluPerspective(70f, 1.5f, -1000f, 1000f);
        gluLookAt(0,0,10,12.5f,1.5f,0,0,1,0);
        glEnable(GL_DEPTH_TEST);
    	
    	Font awtFont = new Font("Times New Roman", Font.BOLD, 50);
		font = new TrueTypeFont(awtFont, true);
		
		Font awtFont2 = new Font("Arial", Font.PLAIN, 30);
		font2 = new TrueTypeFont(awtFont2, true);
		
		final float MOVE_BY = 0.01f;
		setupHouses(3);
	    
	    ArrayList<Wall> house;
		
    	ArrayList<PlayerObject> objects = new ArrayList<PlayerObject>();
    	objects.add(new Teleporter(33.5f,7.5f,8.5f,11,.5f));
    	objects.add(new Teleporter(8.5f,11,33.5f,7.5f,.5f));
    	objects.add(new Teleporter(37.5f,4,37.5f,11f,.5f));
    	objects.add(new Teleporter(37.5f,11,37.5f,4f,.5f));
    	objects.add(new Teleporter(16.5f,11,41.5f,30f,.5f));
    	objects.add(new Teleporter(41.5f,30,16.5f,11f,.5f));
    	objects.add(new Teleporter(29f,23,21f,23f,.5f));
    	objects.add(new Teleporter(21f,23,29f,23f,.5f));
    	objects.add(new Teleporter(29f,33.5f,21f,33.5f,.5f));
    	objects.add(new Teleporter(21f,33.5f,29f,33.5f,.5f));
    	objects.add(new Crowbar(37.5f,0,30,1));
    	
    	
    	
    	
        while (!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            if(objects.get(10).wasFound) end();

            updateGLU(l);
            if (l.getX()>26) {
            	if (l.getZ()>20) house=house3;
            	else house=house2;
            }
            else {
            	if (l.getZ()>20) house=house4;
            	else house=house1;
            }

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
                l.setRotation(l.getRotation()+.003);
            }
            if (right) {
                l.setRotation(l.getRotation()-.003);
            }
            if (up && l.getViewAngle()<45*(Math.PI/180)) l.setViewAngle(l.getViewAngle()+.003);
            if (down && l.getViewAngle()>-45*(Math.PI/180)) l.setViewAngle(l.getViewAngle()-.003);
            
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

            for (int i=0; i<walls.size(); i++) {
            	if(house.get(i).testCollision(l.getX(), l.getZ())) {
            		if (moveRight) l.setVx(l.getVx()+.2f);
                    if (moveLeft) l.setVx(l.getVx()-.2f);
                    if (forward) l.setVz(l.getVz()-.2f);
                    if (back) l.setVz(l.getVz()+.2f); 
            	}
            }

            for (int i=0; i<objects.size(); i++) {
            	PlayerObject obj = objects.get(i);
            	if (obj.isDisplayed()) { 
            		glColor3f(obj.r,obj.g,obj.b);
            		drawCube(obj.getX(),obj.getY(),obj.getZ(), obj.getSize());}
            }
            
            l.updatePosition();
            glColor3f(1,1,1);
    		for (int i=0; i<walls.size(); i++){
    			glColor3f(walls.get(i).getPosX()/20, 1, walls.get(i).getPosZ()/20);
    			house.get(i).draw();
    		}
    		
    		glColor3f(1,.2f,.2f);
    		glBegin(GL_QUADS);
    		glVertex3f(1,0,1);
    		glVertex3f(50,0,1);
    		glVertex3f(50,0,40);
    		glVertex3f(1,0,40);
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
            font2.drawString(10, 10, "EscapeTheHouse Version 1.0.0", Color.white);
            font2.drawString(10, 40, "Press <I> for Information");
            if (Keyboard.isKeyDown(Keyboard.KEY_I)){
            	font2.drawString(10, 70, "Find the crowbar to break out of the mazehouse", Color.white);
            	font2.drawString(10, 100, "To interact with objects, press space.", Color.white);
            	font2.drawString(10, 130, "blue objects are teleports", Color.white);
            	font2.drawString(10, 160, "yellow objects are collectables", Color.white);
            }
            
            Display.update();
        }
        Display.destroy();
    }
    public void updateGLU(PlayerLogic l) {
    	glDisable(GL_BLEND);
        glMatrixMode(GL_PROJECTION);
    	glLoadIdentity();
        glMatrixMode(GL_PROJECTION);
        gluPerspective(70f, 1.5f, .1f, 90f);
        gluLookAt(l.getX(),l.getY(),l.getZ(),(float)(l.getX()+Math.sin(l.getRotation())),l.getY()+(float)Math.tan(l.getViewAngle()),(float)(l.getZ()+Math.cos(l.getRotation())),0,1,0);
    }
    
    private void end() {
    	make2D();
    	font.drawString(10, 400, "You won!! You found the crowbar and can now escape!", Color.white);
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
    
    public void setupHouses(int h) {

	    walls.add(new xyWall(4,h,1,1,false));
	    walls.add(new xyWall(5,h,5,1,false));
	    walls.add(new xyWall(3,h,10,1,false));
	    walls.add(new xyWall(5,h,13,1,false));
	    walls.add(new xyWall(4,h,18,1,false));
	    walls.add(new zyWall(4,h,1,1,false));
	    walls.add(new zyWall(4,h,5,1,false));
	    walls.add(new zyWall(4,h,10,1,false));
	    walls.add(new zyWall(4,h,13,1,false));
	    walls.add(new zyWall(4,h,18,1,false));
	    walls.add(new zyWall(4,h,22,1,false));
	    walls.add(new xyWall(4,h,1,5,false));
	    walls.add(new xyWall(5,h,5,5,false));
	    walls.add(new xyWall(3,h,10,5,false));
	    walls.add(new xyWall(5,h,13,5,false));
	    walls.add(new xyWall(4,h,18,5,false));
	    walls.add(new zyWall(3,h,1,5,false));
	    walls.add(new zyWall(3,h,5,5,false));
	    walls.add(new zyWall(3,h,10,5,false));
	    walls.add(new zyWall(3,h,13,5,false));
	    walls.add(new zyWall(3,h,18,5,false));
	    walls.add(new zyWall(3,h,22,5,false));
	    walls.add(new xyWall(4,h,1,8,false));
	    walls.add(new xyWall(5,h,5,8,false));
	    walls.add(new xyWall(3,h,10,8,false));
	    walls.add(new xyWall(5,h,13,8,false));
	    walls.add(new xyWall(4,h,18,8,false));
	    walls.add(new zyWall(4,h,1,8,false));
	    walls.add(new zyWall(4,h,5,8,false));
	    walls.add(new zyWall(4,h,10,8,false));
	    walls.add(new zyWall(4,h,13,8,false));
	    walls.add(new zyWall(4,h,18,8,false));
	    walls.add(new zyWall(4,h,22,8,false));
	    walls.add(new xyWall(4,h,1,12,false));
	    walls.add(new xyWall(5,h,5,12,false));
	    walls.add(new xyWall(3,h,10,12,false));
	    walls.add(new xyWall(5,h,13,12,false));
	    walls.add(new xyWall(4,h,18,12,false));
	    walls.add(new zyWall(3,h,1,12,false));
	    walls.add(new zyWall(3,h,5,12,false));
	    walls.add(new zyWall(3,h,10,12,false));
	    walls.add(new zyWall(3,h,13,12,false));
	    walls.add(new zyWall(3,h,18,12,false));
	    walls.add(new zyWall(3,h,22,12,false));
	    walls.add(new xyWall(4,h,1,15,false));
	    walls.add(new xyWall(5,h,5,15,false));
	    walls.add(new xyWall(3,h,10,15,false));
	    walls.add(new xyWall(5,h,13,15,false));
	    walls.add(new xyWall(4,h,18,15,false));
	    
	    for (int i=0; i<walls.size(); i++) {
	    	house1.add(walls.get(i).copy());
	    	house2.add(walls.get(i).copy());
	    	house3.add(walls.get(i).copy());
	    	house4.add(walls.get(i).copy());
	    	house1.get(i).addX(1);
	    	house1.get(i).addZ(1);
	    	house2.get(i).addX(26);
	    	house2.get(i).addZ(1);
	    	house3.get(i).addX(26);
	    	house3.get(i).addZ(20);
	    	house4.get(i).addX(1);
	    	house4.get(i).addZ(20);
	    }
	    
	    System.out.println(walls.get(0).getPosX() + walls.get(0).getPosZ());
	    
	    house1.get(6).open();
	    house1.get(7).open();
	    house1.get(8).open();
	    house1.get(9).open();
	    house1.get(11).open();
	    house1.get(15).open();
	    house1.get(18).open();
	    house1.get(19).open();
	    house1.get(22).open();
	    house1.get(23).open();
	    house1.get(24).open();
	    house1.get(25).open();
	    house1.get(26).open();
	    house1.get(33).open();
	    house1.get(35).open();
	    house1.get(37).open();
	    house1.get(39).open();
	    house1.get(40).open();
	    house1.get(41).open();
	    house1.get(42).open();
	    
	    house2.get(6).open();
	    house2.get(7).open();
	    house2.get(12).open();
	    house2.get(14).open();
	    house2.get(15).open();
	    house2.get(17).open();
	    house2.get(18).open();
	    house2.get(20).open();
	    house2.get(22).open();
	    house2.get(23).open();
	    house2.get(25).open();
	    house2.get(26).open();
	    house2.get(30).open();
	    house2.get(33).open();
	    house2.get(36).open();
	    house2.get(37).open();
	    house2.get(39).open();
	    house2.get(40).open();
	    house2.get(42).open();
	    
	    house3.get(6).open();
	    house3.get(7).open();
	    house3.get(8).open();
	    house3.get(9).open();
	    house3.get(11).open();
	    house3.get(12).open();
	    house3.get(14).open();
	    house3.get(15).open();
	    house3.get(19).open();
	    house3.get(20).open();
	    house3.get(26).open();
	    house3.get(28).open();
	    house3.get(33).open();
	    house3.get(34).open();
	    house3.get(35).open();
	    house3.get(36).open();
	    house3.get(37).open();
	    house3.get(40).open();
	    house3.get(42).open();
	    
	    house4.get(6).open();
	    house4.get(7).open();
	    house4.get(8).open();
	    house4.get(11).open();
	    house4.get(15).open();
	    house4.get(17).open();
	    house4.get(19).open();
	    house4.get(20).open();
	    house4.get(23).open();
	    house4.get(25).open();
	    house4.get(29).open();
	    house4.get(31).open();
	    house4.get(33).open();
	    house4.get(34).open();
	    house4.get(36).open();
	    house4.get(39).open();
	    house4.get(40).open();
	    house4.get(41).open();
	    house4.get(42).open();
    }
}
