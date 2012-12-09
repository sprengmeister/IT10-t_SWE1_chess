package client.ui.drawing;

import java.awt.Point;

import model.ChessBoard;
import model.ChessField;
import model.Game;
import model.pieces.Piece;
import client.ui.NecarexDesktop;
import client.viewmodel.ChessBoardViewModel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import control.GameController;

/**
 * Game Screen - der Screen der das Game "in Aktion" darstellt.
 * @author Sebastian
 */
public class GameScreen implements Screen {

	private BoardDrawer boardDrawer;
	private MenuDrawer menuDrawer;
	private PieceDrawer pieceDrawer;
	
	private Table window;
	private Stage stage;
	private SpriteBatch spriteBatch;
	private Game game;
	private GameController controller;
	private ChessBoardViewModel viewModel;
	
	/**
	 * Initialisiert den GameScreen.
	 * @param controller Controller, der über Aktionen informiert wird.
	 * @param viewModel ViewModel mit den für die View relevanten Daten.
	 */
	public GameScreen(GameController controller, ChessBoardViewModel viewModel){
		this.spriteBatch = new SpriteBatch();
		this.game = controller.getGame();
		this.controller = controller;
		this.viewModel = viewModel;
	}
	
	/**
	 * Gibt die Ressourcen frei.
	 */
	@Override
	public void dispose() {
		boardDrawer.dispose();
	}

	/**
	 * Wird momentan nicht verwendet.
	 */
	@Override
	public void hide() {
	}

	/**
	 * Wird momentan nicht verwendet.
	 */
	@Override
	public void pause() {
	}

	/**
	 * Führ das Zeichnen aus.
	 */
	@Override
	public void render(float delta) {
		//Model/KI nachführen
		controller.update();
		
		ChessBoard chessBoard = game.getChessBoard();
		Piece piece;
		
		//Weisser Background
		GLCommon gl = Gdx.gl;
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT | GL10.GL_STENCIL_BUFFER_BIT);

		//Wurde "geklickt"? -> Infos ins ViewModel und Aktion an den Controller delegieren
		doClickReaction(chessBoard);
		
		//Schachbrett zeichnen
        boardDrawer.draw(spriteBatch);
        
        //Figuren zeichnen
        //Figuren von "oben" nach "unten" zeichnen, damit Überlappungen richtig gezeichnet werden
        for(int i=7;i>=0;i--){
        	for (int j=7;j>=0;j--){
        		piece = chessBoard.getField(j, i).getPiece();
        		if (piece != null){
        			pieceDrawer.draw(spriteBatch, piece);
        		}
        	}
        }

        menuDrawer.update(this.game);
        
        stage.addActor(window);
        stage.act( delta );
        stage.draw();
	}

	/***
	 * Prüft ob ein Klick auf das Schachbrett stattgefunden hat und reagiert entsprechend.
	 * @param chessBoard
	 */
	private void doClickReaction(ChessBoard chessBoard) {
		
		if (Gdx.input.isTouched() && Gdx.input.justTouched()){
			int inputX = Gdx.input.getX();
			//Beim Input ist die Y-Achse verglichen zum rendern verkehrt (0 ist oben statt unten), 
			//darum dies hier entsprechend ändern
			int inputY = NecarexDesktop.WINDOW_HEIGHT-Gdx.input.getY();
			Point selectedFieldPoint = boardDrawer.getFieldCoordinates(inputX, inputY);
			//Wurde innerhalb des Schachbretts geklickt?
			if (selectedFieldPoint != null){
				ChessField selectedField = chessBoard.getField(selectedFieldPoint.x, selectedFieldPoint.y);
				//Ein Klick kann folgende Bedeutungen haben:
				// - Figur selektieren -> erster Klick auf ein Feld
				// - Figur deselektieren -> zweiter Klick auf selbes Feld
				// - Figur bewegen -> Zweiter Klick auf anderes Feld
				if (viewModel.getSelectedField() != null && viewModel.getReachableFields().contains(selectedField)){
					//Figur bewegen
					controller.doTurn(viewModel.getSelectedField(), selectedField);
				} else {
					//Figur darf nur ausgewählt werden, wenn sie dem Spieler gehört
					if (selectedField.getPiece()!=null && selectedField.getPiece().getOwner()==game.getActivePlayer()){
						Piece selctedPiece = selectedField.getPiece();
						if (viewModel.getSelectedField() != null 
								&& selctedPiece == viewModel.getSelectedField().getPiece()){
							//Figur deselektieren
							viewModel.resetRound();
						} else {
							//Feld selektieren
							viewModel.setSelectedField(selectedField);
							viewModel.setReachableFields(selctedPiece.getPossibleFields());
						}
					}
				}
			}
		}
	}

	/**
	 * Führt einen Resize durch.
	 */
	@Override
	public void resize(int width, int height) {
        stage.setViewport( width, height, true );
        stage.clear();
	}

	/**
	 * Wird momentan nicht verwendet.
	 */
	@Override
	public void resume() {
	}

	/**
	 * Zeigt den Screen initial.
	 */
	@Override
	public void show() {
		stage = new Stage(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), false);
		Gdx.input.setInputProcessor(stage);
		
		//Schachbrett-Bereich festlegen
		Table boardTable = new Table();
		boardTable.setX(100);
		boardTable.setY(100);
		boardTable.setHeight(300);
		boardTable.setWidth(300);
		
		//Gesamter Zeichnungsbereich festlegen
		window = new Table();
		window.setFillParent(true);
		window.setHeight(Gdx.graphics.getHeight());
		window.setWidth(Gdx.graphics.getWidth());
		
		pieceDrawer = new PieceDrawer((int)(boardTable.getX()+0.95*BoardDrawer.LABEL_WIDTH), (int)(boardTable.getY()+0.75*BoardDrawer.LABEL_HEIGHT));
		boardDrawer = new BoardDrawer(boardTable, viewModel);
		menuDrawer = new MenuDrawer(this.controller, this.viewModel, 450, -60);
		
		menuDrawer.draw(window);
		
		stage.addActor(window);
	}

}
