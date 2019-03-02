
public class Ramp {
	public int width;
	public int height;
	public int x;
	public int y;
	
	public Ramp(int width, int height, int x, int y) {
		super();
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}
	
	public boolean hasCoordinate(int x, int y) {
		return (x >= this.x) && (x<this.x+width) && (y >= this.y) && (y<this.y+height);
	}

}
