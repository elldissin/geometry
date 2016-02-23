package nubiki.game;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class GeneralBehaviour {
	protected GameObject ownerObject;
	public boolean slowable;
	public boolean vulnerable;
	
	protected GeneralBehaviour(GameObject obj) {
		ownerObject=obj;
		slowable=false;
		vulnerable=false;
	}
	
	public void doBehaviour(int amount) {
		if (vulnerable) {
			System.out.println("Player got hit by " + amount +" hp");
			ownerObject.getHit(amount);
		}
		if (slowable) {
			ownerObject.setMaxSpeed((int)(ownerObject.getMaxSpeed()*(100-amount)/100));
			slowable=false;
		}
	}
}
