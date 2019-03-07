
public class StateIStop extends StateI {

	@Override
	public int process(Observations ob, StateIntelligence si) {
		return WorkerIntelligence.NOTHING;
	}

}
