/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import model.*;

/**
 * Spielfigur Pferd, basiert auf Piece. Kennt die Gangart des und kann dessen mögliche Felder ausrechnen.
 * @author beni
 */
public class Knight extends Piece {

    /**
     * Konstruktor: Erzeugt einen Pferd
     * @param player Referenz zum Spieler 
     * @param chessBoard Referenz zum Schachbrett 
     */
    public Knight(Player player, ChessBoard chessBoard){
        super(player, chessBoard);
        //Index des zugehörigen Sprites setzen
        super.setSpriteIndex(new Point(2, player.getColor() == PlayerColor.WHITE ? 0 : 1));

    }

    /**
     * Erzeugt eine Liste der möglichen Zugfelder für diese Figur und gibt sie zurück. 
     * @return Liste aller möglichen Züge, die die Figur machen kann. 
     */
    @Override
    public ArrayList<ChessField> getPossibleFields() {
    	ArrayList<ChessField> possibleFields = new ArrayList<ChessField>();
    
    	//alle hinzufügen, danach unmögliche löschen
        this.addAllKnightTurn(possibleFields);
        this.removeImpossibleKnightTurn(possibleFields);

        //TODO prüfen ob mit diesem Zug eine Schachsituation ausgelöst wurde
        
        return possibleFields;
    }
    
    /**
     * Prüft, ob das Pferd ein Feld nach vorne fahren kann. 
     */
    private void addAllKnightTurn(ArrayList<ChessField> possibleFields){
    	ChessBoard board = this.getChessBoard();
    	int col = this.getChessField().getCol();
    	int row = this.getChessField().getRow();
    	//board.getField gibt null zurück, falls kein gültiges Feld, 
    	//darum hier mit diesem "Hack" nur gültige Felder einfügen.
    	addIfNotNull(possibleFields, board.getField((col + 2), (row + 1)));
    	addIfNotNull(possibleFields, board.getField((col + 2), (row + 1)));
    	addIfNotNull(possibleFields, board.getField((col + 2), (row - 1)));
    	addIfNotNull(possibleFields, board.getField((col - 2), (row + 1)));
    	addIfNotNull(possibleFields, board.getField((col - 2), (row - 1)));
    	addIfNotNull(possibleFields, board.getField((col + 1), (row + 2)));
    	addIfNotNull(possibleFields, board.getField((col + 1), (row - 2)));
    	addIfNotNull(possibleFields, board.getField((col - 1), (row + 2)));
    	addIfNotNull(possibleFields, board.getField((col - 1), (row - 2)));
    }
    
    private void addIfNotNull(ArrayList<ChessField> possibleFields,
			ChessField field) {
		if (field != null){
			possibleFields.add(field);
		}
	}

	/**
     * Prüft, ob das Pferd schlagen kann
     */
    private void removeImpossibleKnightTurn(ArrayList<ChessField> possibleFields){
    	//Figur steht ausserhalb des Schachbretts
    	Iterator<ChessField> outsideChessBoard = possibleFields.iterator();
    	while (outsideChessBoard.hasNext()) {
    		ChessField currentChessfield = outsideChessBoard.next();
    		int col = currentChessfield.getCol();
    		int row = currentChessfield.getRow();
    		if(col > 8 || row > 8 || col < 0 || row < 0){
    			outsideChessBoard.remove();
    		}
    	}
    	
    	
    	//Figur würde andere Figur der gleichen Farbe schlagen
    	Iterator<ChessField> samePlayer = possibleFields.iterator();
    	while (samePlayer.hasNext()) {
    		ChessField currentChessfield = samePlayer.next();
    		if(currentChessfield.getPiece() != null && currentChessfield.getPiece().getOwner() == this.getChessField().getPiece().getOwner()){
    			samePlayer.remove();
    		}
    	}
    }    
}
