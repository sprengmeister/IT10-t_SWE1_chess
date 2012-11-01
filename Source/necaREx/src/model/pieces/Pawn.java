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

    public Pawn(Player player, int col, int row, ChessBoard chessBoard){
        super(player, col, row, chessBoard);
    }

    @Override
    public ArrayList<ChessField> getPossibleFields() {
        ArrayList<ChessField> possibleFields = new ArrayList<>();
        ChessField c = null;

        // Weisse Bauern ziehen von row 1 Richtung row 7
        if(this.getOwner().getColor() == PlayerColor.WHITE){
            // Bauernzug: Bauer bleibt auf der gleichen Spalte und zieht ein Feld nach vorne
            c = this.getChessBoard().getField(this.getCol(), (this.getRow()+1));
            if(c.getPiece() == null){
                possibleFields.add(c);        
            }
            
            if(this.getRow() == 1 && c.getPiece() == null){
                // Doppelschritt, wenn er Bauer noch auf der Grundlinie steht
                c = this.getChessBoard().getField(this.getCol(), (this.getRow()+2));
                if(c.getPiece() == null){
                   possibleFields.add(c);        
                }
            }
            // Schlagen nach links, wenn er nicht am Rand steht
            if(this.getCol() != 0){
                c = this.getChessBoard().getField((this.getCol()-1), (this.getRow()+1));
                if(c.getPiece() != null && c.getPiece().getOwner().getColor() == PlayerColor.BLACK){
                    possibleFields.add(c);
                }
            }
            
            if(this.getCol() != 7){
                c = this.getChessBoard().getField((this.getCol()+1), (this.getRow()+1));
                if(c.getPiece() != null && c.getPiece().getOwner().getColor() == PlayerColor.BLACK){
                    possibleFields.add(c);
                }
            }           
            // TODO Schlagen en passant
            // Schwierigkeit: Abhängigkeit von 2 Zügen, es kann nur en passant geschlagen werden, 
            //wenn weisser Bauer e2 - e4 gezogen hat und schwarz im direkt folgenden Zug d4 x e3 schlägt.                
        } else {
             // Bauernzug: Bauer bleibt auf der gleichen Spalte und zieht ein Feld nach vorne
            c = this.getChessBoard().getField(this.getCol(), (this.getRow()-1));
            if(c.getPiece() == null){
                possibleFields.add(c);        
            }
            // Doppelschritt, wenn er Bauer noch auf der Grundlinie steht
            if(this.getRow() == 6 && c.getPiece() == null){
                c = this.getChessBoard().getField(this.getCol(), (this.getRow()-2));
                if(c.getPiece() == null){
                   possibleFields.add(c);        
                }
            }
            // Schlagen nach links, wenn er nicht am Rand steht
            if(this.getCol() != 0){
                c = this.getChessBoard().getField((this.getCol()-1), (this.getRow()-1));
                if(c.getPiece() != null && c.getPiece().getOwner().getColor() == PlayerColor.WHITE){
                    possibleFields.add(c);
                }
            }
            
            if(this.getCol() != 7){
                c = this.getChessBoard().getField((this.getCol()+1), (this.getRow()-1));
                if(c.getPiece() != null && c.getPiece().getOwner().getColor() == PlayerColor.WHITE){
                    possibleFields.add(c);
                }
            }           
            // TODO Schlagen en passant
            // Schwierigkeit: Abhängigkeit von 2 Zügen, es kann nur en passant geschlagen werden, 
            //wenn weisser Bauer e2 - e4 gezogen hat und schwarz im direkt folgenden Zug d4 x e3 schlägt.              
        }
        
        return possibleFields;
    }
}
