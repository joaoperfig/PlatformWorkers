
public class StateIInitializeCommunication extends StateI {
	
	StateI next;
	int backprob=10;
	
	public StateIInitializeCommunication(StateI returnState) {
		next = returnState;
	}

	@Override
	public int process(Observations ob, StateIntelligence si) {
		if(!ob.workerInFront) {
			System.out.println(si.identifier+"worker: Are you about to start talking?");
			si.state = new StateIStartListening(next);
			return WorkerIntelligence.NOTHING;
		}
		if(StateIntelligence.random.nextInt(100)<=backprob) {
			System.out.println(si.identifier+"worker: I want to talk first!");
			si.state = new StateIStartTalking(next);
			return WorkerIntelligence.STEPBACK;
		}
		return WorkerIntelligence.NOTHING;
	}

}
