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
    	ArrayList<ChessField> possibleFields = new ArrayList<ChessField>();
    
    	ChessField targetField;
    	
    	//nach links fahren
		if (this.getChessField().getCol() != 0) {
			targetField = this.getChessBoard().getField(this.getChessField().getCol(), this.getChessField().getRow());
			for (int i = 0; i < 7; i++){
				if(targetField.getCol() - 1 < 0) break;			
				targetField = this.getChessBoard().getField(targetField.getCol() - 1, targetField.getRow());	
				if (targetField.getPiece() == null) {	
					possibleFields.add(targetField);
				} else {
					checkForPiece(possibleFields, targetField);
					break;
				}
			}				
		}

		//nach rechts fahren
		if (this.getChessField().getCol() != 7) {
			targetField = this.getChessBoard().getField(this.getChessField().getCol(), this.getChessField().getRow());
			for (int i = 0; i < 7; i++){
				if(targetField.getCol() + 1 > 7) break;
				targetField = this.getChessBoard().getField(targetField.getCol() + 1, targetField.getRow());
				if (targetField.getPiece() == null) {
					possibleFields.add(targetField);
				} else {
					checkForPiece(possibleFields, targetField);
					break;
				}
			}
		}

		//nach oben fahren
		if (this.getChessField().getRow() != 7) {
			targetField = this.getChessBoard().getField(this.getChessField().getCol(), this.getChessField().getRow());
			for (int i = 0; i < 7; i++){
				if(targetField.getRow() + 1 > 7) break;
				targetField = this.getChessBoard().getField(targetField.getCol(), targetField.getRow() + 1);
				if (targetField.getPiece() == null) {
					possibleFields.add(targetField);
				} else {
					checkForPiece(possibleFields, targetField);
					break;
				}
			}
		}
			
		//nach unten fahren
		if (this.getChessField().getRow() != 0) {
			targetField = this.getChessBoard().getField(this.getChessField().getCol(), this.getChessField().getRow());
			for (int i = 0; i < 7; i++){
				if(targetField.getRow() - 1 < 0) break;
				targetField = this.getChessBoard().getField(targetField.getCol(), targetField.getRow() - 1);
				if (targetField.getPiece() == null) {
					possibleFields.add(targetField);
				} else {
					checkForPiece(possibleFields, targetField);
					break;
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
    
    
    /**
     * Ist die gefunde Figur vom Gegner oder nicht?
     */
    private void checkForPiece(ArrayList<ChessField> possibleFields, ChessField targetField){
    	if (targetField.getPiece().getOwner() != this.getChessField().getPiece().getOwner()) {
    		possibleFields.add(targetField);
		}
    }       
}
