
public class StateITalkToListen extends StateI {

	StateI next;
	int counter;
	
	public StateITalkToListen(StateI returnState) {
		next = returnState;
		counter = 0;
	}
	
	@Override
	public int process(Observations ob, StateIntelligence si) {
		if(counter == 0) {
			System.out.println(si.identifier+"worker: I finished talking, Imma wait for you now!");
		}

		if(counter==0 && !ob.workerInFront) {
			System.out.println(si.identifier+"worker: Oh, you went away.");
			si.state = next;
			return WorkerIntelligence.NOTHING;
		}
		
		if(counter>0 && !ob.workerInFront) {
			System.out.println(si.identifier+"worker: Will you start talking now?");
			si.state = new StateITalkToListenConfirm(next);
			return WorkerIntelligence.NOTHING;
		}
		if (counter>= 20) {
			System.out.println(si.identifier+"worker: Ok I waited too long, bye!");
			si.state = next;
			return WorkerIntelligence.NOTHING;
		}
			
		counter++;
		return WorkerIntelligence.NOTHING;
	}

}
