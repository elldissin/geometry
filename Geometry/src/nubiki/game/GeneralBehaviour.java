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
}
