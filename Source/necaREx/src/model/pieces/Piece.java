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
    private int row;
    private int col;
    private ChessBoard chessBoard;
    
    public Piece(Player owner, int col, int row, ChessBoard chessBoard){
        this.owner = owner;
        this.col = col;        
        this.row = row;
        this.chessBoard = chessBoard;
    }
    
    public abstract ArrayList<ChessField> getPossibleFields();
    
    public void moveTo(int row, int col){
        this.row = row;
        this.col = col;
    }

    /**
     * @return the owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * @return the col
     */
    public int getCol() {
        return col;
    }
    
    protected ChessBoard getChessBoard(){
        return chessBoard;
    }
    
    
}
