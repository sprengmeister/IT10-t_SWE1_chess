package client.ui.drawing;

import model.ChessBoard;
import model.Game;
import model.pieces.Piece;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;

public class GameScreen implements Screen {

	private BoardDrawer boardDrawer;
	private MenuDrawer menuDrawer;
	private PieceDrawer pieceDrawer;
	private Stage stage;
	private Table window;
	private SpriteBatch spriteBatch;
	private Game game;
	
	public GameScreen(Game game){
		this.spriteBatch = new SpriteBatch();
		this.game = game;
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
		//Weisser Background
		GLCommon gl = Gdx.gl;
		gl.glClearColor(255, 255, 255, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT | GL10.GL_STENCIL_BUFFER_BIT);

		menuDrawer.draw(window);
        boardDrawer.draw(spriteBatch);
        
        ChessBoard chessBoard = game.getChessBoard();
        Piece piece;
        for(int i=0;i<8;i++){
        	for (int j=0;j<8;j++){
        		piece = chessBoard.getField(j, i).getPiece();
        		if (piece != null){
        			pieceDrawer.draw(spriteBatch, piece);
        		}
        	}
        }
        
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
		stage = new Stage(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), true);
		Gdx.input.setInputProcessor(stage);
		
		//Schachbrett-Bereich festlegen
		Table boardTable = new Table();
		boardTable.x = 40;
		boardTable.y = 60;
		boardTable.height = 300;
		boardTable.width = 300;
		
		//Gesamter Zeichnungsbereich festlegen
		window = new Table();
		window.height = stage.height();
		window.width = stage.width();
		window.x = 0;
		window.y = 0;
		
		pieceDrawer = new PieceDrawer((int)(boardTable.x+0.95*BoardDrawer.LABEL_WIDTH), (int)(boardTable.y+0.75*BoardDrawer.LABEL_HEIGHT));
		boardDrawer = new BoardDrawer(boardTable);
		menuDrawer = new MenuDrawer();
	}

}
