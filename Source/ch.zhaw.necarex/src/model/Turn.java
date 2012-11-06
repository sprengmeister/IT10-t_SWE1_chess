package model;

import model.pieces.Piece;

public class Turn {
	private ChessField fromField;
	private Piece fromPiece;
	private ChessField toField;
	private Piece toPiece;
	
	private Turn(ChessField fromField, Piece fromPiece, ChessField toField, Piece toPiece){
		this.fromField = fromField;
		this.fromPiece = fromPiece;
		this.toField = toField;
		this.toPiece = toPiece;
	}
	

}
