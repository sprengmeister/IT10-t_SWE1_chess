/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import model.*;

/**
 * Spielfigur Läufer, basiert auf Piece. Kennt die Gangart des und kann dessen mögliche Felder ausrechnen.
 * @author beni
 */
public class BiShop extends Piece {
    
    private int direction;

    /**
     * Konstruktor: Erzeugt eine neue Figur
     * @param player Referenz zum Spieler 
     * @param chessBoard Referenz zum Schachbrett 
     */
    public BiShop(Player player, ChessBoard chessBoard){
        super(player, chessBoard);
        //Index des zugehörigen Sprites setzen
        super.setSpriteIndex(new Point(4, player.getColor() == PlayerColor.WHITE ? 0 : 1));

    }

    /**
     * Erzeugt eine Liste der möglichen Zugfelder für diese Figur und gibt sie zurück. 
     * @return Liste aller möglichen Züge, die die Figur machen kann. 
     */
    @Override
    public ArrayList<ChessField> getPossibleFields() {
    	ArrayList<ChessField> possibleFields = new ArrayList<ChessField>();
    
        this.addAllBiShopTurn(possibleFields);
        this.removeImpossibleBiShopTurn(possibleFields);

        //TODO prüfen ob mit diesem Zug eine Schachsituation ausgelöst wurde
        
        return possibleFields;
    }
    
    /**
     * Fügt alle Züge hinzu, die die Figur fahren kann. 
     */
    private void addAllBiShopTurn(ArrayList<ChessField> possibleFields){
    	//TODO
    }
    
    /**
     * Entfernt die unerlaubten Züge
     */
    private void removeImpossibleBiShopTurn(ArrayList<ChessField> possibleFields){
    	//Figur würde andere Figur der gleichen Farbe schlagen
    	Iterator<ChessField> samePlayer = possibleFields.iterator();
    	while (samePlayer.hasNext()) {
    		ChessField currentChessfield = samePlayer.next();
    		if(currentChessfield.getPiece() != null && currentChessfield.getPiece().getOwner() == this.getChessField().getPiece().getOwner()){
    			samePlayer.remove();
    		}
    	}
    }    

    
}
