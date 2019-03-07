import java.util.ArrayList;

public class StateIListening extends StateI {

	StateI next;
	ArrayList<Integer> message;
	int counter = 0;
	
	public StateIListening(StateI returnState) {
		next = returnState;
		message = new ArrayList<Integer>();
	}
	
	private void processMessage(ArrayList<Integer> binary, StateIntelligence si) {
		for (int i=0; i<Workshop.colors+1; i++) {
			boolean knows = binary.get(i*13)==1;
			
			ArrayList<Integer> posxb = new ArrayList<Integer>();
			for (int j=0; j<6; j++) {
				posxb.add(binary.get((i*13)+1+j));
			}
			int posx = BinaryEncoding.decode6(posxb);
			
			ArrayList<Integer> posyb = new ArrayList<Integer>();
			for (int j=0; j<6; j++) {
				posyb.add(binary.get((i*13)+1+6+j));
			}
			int posy = BinaryEncoding.decode6(posyb);
			
			if (knows) {
				if (i == 0) {
					System.out.println(si.identifier+"worker: This means the ramp is at "+posx+", "+posy+"!");
					//RAMP
					if((!si.knowsRamp) || (si.InfoOverrides)) {
						si.knowsRamp = true;
						si.knownRampPosx = posx;
						si.knownRampPosy = posy;
					}
				} else {
					//Colored Shelf
					int c = i-1;
					System.out.println(si.identifier+"worker: This means the shelf #"+c+" is at "+posx+", "+posy+"!");
					if((!si.knowns.get(c)) || (si.InfoOverrides)) {
						si.knowns.set(c, true);
						si.knownx.set(c, posx);
						si.knowny.set(c, posy);
					}
				}
			}
		}
	}
	
	@Override
	public int process(Observations ob, StateIntelligence si) {
		if (counter ==0) {
			System.out.println(si.identifier+"worker: I started Listening!");
		}
		counter++;
		if(ob.workerInFront) {
			message.add(1);
		} else {
			message.add(0);
		}
		if(counter == StateITalking.messageSize) {
			String print = si.identifier+"worker: I heard ";
			for (int i=0; i<message.size(); i++) {
				print = print+message.get(i);
			}
			System.out.println(print);
			processMessage(message, si);
			si.state = new StateIListeningToTalking(next); 
			return WorkerIntelligence.NOTHING;
			
		}
		
		return 0;
	}
	


}
