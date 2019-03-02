
public abstract class WorkerIntelligence {
	public static int NOTHING = 0;
	public static int STEP = 1;
	public static int ROTLEFT = 2;
	public static int ROTRIGHT = 3;
	public static int PICKUP = 4;
	public static int DROP = 5;
	public static int STEPBACK = 6;
	
	public abstract int process(Observations ob) ;
}
