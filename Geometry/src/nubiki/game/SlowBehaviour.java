package nubiki.game;

public class SlowBehaviour extends GeneralBehaviour implements Behaviour {

	public SlowBehaviour(GameObject obj) {
		super(obj);
		slowable=true;
	}
	
	@Override
	public void doBehaviour() {
		if (slowable) {
			System.out.println("Object is being slowed now");
			ownerObject.setMaxSpeed((int)(ownerObject.getMaxSpeed()/2));
			slowable=false;
		}
	}

	@Override
	public boolean isSlowable() {
		// TODO Auto-generated method stub
		return slowable;
	}
}
