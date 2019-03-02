import java.util.Random;

public class StateIntelligence extends WorkerIntelligence {
	
	static Random random = new Random();
	static int turnchance = 10; //percent
	
	public StateI state;
	
	public int knownRampPosx;
	public int knownRampPosy;
	public int known0Posx;
	public int known0Posy;
	public int known1Posx;
	public int known1Posy;
	public int known2Posx;
	public int known2Posy;
	public int known3Posx;
	public int known3Posy;
	
	public StateIntelligence() {
		super();
	}

	@Override
	public int process(Observations ob) {
		return state.process(ob, this);
	}

}
