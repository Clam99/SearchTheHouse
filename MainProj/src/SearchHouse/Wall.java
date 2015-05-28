package SearchHouse;

public interface Wall {//A wall to block the player

	public abstract float getPosX();
	
	public abstract float getPosZ();
	
	public abstract void addX(float x);//translate wall
	
	public abstract void addZ(float z);//translate wall
	
	public abstract float getWidth();
	
	public abstract float getHeight();

	public abstract Vector getSurfaceVector();
	
	public abstract Vector getNormalVector();
	
	public abstract void open(); //wall has a open door(-frame) now
	
	public abstract void close(); //wall doesn't have an opening now

	public abstract void draw(); //draw wall
	
	public abstract Wall copy(); //make new wall with same parameters
	
	public abstract boolean testCollision(float x, float z); //tests collisions

}
