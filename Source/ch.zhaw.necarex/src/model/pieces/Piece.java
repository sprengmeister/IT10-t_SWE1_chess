/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;

import model.ChessBoard;
import model.ChessField;
import model.Player;

/**
 * Die Klasse Piece (abstrakt) beschreibt alles, was alle Schachfiguren gemeinsam haben. Alle Schachfiguren gehören einem Owner, stehen auf einem Schachfeld.Jede Schachfigur muss eine Methode implementieren, die die möglichen Felder zurück gibt.  
 * @author florian
 */
public abstract class Piece {
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
    
    
}
