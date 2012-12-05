/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pieces;

import java.awt.Point;
import java.util.ArrayList;

import model.ChessBoard;
import model.ChessField;
import model.Player;
import model.PlayerColor;

/**
 * Spielfigur Bauer, basiert auf Piece. Kennt die Gangart des Bauerns und kann dessen mögliche Felder ausrechnen.
 * @author florian
 */
public class Pawn extends Piece {
    
    private int direction;

    /**
     * Konstruktor: Erzeugt einen Bauern
     * @param player Referenz zum Spieler 
     * @param chessBoard Referenz zum Schachbrett 
     */
    public Pawn(Player player, ChessBoard chessBoard){
        super(player, chessBoard);
        this.setTurnDirectionByPlayer();
        super.setPieceValue(10);
        //Index des zugehörigen Sprites setzen
        super.setSpriteIndex(new Point(0, player.getColor() == PlayerColor.WHITE ? 0 : 1));
    }

    /**
     * Erzeugt eine Liste der möglichen Zugfelder für diese Figur und gibt sie zurück. 
     * @return Liste aller möglichen Züge, die die Figur machen kann. 
     */
    @Override
    public ArrayList<ChessField> getPossibleFields() {
    	possibleFields = new ArrayList<ChessField>();
    

        
        
        this.addPossiblePawnTurn();
        this.addPossiblePawnDoubleTurn();
        this.addPossiblePawnCaptures();
        
        // TODO Schlagen en passant
        // Schwierigkeit: Abhängigkeit von 2 Zügen, es kann nur en passant geschlagen werden, 
        //wenn weisser Bauer e2 - e4 gezogen hat und schwarz im direkt folgenden Zug d4 x e3 schlägt.              

        //this.checkSchlagenEnPassant();

        //TODO prüfen ob mit diesem Zug eine Schachsituation ausgelöst wurde
        
        return this.checkDaringOwnKing(possibleFields);
    }
    
    @Override
    public ArrayList<ChessField> getDaringFields(){
		possibleFields = new ArrayList<ChessField>();		
		this.addPossiblePawnCaptures();
		
		return possibleFields;
    }
    
    
    
    /**
     * Setzt die interne Variable direction aufgrund der Farbe. Weiss fährt von der Reihe 1 zu 7, Schwarz von 6 zu 0
     */
    private void setTurnDirectionByPlayer(){
        if(this.getOwner().getColor() == PlayerColor.WHITE){
            this.direction = 1;
        } else {
            this.direction = -1;
        }
    }
    
    /**
     * Prüft, ob der Bauer ein Feld nach vorne fahren kann. 
     */
    private void addPossiblePawnTurn(){
        ChessField targetField =  this.getChessBoard().getField(this.getChessField().getCol(), (this.getChessField().getRow()+(this.direction * 1 )));
        if(targetField.getPiece() == null){
            possibleFields.add(targetField);    
        }
    }
    /**
     * Prüft, ob der Bauer schlagen kann
     */
    private void addPossiblePawnCaptures(){
        ChessField targetField = null;
        if(this.getChessField().getCol() != 0){
            targetField = this.getChessBoard().getField((this.getChessField().getCol()-1), (this.getChessField().getRow()+direction * 1));
            if(targetField.getPiece() != null && targetField.getPiece().getOwner() != this.getOwner()){
                possibleFields.add(targetField);
            }
        }
        if(this.getChessField().getCol() != 7){
            targetField = this.getChessBoard().getField((this.getChessField().getCol()+1), (this.getChessField().getRow()+ direction * 1));
            if(targetField.getPiece() != null && targetField.getPiece().getOwner() != this.getOwner()){
                possibleFields.add(targetField);
            }
        }           
    }
    
    /**
     * Prüft ob der Bauer zwei Felder nach vorne fahren kann. 
     */
    private void addPossiblePawnDoubleTurn(){
        if(this.checkPawnIsOnBaseLine() && checkNoFigureInFrontOfPawn()){
            ChessField targetField =  this.getChessBoard().getField(this.getChessField().getCol(), (this.getChessField().getRow()+(this.direction * 2)));
            if(targetField.getPiece() == null){
                possibleFields.add(targetField);    
            }
        }
    }
    private boolean checkNoFigureInFrontOfPawn(){
    	return  this.getChessBoard().getField(this.getChessField().getCol(), (this.getChessField().getRow() + direction * 1 )).getPiece() == null;
    }
    
    /**
     * Prüft ob ein Bauer auf der Grundlinie (Weiss Zeile 1, Schwarz Zeile 6)
     * @return true, wenn sich der Bauer auf der Grundlinie befindet, ansonsten false
     */
    private boolean checkPawnIsOnBaseLine(){
        if(this.getOwner().getColor() == PlayerColor.WHITE && this.getChessField().getRow() == 1){
            return true;
        }
        if(this.getOwner().getColor() == PlayerColor.BLACK && this.getChessField().getRow() == 6 ){
            return true;
        }
        return false;
    }
    
}
