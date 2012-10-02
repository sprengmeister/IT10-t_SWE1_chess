package necarex.client.ui.drawer;

import necarex.common.pieces.PieceType;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class PieceDrawer implements Drawable{

	private SpriteSheet _spriteSheet;
	private PieceType _type;
	
	public PieceDrawer(PieceType type) throws SlickException{
		_spriteSheet = new SpriteSheet("sprites/pieces.png", 35, 50);
		_type = type;
	}
	
	@Override
	public void draw(Graphics g) {
		Image img = null;
		
		switch (_type) {
		case PAWN:
			img = _spriteSheet.getSprite(0, 0);
			break;
		}
		
		g.drawImage(img, 0, 200);
		
	}

}
