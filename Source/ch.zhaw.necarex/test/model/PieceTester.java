/*
 x * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import junit.framework.Assert;
import model.pieces.Knight;
import model.pieces.Pawn;
import model.pieces.Piece;
import model.pieces.Rook;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Tests der Figuren
 */
public class PieceTester {
    private static Game game;
    
    /**
     * Erstellt ein Game für die Tests
     */
    @BeforeClass
    public static void setUpClass() {
        game = new Game();
    }
    
    /**
     * Löscht das Game, da die Tests abgeschlossen sind. 
     */
    @AfterClass
    public static void tearDownClass() {
        game = null;
    }
    
    /**
     * Prüft, ob das Game erzeugt wurde. 
     */
    public void initGameStart(){
        assertNotNull(game);
        
    }
    /**
     * Prüft, ob bei der Grundaufstellung die Bauern am richtigen Ort stehen. 
     */
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
    /**
     * Prüft die Grundaufstellung der Springer
     */
    @Test
    public void initGameKnight(){        
        if(!(game.getChessBoard().getField(1, 0).getPiece() instanceof Knight)){
            Assert.fail("Auf Feld 1/0 ist kein Springer");
        }
        if(!(game.getChessBoard().getField(1, 0).getPiece().getOwner().getColor() == PlayerColor.WHITE)){
            Assert.fail("Die Figur auf Feld 1/0 muss weiss sein");
        }        
        if(!(game.getChessBoard().getField(6, 0).getPiece() instanceof Knight)){
            Assert.fail("Auf Feld 6/0 ist kein Springer");
        }
        if(!(game.getChessBoard().getField(6, 0).getPiece().getOwner().getColor() == PlayerColor.WHITE)){
            Assert.fail("Die Figur auf Feld 6/0 muss weiss sein");
        }
        if(!(game.getChessBoard().getField(1, 7).getPiece() instanceof Knight)){
            Assert.fail("Auf Feld 1/7 ist kein Springer");
        }
        if(!(game.getChessBoard().getField(1, 7).getPiece().getOwner().getColor() == PlayerColor.BLACK)){
            Assert.fail("Die Figur auf Feld 1/7 muss schwarz sein");
        }
        if(!(game.getChessBoard().getField(6, 7).getPiece() instanceof Knight)){
            Assert.fail("Auf Feld 6/7 ist kein Springer");
        }
        if(!(game.getChessBoard().getField(6, 7).getPiece().getOwner().getColor() == PlayerColor.BLACK)){
            Assert.fail("Die Figur auf Feld 6/7 muss schwarz sein");
        }
    }

    /**
     * Prüft die Grundaufstellung der Türme
     */
    @Test
    public void initGameRook(){        
        if(!(game.getChessBoard().getField(0, 0).getPiece() instanceof Rook)){
            Assert.fail("Auf Feld 0/0 ist kein Turm");
        }
        if(!(game.getChessBoard().getField(0, 0).getPiece().getOwner().getColor() == PlayerColor.WHITE)){
            Assert.fail("Die Figur auf Feld 0/0 muss weiss sein");
        }        
        if(!(game.getChessBoard().getField(7, 0).getPiece() instanceof Rook)){
            Assert.fail("Auf Feld 7/0 ist kein Turm");
        }
        if(!(game.getChessBoard().getField(7, 0).getPiece().getOwner().getColor() == PlayerColor.WHITE)){
            Assert.fail("Die Figur auf Feld 7/0 muss weiss sein");
        }
        if(!(game.getChessBoard().getField(0, 7).getPiece() instanceof Rook)){
            Assert.fail("Auf Feld 0/7 ist kein Turm");
        }
        if(!(game.getChessBoard().getField(0, 7).getPiece().getOwner().getColor() == PlayerColor.BLACK)){
            Assert.fail("Die Figur auf Feld 0/7 muss schwarz sein");
        }
        if(!(game.getChessBoard().getField(7, 7).getPiece() instanceof Rook)){
            Assert.fail("Auf Feld 7/7 ist kein Turm");
        }
        if(!(game.getChessBoard().getField(7, 7).getPiece().getOwner().getColor() == PlayerColor.BLACK)){
            Assert.fail("Die Figur auf Feld 7/7 muss schwarz sein");
        }
    }

    /**
     * Prüft die Zugmöglichkeiten (getPossibleFields) aus der Startposition aller Bauern
     */
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
    /**
     * Prüft die Zugmöglichkeiten (getPossibleFields) des Springers aus der Startposition
     */
    @Test
    public void knightsOnBaseLine(){
    	Piece p = game.getChessBoard().getField(1, 0).getPiece();
        if(!(p.getPossibleFields().contains(game.getChessBoard().getField(0, 2)))){
            Assert.fail("Das weisse Springer in der Grundstellung auf Feld 1 / 0 muss auf Feld 0 / 2 kommen");
        }
        if(!(p.getPossibleFields().contains(game.getChessBoard().getField(2, 2)))){
            Assert.fail("Das weisse Springer in der Grundstellung auf Feld 1 / 0 muss auf Feld 2 / 2 kommen");
        }            
        if(p.getPossibleFields().size() != 2 ){
            Assert.fail("In der Grundstellung hat der Springer zwei Zugsmöglichkeiten.");
        }
        
    	p = game.getChessBoard().getField(6, 7).getPiece();
        if(!(p.getPossibleFields().contains(game.getChessBoard().getField(5, 5)))){
            Assert.fail("Das schwarze Springer in der Grundstellung auf Feld 6 / 7 muss auf Feld 5 / 5 kommen");
        }
        if(!(p.getPossibleFields().contains(game.getChessBoard().getField(7, 5)))){
            Assert.fail("Das schwarze Springer in der Grundstellung auf Feld 6 / 7 muss auf Feld 7 / 5 kommen");
        }            
        if(p.getPossibleFields().size() != 2 ){
            Assert.fail("In der Grundstellung hat der Springer zwei Zugsmöglichkeiten.");
        }
    }   
    /**
     * Prüfung, dass Türme keine Zugsmöglichkeiten haben in der Grundposition
     */
    @Test
    public void rooksOnBaseLine(){
    	Piece p = game.getChessBoard().getField(0, 0).getPiece();
        if(p.getPossibleFields().size() != 0){
            Assert.fail("In der Grundstellung hat der Turm keine Zugsmöglichkeit.");
        }
        
    	p = game.getChessBoard().getField(7, 7).getPiece();
        if(p.getPossibleFields().size() != 0){
            Assert.fail("In der Grundstellung hat der Turm keine Zugsmöglichkeit.");
        }
    } 
    /**
     * Prüfung, dass Läufer keine Zugsmöglichkeiten haben in der Grundposition
     */    
    @Test
    public void biShopsOnBaseLine(){
    	Piece p = game.getChessBoard().getField(2, 0).getPiece();
        if(p.getPossibleFields().size() != 0){
            Assert.fail("In der Grundstellung hat der Läufer keine Zugsmöglichkeit.");
        }
        
    	p = game.getChessBoard().getField(5, 7).getPiece();
        if(p.getPossibleFields().size() != 0){
            Assert.fail("In der Grundstellung hat der Läufer keine Zugsmöglichkeit.");
        }
    } 
    /**
     * Im ChessBoard hat es ein zweidiemensionales Array aller Felder. Zusätzlich weiss jedes Feld, auf welchem Feld es steht. Dies muss übereinstimmen. 
     */
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
    /**
     * Verschiedene Bauernzüge und Kontrollen: 
     * <ul> 
     * <li>Zug des weissen Bauern B2 nach B4</li>
     * <li><font color="green">Prüfung, ob auf B4 ein Bauer steht </font></li>
     * <li><font color="green">Prüfung, ob auf B2 kein Bauer steht </font></li>
     * <li><font color="green">Prüfung, ob sich in der Zugliste ein Zug befindet </font></li>
     * <li><font color="green">Prüfungen, ob alle Attribute des Turn richtig erfasst sind</font></li>
     * <li>Zug von C7 nach C5</li>
     * <li><font color="green">Prüfung, ob auf C5 ein Bauer steht </font></li>
     * <li><font color="green">Prüfung, ob auf C7 kein Bauer steht </font></li>
 	 * <li>Zug B4 x C5 (Schlagen des schwarzen Bauerns) </li>
     * <li><font color="green">Prüfung, ob auf C5 ein weisser Bauer steht </font></li>
     * <li><font color="green">Prüfung, im Turn das Attribut capturedPieces korrekt gesetzt ist. </font></li>
     * <li><font color="green">In den capturedPieces der Klasse game ist der schwarze Bauer drin</font></li>
     * </ul>
     */
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
    /**
     * Verschiedene Züge mit dem Springer und Kontrollen.
     */
    @Test
    public void moveKnights(){
    	
    	ChessField fromField = game.getChessBoard().getField(6, 7);
    	ChessField toField = game.getChessBoard().getField(5, 5);
    	game.getChessBoard().movePiece(fromField, toField);
    	if(!(game.getChessBoard().getField(5, 5).getPiece() instanceof Knight)){
    		Assert.fail("Der Springerzug nach vorne, eins rechts hat nicht funktioniert. ");
    	}
    	if(!(game.getChessBoard().getField(6, 7).getPiece() == null)){
    		Assert.fail("Das Feld muss leer sein, nachdem der Springer weggezogen ist. ");
    	}
    	if(!(game.getTurnList().size() == 4)){
    		Assert.fail("In der Zugliste müssen 4 Datensätze sein. Es befinden sich "+ game.getTurnList().size() +" Datensätze in der Liste");
    	} else {
    		if(!(game.getTurnList().get(3).getFromField() == fromField)){
    			Assert.fail("Beim ersten Zug ist ein falsches Von-Feld angegeben");
    		}
    		if(!(game.getTurnList().get(3).getToField() == toField)){
    			Assert.fail("Beim ersten Zug ist ein falsches to-Feld angegeben");
    		}
    		if(!(game.getTurnList().get(3).getActivePlayer() == game.getPlayerBlack())){
    			Assert.fail("Beim ersten Zug ist ein falscher Spieler eingetragen");
    		}
    		if(!(game.getTurnList().get(3).getCapturedPiece() == null)){
    			Assert.fail("Beim Zug wurde nichts geschlagen.");
    		}
    		if(!(game.getTurnList().get(3).getMovingPiece() instanceof Knight)){
    			Assert.fail("Beim Zug hat sich ein Springer bewegt");
    		}
    	}
    	
    	fromField = game.getChessBoard().getField(1, 0);
    	toField = game.getChessBoard().getField(2, 2);
    	game.getChessBoard().movePiece(fromField, toField);
    	if(!(game.getChessBoard().getField(2, 2).getPiece() instanceof Knight)){
    		Assert.fail("Der Springerzug von zwei nach vorne, eins rechts hat nicht funktioniert. ");
    	}
    	if(!(game.getChessBoard().getField(1, 0).getPiece() == null)){
    		Assert.fail("Das Feld muss leer sein, nachdem der Springer weggezogen ist.");
    	}
    	
    	//schwarzen Springer in Position bringen, damit er geschlagen werden kann
    	fromField = game.getChessBoard().getField(5, 5);
    	toField = game.getChessBoard().getField(4, 3);
    	game.getChessBoard().movePiece(fromField, toField);
    	
    	fromField = game.getChessBoard().getField(2, 2);
    	toField = game.getChessBoard().getField(4, 3);
    	Piece pieceToCapture = toField.getPiece();
    	game.getChessBoard().movePiece(fromField, toField);
    	
    	if(!(game.getChessBoard().getField(4, 3).getPiece() instanceof Knight 
    			&& game.getChessBoard().getField(4, 3).getPiece().getOwner().getColor() == PlayerColor.WHITE)) {
    		Assert.fail("Springer schlagen hat nicht funktioniert. Auf Feld 4, 3 steht kein weisser Springer. ");
    	}
    	if(!(game.getTurnList().get(6).getCapturedPiece() instanceof Knight && game.getTurnList().get(6).getCapturedPiece().getOwner().getColor() == PlayerColor.BLACK)){
    		Assert.fail("In der Zugsauflistung befindet sich kein schwarzer Springer als captured Piece");
    	}
    	if(!(game.getCapturedPieces().contains(pieceToCapture))){
    		Assert.fail("In der Liste der geschlagenen Figuren fehlt der schwarze Springer. ");
    	}
    }
    /**
     * Verschiedene Züge mit dem Turm und Kontrolle danach. 
     */
    @Test
    public void moveRooks(){
    	
    	ChessField fromField = game.getChessBoard().getField(7, 7);
    	ChessField toField = game.getChessBoard().getField(6, 7);
    	game.getChessBoard().movePiece(fromField, toField);
    	if(!(game.getChessBoard().getField(6, 7).getPiece() instanceof Rook)){
    		Assert.fail("Der Turmzug eins nach rechts hat nicht funktioniert. ");
    	}
    	if(!(game.getChessBoard().getField(7, 7).getPiece() == null)){
    		Assert.fail("Das Feld muss leer sein, nachdem der Turm weggezogen ist. ");
    	}
    	if(!(game.getTurnList().size() == 8)){
    		Assert.fail("In der Zugliste müssen 7 Datensätze sein. Es befinden sich "+ game.getTurnList().size() +" Datensätze in der Liste");
    	} else {
    		if(!(game.getTurnList().get(7).getFromField() == fromField)){
    			Assert.fail("Beim ersten Zug ist ein falsches Von-Feld angegeben");
    		}
    		if(!(game.getTurnList().get(7).getToField() == toField)){
    			Assert.fail("Beim ersten Zug ist ein falsches to-Feld angegeben");
    		}
    		if(!(game.getTurnList().get(7).getActivePlayer() == game.getPlayerBlack())){
    			Assert.fail("Beim ersten Zug ist ein falscher Spieler eingetragen");
    		}
    		if(!(game.getTurnList().get(7).getCapturedPiece() == null)){
    			Assert.fail("Beim Zug wurde nichts geschlagen.");
    		}
    		if(!(game.getTurnList().get(7).getMovingPiece() instanceof Rook)){
    			Assert.fail("Beim Zug hat sich ein Turm bewegt");
    		}
    	}
    	
    	fromField = game.getChessBoard().getField(0, 0);
    	toField = game.getChessBoard().getField(1, 0);
    	game.getChessBoard().movePiece(fromField, toField);
    	if(!(game.getChessBoard().getField(1, 0).getPiece() instanceof Rook)){
    		Assert.fail("Der Turmzug eins nach rechts hat nicht funktioniert. ");
    	}
    	if(!(game.getChessBoard().getField(0, 0).getPiece() == null)){
    		Assert.fail("Das Feld muss leer sein, nachdem der Turm weggezogen ist.");
    	}
    	
    	//Schwarz einen Zug machen lassen (mit seinem Bauern)
    	fromField = game.getChessBoard().getField(3, 1);
    	toField = game.getChessBoard().getField(3, 2);
    	game.getChessBoard().movePiece(fromField, toField);
    	
    	fromField = game.getChessBoard().getField(1, 0);
    	toField = game.getChessBoard().getField(1, 6);
    	Piece pieceToCapture = toField.getPiece();
    	game.getChessBoard().movePiece(fromField, toField);
    	
    	if(!(game.getChessBoard().getField(1, 6).getPiece() instanceof Rook 
    			&& game.getChessBoard().getField(1, 6).getPiece().getOwner().getColor() == PlayerColor.WHITE)) {
    		Assert.fail("Turm schlagen hat nicht funktioniert. Auf Feld 1, 6 steht kein weisser Turm. ");
    	}
    	if(!(game.getTurnList().get(10).getCapturedPiece() instanceof Pawn && game.getTurnList().get(10).getCapturedPiece().getOwner().getColor() == PlayerColor.BLACK)){
    		Assert.fail("In der Zugsauflistung befindet sich kein schwarzer Bauer als captured Piece");
    	}
    	if(!(game.getCapturedPieces().contains(pieceToCapture))){
    		Assert.fail("In der Liste der geschlagenen Figuren fehlt der schwarze Bauer. ");
    	}
    }
}
