package my.games.geometry.exceptions;

/**
 * @author LCrystal Exception which happens when given game object is null
 */
public class NullGameObjectException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NullGameObjectException() {
		super("Given GameObject instance is null!");
	}

}
