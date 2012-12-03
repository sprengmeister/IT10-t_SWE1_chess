/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pieces;

import java.awt.Point;
import java.util.ArrayList;

import model.*;

/**
 * Spielfigur Springer, basiert auf Piece. Kennt die Gangart des Springers und kann dessen mögliche Felder ausrechnen.
 * @author beni
 */
public class Knight extends Piece {

    /**
     * Konstruktor: Erzeugt einen Springer
     * @param player Referenz zum Spieler 
     * @param chessBoard Referenz zum Schachbrett 
     */
    public Knight(Player player, ChessBoard chessBoard){
        super(player, chessBoard);
        super.setSpriteIndex(new Point(2, player.getColor() == PlayerColor.WHITE ? 0 : 1));
    }

    /**
     * Erzeugt eine Liste der möglichen Zugfelder für diese Figur und gibt sie zurück. 
     * @return Liste aller möglichen Züge, die die Figur machen kann. 
     */
    @Override
    public ArrayList<ChessField> getPossibleFields() {
    	ArrayList<ChessField> possibleFields = new ArrayList<ChessField>();
    
    	//allFields[i] und allFields[i+1] sind zusammen eine Koordinate eines Spielfelds
    	Integer[] allFields = new Integer[16];
    	allFields[0] = this.getChessField().getCol() + 2;
    	allFields[1] = this.getChessField().getRow() + 1;
    	allFields[2] = this.getChessField().getCol() + 2;
    	allFields[3] = this.getChessField().getRow() - 1;
    	allFields[4] = this.getChessField().getCol() - 2;
    	allFields[5] = this.getChessField().getRow() + 1;
    	allFields[6] = this.getChessField().getCol() - 2;
    	allFields[7] = this.getChessField().getRow() - 1;
    	allFields[8] = this.getChessField().getCol() + 1;
    	allFields[9] = this.getChessField().getRow() + 2;
    	allFields[10] = this.getChessField().getCol() + 1;
    	allFields[11] = this.getChessField().getRow() - 2;
    	allFields[12] = this.getChessField().getCol() - 1;
    	allFields[13] = this.getChessField().getRow() + 2;
    	allFields[14] = this.getChessField().getCol() - 1;
    	allFields[15] = this.getChessField().getRow() - 2;
    	
    	for (int i = 0; i < allFields.length; i+=2) {
    		//Figur steht nicht ausserhalb des Schachbretts und ist keine Figur der gleichen Farbe
			if (!(allFields[i] > 7 || allFields[i+1] > 7 || allFields[i] < 0 || allFields[i+1] < 0)) { 
				ChessField currentChessfield = this.getChessBoard().getField(allFields[i], allFields[i+1]);
				if (!(currentChessfield.getPiece() != null && currentChessfield.getPiece().getOwner() == this.getChessField().getPiece().getOwner())) {
					possibleFields.add(currentChessfield);
				}
			}
		}

        //TODO prüfen ob mit diesem Zug eine Schachsituation ausgelöst wurde
        
        return possibleFields;
    }
    @Override
    public ArrayList<ChessField> getDaringFields(){
    	return this.getPossibleFields();
    }
    
}
