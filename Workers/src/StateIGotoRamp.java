
public class StateIGotoRamp extends StateI {

	@Override
	public int process(Observations ob, StateIntelligence si) {
		
		if (ob.workerInFront) {
			si.state = new StateIAttemptCommunication(this);
			return WorkerIntelligence.NOTHING;
		}
		
		if (ob.shelfInFront) { //found a shelf
			int co = ob.shelfInFrontColor;
			si.knowns.set(co, true);
			si.knownx.set(co, ob.frontx);
			si.knowny.set(co, ob.fronty);
		}
		
		if(si.knowsRamp) {
			if (ob.rampInFront) {
				si.knownRampPosx = ob.frontx;
				si.knownRampPosy = ob.fronty;
				si.state = new StateIRoamAroundRamp(12);
				return WorkerIntelligence.NOTHING;
			}
			int rampx = si.knownRampPosx;
			int rampy = si.knownRampPosy;
			//System.out.println("Rampx:"+rampx+" Rampy:"+rampy+" x:"+ob.x+" y:"+ob.y);
			int desiredDirection = Direction.directionTo(ob.x, ob.y, rampx, rampy);
			
			if (desiredDirection != ob.direction) {
				return WorkerIntelligence.ROTRIGHT;
			}
			if (!ob.obstacleInFront) {
				return WorkerIntelligence.STEP;
			} 
			si.state = new StateITemporaryAvoid(5, this);
			if(StateIntelligence.random.nextInt(100)>50) {
				return WorkerIntelligence.ROTLEFT;
			} else {
				return WorkerIntelligence.ROTRIGHT;
			}
		}
		else { //Does not know Ramp
			if (ob.rampInFront) { //FOUND IT
				si.knowsRamp = true;
				si.knownRampPosx = ob.frontx;
				si.knownRampPosy = ob.fronty;
				si.state = new StateIRoamAroundRamp(12);
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
