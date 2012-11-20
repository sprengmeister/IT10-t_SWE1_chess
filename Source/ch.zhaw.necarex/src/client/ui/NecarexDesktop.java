package client.ui;

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
	 * @param args Startparameter
	 */
	public static void main(String[] args) {
		//necaREx Controller
		GameController controller = new GameController();
		
		//LibGDX Applikation starten
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = WINDOW_HEIGHT;
		config.width = WINDOW_WIDTH;
		//resizing verhindern, da sonst Figuren-Sprites zu gross/klein
		config.resizable = false;
		config.title = "necaREx";
		new LwjglApplication(new NecarexGame(controller), config);  
	}

}
