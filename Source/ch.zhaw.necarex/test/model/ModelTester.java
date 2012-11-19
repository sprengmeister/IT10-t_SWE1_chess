/*
 x * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import junit.framework.Assert;
import model.pieces.Pawn;
import model.pieces.Piece;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author florian
 */
public class ModelTester {
    private static Game game;
    
    public ModelTester() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        game = new Game();
    }
    
    @AfterClass
    public static void tearDownClass() {
        game = null;
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void initGameStart(){
        assertNotNull(game);
        
    }
    @Test
    public void initGamePawn(){
        for(int col = 0;col<8;col++){
            if(!(game.getChessBoard().getField(col, 1).getPiece() instanceof Pawn)){
                Assert.fail("Auf Feld "+col+"/1 ist kein Bauer");
            }
            if(!(game.getChessBoard().getField(col, 1).getPiece().getOwner().getColor() == PlayerColor.WHITE)){
                Assert.fail("Die Figur auf Feld "+col+"/1 muss weiss sein");
            }            
        }
        for(int col = 0;col<8;col++){
            if(!(game.getChessBoard().getField(col, 6).getPiece() instanceof Pawn)){
                Assert.fail("Auf Feld "+col+"/6 ist kein Bauer");
            }
            if(!(game.getChessBoard().getField(col, 6).getPiece().getOwner().getColor() == PlayerColor.BLACK)){
                Assert.fail("Die Figur auf Feld "+col+"/6 muss schwarz sein");
            }            
        }        
    }
    @Test
    public void pawnsOnBaseLine(){
        for(int col = 0; col< 8 ;col++){
            Piece p = game.getChessBoard().getField(col, 1).getPiece();
            ArrayList<ChessField> list = p.getPossibleFields();
            if(!(list.contains(game.getChessBoard().getField(col, 2)))){
                Assert.fail("Der weisse Bauer in der Grundstellung auf Feld "+ col+ " / 1 muss auf Feld "+ col+ " / 2 kommen");
            }
            if(!(list.contains(game.getChessBoard().getField(col, 3)))){
                Assert.fail("Der weisse Bauer in der Grundstellung auf Feld "+ col+ " / 1 muss auf Feld "+ col+ " / 3 kommen");
            }            
            if(list.size() != 2 ){
                Assert.fail("In der Grundstellung hat jeder Bauer zwei Zugsmöglichkeiten.");
            }
        }
    }
    
    @Test
    public void checkFieldColsAndRows(){
        for(int row = 0;row < 8 ;row++){
            for(int col = 0; col < 8 ; col++){
                ChessField chessfield = game.getChessBoard().getField(col, row) ;
                if(!(chessfield.getRow() == row)){
                    Assert.fail("Feld "+ col +" / "+ row +" meint es sei Feld  " + chessfield.getCol() + " / " + chessfield.getRow() + ".");
                }
                if(!(chessfield.getCol() == col)){                 
                    Assert.fail("Feld "+ col +" / "+ row +" meint es sei Feld  " + chessfield.getCol() + " / " + chessfield.getRow() + ".");
                }
            }
        }
    }
    @Test
    public void movePawns(){
    	
    	ChessField fromField = game.getChessBoard().getField(1, 1);
    	ChessField toField = game.getChessBoard().getField(1, 3);
    	game.getChessBoard().movePiece(fromField, toField);
    	if(!(game.getChessBoard().getField(1, 3).getPiece() instanceof Pawn)){
    		Assert.fail("Der Bauernzug von schwarz zwei nach vorne hat nicht funktioniert. ");
    	}
    	if(!(game.getChessBoard().getField(1, 1).getPiece() == null)){
    		Assert.fail("Das Feld muss leer sein, nachdem der Bauer weggezogen ist. ");
    	}
    	if(!(game.getTurnList().size() == 1)){
    		Assert.fail("In der Zugliste muss ein Datensatz sein. Es befinden sich "+ game.getTurnList().size() +" Datensätze in der Liste");
    	} else {
    		if(!(game.getTurnList().get(0).getFromField() == fromField)){
    			Assert.fail("Beim ersten Zug ist ein falsches Von-Feld angegeben");
    		}
    		if(!(game.getTurnList().get(0).getToField() == toField)){
    			Assert.fail("Beim ersten Zug ist ein falsches to-Feld angegeben");
    		}
    		if(!(game.getTurnList().get(0).getActivePlayer() == game.getPlayerWhite())){
    			Assert.fail("Beim ersten Zug ist ein falscher Spieler eingetragen");
    		}
    		if(!(game.getTurnList().get(0).getCapturedPiece() == null)){
    			Assert.fail("Beim Zug wurde nichts geschlagen.");
    		}
    		if(!(game.getTurnList().get(0).getMovingPiece() instanceof Pawn)){
    			Assert.fail("Beim Zug hat sich ein Bauer bewegt");
    		}
    	}
    	
    	fromField = game.getChessBoard().getField(2, 6);
    	toField = game.getChessBoard().getField(2,4);
    	game.getChessBoard().movePiece(fromField, toField);
    	if(!(game.getChessBoard().getField(2, 4).getPiece() instanceof Pawn)){
    		Assert.fail("Der Bauernzug von schwarz zwei nach vorne hat nicht funktioniert. ");
    	}
    	if(!(game.getChessBoard().getField(2, 6).getPiece() == null)){
    		Assert.fail("Das Feld muss leer sein, nachdem der Bauer weggezogen ist.");
    	}
    	
    	fromField = game.getChessBoard().getField(1, 3);
    	toField = game.getChessBoard().getField(2, 4);
    	Piece pieceToCapture = toField.getPiece();
    	game.getChessBoard().movePiece(fromField, toField);
    	
    	if(!(game.getChessBoard().getField(2, 4).getPiece() instanceof Pawn 
    			&& game.getChessBoard().getField(2, 4).getPiece().getOwner().getColor() == PlayerColor.WHITE)) {
    		Assert.fail("Bauer schlagen hat nicht funktioniert. Auf Feld 2,4 steht kein weisser Bauer. ");
    	}
    	if(!(game.getTurnList().get(2).getCapturedPiece() instanceof Pawn && game.getTurnList().get(2).getCapturedPiece().getOwner().getColor() == PlayerColor.BLACK)){
    		Assert.fail("In der Zugsauflistung befindet sich kein schwarzer Bauer als captured Piece");
    	}
    	if(!(game.getCapturedPieces().contains(pieceToCapture))){
    		Assert.fail("In der Liste der geschlagenen Figuren fehlt der schwarze Bauer. ");
    	}
    		
    	
    }
    
}
