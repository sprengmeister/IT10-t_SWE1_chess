package necarex.client.ui;

import necarex.client.ui.drawer.FieldDrawer;
import necarex.client.ui.drawer.PieceDrawer;
import necarex.common.pieces.PieceType;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;

public class SimpleTest extends BasicGame {

    public SimpleTest() {
        super("SimpleTest");
    }
    
    @Override
    public void init(GameContainer container) throws SlickException {}

    @Override
    public void update(GameContainer container, int delta)
            throws SlickException {}

    @Override
    public void render(GameContainer container, Graphics g)
            throws SlickException {
    	g.setBackground(Color.white);
        g.drawString("Hello, Slick world!", 0, 100);
        
        FieldDrawer fd = new FieldDrawer(0, 200);
        fd.draw(g);
        
        PieceDrawer pd = new PieceDrawer(PieceType.PAWN);
        pd.draw(g);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer app = new AppGameContainer(new SimpleTest());
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}