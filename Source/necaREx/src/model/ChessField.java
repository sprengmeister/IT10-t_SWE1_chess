/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.pieces.*;

/**
 *
 * @author florian
 */
public class ChessField {
    private Piece piece;

    /**
     * Erstellt ein Schachfeld. 
     * @param initialPiece Figur in Grundaufstellung 
     */
    public ChessField(Piece initialPiece){
        this.piece = initialPiece;
    }
    /**
     * 
     * @param newPiece
     * @return 
     */
    public Piece exchangePiece(Piece newPiece){
        Piece oldPiece = getPiece();
        this.piece = newPiece;
        return oldPiece;
    }

    /**
     * @return the piece
     */
    public Piece getPiece() {
        return piece;
    }
    
}
