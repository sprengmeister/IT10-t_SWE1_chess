/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.pieces.BiShop;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Pawn;
import model.pieces.Piece;
import model.pieces.Queen;
import model.pieces.Rook;

/**
 * Das ChessBoard kontrolliert die Schachfelder und macht die Anfangsaufstellung zu Beginn des Spiels. 
 * @author florian
 */
public class ChessBoard implements Cloneable{
    private ChessField[][] chessField;
    
    private Game game;
   
    public ChessBoard(Game game){
        this.game = game;
        //TODO: Init raus nehmen, um testability zu erhöhen
        this.initChessboard();        
    } 
    
    private Piece getInitialPiece(int col, int row){
    	Player player;
        if(row < 3 ){
            player = game.getPlayerWhite();
        } else {
            player = game.getPlayerBlack();
        }
        
        //Aus Row/Column Werten eine Zahl machen -> Zehner = Row, Einer = Column
        int fieldValue = row*10+col;
        // Reihe 0 -> Weisse Grundlinie 
        // Reihe 7 -> Schwarze Grundlinie
        switch (fieldValue){
	        case 0:
	        case 7:
	        case 70:
	        case 77:
	        	// Türme
	        	return new Rook(player, this);
	        case 1:
	        case 6:
	        case 71:
	        case 76:
	        	// Springer
	        	return new Knight(player, this);
	        case 2:
	        case 5:
	        case 72:
	        case 75:
	        	// Läufer
	        	return new BiShop(player, this);
	        case 3:
	        case 73:
	        	// Dame
	        	return new Queen(player, this);
	        case 4:
	        case 74:
	        	// König
	        	return new King(player, this);
        }
        
        // Bauern separat Abfragen (um sich die ganzen case-Statements zu sparen)
        if(row == 1 || row == 6 ){
            return new Pawn(player, this);
        }
        
        return null;
    }
    

    public void initChessboard(){
        chessField = new ChessField[8][8];
        for(int col = 0;col<8;col++){
            for(int row = 0;row<8;row++){
                Piece initialPiece = this.getInitialPiece(col, row);
                chessField[col][row] = new ChessField(initialPiece, col, row);
                if( initialPiece != null) {
                    initialPiece.setChessField(chessField[col][row]);
                }
            }
        }
    }

    public void initChessboard(ChessField[][] chessField){
    	//TODO: check dimension
        this.chessField = chessField;
    }
    
    public ChessField getField(int col, int row){
        return chessField[col][row];
    }
    
    public void movePiece(ChessField from, ChessField to){
    	Turn turn = new Turn(from, to);
    	game.addToTurnList(turn);
    	Piece capturedPiece = to.exchangePiece(from.movePieceAway());
    	if(capturedPiece != null){
    		game.addToCapturedPieces(capturedPiece);
    	}
    }
    
    public boolean checkMate(){
    	for(int col=0;col<8;col++){
    		for(int row = 0;row < 8; row++){
    			if(this.getField(col, row).getPiece() != null && this.getField(col, row).getPiece().daresOpponentKing()){
    				return true;
    			}
    		}
    	}
    	return false;
    }
    public ChessBoard clone(){
		try {
			return (ChessBoard) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
    	
    }
    
}