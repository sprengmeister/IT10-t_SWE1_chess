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
	
	
	
	public void doTurn(GameController controller){
		PossibleSituation actSituation = new PossibleSituation(game, game.getChessBoard());
		actSituation.findPossibleSituationChilds();
		PossibleSituation bestSituation = actSituation.getBestSituation();
		
		controller.doTurn(game.getChessBoard().getField(bestSituation.getFrom().getCol(), bestSituation.getFrom().getRow()), game.getChessBoard().getField(bestSituation.getTo().getCol(), bestSituation.getTo().getRow()));
		// game.getChessBoard().movePiece(bestSituation.getFrom(), bestSituation.getTo());
	}



	

}
