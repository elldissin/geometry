package my.games.geometry.game.engine;

public class LogDisplayNotifier implements WorldChangeObserver {

	private boolean isChanged = false;

	@Override
	public void worldHasChanged() {
		isChanged = true;
	}

	public boolean isChanged() {
		boolean wasInState = isChanged;
		isChanged = false;
		return wasInState;
	}

}
