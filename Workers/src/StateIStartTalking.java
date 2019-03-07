
public class StateIStartTalking extends StateI {

	StateI next;
	
	public StateIStartTalking(StateI returnState) {
		next = returnState;
	}
	
	@Override
	public int process(Observations ob, StateIntelligence si) {
		System.out.println(si.identifier+"worker: I will talk first!");
		si.state = new StateITalking(next);
		return WorkerIntelligence.STEP;
	}

}
