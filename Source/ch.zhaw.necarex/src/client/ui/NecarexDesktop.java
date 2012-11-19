package client.ui;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

/**
 * Einstiegsklasse für Desktop Anwendungen. 
 * @author sprengmeister
 */
public class NecarexDesktop {

	/**
	 * @param args Startparameter
	 */
	public static void main(String[] args) {
		 new LwjglApplication(new NecarexGame(), "necaREx", 600, 500, false);  
	}

}
