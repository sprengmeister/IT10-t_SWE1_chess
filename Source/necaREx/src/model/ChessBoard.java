/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.pieces.Pawn;
import model.pieces.Piece;

/**
 *
 * @author florian
 */
public class ChessBoard {
    private ChessField[][] chessField;
    
    private Game game;
   
    
    private Piece getInitialFigure(int col, int row){
        Player player;
        if(row < 3 ){
            player = game.getPlayerWhite();
        } else {
            player = game.getPlayerBlack();
        }
        // Zeile 1, Weisse Grundlinie
        if(row == 0){
            // Türme weiss
            if(col == 0 || col == 7){
                
            }
            // Springer weiss
            if(col == 1 || col == 6){
                
            }
            // Läufer weiss
            if(col == 2 || col == 5){
                
            }
            // Dame weiss
            if(col == 3){
                
            }
            // König weiss
            if(col == 4){
                
            }
        }
        // Zeile 7, Schwarze Grundlinie
        if(row == 7){
            // Türme schwarz
            if(col == 0 || col == 7){
                
            }
            // Springer schwarz
            if(col == 1 || col == 6){
                
            }
            // Läufer schwarz
            if(col == 2 || col == 5){
                
            }
            // König schwarz
            if(col == 3){
                
            }
            // Dame schwarz
            if(col == 4){
                
            }
        }
        
        // Bauern weiss und schwarz
        if(row == 1 || row == 6 ){
            return new Pawn(player, col, row, this);
        }
        
        
        return null;
    }
    
    private void initChessboard(){
        chessField = new ChessField[8][8];
        for(int col = 0;col<8;col++){
            for(int row = 0;row<8;row++){
                chessField[col][row] = new ChessField(this.getInitialFigure(col, row));
            }
        }

    }
    
    public ChessBoard(Game game){
        this.game = game;
        this.initChessboard();        
    }
    
    public ChessField getField(int col, int row){
        return chessField[col][row];
    }
    public ChessField getField(ChessField chessfieldSearch){
        Piece p = chessfieldSearch.getPiece();
        if(p != null){
            return getField(p.getCol(), p.getRow());
        }
        return null;
    }
    
    
}
