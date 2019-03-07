
public class StateITemporaryAvoid extends StateI {

	int stepsLeft;
	StateI next;
	public StateITemporaryAvoid(int duration, StateI returnState) {
		stepsLeft = duration;
		next = returnState;
	}
	
	@Override
	public int process(Observations ob, StateIntelligence si) {
		
		if (ob.workerInFront) {
			si.state = new StateIAttemptCommunication(this);
			return WorkerIntelligence.NOTHING;
		}
		
		if (ob.shelfInFront ) { //found a shelf
			int co = ob.shelfInFrontColor;
			si.knowns.set(co, true);
			si.knownx.set(co, ob.frontx);
			si.knowny.set(co, ob.fronty);
		}
		
		stepsLeft--;
		if (stepsLeft == 0) {
			si.state = next;
			return WorkerIntelligence.NOTHING;
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
