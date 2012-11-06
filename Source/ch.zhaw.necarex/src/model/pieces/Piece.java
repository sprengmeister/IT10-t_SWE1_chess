/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pieces;

import java.util.ArrayList;
import model.*;

/**
 *
 * @author florian
 */
public abstract class Piece {
    private Player owner;
    private ChessField chessField;
    private ChessBoard chessBoard;
    
    public Piece(Player owner, ChessBoard chessBoard){
        this.owner = owner;
        this.chessBoard = chessBoard;
    }
    
    public abstract ArrayList<ChessField> getPossibleFields();

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
        return chessBoard;
    }
    
    
    
    
}
