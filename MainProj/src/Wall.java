public interface Wall {

	public abstract float getPosX();
	
	public abstract float getPosZ();
	
	public abstract void open();

	public abstract void draw();
	
	public abstract boolean testCollision(float x, float z);

}
