package client.ui;

import client.ui.drawing.GameScreen;

import com.badlogic.gdx.Game;

/**
 * LibGDX Main Klasse, übernimmt das Draw/Update.
 * @author sprengmeister
 */
public class NecarexGame extends Game {
	
	private GameScreen gameScreen;
	
	@Override
	public void create() {
		model.Game game = new model.Game();
		gameScreen = new GameScreen(game);
		setScreen(gameScreen);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
