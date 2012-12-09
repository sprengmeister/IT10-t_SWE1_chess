package model;

import model.ai.PossibleSituation;

/**
 * Stellt einen Computerspieler dar. Benutzt für die Zugberechnung das Package model.ai
 *
 */
public class ComputerPlayer extends Player {

	Game game;
	/**
	 * Erzeugt einen Computerspieler
	 * @param color Farbe des Spiels. 
	 * @param game Referenz zum aktuellen Spiel
	 */
	public ComputerPlayer(PlayerColor color, Game game) {
		super(color);
		this.game = game; 
	}
	/**
	 * Lässt den besten Zug berechnen und gibt ihn zurück. 
	 * @return Besten Zug
	 */
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
