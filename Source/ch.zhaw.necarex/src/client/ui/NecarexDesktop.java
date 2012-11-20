package client.ui;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * Einstiegsklasse für Desktop Anwendungen. 
 * @author sprengmeister
 */
public class NecarexDesktop {

	/**
	 * @param args Startparameter
	 */
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 500;
		config.width = 600;
		//resizing verhindern, da sonst Figuren-Sprites zu gross/klein
		config.resizable = false;
		config.title = "necaREx";
		new LwjglApplication(new NecarexGame(), config);  
	}

}
