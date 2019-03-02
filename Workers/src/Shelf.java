
public class Shelf {
	public int width;
	public int height;
	public int color;
	public int x;
	public int y;
	
	public Shelf(int width, int height, int color, int x, int y) {
		super();
		this.width = width;
		this.height = height;
		this.color = color;
		this.x = x;
		this.y = y;
	}
	
	public boolean hasCoordinate(int x, int y) {
		return (x >= this.x) && (x<this.x+width) && (y >= this.y) && (y<this.y+height);
	}

}
