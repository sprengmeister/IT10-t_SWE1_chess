package model.ai;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.text.Position;

import model.*;

/**
 * Stellt eine mögliche Schachsituation dar. Diese Klasse nimmt die Bewertung der Schachsituation vor. 
 * @author florian
 *
 */
public class PossibleSituation {
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
	}
	
	public void findPossibleSituationChilds(){
	   	for(int col=0;col<8;col++){
	   		for(int row = 0;row < 8; row++){
	   			ChessField fromField = board.getField(col, row);
	   			if(fromField.getPiece() != null && fromField.getPiece().getOwner() == game.getActivePlayer()){
	   				ArrayList<ChessField> possibleFieldList = fromField.getPiece().getPossibleFields();
	   				
	   				for (ChessField chessField : possibleFieldList) {
		   				ChessBoard cbAfterTurn = board.clone();
		   				ChessField from = cbAfterTurn.getField(fromField.getCol(), fromField.getRow());
		   				ChessField to = cbAfterTurn.getField(chessField.getCol(), chessField.getRow());
		   				
		   				to.exchangePiece(from.movePieceAway());						
		   				
		   				PossibleSituation possibleSituation = new PossibleSituation(this.game, cbAfterTurn, from, to);
		   				possibleSituationChilds.add(possibleSituation);
					}
	   			}
	   		}
	   	}
	}
	
	public PossibleSituation getBestSituation(){
		Collections.shuffle(possibleSituationChilds);
		return possibleSituationChilds.get(0);
	}
	public ChessField getFrom() {
		return from;
	}
	public ChessField getTo() {
		return to;
	}
	
}
