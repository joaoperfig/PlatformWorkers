import java.util.ArrayList;
import java.util.Random;

public class StateIntelligence extends WorkerIntelligence {
	
	public boolean InfoOverrides = true;
	
	static Random random = new Random();
	static int turnchance = 10; //percent
	
	public StateI state;
	
	public boolean knowsRamp;
	public int knownRampPosx;
	public int knownRampPosy;
	
	public ArrayList<Boolean> knowns;
	public ArrayList<Integer> knownx;
	public ArrayList<Integer> knowny;
	
	public int identifier;

	
	public StateIntelligence() {
		super();
		identifier = random.nextInt(1000);
		knowns = new  ArrayList<Boolean>(Workshop.colors);
		knownx = new  ArrayList<Integer>(Workshop.colors);
		knowny = new  ArrayList<Integer>(Workshop.colors);
		for(int i=0; i<Workshop.colors;i++) {
			knowns.add(false);
			knownx.add(0);
			knowny.add(0);
		}
		this.state = new StateIGotoRamp();
	}

	@Override
	public int process(Observations ob) {
		return state.process(ob, this);
	}

}
