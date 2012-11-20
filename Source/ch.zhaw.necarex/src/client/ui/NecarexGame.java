package client.ui;

import client.ui.drawing.GameScreen;

import com.badlogic.gdx.Game;

import control.GameController;

/**
 * LibGDX Main Klasse, übernimmt das Draw/Update.
 * @author sprengmeister
 */
public class NecarexGame extends Game {
	
	private GameScreen gameScreen;
	private GameController controller;
	
	/**
	 * Erstellt und initialisiert Haupt-Draw-Klasse
	 * @param game Model mit den darzustellenden Informationen
	 */
	public NecarexGame(GameController controller){
		this.controller = controller;
	}
	
	@Override
	public void create() {
		gameScreen = new GameScreen(controller);
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
