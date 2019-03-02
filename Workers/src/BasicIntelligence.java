import java.util.Random;

public class BasicIntelligence extends WorkerIntelligence {

	static Random random = new Random();
	static int turnchance = 5; //percent
	
	@Override
	public int process(Observations ob) {
		if(ob.holdingBox) {
			if (ob.shelfInFront) {
				if (ob.shelfInFrontColor == ob.holdingBoxColor) {
					if (!ob.obstacleInFront) {
						//System.out.println("right place!");
						return DROP;
					}
				}
			}
			if (ob.obstacleInFront) {
				if(random.nextInt(100)>50) {
					return ROTLEFT;
				} else {
					return ROTRIGHT;
				}
			}
			if(random.nextInt(100)<=turnchance) {
				if(random.nextInt(100)>50) {
					return ROTLEFT;
				} else {
					return ROTRIGHT;
				}
			}
			return STEP;
		} else { //NOT HOLDING A BOX
			if (ob.boxInFront) {
				if (ob.shelfInFront) {
					if (ob.shelfInFrontColor == ob.boxInFrontColor) {
						//LOOKING AT A CORRECTLY PLACED BOX
						if(random.nextInt(100)>50) {
							return ROTLEFT;
						} else {
							return ROTRIGHT;
						}
					}
				} //BOX IS NOT IN THE RIGHT SHELF
				if (ob.workerInFront) { //DO NOT STEAL BOX FROM OTHER WORKER
					if(random.nextInt(100)>50) {
						return ROTLEFT;
					} else {
						return ROTRIGHT;
					}
				}
				return PICKUP;
			}
			if (ob.obstacleInFront) {
				if(random.nextInt(100)>50) {
					return ROTLEFT;
				} else {
					return ROTRIGHT;
				}
			}
			if(random.nextInt(100)<=turnchance) {
				if(random.nextInt(100)>50) {
					return ROTLEFT;
				} else {
					return ROTRIGHT;
				}
			}
			return STEP;
		}
	}

}
