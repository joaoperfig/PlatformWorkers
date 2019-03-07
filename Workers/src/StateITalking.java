import java.util.ArrayList;

public class StateITalking extends StateI {
	
	StateI next;
	int totalProgress=0;
	public static int messageSize = (Workshop.colors+1)*((6*2)+1);
	ArrayList<Integer> message;
	private boolean hasmessage = false;
	boolean front;
	
	public StateITalking(StateI returnState) {
		message = new ArrayList<Integer>();
		front = true;
		next = returnState;
		
	}
	
	private ArrayList<Integer> generateMessage(StateIntelligence si){
		ArrayList<Integer> m = new ArrayList<Integer>();
		if(si.knowsRamp) {
			m.add(1);
			m.addAll(BinaryEncoding.encode6(si.knownRampPosx));
			m.addAll(BinaryEncoding.encode6(si.knownRampPosy));
		}
		else {
			m.add(0);
			m.addAll(BinaryEncoding.encode6(0));
			m.addAll(BinaryEncoding.encode6(0));
		}
		for (int c=0; c<Workshop.colors; c++) {
			if(si.knowns.get(c)) {
				m.add(1);
				m.addAll(BinaryEncoding.encode6(si.knownx.get(c)));
				m.addAll(BinaryEncoding.encode6(si.knowny.get(c)));
			}
			else {
				m.add(0);
				m.addAll(BinaryEncoding.encode6(0));
				m.addAll(BinaryEncoding.encode6(0));
			}
			
		}
		
		return m;
	}
	
	@Override
	public int process(Observations ob, StateIntelligence si) {
		if(!hasmessage) {
			message = generateMessage(si);
			hasmessage = true;
		}

		if (totalProgress==messageSize) {
			si.state = new StateITalkToListen(next);
			if(front) {
				return WorkerIntelligence.NOTHING;
			} else {
				front=false;
				return WorkerIntelligence.STEP;
			}
		}
		
		int bit = message.get(totalProgress);
		totalProgress++;
		
		if (totalProgress==messageSize) {
			String print = si.identifier+"worker: ";
			for (int i=0; i<messageSize; i++) {
				print = print+message.get(i);
			}
			System.out.println(print);
		}		
		
		
		if (front) {
			if (bit==1) {
				front = true;
				return WorkerIntelligence.NOTHING;
			} else {
				front = false;
				return WorkerIntelligence.STEPBACK;
			}
		} else {
			if (bit==1) {
				front = true;
				return WorkerIntelligence.STEP;
			} else {
				front = false;
				return WorkerIntelligence.NOTHING;
			}
		}
	}

}
