/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pieces;

import java.awt.Point;
import java.util.ArrayList;

import model.*;

/**
 * Spielfigur Turm, basiert auf Piece. Kennt die Gangart des Turms und kann dessen mögliche Felder ausrechnen.
 * @author beni
 */
public class Rook extends Piece {

    /**
     * Konstruktor: Erzeugt eine neue Figur
     * @param player Referenz zum Spieler 
     * @param chessBoard Referenz zum Schachbrett 
     */
    public Rook(Player player, ChessBoard chessBoard){
        super(player, chessBoard);
        //Index des zugehörigen Sprites setzen
        super.setSpriteIndex(new Point(3, player.getColor() == PlayerColor.WHITE ? 0 : 1));

    }

    /**
     * Erzeugt eine Liste der möglichen Zugfelder für diese Figur und gibt sie zurück. 
     * @return Liste aller möglichen Züge, die die Figur machen kann. 
     */
    @Override
    public ArrayList<ChessField> getPossibleFields() {
    	possibleFields = new ArrayList<ChessField>();
       	
    	this.checkTurnColumn();
    	this.checkTurnRow();
 
    	return this.checkDaringOwnKing(possibleFields);
    }
    
    @Override
    public ArrayList<ChessField> getDaringFields(){
    	possibleFields = new ArrayList<ChessField>();
       	
    	this.checkTurnColumn();
    	this.checkTurnRow();
 
    	return possibleFields;
    }
    
    
    
    
    
    
       
}
