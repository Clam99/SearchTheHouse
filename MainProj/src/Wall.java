public interface Wall {
	
	public abstract void draw();
	
	public abstract boolean testCollision(float x, float z);

	public abstract float getPosX();
	public abstract float getPosZ();
}
