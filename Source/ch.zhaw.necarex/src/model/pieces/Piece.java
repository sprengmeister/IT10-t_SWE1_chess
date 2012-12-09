package model.pieces;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import model.ChessBoard;
import model.ChessField;
import model.Player;

/**
 * Die Klasse Piece (abstrakt) beschreibt alles, was alle Schachfiguren gemeinsam haben. Alle Schachfiguren gehören einem Owner, stehen auf einem Schachfeld.Jede Schachfigur muss eine Methode implementieren, die die möglichen Felder zurück gibt.  
 */
public abstract class Piece implements Cloneable {
    private Player owner;
    private ChessField chessField;
    private ChessBoard chessBoard;
    private Point spriteIndex;
    protected ArrayList<ChessField> possibleFields;
    protected int pieceValue;

	/**
	 * Setzt Referenzen zum Owner der Figur und zum Schachbrett
	 * @param owner Spieler (weiss/schwarz), dem die Figur gehört
	 * @param chessBoard Referenz zum Schachbrett
	 */
    public Piece(Player owner, ChessBoard chessBoard){
        this.owner = owner;
        this.chessBoard = chessBoard;
    }
    /**
     * Abstrakte Methode, die alle Felder zurück gibt, auf welche die Figur aktuell ziehen kann.<br />
     * Achtung: Ein Zug, nachdem der eigene König sich in einer Schachsituation befindet ist nicht zulässig! 
     * @return Liste aller Felder, auf die eine Figur ziehen kann. 
     */
    public abstract ArrayList<ChessField> getPossibleFields();
    
    /**
     * Gibt eine Liste zurück aller Felder, die von der Figur bedroht 
     * sind und zum Beispiel einen daraufstehenden gegnerischen König schachstellen
     * @return Liste aller Felder, die von der Figur bedroht werden. 
     */
    public abstract ArrayList<ChessField> getDaringFields();


	/**
	 * Prüft die Zugliste, ob ein Zug den eigenen König in eine Schachsituation bringt und entfernt entsprechende Züge.  
	 * @param possibleFields 
	 * @return possible Fields ohne die nicht erlaubten Züge 
	 */
	protected ArrayList<ChessField> checkDaringOwnKing(ArrayList<ChessField> possibleFields){

		Iterator<ChessField> possibleFieldIterator = possibleFields.iterator();
		while (possibleFieldIterator.hasNext()) {
			ChessField toItem = possibleFieldIterator.next();
			// Zug probeweise durchführen
			ChessBoard cbTest = this.getChessBoard().clone();
			ChessField from = cbTest.getField(this.getChessField().getCol(), this.getChessField().getRow());
			ChessField to = cbTest.getField(toItem.getCol(), toItem.getRow());
			
			to.exchangePiece(from.movePieceAway());
			boolean delete = false;
	    	for(int col=0;col<8;col++){
	    		for(int row = 0;row < 8; row++){
	    			Piece pieceOnField = cbTest.getField(col, row).getPiece();
	    			if(pieceOnField!= null && pieceOnField.getOwner() != this.getOwner() &&  pieceOnField.daresOpponentKing()){
	    				delete = true;
	    			}
	    		}
	    	}
	    	// remove des Iterators darf nur einmal aufgerufen werden. 
	    	if(delete){
				possibleFieldIterator.remove();
	    	}
	    	// Testschachbrett löschen
	    	cbTest = null;
        }
		
		return possibleFields;
	}
	
	/**
	 * Prüft, ob die Figur den gegnerischen König bedroht
	 * @return true: Figur bedroht den gegnerischen König, ansonsten false
	 */
	public boolean daresOpponentKing(){
		ArrayList<ChessField> reachableFieldList = this.getDaringFields();
		for (ChessField chessField : reachableFieldList) {
			// auf einem reachable Field steht ein gegnerischer König. 
			if(chessField.getPiece() != null &&  
				chessField.getPiece() instanceof King && 
				chessField.getPiece().getOwner() != this.getOwner()){
				
				return true;
			}
		}
		return false;
	}
	/**
	 * Klont die aktuelle Figur
	 * @param chessField geklontes Schachfeld, auf dem die geklonte Figur zu stehen kommt
	 * @param chessBoard geklontes Schachbrett, auf dem die geklonte Figur steht. 
	 * @return
	 */
    public Piece clone(ChessField chessField, ChessBoard chessBoard){
    	try {
			Piece p = (Piece) super.clone();
			p.chessField = chessField;
			p.chessBoard = chessBoard;
			return p;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * Wenn es sich bei der Figur auf dem Feld um eine eigene Figur handelt, kann dieses Feld nicht erreicht werden
     * Handelt es sich um eine gegnerische Figur, kann diese geschlagen werden. 
     * @param targetField zu prüfendes Feld
     */
    protected void checkForPieceCapture(ChessField targetField){
    	if (targetField.getPiece().getOwner() != this.getChessField().getPiece().getOwner()) {
    		possibleFields.add(targetField);
		}
    }
    /**
     * Methode, die von einzelnen Figuren verwendet werden kann. Prüft, welche Felder in der Spalte von einer Figur erreichbar sind / sie erreichen kann und fügt die möglichen Felder der Liste possibleFields hinzu. 
     */
    protected void checkTurnColumn(){
    	boolean goFurther = true;
    	int col = (this.getChessField().getCol()+1);
    	while(col <= 7 && goFurther){
    		goFurther = this.addToPossibleFieldList(col, this.getChessField().getRow());
    		col++;
    	}
    	goFurther = true;
    	col = (this.getChessField().getCol()-1);
    	while(col >= 0 && goFurther){
    		goFurther = this.addToPossibleFieldList(col, this.getChessField().getRow());
    		col--;
    	}
    } 
    /**
     * Methode, die von einzelnen Figuren verwendet werden kann. Prüft, welche Felder in der Zeile von einer Figur erreichbar sind / sie erreichen kann und fügt die möglichen Felder der Liste possibleFields hinzu. 
     */
   
    protected void checkTurnRow(){
    	
    	boolean goFurther = true;
    	int row = (this.getChessField().getRow()+1);
    	while(row <= 7 && goFurther){
    		goFurther = this.addToPossibleFieldList(this.getChessField().getCol(), row);
    		row++;
    	}
    	goFurther = true;
    	row = (this.getChessField().getRow()-1);
    	while(row >= 0 && goFurther){
    		goFurther = this.addToPossibleFieldList(this.getChessField().getCol(), row);
    		row--;
    	}

   }   
    /**
     * Methode, die von einzelnen Figuren verwendet werden kann. Prüft, welche Felder in den Diagonalen von einer Figur erreichbar sind / sie erreichen kann und fügt die möglichen Felder der Liste possibleFields hinzu. 
     */
    protected void checkTurnDiag(){
    	
    	int row = this.getChessField().getRow()+1;
    	int col = this.getChessField().getCol()+1;
    	boolean goFurther = true;
    	while(row <= 7 && col <= 7 && goFurther){
    		goFurther = this.addToPossibleFieldList(col, row);
    		row++;
    		col++;
    	}
    	
    	row = this.getChessField().getRow()-1;
    	col = this.getChessField().getCol()-1;
    	goFurther = true;
    	while(row >=0 && col >= 0 && goFurther){
    		goFurther = this.addToPossibleFieldList(col, row);
    		row--;
    		col--;
    	}
    	
    	row = this.getChessField().getRow()-1;
    	col = this.getChessField().getCol()+1;
    	goFurther = true;
    	while(row >=0 && col <=7 && goFurther){
    		goFurther = this.addToPossibleFieldList(col, row);
    		row--;
    		col++;
    	}

    	row = this.getChessField().getRow()+1;
    	col = this.getChessField().getCol()-1;
    	goFurther = true;
    	while(row <= 7 && col >= 0 && goFurther){
    		goFurther = this.addToPossibleFieldList(col, row);
    		row++;
    		col--;
    	}    	
    	
     }
    /**
     * Prüft ob ein Feld zur Liste hinzugefügt werden kann (keine eigene Figur steht darauf). Gibt false zurück, falls die Figur nicht mehr weiter fahren kann
     * @param col
     * @param row
     * @return true: das Feld wurde eingefügt, die Figur. Es wurde keine Figur geschlagen. <br /> false: die Figur ist auf eine andere Figur gestossen - falls es eine gegnerische ist, wurde diese geschlagen
     */
    private boolean addToPossibleFieldList(int col, int row){   	
    	ChessField targetField = this.getChessBoard().getField(col, row);	
		if (targetField.getPiece() == null) {	
			possibleFields.add(targetField);
			return true;
		} else {
			checkForPieceCapture(targetField);
			return false; 
		}    			
    } 
    /**
     * Gibt das Schachfeld zurück, auf dem die Figur steht
     * @return Schachfeld
     */
    public ChessField getChessField() {
    	return chessField;
    }

    /**
     * Setzt das Schachfeld auf dem die Figur steht
     * @param chessField Schachfeld, auf dem die Figur neu steht
     */
    public void setChessField(ChessField chessField) {
        this.chessField = chessField;
    }

    /**
     * Gibt den Spieler zurück, dem die Figur gehört
     * @return Spieler dem die Figur gehört
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Gibt das aktuelle Schachbrett zurück
     * @return Schachbrett
     */
    public ChessBoard getChessBoard() {
    	return chessBoard;
    }
    
    /**
     * Gibt den Wert der Figur zurück. <br/>
     * Wird benötigt zur Bewertung der Schachsituation durch den Computer. 
     * @return Figurenwert 
     */
    public int getPieceValue(){
    	return pieceValue;
    }
    /**
     * Setzt den Wert einer Figur
     * Kann verwendet werden, um aufgrund einer Schachsituation der Wert einer Figur zu verändern
     * @param pieceValue
     */
    protected void setPieceValue(int pieceValue){
    	this.pieceValue = pieceValue;
    }
    /**
     * Position des Bildes der Schachfigur auf dem Sprite-Bild
     * @return Index der Position auf dem Bild
     */
    public Point getSpriteIndex() {
		return spriteIndex;
	}
    /**
     * Setzt die Position des Bildes der Schachfigur auf dem Sprite-Bild
     * @param spriteIndex neuer Index der Position des Bildes
     */
	protected void setSpriteIndex(Point spriteIndex) {
		this.spriteIndex = spriteIndex;
	}
}
