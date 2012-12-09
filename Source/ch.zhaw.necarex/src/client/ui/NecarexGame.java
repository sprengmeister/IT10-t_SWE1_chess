package client.ui;

import client.ui.drawing.GameScreen;
import client.viewmodel.ChessBoardViewModel;

import com.badlogic.gdx.Game;

import control.GameController;

/**
 * LibGDX Main Klasse, übernimmt das Draw/Update.
 * @author sprengmeister
 */
public class NecarexGame extends Game {
	
	private GameScreen gameScreen;
	private GameController controller;
	private ChessBoardViewModel viewModel;
	
	/**
	 * Erstellt und initialisiert Haupt-Draw-Klasse
	 * @param game Model mit den darzustellenden Informationen
	 */
	public NecarexGame(GameController controller, ChessBoardViewModel viewModel){
		this.controller = controller;
		this.viewModel = viewModel;
	}
	
	/**
	 * Initialisiert alle Ressourcen.
	 */
	@Override
	public void create() {
		gameScreen = new GameScreen(controller, viewModel);
		setScreen(gameScreen);
	}

	/**
	 * Räumt alle verwendeten Ressourcen wieder auf.
	 */
	@Override
	public void dispose() {
		gameScreen.dispose();
	}

	/**
	 * Pausiert das Spiel - Wird momentan nicht verwendet.
	 */
	@Override
	public void pause() {
	}

	/**
	 * Zeichnet die Oberfläche
	 */
	@Override
	public void render() {
		super.render();
	}

	/**
	 * Resized die Oberfläche - wird momentan nicht verwendet.
	 */
	@Override
	public void resize(int arg0, int arg1) {
	}

	/**
	 * Beendet Pausierung - wird momentan nicht verwendet.
	 */
	@Override
	public void resume() {
	}
}
