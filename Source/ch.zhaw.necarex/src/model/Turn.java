package model;

import model.pieces.Piece;
/**
 * Ein Zug (Turn) im Schach besteht aus einer Figur, die von einem Feld zu einem anderen zieht und dort allenfalls eine andere Figur schlägt.
 */
public class Turn {
	private ChessField fromField;
	private Piece movingPiece;
	private ChessField toField;
	private Piece capturedPiece;
	private Player activePlayer;
	
	/**
	 * Setzt alle Attribute des Zugs. Danach sind keine Änderungen mehr möglich, da ein gemachter Spielzug nicht geändert werden kann. 
	 * @param fromField
	 * @param toField
	 */
	public Turn(ChessField fromField, ChessField toField){
		this.fromField = fromField;
		this.toField = toField;
		
		this.movingPiece = fromField.getPiece();
		this.capturedPiece = toField.getPiece();
		this.activePlayer = fromField.getPiece().getOwner();
	}

	/**
	 * Gibt das Startfeld zurück
	 * @return Startfeld
	 */
	public ChessField getFromField() {
		return fromField;
	}
	/**
	 * Gibt die Figur zurück, die sich bewegt
	 * @return Figur, die sich bewegt
	 */
	public Piece getMovingPiece() {
		return movingPiece;
	}
	/**
	 * Gibt das Zielfeld zurück
	 * @return Zielfeld des Zugs
	 */
	public ChessField getToField() {
		return toField;
	}

	/**
	 * Gibt die geschlagene Figur zurück. Falls keine Figur geschlagen wurde, wird null zurückgegeben. 
	 * @return geschlagene Figur / null
	 */
	public Piece getCapturedPiece() {
		return capturedPiece;
	}

	/**
	 * Gibt den Spieler zurück, der den Zug ausgeführt hat. 
	 * @return Gibt den Spieler zurück, der den Zug ausgeführt hat. 
	 */
	public Player getActivePlayer() {
		return activePlayer;
	}
	

}