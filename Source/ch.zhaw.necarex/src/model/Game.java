/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import model.pieces.Piece;

/**
 * Ein Spiel (Game) besteht aus weissem und schwarzem Spieler, sowie einer Referenz des Schachbretts. Die Liste der vergangenen Züge und die bisher geschlagenen Figuren werden hier geführt. 
 */
public class Game {
	/**
	 * Weisser Spieler
	 */
    private Player playerWhite;
    /**
     * Schwarzer Spieler
     */
    private Player playerBlack;
    /**
     * Schwarzer Spieler (Menschlich): Wird benötigt, falls der Benutzer auf den Computerspieler wechselt. 
     */
    private Player playerBlackHuman;
    /**
     * Aktuelles Schachbrett
     */
    private ChessBoard chessBoard;
    /**
     * Spieler, der zur Zeit am Zug ist
     */
    private Player activePlayer;
    /**
     * Liste aller geschlagenen Figuren
     */
    private ArrayList<Piece> capturedPieces;
    /**
     * Liste aller vergangenen Züge
     */
    private ArrayList<Turn> turnList;
   
    
    public ArrayList<Turn> getTurnList() {
		return turnList;
	}
    /**
     * Erzeugt ein neues Spiel. Greift dabei auf die Methode initialize() zurück
     * @see initialize()
     */
	public Game(){
		initialize();
    }
	/**
	 * Erstellt due Schachspieler, das Schachbrett und die leeren Listen für die geschlagenen Figuren und Zugsliste.  
	 */
	public void initialize(){
        playerWhite = new Player(PlayerColor.WHITE);
        playerBlack = new Player(PlayerColor.BLACK);
        playerBlackHuman = playerBlack;
        activePlayer = playerWhite;
        chessBoard = new ChessBoard(this);
        capturedPieces = new ArrayList<Piece>();
        turnList = new ArrayList<Turn>();
	}

    /**
     * Wechselt den schwarzen Spieler vom menschlichen Spieler zum Computerspieler und umgekehrt. 
     */
    public void changeBlackPlayer(){
    	if (playerBlack instanceof ComputerPlayer){
    		playerBlack = playerBlackHuman;
    	} else {
    		playerBlack = new ComputerPlayer(PlayerColor.BLACK, this);
    	}
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
	
    /**
     * Gibt den weissen Spieler zurück
     * @return Weisser Spieler
     */
    public Player getPlayerWhite() {
        return playerWhite;
    }

    /**
     * Gibt den schwarzen Spieler zurück
     * @return schwarzer Spieler
     */
    public Player getPlayerBlack() {
        return playerBlack;
    }
    
    /**
     * Gibt den Spieler zurück, der gerade am Zug ist. 
     * @return der Spieler der am Zug ist.
     */
    public Player getActivePlayer(){
    	return activePlayer;
    }
    
    /**
     * Gibt den Spieler zurück, der gerade nicht am Zug ist. 
     * @return Gibt den Spieler zurück, der gerade nicht am Zug ist. 
     */
    public Player getInactivePlayer(){
    	return activePlayer == playerWhite ? playerBlack : playerWhite;
    }

    /**
     * Gibt das Schachbrett zurück. 
     * @return Aktuelles Schachbrett
     */
    public ChessBoard getChessBoard() {
        return chessBoard;
    }
    
    /**
     * Fügt eine Figur zu den geschlagenen Figuren hinzu. 
     * @param capturedPiece geschlagene Figur
     */
    public void addToCapturedPieces(Piece capturedPiece){
    	this.capturedPieces.add(capturedPiece);
    }
    /**
     * Fügt einen Zug zur Zugsliste hinzu. 
     * @param turn Durchgeführten Zug
     */
    public void addToTurnList(Turn turn){
    	this.turnList.add(turn);
    }

    /**
     * Gibt alle geschlagenen Figuren zurück. 
     * @return geschlagene Figuren. 
     */
	public ArrayList<Piece> getCapturedPieces() {
		return capturedPieces;
	}

}