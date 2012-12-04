package client.ui.drawing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import control.GameController;

public class MenuDrawer {
	
	private Skin skin = new Skin( Gdx.files.internal("assets/skin/uiskin.json" ));
	private GameController controller;
	private int initX;
	private int initY;

	public MenuDrawer(GameController controller, int initX, int initY){
		this.controller = controller;
		this.initX = initX;
		this.initY = initY;
	}
	
	public void draw(Table window) {
        TextButton startGameButton = new TextButton("Neues Spiel", skin);
        startGameButton.addListener(new EventListener()
        {
			public boolean handle(Event arg) {
				if (arg.getTarget() instanceof TextButton && ((TextButton)arg.getTarget()).isPressed()){
					controller.startNewGame();
					return true;
				}
				return false;
		} });
 
        Label addressLabel = new Label("Gegenspieler:", skin);
        List enemyList = new List(new Object[]{"Mensch", "Computer "}, skin);
        window.add(startGameButton).expandX().padLeft(initX).padBottom(20);
        window.row();
        window.add(addressLabel).padLeft(initX);
        window.row();
        window.add(enemyList).padLeft(initX).padBottom(initY);
	}
}
