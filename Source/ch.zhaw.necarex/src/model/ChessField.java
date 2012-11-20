/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.pieces.*;

/**
 * Auf einem Schachfeld (ChessField), das auf einer Spalte und einer Zeile steht, kann eine Figur stehen. 
 * @author florian
 */
public class ChessField {
    private Piece piece;
    private int col;
    private int row;

    /**
     * Erstellt ein Schachfeld. 
     * @param initialPiece Figur in Grundaufstellung 
     */
    public ChessField(Piece initialPiece, int col, int row){
        this.piece = initialPiece;
        this.col = col;
        this.row = row;
    }
    /**
     * 
     * @param newPiece
     * @return 
     */
    public Piece exchangePiece(Piece newPiece){
        Piece oldPiece = getPiece();
        this.piece = newPiece;
        newPiece.setChessField(this);
        return oldPiece;
    }
    public Piece movePieceAway(){
    	Piece oldPiece = this.piece;
    	this.piece = null;
    	return oldPiece;
    }

    /**
     * @return the piece
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * @return the col
     */
    public int getCol() {
        return col;
    }

    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }
    
}

