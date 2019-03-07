
public class StateIRoamAroundShelf extends StateI {
	
	private int c;
	private int stepsLeft;
	public StateIRoamAroundShelf (int color, int duration) {
		c = color;
		stepsLeft=duration;
	}

	@Override
	public int process(Observations ob, StateIntelligence si) {
		
		if (ob.workerInFront) {
			si.state = new StateIAttemptCommunication(this);
			return WorkerIntelligence.NOTHING;
		}
		
		if (ob.shelfInFront && (ob.shelfInFrontColor!=c) ) { //found a shelf
			int co = ob.shelfInFrontColor;
			si.knowns.set(co, true);
			si.knownx.set(co, ob.frontx);
			si.knowny.set(co, ob.fronty);
		}		
		
		stepsLeft--;
		if (stepsLeft == 0) {
			si.state = new StateIGotoShelf(c);
			return WorkerIntelligence.NOTHING;
		}
		if (!ob.boxInFront && ob.shelfInFront && !ob.workerInFront && (ob.shelfInFrontColor==c)) { //FOUND BOX
			si.state = new StateIGotoRamp();
			return WorkerIntelligence.DROP;
		}
		if (ob.obstacleInFront) {
			if(StateIntelligence.random.nextInt(100)>50) {
				return WorkerIntelligence.ROTLEFT;
			} else {
				return WorkerIntelligence.ROTRIGHT;
			}
		} else {
			if(StateIntelligence.random.nextInt(100)<=20) {
				if(StateIntelligence.random.nextInt(100)>50) {
					return WorkerIntelligence.ROTLEFT;
				} else {
					return WorkerIntelligence.ROTRIGHT;
				}
			}
			return WorkerIntelligence.STEP;
		}
	}

}
