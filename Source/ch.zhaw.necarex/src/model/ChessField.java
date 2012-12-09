package model;

import model.pieces.*;

/**
 * Auf einem Schachfeld (ChessField), das auf einer Spalte und einer Zeile steht, kann eine Figur stehen. 
 */
public class ChessField implements Cloneable {
    private Piece piece;
    private int col;
    private int row;

    /**
     * Erstellt ein Schachfeld. 
     * @param initialPiece Figur in Grundaufstellung 
     */
    public ChessField(Piece initialPiece, int col, int row){
        this.piece = initialPiece;
        this.col = col;
        this.row = row;
    }
    /**
     * Setzt die übergebene Figur auf das Feld und gibt die alte Figur zurück
     * @param newPiece Figur, die neu auf dem Feld stehen soll
     * @return Figur, die vorher auf dem Feld stand (null, bei keiner Figur)
     */
    public Piece exchangePiece(Piece newPiece){
        Piece oldPiece = getPiece();
        this.piece = newPiece;
        newPiece.setChessField(this);
        return oldPiece;
    }
    /**
     * Gibt die Figur zurück und entfernt sie vom aktuellen Feld. <br />
     * Wird benötigt, wenn eine Figur von diesem Feld auf ein anderes Feld zieht. 
     * @return Figur, die vorher auf dem Feld stand
     */
    public Piece movePieceAway(){
    	Piece oldPiece = this.piece;
    	this.piece = null;
    	return oldPiece;
    }

    /**
     * Gibt die Figur, die auf dem Feld steht zurück
     * @return Figur die auf dem Feld steht
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Gibt die Spalte zurück, in der sich das Schachfeld befindet
     * @return Spalte des Schachfelds
     */
    public int getCol() {
        return col;
    }

    /**
     * Gibt die Zeile zurück, in der sich das Schachfeld befindet. 
     * @return Zeile
     */
    public int getRow() {
        return row;
    }
    
    /**
     * Klont ein Schachfeld sowie, falls vorhanden, die Figur die darauf steht. 
     * @param cb Referenz zum Schachbrett. Wird benötigt, da die Figur eine Referenz zum Schachbrett benötigt
     * @return geklontes Schachfeld
     */
    public ChessField clone(ChessBoard cb){
    	ChessField cf = null;
    	try {
			cf = (ChessField)super.clone();
			if(this.piece != null){
				cf.piece = this.piece.clone(cf, cb);
			}
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return cf;
    	
    }
    
}

