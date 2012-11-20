/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pieces;

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
    	possibleFields.add(this.getChessBoard().getField((this.getChessField().getCol() + 2), (this.getChessField().getRow() + 1)));
    	possibleFields.add(this.getChessBoard().getField((this.getChessField().getCol() + 2), (this.getChessField().getRow() - 1)));
    	possibleFields.add(this.getChessBoard().getField((this.getChessField().getCol() - 2), (this.getChessField().getRow() + 1)));
    	possibleFields.add(this.getChessBoard().getField((this.getChessField().getCol() - 2), (this.getChessField().getRow() - 1)));
    	possibleFields.add(this.getChessBoard().getField((this.getChessField().getCol() + 1), (this.getChessField().getRow() + 2)));
    	possibleFields.add(this.getChessBoard().getField((this.getChessField().getCol() + 1), (this.getChessField().getRow() - 2)));
    	possibleFields.add(this.getChessBoard().getField((this.getChessField().getCol() - 1), (this.getChessField().getRow() + 2)));
    	possibleFields.add(this.getChessBoard().getField((this.getChessField().getCol() - 1), (this.getChessField().getRow() - 2)));
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
