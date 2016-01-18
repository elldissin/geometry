package nubiki.game;

public class SlowEffect implements Effect {

	@Override
	public void applyTo(Collidable o) {
//		o.setSpeed((int)(o.getSpeed()*0.5));
		o.setMaxSpeed(2);
	}

}
