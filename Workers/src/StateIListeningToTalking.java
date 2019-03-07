public class StateIListeningToTalking extends StateI {
	
	StateI next;
	int counter = 0;
	
	public StateIListeningToTalking(StateI returnState) {
		next = returnState;
	}

	@Override
	public int process(Observations ob, StateIntelligence si) {
		counter++;
		if (counter<3) {
			if(!ob.workerInFront) {
				System.out.println(si.identifier+"worker: You went away without giving me my turn to talk?!");
				si.state = next;
				return WorkerIntelligence.NOTHING;
			}
			return WorkerIntelligence.NOTHING;
		}
		if (counter==3) {
			return WorkerIntelligence.STEPBACK;
		} else {
			System.out.println(si.identifier+"worker: My turn to talk!");
			si.state = new StateISecondTalking(next);
			return WorkerIntelligence.STEP;
		}
	}

}
