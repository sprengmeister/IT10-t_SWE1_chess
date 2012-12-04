/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pieces;

import java.awt.Point;
import java.util.ArrayList;

import model.*;

/**
 * Spielfigur König, basiert auf Piece. Kennt die Gangart des Königs und kann dessen mögliche Felder ausrechnen.
 * @author beni
 */
public class King extends Piece {

    /**
     * Konstruktor: Erzeugt eine neue Figur
     * @param player Referenz zum Spieler 
     * @param chessBoard Referenz zum Schachbrett 
     */
    public King(Player player, ChessBoard chessBoard){
        super(player, chessBoard);
        super.setPieceValue(999);
        //Index des zugehörigen Sprites setzen
        super.setSpriteIndex(new Point(1, player.getColor() == PlayerColor.WHITE ? 0 : 1));
    }

    /**
     * Erzeugt eine Liste der möglichen Zugfelder für diese Figur und gibt sie zurück. 
     * @return Liste aller möglichen Züge, die die Figur machen kann. 
     */
    @Override
    public ArrayList<ChessField> getPossibleFields() {
    	ArrayList<ChessField> possibleFields = this.getPossibleFieldsAroundKing();

        return this.checkDaringOwnKing(possibleFields);
    }
    /**
     * Erzeugt eine Liste aller Felder, auf welchen die Figur den gegnerischen König bedrohen würde
     * @return Liste aller Felder, die durch die Figur bedroht werden
     */
    @Override
    public ArrayList<ChessField> getDaringFields(){
    	return this.getPossibleFieldsAroundKing();
    }
    /**
     * Gibt alle Felder die für den König erreichbar sind zurück
     * @return Liste der Felder 
     */
    private ArrayList<ChessField> getPossibleFieldsAroundKing(){
       	possibleFields = new ArrayList<ChessField>();

    	//allFields[i] und allFields[i+1] sind zusammen eine Koordinate (Spalte/Zeile) eines Spielfelds
    	Integer[] allFields = new Integer[16];
    	allFields[0] = this.getChessField().getCol() + 1;
    	allFields[1] = this.getChessField().getRow() + 1;
    	allFields[2] = this.getChessField().getCol() + 1;
    	allFields[3] = this.getChessField().getRow() - 1;
    	allFields[4] = this.getChessField().getCol() - 1;
    	allFields[5] = this.getChessField().getRow() + 1;
    	allFields[6] = this.getChessField().getCol() - 1;
    	allFields[7] = this.getChessField().getRow() - 1;
    	allFields[8] = this.getChessField().getCol() + 1;
    	allFields[9] = this.getChessField().getRow();
    	allFields[10] = this.getChessField().getCol();
    	allFields[11] = this.getChessField().getRow() + 1;
    	allFields[12] = this.getChessField().getCol() - 1;
    	allFields[13] = this.getChessField().getRow();
    	allFields[14] = this.getChessField().getCol();
    	allFields[15] = this.getChessField().getRow() - 1;
    	
    	for (int i = 0; i < allFields.length; i+=2) {
    		// Figur steht nicht ausserhalb des Schachbretts und ist keine Figur der gleichen Farbe
			if (!(allFields[i] > 7 || allFields[i+1] > 7 || allFields[i] < 0 || allFields[i+1] < 0)) { 
				ChessField currentChessfield = this.getChessBoard().getField(allFields[i], allFields[i+1]);
				if (!(currentChessfield.getPiece() != null && currentChessfield.getPiece().getOwner() == this.getChessField().getPiece().getOwner())) {
					possibleFields.add(currentChessfield);
				}
			}
		}

        return possibleFields;   	
    }
    
    
    public boolean isCheck(){
    	return false;
    }
    
    public boolean isCheckMate(){
    	return false;
    }    
}
