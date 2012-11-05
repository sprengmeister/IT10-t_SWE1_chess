/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pieces;

import java.util.ArrayList;
import model.*;

/**
 * Spielfigur Bauer
 * @author florian
 */
public class Pawn extends Piece {
    
    private ArrayList<ChessField> possibleFields;
    private int direction;

    /**
     * Konstruktor: Erzeugt einen Bauern
     * @param player Referenz zum Spieler 
     * @param col Referenz zur Spalte
     * @param row Referenz zur Zeile
     * @param chessBoard Referenz zum Schachbrett 
     */
    public Pawn(Player player, int col, int row, ChessBoard chessBoard){
        super(player, col, row, chessBoard);
        this.setTurnDirectionByPlayer();     
    }

    /**
     * Erzeugt eine Liste der möglichen Zugfelder für diese Figur und gibt sie zurück. 
     * @return Liste aller möglichen Züge, die die Figur machen kann. 
     */
    @Override
    public ArrayList<ChessField> getPossibleFields() {
        if(possibleFields == null){
            possibleFields = new ArrayList<>();
        } else{
            possibleFields.clear();
        }

        // TODO Schlagen en passant
        // Schwierigkeit: Abhängigkeit von 2 Zügen, es kann nur en passant geschlagen werden, 
        //wenn weisser Bauer e2 - e4 gezogen hat und schwarz im direkt folgenden Zug d4 x e3 schlägt.              

        this.checkPossiblePawnTurn();
        this.checkPossiblePawnDoubleTurn();
        this.checkPossiblePawnCapture();

        //TODO prüfen ob mit diesem Zug eine Schachsituation ausgelöst wurde
        
        return possibleFields;
    }
    /**
     * Setzt die interne Variable direction aufgrund der Farbe. Weiss fährt von der Reihe 1 zu 7, Schwarz von 6 zu 0
     */
    private void setTurnDirectionByPlayer(){
        if(this.owner.getColor() == PlayerColor.WHITE){
            this.direction = 1;
        } else {
            this.direction = -1;
        }
    }
    
    /**
     * Prüft, ob der Bauer ein Feld nach vorne fahren kann. 
     */
    private void checkPossiblePawnTurn(){
        ChessField targetField =  this.chessBoard.getField(col, (row+(this.direction * 1 )));
        if(targetField.getPiece() == null){
            possibleFields.add(targetField);    
        }
    }
    /**
     * Prüft, ob der Bauer schlagen kann
     */
    private void checkPossiblePawnCapture(){
        ChessField targetField = null;
        if(this.getCol() != 0){
            targetField = this.getChessBoard().getField((this.getCol()-1), (this.getRow()+direction * 1));
            if(targetField.getPiece() != null && targetField.getPiece().getOwner().getColor() == PlayerColor.BLACK){
                possibleFields.add(targetField);
            }
        }
        if(this.getCol() != 7){
            targetField = this.getChessBoard().getField((this.getCol()+1), (this.getRow()+ direction * 1));
            if(targetField.getPiece() != null && targetField.getPiece().getOwner().getColor() == PlayerColor.BLACK){
                possibleFields.add(targetField);
            }
        }           
    }
    
    /**
     * Prüft ob der Bauer zwei Felder nach vorne fahren kann. 
     */
    private void checkPossiblePawnDoubleTurn(){
        if(this.checkPawnIsOnBaseLine()){
            ChessField targetField =  this.chessBoard.getField(col, (row+(this.direction * 2)));
            if(targetField.getPiece() == null){
                possibleFields.add(targetField);    
            }
        }
    }
    /**
     * Prüft ob ein Bauer auf der Grundlinie (Weiss Zeile 1, Schwarz Zeile 6)
     * @return true, wenn sich der Bauer auf der Grundlinie befindet, ansonsten false
     */
    private boolean checkPawnIsOnBaseLine(){
        if(this.owner.getColor() == PlayerColor.WHITE && row == 1){
            return true;
        }
        if(this.owner.getColor() == PlayerColor.BLACK && row == 6 ){
            return true;
        }
        return false;
    }
    
}
