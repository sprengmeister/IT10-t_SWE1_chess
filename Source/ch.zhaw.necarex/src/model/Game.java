/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

import model.pieces.Piece;

/**
 *
 * @author florian
 */
public class Game {
    private Player playerWhite;
    private Player playerBlack;
    private ChessBoard chessBoard;
    private Player activePlayer;
    private ArrayList<Piece> capturedPieces;
    private ArrayList<Turn> turnList;
    
    public ArrayList<Turn> getTurnList() {
		return turnList;
	}

	public Game(){
        playerWhite = new Player(PlayerColor.WHITE);
        playerBlack = new Player(PlayerColor.BLACK);
        activePlayer = playerWhite;
        chessBoard = new ChessBoard(this);
        capturedPieces = new ArrayList<Piece>();
        turnList = new ArrayList<Turn>();
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
    /**
     * Wechselt den aktiven Spieler
     * @return Der Spieler der nachher am Zug ist 
     */
    public Player changeActivePlayer(){
    	if(activePlayer.equals(playerWhite)){
    		activePlayer = playerBlack;
    	}else {
    		activePlayer = playerWhite;
    	}
    	return activePlayer;
    }
    public void addToCapturedPieces(Piece capturedPiece){
    	this.capturedPieces.add(capturedPiece);
    }
    public void addToTurnList(Turn turn){
    	this.turnList.add(turn);
    }

	public ArrayList<Piece> getCapturedPieces() {
		return capturedPieces;
	}

}
