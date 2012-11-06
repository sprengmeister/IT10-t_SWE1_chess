/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import exception.NoFigureOnFieldException;
import java.util.ArrayList;
import model.ChessField;
import model.Game;

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
    }
    
    public ArrayList<ChessField> getPossibleFields(ChessField field) throws NoFigureOnFieldException{
    
        if(field.getPiece() == null){
            throw new NoFigureOnFieldException();
        }
        return game.getChessBoard().getField(field.getCol(), field.getRow()).getPiece().getPossibleFields();
    }
    
    public void doTurn(ChessField from, ChessField to){
        
        // Verschiebe
        
        
        // Prüfe auf Schach, Schachmatt, etc. 
        
        
    }
}
