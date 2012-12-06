/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

import model.pieces.Piece;

/**
 * Ein Spiel (Game) besteht aus weissem und schwarzem Spieler, sowie einer Referenz des Schachbretts. Die Liste der vergangenen Züge und die bisher geschlagenen Figuren werden hier geführt. 
 * @author florian
 */
public class Game {
    private Player playerWhite;
    private Player playerBlack;
    private Player playerBlackHuman;
    private ChessBoard chessBoard;
    private Player activePlayer;
    private ArrayList<Piece> capturedPieces;
    private ArrayList<Turn> turnList;
    
    public ArrayList<Turn> getTurnList() {
		return turnList;
	}

	public Game(){
		initialize();
    }

	public void initialize(){
        playerWhite = new Player(PlayerColor.WHITE);
        // TODO change computer player
        //playerBlack = new ComputerPlayer(PlayerColor.BLACK, this);
        playerBlack = new Player(PlayerColor.BLACK);
        playerBlackHuman = playerBlack;
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
     * @return der Spieler der am Zug ist.
     */
    public Player getActivePlayer(){
    	return activePlayer;
    }
    
    public Player getInactivePlayer(){
    	return activePlayer == playerWhite ? playerBlack : playerWhite;
    }

    public void changeBlackPlayer(){
    	if (playerBlack instanceof ComputerPlayer){
    		playerBlack = playerBlackHuman;
    	} else {
    		playerBlack = new ComputerPlayer(PlayerColor.BLACK, this);
    	}
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