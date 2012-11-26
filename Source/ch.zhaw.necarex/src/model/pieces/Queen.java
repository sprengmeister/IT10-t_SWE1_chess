/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pieces;

import java.awt.Point;
import java.util.ArrayList;
import model.*;

/**
 * Spielfigur Königin, basiert auf Piece. Kennt die Gangart der Königin und kann ihre möglichen Felder ausrechnen.
 * @author beni
 */
public class Queen extends Piece {
    
    private int direction;

    /**
     * Konstruktor: Erzeugt eine neue Figur
     * @param player Referenz zum Spieler 
     * @param chessBoard Referenz zum Schachbrett 
     */
    public Queen(Player player, ChessBoard chessBoard){
        super(player, chessBoard);
        //Index des zugehörigen Sprites setzen
        super.setSpriteIndex(new Point(1, player.getColor() == PlayerColor.WHITE ? 0 : 1));
    }

    /**
     * Erzeugt eine Liste der möglichen Zugfelder für diese Figur und gibt sie zurück. 
     * @return Liste aller möglichen Züge, die die Figur machen kann. 
     */
    @Override
    public ArrayList<ChessField> getPossibleFields() {
    	ArrayList<ChessField> possibleFields = new ArrayList<ChessField>();
    



        //TODO prüfen ob mit diesem Zug eine Schachsituation ausgelöst wurde
        
        return possibleFields;
    }

    
}
