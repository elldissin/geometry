package my.games.geometry.exceptions;

/**
 * @author LCrystal Exception which happens at attempt to create illegal game object position
 */
public class WrongPositionException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WrongPositionException() {
		super("Negative position assignment attempt!");
	}

}
