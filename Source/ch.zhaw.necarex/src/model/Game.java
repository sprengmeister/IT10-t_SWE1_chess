/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author florian
 */
public class Game {
    private Player playerWhite;
    private Player playerBlack;
    private ChessBoard chessBoard;
    
    public Game(){
        playerWhite = new Player(PlayerColor.WHITE);
        playerBlack = new Player(PlayerColor.BLACK);
        
        chessBoard = new ChessBoard(this);
    }

    /**
     * @return the playerWhite
     */
    public Player getPlayerWhite() {
        return playerWhite;
    }

    /**
     * @return the playerBlack
     */
    public Player getPlayerBlack() {
        return playerBlack;
    }

    /**
     * @return the chessBoard
     */
    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
