package client.ui;

import client.viewmodel.ChessBoardViewModel;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import control.GameController;

/**
 * Einstiegsklasse für Desktop Anwendungen. 
 * @author sprengmeister
 */
public class NecarexDesktop {

	public static final int WINDOW_HEIGHT = 500;
	public static final int WINDOW_WIDTH = 600;
	
	/**
	 * Einstiegsmethode
	 * @param args Startparameter
	 */
	public static void main(String[] args) {
		//necaREx Controller
		ChessBoardViewModel vieModel = new ChessBoardViewModel();
		GameController controller = new GameController(vieModel);
		
		//LibGDX Applikation starten
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = WINDOW_HEIGHT;
		config.width = WINDOW_WIDTH;
		//resizing verhindern, da sonst Figuren-Sprites zu gross/klein
		config.resizable = false;
		config.title = "necaREx";
		new LwjglApplication(new NecarexGame(controller, vieModel), config);  
	}

}
