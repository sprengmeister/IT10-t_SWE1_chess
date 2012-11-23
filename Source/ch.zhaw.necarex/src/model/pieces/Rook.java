/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pieces;

import java.awt.Point;
import java.util.ArrayList;

import model.*;

/**
 * Spielfigur Turm, basiert auf Piece. Kennt die Gangart des und kann dessen mögliche Felder ausrechnen.
 * @author beni
 */
public class Rook extends Piece {

    private enum Direction { LEFT, RIGHT, UP, DOWN }; 

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
    
        this.addRookTurn(possibleFields);
        
        //TODO prüfen ob mit diesem Zug eine Schachsituation ausgelöst wurde
        
        return possibleFields;
    }

    /**
     * Fügt alle Züge hinzu, die die Figur fahren kann. 
     */
    private void addRookTurn(ArrayList<ChessField> possibleFields){
    	stepDirectionTillOtherPieceOrEndOfBoard(possibleFields, Direction.LEFT);
    	stepDirectionTillOtherPieceOrEndOfBoard(possibleFields, Direction.RIGHT);
    	stepDirectionTillOtherPieceOrEndOfBoard(possibleFields, Direction.UP);
    	stepDirectionTillOtherPieceOrEndOfBoard(possibleFields, Direction.DOWN);
    }
    
    /**
     * Fügt alle Züge hinzu, die die Figur in eine bestimmte Richtung fahren kann. 
     */
    private void stepDirectionTillOtherPieceOrEndOfBoard(ArrayList<ChessField>  possibleFields, Direction direction){
    	ChessField targetField;
    	switch (direction) {
		case LEFT:
			if (!(this.getChessField().getCol() == 0)) {
				targetField = this.getChessBoard().getField(this.getChessField().getCol() - 1, this.getChessField().getRow());
				while (!(targetField.getCol() < 0)) {
					if (targetField.getPiece() == null) {
						possibleFields.add(targetField);
					} else {
						checkForPiece(possibleFields, targetField);
						break;
					}
				}				
			}
		break;

		case RIGHT:
			if (!(this.getChessField().getCol() == 7)) {
				targetField = this.getChessBoard().getField(this.getChessField().getCol() + 1, this.getChessField().getRow());
				while (!(targetField.getCol() > 7)) {
					if (targetField.getPiece() == null) {
						possibleFields.add(targetField);
					} else {
						checkForPiece(possibleFields, targetField);
						break;
					}
				}
			}
		break;

		case UP:
			if (!(this.getChessField().getRow() == 7)) {
				targetField = this.getChessBoard().getField(this.getChessField().getCol(), this.getChessField().getRow() + 1);
				while (!(targetField.getRow() > 7)) {
					if (targetField.getPiece() == null) {
						possibleFields.add(targetField);
					} else {
						checkForPiece(possibleFields, targetField);
						break;
					}
				}
			}
		break;

		case DOWN:
			if (!(this.getChessField().getRow() == 0)) {
				targetField = this.getChessBoard().getField(this.getChessField().getCol(), this.getChessField().getRow() - 1);
				while (!(targetField.getRow() < 0)) {
					if (targetField.getPiece() == null) {
						possibleFields.add(targetField);
					} else {
						checkForPiece(possibleFields, targetField);
						break;
					}
				}
			}
		break;
		}
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
