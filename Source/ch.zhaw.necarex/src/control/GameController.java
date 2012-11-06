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
        	return field.getPiece().getPossibleFields();
        } else {
        	return new ArrayList<ChessField>();
        }
    }
    
    public void doTurn(ChessField fromField, ChessField toField){        
    	if(fromField.getPiece().getPossibleFields().contains(toField)){
    		game.getChessBoard().movePiece(fromField, toField);
    		game.changeActivePlayer();
    	}
	
    	// Prüfe auf Schach, Schachmatt, etc. 
              
    }
}
