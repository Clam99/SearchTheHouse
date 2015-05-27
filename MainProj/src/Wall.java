public interface Wall {

	public abstract float getPosX();
	
	public abstract float getPosZ();
	
	public abstract void addX(float x);
	
	public abstract void addZ(float z);
	
	public abstract float getWidth();
	
	public abstract float getHeight();

	public abstract Vector getSurfaceVector();
	
	public abstract Vector getNormalVector();
	
	public abstract void open();
	
	public abstract void close();

	public abstract void draw();
	
	public abstract Wall copy();
	
	public abstract boolean testCollision(float x, float z);

}
