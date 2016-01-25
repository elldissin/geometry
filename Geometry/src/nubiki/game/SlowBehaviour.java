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
			ownerObject.setSpeed((int)(ownerObject.getSpeed()/2));	
		}
	}

	@Override
	public boolean isSlowable() {
		// TODO Auto-generated method stub
		return slowable;
	}
}
