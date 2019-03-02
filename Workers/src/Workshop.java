import java.util.ArrayList;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class Workshop {
	public static int width = 64;
	public static int height = 64;
	public int colors;
	public ArrayList<Shelf> shelves;
	public ArrayList<Worker> workers;
	public ArrayList<Box> boxes;
	public Ramp ramp;
	
	public Workshop() {
		super();
		this.colors = 4;
		shelves = new ArrayList<Shelf>();
		for (int i = 0; i<4; i++) {
			shelves.add(new Shelf(2, 2, i, 56, (15*i)+4));
		}
		workers = new ArrayList<Worker>();
		for (int i = 0; i<12; i++) {
			workers.add(new Worker(1, i+1, Direction.right));
		}
		ramp = new Ramp(5, 5, 20, 30);
		boxes = new ArrayList<Box>();
		for (int i=0; i<4; i++) {
			for (int j=0; j<3; j++) {
				int index = (i*3)+j;
				int x = index%5;
				int y = index/5;
				boxes.add(new Box(x+ramp.x, y+ramp.y, i));
			}
		}
	}
	
	public void update() {
		for (int i=0; i<workers.size(); i++) {
			workers.get(i).update(this);
		}
	}
}
