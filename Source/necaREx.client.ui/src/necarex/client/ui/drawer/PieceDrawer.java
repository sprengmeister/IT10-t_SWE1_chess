package necarex.client.ui.drawer;

import necarex.common.pieces.PieceType;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class PieceDrawer implements Drawable{

	private SpriteSheet _spriteSheet;
	private PieceType _type;
	private float _x;
	private float _y;
	private int _playerNr;
	
	public PieceDrawer(PieceType type, int playerNr, float x, float y) throws SlickException{
		_spriteSheet = new SpriteSheet("sprites/pieces.png", 33, 45);
		_type = type;
		_x = x;
		_y = y;
		_playerNr = playerNr;
	}
	
	@Override
	public void draw(Graphics g) {
		Image img = null;
		
		switch (_type) {
		case PAWN:
			img = _spriteSheet.getSprite(0, _playerNr);
			break;
		case KING:
			img = _spriteSheet.getSprite(1, _playerNr);
			break;
		case KNIGHT:
			img = _spriteSheet.getSprite(2, _playerNr);
			break;
		case ROOK:
			img = _spriteSheet.getSprite(3, _playerNr);
			break;
		case BISHOP:
			img = _spriteSheet.getSprite(4, _playerNr);
			break;
		case QUEEN:
			img = _spriteSheet.getSprite(5, _playerNr);
			break;
		}
		
		g.drawImage(img, _x, _y);
	}

}
