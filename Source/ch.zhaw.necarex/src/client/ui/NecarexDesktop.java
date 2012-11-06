package client.ui;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class NecarexDesktop {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 new LwjglApplication(new NecarexGame(), "necaREx", 480, 320, false);  
	}

}
