
public class StateITalkToListenConfirm extends StateI {
	
	StateI next;
	
	public StateITalkToListenConfirm(StateI returnState) {
		next = returnState;
	}

	@Override
	public int process(Observations ob, StateIntelligence si) {
		if(ob.workerInFront) {
			System.out.println(si.identifier+"worker: Ok, you will indeed start talking.");
			si.state = new StateISecondListen(next);
			return WorkerIntelligence.NOTHING;
		} else {
			System.out.println(si.identifier+"worker: Oh, you were actually just going away. :(");
			si.state = next;
			return WorkerIntelligence.NOTHING;
		}
	}

}
