package model.ai;

import java.util.ArrayList;
import java.util.Collections;

import model.ChessBoard;
import model.ChessField;
import model.Game;
import model.Player;
import model.pieces.Piece;

/**
 * Stellt eine mögliche Schachsituation dar. Diese Klasse nimmt die Bewertung der Schachsituation vor. 
 * @author florian
 *
 */
public class PossibleSituation implements Comparable<PossibleSituation> {
	private ChessBoard board;
	private ChessField from;
	private ChessField to;
	private ArrayList<PossibleSituation> possibleSituationChilds;
	
	private int score;
	
	private Game game;

	public PossibleSituation(Game game, ChessBoard board){
		this.board = board;
		this.game = game;
		
		possibleSituationChilds = new ArrayList<PossibleSituation>();
	}
	public PossibleSituation(Game game, ChessBoard board, ChessField from, ChessField to){
		this.from = from;
		this.to = to;
		this.game = game;
		this.board = board;
		this.possibleSituationChilds = new ArrayList<PossibleSituation>();
	}
	
	public void calcScore(int hop) {
		ChessField field;
		Piece piece;
		for (int col = 0; col < 8; col++) {
			for (int row = 0; row < 8; row++) {
				field = board.getField(col, row);
				piece = field.getPiece();
				if (piece != null && piece.getOwner() != this.getActivePlayer(hop)){
					if (hop%2!=0)
						 this.score += piece.getPieceValue();
					else this.score -= piece.getPieceValue();
				}
			}
		}
	}
	
	public void findPossibleSituationChilds(int hop){
	   	for(int col=0;col<8;col++){
	   		for(int row = 0;row < 8; row++){
	   			ChessField fromField = board.getField(col, row);
	   			if(fromField.getPiece() != null && fromField.getPiece().getOwner() == this.getActivePlayer(hop)){
	   				ArrayList<ChessField> possibleFieldList = fromField.getPiece().getPossibleFields();
	   				
	   				for (ChessField chessField : possibleFieldList) {
		   				ChessBoard cbAfterTurn = board.clone();
		   				ChessField from = cbAfterTurn.getField(fromField.getCol(), fromField.getRow());
		   				ChessField to = cbAfterTurn.getField(chessField.getCol(), chessField.getRow());
		   				
		   				to.exchangePiece(from.movePieceAway());						
		   				
		   				PossibleSituation possibleSituation = new PossibleSituation(this.game, cbAfterTurn, from, to);
		   				possibleSituation.calcScore(hop);
		   				if (hop < 2)
		   					possibleSituation.findPossibleSituationChilds(hop+1);
		   				possibleSituationChilds.add(possibleSituation);
					}
	   			}
	   		}
	   	}
	}
	
	private Player getActivePlayer(int hop){
		return hop%2==0 ? game.getActivePlayer() : game.getInactivePlayer();
	}
	
	public PossibleSituation getBestSituation(int i){
		for (PossibleSituation posSit : possibleSituationChilds) {
			PossibleSituation bestChildSituation = posSit.getBestSituation(i+1);
			if (bestChildSituation != null){
				this.score += bestChildSituation.getScore();
				System.out.println(posSit.hashCode() + ";"+i + ";" + posSit.getScore() + ";" + posSit.to.getPiece().getOwner().getColor());
			}
			Collections.sort(possibleSituationChilds);
			return possibleSituationChilds.get(0);
		}
		return null;
	}
	public ChessField getFrom() {
		return from;
	}
	public ChessField getTo() {
		return to;
	}
	public int getScore(){
		return score;
	}

	@Override
	public int compareTo(PossibleSituation situation) {
		if (situation.getScore() > this.getScore()) return 1;
		if (situation.getScore() < this.getScore()) return -1;
		return 0;
	}
	
}
