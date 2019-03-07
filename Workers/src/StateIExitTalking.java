
public class StateIExitTalking extends StateI {
	
	StateI next;
	int [] steps;
	int progress;
	
	public StateIExitTalking(StateI returnState) {
		progress = 0;
		steps = new int [] {1,0,0,2,0,0,2,0,0,1,3}; // 0=step, 1=right, 2=left, 3=end  // This makes them make a cute circle
		next = returnState;
	}


	@Override
	public int process(Observations ob, StateIntelligence si) {
		if(progress == 0) {
			System.out.println(si.identifier+"worker: Bye!");
		}
		
		int order = steps[progress];
		progress++;
		if (order == 0) { //step
			if(ob.obstacleInFront) {
				//OBSTACLE, CANNOT DO ROUTINE, ABORT
				si.state = next;
				return WorkerIntelligence.NOTHING;
			} else {
				return WorkerIntelligence.STEP;
			}
			
		} else if (order == 1) { //right
			return WorkerIntelligence.ROTRIGHT;
		} else if (order == 2) { //left
			return WorkerIntelligence.ROTLEFT;
		} else { //end
			si.state = next;
			return WorkerIntelligence.NOTHING;
		}
		
	}

}
