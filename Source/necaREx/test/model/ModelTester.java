/*
 * To change this template, choose Tools | Templates
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
    
}
