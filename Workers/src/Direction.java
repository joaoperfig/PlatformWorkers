
public class Direction {
	public static int left = 0;
	public static int up = 1;
	public static int right = 2;
	public static int down = 3;
	
	public static int getx(int direction) {
		if (direction == left) return -1;
		if (direction == up) return 0;
		if (direction == right) return 1;
		if (direction == down) return 0;
		return 999; //ERROR HAPPENED
	}
	
	public static int gety(int direction) {
		if (direction == left) return 0;
		if (direction == up) return -1;
		if (direction == right) return 0;
		if (direction == down) return 1;
		return 999; //ERROR HAPPENED
	}
	
	public static int getLeft(int direction) {
		if (direction == left) return down;
		if (direction == up) return left;
		if (direction == right) return up;
		if (direction == down) return right;
		return 999; //ERROR HAPPENED
	}
	
	public static int getRight(int direction) {
		if (direction == left) return up;
		if (direction == up) return right;
		if (direction == right) return down;
		if (direction == down) return left;
		return 999; //ERROR HAPPENED
	}
	
	public static int getBackx(int direction) {
		if (direction == left) return 1;
		if (direction == up) return 0;
		if (direction == right) return -1;
		if (direction == down) return 0;
		return 999; //ERROR HAPPENED
	}
	
	public static int getBacky(int direction) {
		if (direction == left) return 0;
		if (direction == up) return 1;
		if (direction == right) return 0;
		if (direction == down) return -1;
		return 999; //ERROR HAPPENED
	}
	
}
