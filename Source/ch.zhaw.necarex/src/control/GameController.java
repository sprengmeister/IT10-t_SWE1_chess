/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import model.*;

/**
 *
 * @author florian
 */
public class GameController {
    private Game game;
    
    public GameController(){
        game = new Game();
    }
    
    public void newGame(){
        game = new Game();
        //view = new View(game, this);
    }
    
    public ArrayList<ChessField> getPossibleFields(ChessField field){
    
        if(field.getPiece() != null){
        	return game.getChessBoard().getField(field.getCol(), field.getRow()).getPiece().getPossibleFields();
        } else {
        	return new ArrayList<ChessField>();
        }
    }
    
    public void doTurn(ChessField from, ChessField to){
        // Verschiebe
    	if(from.getPiece().getPossibleFields().contains(to)){
    		game.getChessBoard().movePawn(from, to);
    	}
    	
    	
    	// Prüfe auf Schach, Schachmatt, etc. 
        
        
    }
}
