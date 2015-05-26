public interface Wall {
	
	public abstract void draw();
	
	public abstract boolean testCollision(float x, float z);

	public abstract float getPosx();
	public abstract float getPosz();
	public abstract Vector getSurfaceVector();
}
