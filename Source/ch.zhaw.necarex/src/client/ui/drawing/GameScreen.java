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
	
	public GameScreen(GameController controller, ChessBoardViewModel viewModel){
		this.spriteBatch = new SpriteBatch();
		this.game = controller.getGame();
		this.controller = controller;
		this.viewModel = viewModel;
	}
	
	@Override
	public void dispose() {
		boardDrawer.dispose();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
		ChessBoard chessBoard = game.getChessBoard();
		Piece piece;
		
		//Weisser Background
		GLCommon gl = Gdx.gl;
		gl.glClearColor(255, 255, 255, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT | GL10.GL_STENCIL_BUFFER_BIT);

		if (Gdx.input.isTouched()){
			int inputX = Gdx.input.getX();
			//Beim Input ist die Y-Achse verglichen zum rendern verkehrt (0 ist oben statt unten), 
			//darum dies hier entsprechend ändern
			int inputY = NecarexDesktop.WINDOW_HEIGHT-Gdx.input.getY();
			Point selectedFieldPoint = boardDrawer.getFieldCoordinates(inputX, inputY);
			if (selectedFieldPoint != null){
				ChessField selectedField = chessBoard.getField(selectedFieldPoint.x, selectedFieldPoint.y);
				//Wurde eine Figur ausgewählt, oder eine Figur bewegt?
				if (viewModel.getSelectedField() != null && viewModel.getReachableFields().contains(selectedField)){
					//Bewegung ausführen
					controller.doTurn(viewModel.getSelectedField(), selectedField);
				} else {
					//Figur darf nur ausgewählt werden, wenn Spieler am Zug ist
					if (selectedField.getPiece()!=null && selectedField.getPiece().getOwner()==game.getActivePlayer()){
						//Feld selektieren
						Piece selctedPiece = selectedField.getPiece();
						if (selctedPiece != null) {
							//Nur Felder mit einer Figur darauf können markiert werden
							viewModel.setSelectedField(selectedField);
							viewModel.setReachableFields(selctedPiece.getPossibleFields());
						}
					}
				}
			}
		}
		
		//menuDrawer.draw(window);
        boardDrawer.draw(spriteBatch);
        
        //Figuren von "oben" nach "unten" zeichnen, damit Überlappungen richtig gezeichnet werden
        for(int i=7;i>=0;i--){
        	for (int j=7;j>=0;j--){
        		piece = chessBoard.getField(j, i).getPiece();
        		if (piece != null){
        			pieceDrawer.draw(spriteBatch, piece);
        		}
        	}
        }
        
//        spriteBatch.begin();
//        window.draw(spriteBatch, 1);
//        spriteBatch.end();
        
        stage.addActor(window);
        stage.act( delta );
        stage.draw();
	}

	@Override
	public void resize(int width, int height) {
        stage.setViewport( width, height, true );
        stage.clear();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		stage = new Stage(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), false);
		Gdx.input.setInputProcessor(stage);
		
		//Schachbrett-Bereich festlegen
		Table boardTable = new Table();
		boardTable.setX(40);
		boardTable.setY(60);
		boardTable.setHeight(300);
		boardTable.setWidth(300);
		
		//Gesamter Zeichnungsbereich festlegen
		window = new Table();
		window.setFillParent(true);
		window.setHeight(Gdx.graphics.getHeight());
		window.setWidth(Gdx.graphics.getWidth());
		
		pieceDrawer = new PieceDrawer((int)(boardTable.getX()+0.95*BoardDrawer.LABEL_WIDTH), (int)(boardTable.getY()+0.75*BoardDrawer.LABEL_HEIGHT));
		boardDrawer = new BoardDrawer(boardTable, viewModel);
		menuDrawer = new MenuDrawer(this.controller);
		
		menuDrawer.draw(window);
		stage.addActor(window);
	}

}
