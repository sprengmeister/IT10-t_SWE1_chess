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
        g.drawString("necaREx, Porno Schach!", 150, 50);
        
        float fieldInitX = 100;
        float fieldInitY = 100;
        float labelWidth = 30;
        float labelHeight = 10;
        float pieceWidth = 30;
        float pieceHeight = 30;
        
        FieldDrawer fd = new FieldDrawer(fieldInitX, fieldInitY);
        fd.draw(g);
        
        PieceDrawer wpd1 = new PieceDrawer(PieceType.PAWN, 0, fieldInitX + labelWidth, fieldInitY + labelHeight + pieceHeight);
        PieceDrawer wpd2 = new PieceDrawer(PieceType.PAWN, 0, fieldInitX + labelWidth + 1*pieceWidth, fieldInitY + labelHeight + pieceHeight);
        PieceDrawer wpd3 = new PieceDrawer(PieceType.PAWN, 0, fieldInitX + labelWidth + 2*pieceWidth, fieldInitY + labelHeight + pieceHeight);
        PieceDrawer wpd4 = new PieceDrawer(PieceType.PAWN, 0, fieldInitX + labelWidth + 3*pieceWidth, fieldInitY + labelHeight + pieceHeight);
        PieceDrawer wpd5 = new PieceDrawer(PieceType.PAWN, 0, fieldInitX + labelWidth + 4*pieceWidth, fieldInitY + labelHeight + pieceHeight);
        PieceDrawer wpd6 = new PieceDrawer(PieceType.PAWN, 0, fieldInitX + labelWidth + 5*pieceWidth, fieldInitY + labelHeight + pieceHeight);
        PieceDrawer wpd7 = new PieceDrawer(PieceType.PAWN, 0, fieldInitX + labelWidth + 6*pieceWidth, fieldInitY + labelHeight + pieceHeight);
        PieceDrawer wpd8 = new PieceDrawer(PieceType.PAWN, 0, fieldInitX + labelWidth + 7*pieceWidth, fieldInitY + labelHeight + pieceHeight);
        PieceDrawer wrd1 = new PieceDrawer(PieceType.ROOK, 0, fieldInitX + labelWidth, fieldInitY + labelHeight);
        PieceDrawer wkd1 = new PieceDrawer(PieceType.KNIGHT, 0, fieldInitX + labelWidth + 1*pieceWidth, fieldInitY + labelHeight);
        PieceDrawer wbd1 = new PieceDrawer(PieceType.BISHOP, 0, fieldInitX + labelWidth + 2*pieceWidth, fieldInitY + labelHeight);
        PieceDrawer wkid1 = new PieceDrawer(PieceType.KING, 0, fieldInitX + labelWidth + 3*pieceWidth, fieldInitY + labelHeight);
        PieceDrawer wqd1 = new PieceDrawer(PieceType.QUEEN, 0, fieldInitX + labelWidth + 4*pieceWidth, fieldInitY + labelHeight);
        PieceDrawer wbd2 = new PieceDrawer(PieceType.BISHOP, 0, fieldInitX + labelWidth + 5*pieceWidth, fieldInitY + labelHeight);
        PieceDrawer wkd2 = new PieceDrawer(PieceType.KNIGHT, 0, fieldInitX + labelWidth + 6*pieceWidth, fieldInitY + labelHeight);
        PieceDrawer wrd2 = new PieceDrawer(PieceType.ROOK, 0, fieldInitX + labelWidth + 7*pieceWidth, fieldInitY + labelHeight);
        
        PieceDrawer bpd1 = new PieceDrawer(PieceType.PAWN, 1, fieldInitX + labelWidth, fieldInitY + 2*labelHeight + 6*pieceHeight);
        PieceDrawer bpd2 = new PieceDrawer(PieceType.PAWN, 1, fieldInitX + labelWidth + 1*pieceWidth, fieldInitY + 2*labelHeight + 6*pieceHeight);
        PieceDrawer bpd3 = new PieceDrawer(PieceType.PAWN, 1, fieldInitX + labelWidth + 2*pieceWidth, fieldInitY + 2*labelHeight + 6*pieceHeight);
        PieceDrawer bpd4 = new PieceDrawer(PieceType.PAWN, 1, fieldInitX + labelWidth + 3*pieceWidth, fieldInitY + 2*labelHeight + 6*pieceHeight);
        PieceDrawer bpd5 = new PieceDrawer(PieceType.PAWN, 1, fieldInitX + labelWidth + 4*pieceWidth, fieldInitY + 2*labelHeight + 6*pieceHeight);
        PieceDrawer bpd6 = new PieceDrawer(PieceType.PAWN, 1, fieldInitX + labelWidth + 5*pieceWidth, fieldInitY + 2*labelHeight + 6*pieceHeight);
        PieceDrawer bpd7 = new PieceDrawer(PieceType.PAWN, 1, fieldInitX + labelWidth + 6*pieceWidth, fieldInitY + 2*labelHeight + 6*pieceHeight);
        PieceDrawer bpd8 = new PieceDrawer(PieceType.PAWN, 1, fieldInitX + labelWidth + 7*pieceWidth, fieldInitY + 2*labelHeight + 6*pieceHeight);
        PieceDrawer brd1 = new PieceDrawer(PieceType.ROOK, 1, fieldInitX + labelWidth, fieldInitY + 2*labelHeight + 7*pieceHeight ); 
        PieceDrawer bkd1 = new PieceDrawer(PieceType.KNIGHT, 1, fieldInitX + labelWidth + 1*pieceWidth, fieldInitY + 2*labelHeight+ 7*pieceHeight);
        PieceDrawer bbd1 = new PieceDrawer(PieceType.BISHOP, 1, fieldInitX + labelWidth + 2*pieceWidth, fieldInitY + 2*labelHeight+ 7*pieceHeight);
        PieceDrawer bkid1 = new PieceDrawer(PieceType.KING, 1, fieldInitX + labelWidth + 3*pieceWidth, fieldInitY + 2*labelHeight+ 7*pieceHeight);
        PieceDrawer bqd1 = new PieceDrawer(PieceType.QUEEN, 1, fieldInitX + labelWidth + 4*pieceWidth, fieldInitY + 2*labelHeight+ 7*pieceHeight);
        PieceDrawer bbd2 = new PieceDrawer(PieceType.BISHOP, 1, fieldInitX + labelWidth + 5*pieceWidth, fieldInitY + 2*labelHeight+ 7*pieceHeight);
        PieceDrawer bkd2 = new PieceDrawer(PieceType.KNIGHT, 1, fieldInitX + labelWidth + 6*pieceWidth, fieldInitY + 2*labelHeight+ 7*pieceHeight);
        PieceDrawer brd2 = new PieceDrawer(PieceType.ROOK, 1, fieldInitX + labelWidth + 7*pieceWidth, fieldInitY + 2*labelHeight+ 7*pieceHeight);
        
        
        wrd1.draw(g);
        wkd1.draw(g);
        wbd1.draw(g);
        wkid1.draw(g);
        wqd1.draw(g);
        wbd2.draw(g);
        wkd2.draw(g);
        wrd2.draw(g);
        wpd1.draw(g);
        wpd2.draw(g);
        wpd3.draw(g);
        wpd4.draw(g);
        wpd5.draw(g);
        wpd6.draw(g);
        wpd7.draw(g);
        wpd8.draw(g);
        

        brd1.draw(g);
        bkd1.draw(g);
        bbd1.draw(g);
        bkid1.draw(g);
        bqd1.draw(g);
        bbd2.draw(g);
        bkd2.draw(g);
        brd2.draw(g);
        bpd1.draw(g);
        bpd2.draw(g);
        bpd3.draw(g);
        bpd4.draw(g);
        bpd5.draw(g);
        bpd6.draw(g);
        bpd7.draw(g);
        bpd8.draw(g);
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