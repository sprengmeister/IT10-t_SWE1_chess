package model;

import model.pieces.Piece;

public class Turn {
	private ChessField fromField;
	private Piece fromPiece;
	private ChessField toField;
	private Piece capturedPiece;
	private Player activePlayer;
	
	public Turn(ChessField fromField, ChessField toField){
		this.fromField = fromField;
		this.toField = toField;
		
		this.fromPiece = fromField.getPiece();
		this.capturedPiece = toField.getPiece();
		this.activePlayer = fromField.getPiece().getOwner();
	}

	public ChessField getFromField() {
		return fromField;
	}

	public Piece getFromPiece() {
		return fromPiece;
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