package model;

import model.pieces.Piece;
/**
 * Ein Zug (Turn) im Schach besteht aus einer Figur, die von einem Feld zu einem anderen zieht und dort allenfalls eine andere Figur schlägt.
 * Die Attribute werden über den Konstruktor gesetzt, danach sind keine Änderungen mehr möglich, da ein gemachter Spielzug nicht geändert werden kann. 
 * @author florian
 */
public class Turn {
	private ChessField fromField;
	private Piece movingPiece;
	private ChessField toField;
	private Piece capturedPiece;
	private Player activePlayer;
	
	public Turn(ChessField fromField, ChessField toField){
		this.fromField = fromField;
		this.toField = toField;
		
		this.movingPiece = fromField.getPiece();
		this.capturedPiece = toField.getPiece();
		this.activePlayer = fromField.getPiece().getOwner();
	}

	public ChessField getFromField() {
		return fromField;
	}

	public Piece getMovingPiece() {
		return movingPiece;
	}

	public ChessField getToField() {
		return toField;
	}

	public Piece getCapturedPiece() {
		return capturedPiece;
	}

	public Player getActivePlayer() {
		return activePlayer;
	}
	

}