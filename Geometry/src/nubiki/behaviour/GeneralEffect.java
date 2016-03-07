package nubiki.behaviour;

abstract public class GeneralEffect implements Effect {
	protected int amount;

	public GeneralEffect(int amount) {
		this.amount=amount;
	}

//	@Override
//	public void applyTo(Behaviour b) {
//		b.doBehaviour(amount);
//	}
}
