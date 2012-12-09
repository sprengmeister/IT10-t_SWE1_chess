package client.ui.drawing;

import model.Game;
import model.PlayerColor;
import client.viewmodel.ChessBoardViewModel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import control.GameController;

/**
 * Zeichnet das Menu und stellt die Spielinformationen dar.
 * @author Sebastian
 */
public class MenuDrawer {
	
	private Skin skin = new Skin( Gdx.files.internal("assets/skin/uiskin.json" ));
	private GameController controller;
	private ChessBoardViewModel viewModel;
	
	private int initX;
	private int initY;

	private Label numberOfTurnsValue;
	private Label activePlayerValue;
	private Label wonNotificationValue;
	
	/**
	 * Initialisiert den Drawer.
	 * @param controller Kontroller der über Aktionen informiert wird.
	 * @param viewModel Model mit den View-relevanten Daten.
	 * @param initX Initiale X-Koordinate.
	 * @param initY Initiale Y-Koordinate.
	 */
	public MenuDrawer(GameController controller, ChessBoardViewModel viewModel, int initX, int initY){
		this.controller = controller;
		this.initX = initX;
		this.initY = initY;
		this.viewModel = viewModel;
	}
	
	/**
	 * Updated die Spielinformationen.
	 * @param model Model mit den relevanten Daten.
	 */
	public void update(Game model){
		numberOfTurnsValue.setText(String.valueOf(model.getTurnList().size()));
		activePlayerValue.setText(model.getActivePlayer().getColor() == PlayerColor.WHITE ? "Weiss" : "Schwarz");
		if (viewModel.getPlayerWonColor() != null){
			wonNotificationValue.setText(viewModel.getPlayerWonColor() == PlayerColor.WHITE ? "Weiss" : "Schwarz");
		}else {
			wonNotificationValue.setText("-");
		}
	}
	
	/**
	 * Zeichnet die Controls und Labels.
	 * @param window Bereich in den gezeichnet wird.
	 */
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
        enemyList.addListener(new EventListener(){
        	public boolean handle(Event arg){
        		if (arg.getTarget() instanceof List){
        			viewModel.setArtificalIntelligenceEnabled(((List)arg.getTarget()).getSelectedIndex()==1);
        			return true;
        		}
        		return false;
        	}
        });
        
        Label numberOfTurnsLabel = new Label("Anzahl Zuege:", skin);
        numberOfTurnsValue = new Label("0", skin);
        numberOfTurnsValue.setFontScale(0.95f);
        
        Label activePlayerLabel = new Label("Am Zug:", skin);
        activePlayerValue = new Label("Weiss", skin);
        activePlayerValue.setFontScale(0.95f);
        
        Label wonNotificationLabel = new Label("Gewonnen:", skin);
        wonNotificationValue = new Label("-", skin);
        wonNotificationValue.setFontScale(0.95f);

        window.padBottom(initY);
        window.padLeft(initX);
        window.add(startGameButton).left().expandX().padBottom(20);
        window.row();
        window.add(addressLabel).left();
        window.row();
        window.add(enemyList).left().padBottom(20);
        window.row();
        window.add(activePlayerLabel).left();
        window.row();
        window.add(activePlayerValue).left().padBottom(20);
        window.row();
        window.add(numberOfTurnsLabel).left();
        window.row();
        window.add(numberOfTurnsValue).left().padBottom(20);
        window.row();
        window.add(wonNotificationLabel).left();
        window.row();
        window.add(wonNotificationValue).left().padBottom(20);
	}
}
