package model;

import control.GameController;
import model.ai.PossibleSituation;

/**
 * Stellt einen Computerspieler dar. Benutzt für die Zugberechnung das Package model.ai
 * @author florian
 *
 */
public class ComputerPlayer extends Player {

	Game game;
	
	public ComputerPlayer(PlayerColor color, Game game) {
		super(color);
		this.game = game; 
	}
	
	
	
	public Turn getBestTurn(){
		PossibleSituation actSituation = new PossibleSituation(game, game.getChessBoard());
		actSituation.findPossibleSituationChilds(0);
		PossibleSituation bestSituation = actSituation.getBestSituation(0);
		
		ChessField fromField = game.getChessBoard().getField(bestSituation.getFrom().getCol(), bestSituation.getFrom().getRow());
		ChessField toField = game.getChessBoard().getField(bestSituation.getTo().getCol(), bestSituation.getTo().getRow()); 
		Turn bestTurn = new Turn(fromField, toField);
		return bestTurn;
	}



	

}
