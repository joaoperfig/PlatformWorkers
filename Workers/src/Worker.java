import java.awt.Color;
import java.util.Random;

public class Worker {
	public int x;
	public int y;
	public int direction;
	private WorkerIntelligence intel;
	private boolean carrying;
	private Box carried;
	public Color color;

	public Worker(int x, int y, int direction) {
		super();
		this.x = x;
		this.y = y;
		this.direction = direction;
		carrying = false;
		intel = new StateIntelligence();//new BasicIntelligence();
		Random random = new Random();
		color = Color.getHSBColor(random.nextFloat(), 1f, 0.45f);
	}
	
	public void update(Workshop ws) {
		Observations obs = new Observations();
		int frontx = x+Direction.getx(direction);
		int fronty = y+Direction.gety(direction);
		obs.x = x;
		obs.y = y;
		obs.frontx = frontx;
		obs.fronty = fronty;
		obs.direction = direction;
		if((frontx<0)||(frontx>=Workshop.width)||(fronty<0)||(fronty>=Workshop.height)) {
			//System.out.println("Out of bounds");
			obs.obstacleInFront = true;
			obs.boxInFront = false;
			obs.rampInFront = false;
			obs.shelfInFront = false;
			obs.workerInFront = false;
		} else {
			obs.obstacleInFront = false;
			if (ws.ramp.hasCoordinate(frontx, fronty)) {
				obs.rampInFront = true;
			} else {
				obs.rampInFront = false;
			}
			obs.shelfInFront = false;
			for (int i=0; i<ws.shelves.size(); i++) {
				if (ws.shelves.get(i).hasCoordinate(frontx, fronty)) {
					obs.shelfInFront = true;
					obs.shelfInFrontColor = ws.shelves.get(i).color;
				}
			}
			obs.workerInFront = false;
			for (int i=0; i<ws.workers.size(); i++) {
				if ((ws.workers.get(i).x == frontx)&&(ws.workers.get(i).y == fronty)) {
					obs.workerInFront = true;
					obs.obstacleInFront = true;
					//System.out.println("Worker in front");
				}
			}
			obs.boxInFront = false;
			for (int i=0; i<ws.boxes.size(); i++) {
				if ((ws.boxes.get(i).x == frontx)&&(ws.boxes.get(i).y == fronty)) {
					obs.boxInFront = true;
					obs.boxInFrontColor = ws.boxes.get(i).color;
					obs.obstacleInFront = true;
					//System.out.println("Box in front");
				}
			}
		}
		if(carrying) {
			obs.holdingBox = true;
			obs.holdingBoxColor = carried.color;
		} else {
			obs.holdingBox = false;
		}
		
		
		int action = intel.process(obs);
		
		if (action == WorkerIntelligence.NOTHING) {
			return;
		} else if (action == WorkerIntelligence.STEP) {
			if(obs.obstacleInFront) {
				System.out.println("Error: Trying to move to obstacle");
				return;
			} else {
				x = frontx;
				y = fronty;
				if (carrying) {
					carried.x = x;
					carried.y = y;
				}
				return;
			}
		}else if (action == WorkerIntelligence.ROTLEFT) {
			direction = Direction.getLeft(direction);
		}else if (action == WorkerIntelligence.ROTRIGHT) {
			direction = Direction.getRight(direction);
			
		}else if (action == WorkerIntelligence.PICKUP) {
			//System.out.println("Pick up!");
			if((!obs.boxInFront)||(obs.workerInFront)) {
				System.out.println("Error: Trying to take a bad box!");
				return;
			} else {
				for (int i=0; i<ws.boxes.size(); i++) {
					if ((ws.boxes.get(i).x == frontx)&&(ws.boxes.get(i).y == fronty)) {
						carrying = true;
						carried = ws.boxes.get(i);
						carried.x = x;
						carried.y = y;
						return;
					}
				}
				return;
			}
		}else if (action == WorkerIntelligence.DROP) {
			//System.out.println("Drop!");
			if(obs.obstacleInFront) {
				System.out.println("Error: Trying to drop box onto obstacle");
				return;
			} else {
				carried.x = frontx;
				carried.y = fronty;
				carrying = false;
			}
		} else if (action == WorkerIntelligence.STEPBACK) {
			x = x+Direction.getBackx(direction);;
			y = y+Direction.getBacky(direction);
			if (carrying) {
				carried.x = x;
				carried.y = y;
			}
			return;
		}
		return;
	}
	
	public static int NOTHING = 0;
	public static int STEP = 1;
	public static int ROTLEFT = 2;
	public static int ROTRIGHT = 3;
	public static int PICKUP = 4;
	public static int DROP = 4;
	public static int STEPBACK = 5;
	
	
}
