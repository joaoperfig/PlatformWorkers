
public class StateIAttemptCommunication extends StateI {
	
	private int confirmationTime=4;
	private int waited;
	StateI next;
	
	public StateIAttemptCommunication(StateI returnState) {
		next = returnState;
		waited = 0;
	}

	@Override
	public int process(Observations ob, StateIntelligence si) {
		if (waited==0) {
			System.out.println(si.identifier+"worker: I found someone I might talk to!");
		}
		if (!ob.workerInFront) {
			si.state = next;
			return WorkerIntelligence.NOTHING;
		} else{
			waited++;
			if(waited == confirmationTime) {
				System.out.println(si.identifier+"worker: So you do want to talk!");
				si.state = new StateIInitializeCommunication(next); //CHANGE ME
			}
			return WorkerIntelligence.NOTHING;
		}
	}

}
