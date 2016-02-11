package nubiki.game;

public class SlowBehaviour extends GeneralBehaviour implements Behaviour {

	public SlowBehaviour(GameObject obj) {
		super(obj);
		slowable=true;
	}
	
	@Override
	public void doBehaviour(int percentage) {
		if (slowable) {
			ownerObject.setMaxSpeed((int)(ownerObject.getMaxSpeed()*(100-percentage)/100));
			slowable=false;
		}
	}

	@Override
	public boolean isSlowable() {
		// TODO Auto-generated method stub
		return slowable;
	}
}
