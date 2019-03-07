
public class StateIGotoShelf extends StateI {
	
	private int c;
	public StateIGotoShelf (int color) {
		c = color;
	}

	@Override
	public int process(Observations ob, StateIntelligence si) {
	
		
		if (ob.workerInFront) {
			si.state = new StateIAttemptCommunication(this);
			return WorkerIntelligence.NOTHING;
		}
		
		if (ob.shelfInFront && (ob.shelfInFrontColor!=c)) { //found a shelf
			int co = ob.shelfInFrontColor;
			si.knowns.set(co, true);
			si.knownx.set(co, ob.frontx);
			si.knowny.set(co, ob.fronty);
		}		
		
		if(si.knowns.get(c)) {
			if (ob.shelfInFront && (ob.shelfInFrontColor==c)) {
				si.state = new StateIRoamAroundShelf(c, 8);
				return WorkerIntelligence.NOTHING;
			}
			int shelfx = si.knownx.get(c);
			int shelfy = si.knowny.get(c);
			
			int desiredDirection = Direction.directionTo(ob.x, ob.y, shelfx, shelfy);
			
			if (desiredDirection != ob.direction) {
				return WorkerIntelligence.ROTRIGHT;
			}
			if (!ob.obstacleInFront) {
				return WorkerIntelligence.STEP;
			} 
			si.state = new StateITemporaryAvoid(8, this);
			if(StateIntelligence.random.nextInt(100)>50) {
				return WorkerIntelligence.ROTLEFT;
			} else {
				return WorkerIntelligence.ROTRIGHT;
			}
		}
		else { //Does not know Shelf of c
			if (ob.shelfInFront && (ob.shelfInFrontColor==c)) { //FOUND IT
				si.knowns.set(c, true);
				si.knownx.set(c, ob.frontx);
				si.knowny.set(c, ob.fronty);
				si.state = new StateIRoamAroundShelf(c, 8);
				return WorkerIntelligence.NOTHING;
			}
			else { //Look for it
				if (ob.obstacleInFront) {
					if(StateIntelligence.random.nextInt(100)>50) {
						return WorkerIntelligence.ROTLEFT;
					} else {
						return WorkerIntelligence.ROTRIGHT;
					}
				} else {
					if(StateIntelligence.random.nextInt(100)<=10) {
						if(StateIntelligence.random.nextInt(100)>50) {
							return WorkerIntelligence.ROTLEFT;
						} else {
							return WorkerIntelligence.ROTRIGHT;
						}
					}
					return WorkerIntelligence.STEP;
				}
				
			}
		}

	}

}
