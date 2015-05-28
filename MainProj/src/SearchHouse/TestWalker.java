package SearchHouse;//imports needed for lwjgl, text display and texture import/display
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.TextureLoader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

/**
* Created by Sam Noyes and Jan Stratmann on 5/14/15.
*/

public class TestWalker implements TargetDelegate {
  final float MOVE_BY = 0.03f;
  boolean isTesting = false; //testing mode can be activated by changing this to true
  boolean isOver = false;
  private TrueTypeFont font, font2;
  boolean spacePressed = false;
  int WALL_HEIGHT = 3;
  String keyString = "elephant";
  String enteredString = "";
  ArrayList<Wall> walls, house1, house2, house3, house4;
  Texture E,L,P,H,A,N,T;
  boolean isEnteringString = false;
  boolean backspace = true, a = true,b = true,c = true,d = true,e = true,f = true,g = true,h = true,i = true,j = true,k = true,lb = true,m = true,n = true,o = true,p = true,q = true,r = true,sb = true,t = true,u = true,v = true,w = true,x = true,y = true,z = true;
  StringTeleporter st;
  PlayerLogic l = new PlayerLogic();
  String s = new String();
  Font awtFont;

  Font awtFont2;
  ArrayList<PlayerObject> objects;

  public void setup() {
      isOver = false;
      spacePressed = false;
      WALL_HEIGHT = 3;
      keyString = "elephant";
      enteredString = "";
      walls = new ArrayList<Wall>(48);
      house1 = new ArrayList<Wall>(48);
      house2 = new ArrayList<Wall>(48);
      house3 = new ArrayList<Wall>(48);
      house4 = new ArrayList<Wall>(48);
       isEnteringString = false;
      backspace = true; a = true;b = true;c = true;d = true;e = true;f = true;g = true;h = true;i = true;j = true;k = true;lb = true;m = true;n = true;o = true;p = true;q = true;r = true;sb = true;t = true;u = true;v = true;w = true;x = true;y = true;z = true;
      l = new PlayerLogic();
      s = new String();
      try {
          Display.setDisplayMode(new DisplayMode(1200, 800));
      } catch (LWJGLException e1) {
          e1.printStackTrace();
      }
      glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
      glMatrixMode(GL_PROJECTION);
      gluPerspective(70f, 1.5f, -1000f, 1000f);
      gluLookAt(0,0,10,12.5f,1.5f,0,0,1,0);
      glEnable(GL_DEPTH_TEST);

      awtFont = new Font("Times New Roman", Font.BOLD, 50);
      font = new TrueTypeFont(awtFont, true);

      awtFont2 = new Font("Arial", Font.PLAIN, 30);
      font2 = new TrueTypeFont(awtFont2, true);

      setupHouses(WALL_HEIGHT);

      st = new StringTeleporter(29f,23,21f,23f,.5f,keyString, this);
      objects = new ArrayList<PlayerObject>();
      objects.add(new Teleporter(33.5f,7.5f,8.5f,11,.5f));
      objects.add(new Teleporter(8.5f,11,33.5f,7.5f,.5f));
      objects.add(new Teleporter(37.5f,4,37.5f,11f,.5f));
      objects.add(new Teleporter(37.5f,11,37.5f,4f,.5f));
      objects.add(new Teleporter(16.5f,11,41.5f,30f,.5f));
      objects.add(new Teleporter(41.5f,30,16.5f,11f,.5f));
      objects.add(st);
      objects.add(new Teleporter(21f,23,29f,23f,.5f));
      objects.add(new Teleporter(29f,33.5f,21f,33.5f,.5f));
      objects.add(new Teleporter(21f,33.5f,29f,33.5f,.5f));
      objects.add(new Keys(house4.get(42),3,0,3,.5f));
      objects.add(new Keys(house3.get(7),46,0,2.5f,.5f));
      objects.add(new Crowbar(37.5f,0,30,1, this));
    	
    	//import textures as images
        try {
            E = TextureLoader.getTexture("PNG", getClass().getResourceAsStream("res/Letter-E.png"));
            L = TextureLoader.getTexture("PNG", getClass().getResourceAsStream("res/Letter-L.png"));
            P = TextureLoader.getTexture("PNG", getClass().getResourceAsStream("res/Letter-P.png"));
            H = TextureLoader.getTexture("PNG", getClass().getResourceAsStream("res/Letter-H.png"));
            A = TextureLoader.getTexture("PNG", getClass().getResourceAsStream("res/Letter-A.png"));
            N = TextureLoader.getTexture("PNG", getClass().getResourceAsStream("res/Letter-N.png"));
            T = TextureLoader.getTexture("PNG", getClass().getResourceAsStream("res/Letter-T.png"));
        } catch (IOException e1) {
            e1.printStackTrace(); 
        }
  }
    	
    	
        public TestWalker()  {
            try {
                start();
            }
            catch (LWJGLException e) {
                e.printStackTrace();
            }
        }
            
        public void start() throws org.lwjgl.LWJGLException {
        	//set up opengl
            Display.setDisplayMode(new DisplayMode(1200, 800));
            Display.create();
            setup();
        	
            while (!Display.isCloseRequested()) { //game loop
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

                //update camera view and chang to 3D drawing mode
                updateGLU(l);

                ArrayList<ArrayList<Wall>> houses = new ArrayList<ArrayList<Wall>>(4);
                if (!isTesting) {
                    if (l.getX() > 26) { //some primitive culling (only the house with the player in it is drawn
                        if (l.getZ() > 20) houses.add(house3);
                        else houses.add(house2);
                    } else {
                        if (l.getZ() > 20) houses.add(house4);
                        else houses.add(house1);
                    }
                }
                else {
                    houses.add(house1);
                    houses.add(house2);
                    houses.add(house3);
                    houses.add(house4);
                }
                
                //get keyboard inputs


                if (Keyboard.isKeyDown(Keyboard.KEY_W) && !w  && !isEnteringString) {
                    w = true;
                }
                else if (!Keyboard.isKeyDown(Keyboard.KEY_W) && !isEnteringString) {
                    w = false;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_A) && !a && !isEnteringString) {
                    a = true;
                }
                else if (!Keyboard.isKeyDown(Keyboard.KEY_A) && !isEnteringString) {
                    a = false;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_S) && !sb && !isEnteringString) {
                    sb = true;
                }
                else if (!Keyboard.isKeyDown(Keyboard.KEY_S) && !isEnteringString) {
                    sb = false;
                }
                if (Keyboard.isKeyDown(Keyboard.KEY_D) && !d && !isEnteringString) {
                    d = true;
                }
                else if (!Keyboard.isKeyDown(Keyboard.KEY_D) && !isEnteringString) {
                    d = false;
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

                
                //move and turn setters
                if (moveLeft && !isEnteringString) l.setVx(MOVE_BY);
                else if (moveRight && !isEnteringString) l.setVx(-MOVE_BY);
                else l.setVx(0);
                if (forward && !isEnteringString) l.setVz(MOVE_BY);
                else if (back && !isEnteringString) l.setVz(-MOVE_BY);
                else l.setVz(0);
                if (left) {
                    l.setRotation(l.getRotation()+.007);
                }
                if (right) {
                    l.setRotation(l.getRotation()-.007);
                }
                if (up && l.getViewAngle()<45*(Math.PI/180)) l.setViewAngle(l.getViewAngle()+.007);
                if (down && l.getViewAngle()>-45*(Math.PI/180)) l.setViewAngle(l.getViewAngle()-.007);
                
                //object interaction happens here
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
                
                //collision detection and glitch prevention - detects if a user is very close to a wall, and moves them back if so
                for (ArrayList<Wall> house : houses) {
                    for (int i = 0; i < walls.size(); i++) {
                        if (house.get(i).testCollision(l.getX(), l.getZ())) {
                            Wall w = house.get(i);
                            if (w.getSurfaceVector().getZ() ==1) {
                                if (l.getX()>w.getPosX()) {
                                    l.setX(w.getPosX()+.2f);
                                }
                                else {
                                    l.setX(w.getPosX()-.2f);
                                }
                            }
                            if (w.getSurfaceVector().getX() ==1) {
                                if (l.getZ()>w.getPosZ()) {
                                    l.setZ(w.getPosZ() + .2f);
                                }
                                else {
                                    l.setZ(w.getPosZ() - .2f);
                                }
                            }
                        }
                    }
                }
                
                //draw objects
                for (int i=0; i<objects.size(); i++) {
                	PlayerObject obj = objects.get(i);
                	if (obj.isDisplayed()) { 
                		glColor3f(obj.r,obj.g,obj.b);
                		drawCube(obj.getX(),obj.getY(),obj.getZ(), obj.getSize());}
                }
                
                //update position of player
                l.updatePosition();
                
                //draw walls
                glColor3f(1,1,1);
                for (ArrayList<Wall> h:houses) {
                    for (int i = 0; i < walls.size(); i++) {
                        glColor3f(walls.get(i).getPosX() / 20, 1, walls.get(i).getPosZ() / 20);
                        //if (i!=46)
                        h.get(i).draw();
                    }
                }
                
                //draw letters (as textures)
                drawLetterOnXYWall(A, (xyWall)house1.get(46));
                drawLetterOnZYWall(E, (zyWall)house1.get(21));
                drawLetterOnZYWall(L, (zyWall)house3.get(5));
                drawLetterOnZYWall(E, (zyWall)house2.get(43));
                drawLetterOnXYWall(N, (xyWall)house2.get(3));
                drawLetterOnZYWall(P, (zyWall)house3.get(43));
                drawLetterOnXYWall(H, (xyWall)house2.get(45));
                drawLetterOnZYWall(T, (zyWall)house3.get(18));

                // draw red ground
        		glColor3f(1,.2f,.2f);
        		glBegin(GL_QUADS);
        		glVertex3f(1,0,1);
        		glVertex3f(50,0,1);
        		glVertex3f(50,0,40);
        		glVertex3f(1,0,40);
        		glEnd();
        		
        		if (isTesting) {
                    glColor3f(1,1,1);
                    glBegin(GL_QUADS);
                    glVertex3f(1,0,1);
                    glVertex3f(-1,0,1);
                    glVertex3f(-1,0,-1);
                    glVertex3f(1,0,-1);
                    glEnd();
        		}
        		
        		//endscreen check
                if (isOver) {
                    end();
                }
                if (isTesting) {
                    drawCube(l.getX(), l.getY(), l.getZ(), 1);
                }

                make2D();
                glEnable(GL_TEXTURE_2D);
                TextureImpl.bindNone();
                Color.white.bind();
                
                // everything in the following if-loop is for the SearchHouse.StringTeleporter
                if (isEnteringString) {
                    font2.drawString(10,400,"Enter the 8-character code, or press 0 to exit:  " + enteredString, Color.white);

                    if (Keyboard.isKeyDown(Keyboard.KEY_A) && !a) {
                        enteredString+="A";
                        a = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_A)) {
                        a = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_BACK) && enteredString.length()>0 && !backspace) {
                        enteredString = enteredString.substring(0,enteredString.length()-1);
                        backspace = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
                        backspace = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_B) && !b) {
                        enteredString+="B";
                        b = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_B)) {
                        b = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_C) && !c) {
                        enteredString+="C";
                        c = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_C)) {
                        c = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_D) && !d) {
                        enteredString+="D";
                        d = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_D)) {
                        d = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_E) && !e) {
                        enteredString+="E";
                        e = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_E)) {
                        e = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_F) && !f) {
                        enteredString+="F";
                        f = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_F)) {
                        f = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_G) && !g) {
                        enteredString+="G";
                        g = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_G)) {
                        g = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_H) && !h) {
                        enteredString+="H";
                        h = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_H)) {
                        h = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_I) && !i) {
                        enteredString+="I";
                        i = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_I)) {
                        i = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_J) && !j) {
                        enteredString+="J";
                        j = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_J)) {
                        j = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_K) && !k) {
                        enteredString+="K";
                        k = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_K)) {
                        k = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_L) && !lb) {
                        enteredString+="L";
                        lb = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_L)) {
                        lb = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_M) && !m) {
                        enteredString+="M";
                        m = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_M)) {
                        m = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_N) && !n) {
                        enteredString+="N";
                        n = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_N)) {
                        n = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_O) && !o) {
                        enteredString+="O";
                        o=true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_O)) {
                        o = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_P) && !p) {
                        enteredString+="P";
                        p = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_P)) {
                        p = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_Q) && !q) {
                        enteredString+="Q";
                        q = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_Q)) {
                        q = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_R) && !r) {
                        enteredString+="R";
                        r = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_R)) {
                        r = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_S) && !sb) {
                        enteredString+="S";
                        sb = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_S)) {
                        sb = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_T) && !t) {
                        enteredString+="T";
                        t = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_T)) {
                        t = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_U) && !u) {
                        enteredString+="U";
                        u = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_U)) {
                        u = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_V) && !v) {
                        enteredString+="V";
                        v = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_V)) {
                        v = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_W) && !w) {
                        enteredString+="W";
                        w = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_W)) {
                        w = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_X) && !x) {
                        enteredString+="X";
                        x = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_X)) {
                        x = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_Y) && !y) {
                        enteredString+="Y";
                        y = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_Y)) {
                        y = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_Z) && !z) {
                        enteredString+="Z";
                        z = true;
                    }
                    else if (!Keyboard.isKeyDown(Keyboard.KEY_Z)) {
                        z = false;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_0)) {
                        isEnteringString = false;
                    }

                    if (enteredString.equalsIgnoreCase(keyString)) {
                        isEnteringString = false;
                        l.setX(st.getTx());
                        l.setZ(st.getTz());
                    }
                }
                
                //draw little HUD messages
                font2.drawString(10, 10, "EscapeTheHouse Version 1.0.20", Color.white);
                font2.drawString(10, 40, "Press <I> for Information");
                if (Keyboard.isKeyDown(Keyboard.KEY_I) && !isEnteringString){
                    font2.drawString(10, 70, "Find the crowbar to break out of the mazehouse", Color.white);
                    font2.drawString(10, 100, "To interact with objects, press space.", Color.white);
                    font2.drawString(10, 130, "blue objects are teleports", Color.white);
                    font2.drawString(10, 160, "yellow objects are remote keys", Color.white);
                }
                glDisable(GL_BLEND);
                glDisable(GL_TEXTURE_2D);
                //update Display
                Display.update();

                 if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
                    if (isOver) {
                        setup();
                    }
                }
            }
            Display.destroy();
        }
        
        public void updateGLU(PlayerLogic l) {
        	//set to "3D mode"
        	glDisable(GL_BLEND);
            glMatrixMode(GL_PROJECTION);
        	glLoadIdentity();
            glMatrixMode(GL_PROJECTION);
            
            //update camera
            gluPerspective(50f, 1.5f, .1f, 90f);
            if (isTesting) {
                gluLookAt(25, 60, 20, 25, 0, 20, 0, 0, 1);
            }
            else {
                gluLookAt(l.getX(), l.getY(), l.getZ(), (float) (l.getX() + Math.sin(l.getRotation())), l.getY() + (float) Math.tan(l.getViewAngle()), (float) (l.getZ() + Math.cos(l.getRotation())), 0, 1, 0);
            }
        }
        
        private void end() {
        	make2D();
            font.drawString(10, 360, "You won!! You found the crowbar and can now escape!", Color.white);
            font.drawString(400, 440, "Press R to restart", Color.white);
            isOver = true;
        }
        
        protected static void make2D() { //make 2D
            glEnable(GL_BLEND);
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(0.0f, Display.getWidth(), Display.getHeight(), 0.0f, 0.0f, 1.0f);
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();
        }
        
        public void drawCube(float x, float y, float z, float size) { //draw Cubes (graphic representation of objects)
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
        
        public void setupHouses(int h) { //setup map
        	
        	//setup one floor of a house with all closed doors
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


    	    // make copies of standard floor (NOT pointers)
    	    for (int i=0; i<walls.size(); i++) {
    	    	house1.add(walls.get(i).copy());
    	    	house2.add(walls.get(i).copy());
    	    	house3.add(walls.get(i).copy());
    	    	house4.add(walls.get(i).copy());
    	    	//move the copies across the map so that there is no overlay of floors
    	    	house1.get(i).addX(1);
    	    	house1.get(i).addZ(1);
    	    	house2.get(i).addX(26);
    	    	house2.get(i).addZ(1);
    	    	house3.get(i).addX(26);
    	    	house3.get(i).addZ(20);
    	    	house4.get(i).addX(1);
    	    	house4.get(i).addZ(20);
    	    }
    	    
    	    //open certain doors on certain floors to set up labyrinth
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

        public void beginEntering() { //checks if text is typed in
            isEnteringString = true;
        }

        @Override
        public void receiveAction(String passedData) { //receive action from objects (inter-class communication)
            if (passedData.equals("end")) {
                end();
            }
            else if (passedData.equals("enter string")) {
                beginEntering();
            }
        }


        float letterWidth = WALL_HEIGHT*((float)1/(float)5);
        public void drawLetterOnXYWall(Texture letter ,xyWall wall) { //draw letter images on walls


            glBindTexture(GL_TEXTURE_2D, letter.getTextureID());

            glEnable(GL_TEXTURE_2D);
            glBegin(GL_QUADS);
            glTexCoord2f(0, 0);
            glVertex3f(wall.getPosX() + wall.getWidth()/(float)2-letterWidth/(float)2, (float)WALL_HEIGHT/(float)2+letterWidth/(float)2, wall.getPosZ()+0.01f);
            glTexCoord2f(1, 0);
            glVertex3f(wall.getPosX() + wall.getWidth()/(float)2+letterWidth/(float)2, (float)WALL_HEIGHT/(float)2+letterWidth/(float)2, wall.getPosZ()+0.01f);
            glTexCoord2f(1, 1);
            glVertex3f(wall.getPosX() + wall.getWidth()/(float)2+letterWidth/(float)2, (float)WALL_HEIGHT/(float)2-letterWidth/(float)2, wall.getPosZ()+0.01f);
            glTexCoord2f(0, 1);
            glVertex3f(wall.getPosX() + wall.getWidth()/(float)2-letterWidth/(float)2, (float)WALL_HEIGHT/(float)2-letterWidth/(float)2, wall.getPosZ()+0.01f);

            glTexCoord2f(1, 0);
            glVertex3f(wall.getPosX() + wall.getWidth()/(float)2-letterWidth/(float)2, (float)WALL_HEIGHT/(float)2+letterWidth/(float)2, wall.getPosZ()-0.01f);
            glTexCoord2f(0, 0);
            glVertex3f(wall.getPosX() + wall.getWidth()/(float)2+letterWidth/(float)2, (float)WALL_HEIGHT/(float)2+letterWidth/(float)2, wall.getPosZ()-0.01f);
            glTexCoord2f(0, 1);
            glVertex3f(wall.getPosX() + wall.getWidth()/(float)2+letterWidth/(float)2, (float)WALL_HEIGHT/(float)2-letterWidth/(float)2, wall.getPosZ()-0.01f);
            glTexCoord2f(1, 1);
            glVertex3f(wall.getPosX() + wall.getWidth()/(float)2-letterWidth/(float)2, (float)WALL_HEIGHT/(float)2-letterWidth/(float)2, wall.getPosZ()-0.01f);

            glEnd();
            //glBindTexture(GL_TEXTURE_2D, 0);
            glDisable(GL_TEXTURE_2D);
        }

        public void drawLetterOnZYWall(Texture letter,zyWall wall) { //draw textures


            glBindTexture(GL_TEXTURE_2D, letter.getTextureID());

            glEnable(GL_TEXTURE_2D);
            glBegin(GL_QUADS);
            glTexCoord2f(1, 0);
            glVertex3f(wall.getPosX()+0.01f, (float)WALL_HEIGHT/(float)2+letterWidth/(float)2, wall.getPosZ() + wall.getWidth()/(float)2-letterWidth/(float)2);
            glTexCoord2f(0, 0);
            glVertex3f(wall.getPosX()+0.01f, (float)WALL_HEIGHT/(float)2+letterWidth/(float)2, wall.getPosZ() + wall.getWidth()/(float)2+letterWidth/(float)2);
            glTexCoord2f(0, 1);
            glVertex3f(wall.getPosX()+0.01f, (float)WALL_HEIGHT/(float)2-letterWidth/(float)2, wall.getPosZ() + wall.getWidth()/(float)2+letterWidth/(float)2);
            glTexCoord2f(1, 1);
            glVertex3f(wall.getPosX()+0.01f, (float)WALL_HEIGHT/(float)2-letterWidth/(float)2, wall.getPosZ() + wall.getWidth()/(float)2-letterWidth/(float)2);

            glTexCoord2f(0, 0);
            glVertex3f(wall.getPosX()-0.01f, (float)WALL_HEIGHT/(float)2+letterWidth/(float)2, wall.getPosZ() + wall.getWidth()/(float)2-letterWidth/(float)2);
            glTexCoord2f(1, 0);
            glVertex3f(wall.getPosX()-0.01f, (float)WALL_HEIGHT/(float)2+letterWidth/(float)2, wall.getPosZ() + wall.getWidth()/(float)2+letterWidth/(float)2);
            glTexCoord2f(1, 1);
            glVertex3f(wall.getPosX() - 0.01f, (float)WALL_HEIGHT/(float)2-letterWidth/(float)2, wall.getPosZ() + wall.getWidth()/(float)2+letterWidth/(float)2);
            glTexCoord2f(0, 1);
            glVertex3f(wall.getPosX() - 0.01f, (float) WALL_HEIGHT/(float)2-letterWidth/(float)2, wall.getPosZ() + wall.getWidth()/(float)2-letterWidth/(float)2);

            glEnd();
            //glBindTexture(GL_TEXTURE_2D, 0);
            glDisable(GL_TEXTURE_2D);
        }
}
