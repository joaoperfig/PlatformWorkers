
public class StateIStartListening extends StateI {

	StateI next;
		
	public StateIStartListening(StateI returnState) {
		next = returnState;
	}
	
	@Override
	public int process(Observations ob, StateIntelligence si) {
		if(ob.workerInFront) {
			//WORKER RETURNED SUCCESS!
			System.out.println(si.identifier+"worker: I understand you will talk first!");
			si.state = new StateIListening(next);
			return WorkerIntelligence.NOTHING;
		} else {
			//Failed
			System.out.println(si.identifier+"worker: Oh you went away? :(");
			si.state = next;
			return WorkerIntelligence.NOTHING;
		}
	}

}
