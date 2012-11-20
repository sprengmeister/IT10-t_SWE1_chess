package client.ui.drawing;

import java.awt.Point;

import model.pieces.Piece;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Der PieceDrawer übernimmt alle Zeichnungsaufgaben bezogen auf die Schachfiguren.
 * @author sprengmeister
 */
public class PieceDrawer {
	
	private Texture texture;
	private final int spriteWidth = 33;
	private final int spriteHeight = 41;
	private int initX;
	private int initY;
	
	public PieceDrawer(int initX, int initY){
		this.texture = new Texture(Gdx.files.internal("assets/sprites/piecesWide.png"));
		this.initX = initX;
		this.initY = initY;
	}
	
    /**
     * Zeichnet die Figur
     * @param p Figur die gezeichnet werden soll.
     */
	public void draw(SpriteBatch spriteBatch, Piece p){
		Point spriteCoord = p.getSpriteIndex();
		TextureRegion spriteRegion = new TextureRegion(this.texture, spriteCoord.x*spriteWidth, spriteCoord.y*spriteHeight, spriteWidth, spriteHeight);
		
		int x = (int) (p.getChessField().getCol() * BoardDrawer.FIELD_WIDTH);
		int y = (int) (p.getChessField().getRow() * BoardDrawer.FIELD_HEIGHT);
		
		spriteBatch.begin();
		spriteBatch.draw(spriteRegion, initX+x, initY+y);
		spriteBatch.end();
	}
}
