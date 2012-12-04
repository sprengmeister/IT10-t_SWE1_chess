/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import model.ChessBoard;
import model.ChessField;
import model.Player;

/**
 * Die Klasse Piece (abstrakt) beschreibt alles, was alle Schachfiguren gemeinsam haben. Alle Schachfiguren gehören einem Owner, stehen auf einem Schachfeld.Jede Schachfigur muss eine Methode implementieren, die die möglichen Felder zurück gibt.  
 * @author florian
 */
public abstract class Piece implements Cloneable {
    private Player owner;
    private ChessField chessField;
    private ChessBoard chessBoard;
    private Point spriteIndex;

	public Piece(Player owner, ChessBoard chessBoard){
        this.owner = owner;
        this.chessBoard = chessBoard;
    }
    
    public abstract ArrayList<ChessField> getPossibleFields();
    
    /**
     * Gibt eine Liste zurück aller Felder, die von der Figur bedroht 
     * sind und zum Beispiel einen daraufstehenden König schachstellen
     * @return
     */
    public abstract ArrayList<ChessField> getDaringFields();

    /**
     * @return the chessField
     */
    public ChessField getChessField() {
    	return chessField;
    }

    /**
     * @param chessField the chessField to set
     */
    public void setChessField(ChessField chessField) {
        this.chessField = chessField;
    }

    /**
     * @return the owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * @return the chessBoard
     */
    public ChessBoard getChessBoard() {
        // TODO: get over chessBoard
    	return chessBoard;
    }
    
    public Point getSpriteIndex() {
		return spriteIndex;
	}

	protected void setSpriteIndex(Point spriteIndex) {
		this.spriteIndex = spriteIndex;
	}
	
	/**
	 * Prüft die Zugliste, ob ein Zug den eigenen König in eine Schachsituation bringt und entfernt entsprechende Züge.  
	 * @param possibleFields 
	 * @return possible Fields ohne die nicht erlaubten Züge 
	 */
	protected ArrayList<ChessField> checkDaringOwnKing(ArrayList<ChessField> possibleFields){

		Iterator<ChessField> possibleFieldIterator = possibleFields.iterator();
		while (possibleFieldIterator.hasNext()) {
			ChessField toItem = possibleFieldIterator.next();
			// Zug probeweise durchführen
			ChessBoard cbTest = this.getChessBoard().clone();
			ChessField from = cbTest.getField(this.getChessField().getCol(), this.getChessField().getRow());
			ChessField to = cbTest.getField(toItem.getCol(), toItem.getRow());
			
			to.exchangePiece(from.movePieceAway());
			boolean delete = false;
	    	for(int col=0;col<8;col++){
	    		for(int row = 0;row < 8; row++){
	    			Piece pieceOnField = cbTest.getField(col, row).getPiece();
	    			if(pieceOnField!= null && pieceOnField.getOwner() != this.getOwner() &&  pieceOnField.daresOpponentKing()){
	    				delete = true;
	    			}
	    		}
	    	}
	    	// remove des Iterators darf nur einmal aufgerufen werden. 
	    	if(delete){
				possibleFieldIterator.remove();
	    	}
	    	// Testschachbrett löschen
	    	cbTest = null;
        }
		
		return possibleFields;
	}
	
	
	public boolean daresOpponentKing(){
		ArrayList<ChessField> reachableFieldList = this.getDaringFields();
		for (ChessField chessField : reachableFieldList) {
			// auf einem reachable Field steht ein gegnerischer König. 
			if(chessField.getPiece() != null &&  
				chessField.getPiece() instanceof King && 
				chessField.getPiece().getOwner() != this.getOwner()){
				
				return true;
			}
		}
		return false;
	}
    public Piece clone(ChessField chessField, ChessBoard chessBoard){
    	try {
			Piece p = (Piece) super.clone();
			p.chessField = chessField;
			p.chessBoard = chessBoard;
			return p;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
    }
    
}
