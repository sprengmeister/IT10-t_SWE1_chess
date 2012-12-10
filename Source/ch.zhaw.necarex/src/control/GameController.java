/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;

import model.ChessField;
import model.ComputerPlayer;
import model.Game;
import model.Player;
import model.PlayerColor;
import model.Turn;
import client.viewmodel.ChessBoardViewModel;

/**
 * Kontrolliert den Ablauf des Schachspiels
 */
public class GameController {
    private Game game;
    private ChessBoardViewModel viewModel;
    /**
     * Erzeugt einen GameController
     * @param viewModel ViewModel 
     */
    public GameController(ChessBoardViewModel viewModel){
        this.game = new Game();
        this.viewModel = viewModel;
    }
    /**
     * Gibt alle möglichen Zielfelder zurück, auf dem die Figur auf dem Feld fahren kann. Steht auf dem Feld keine Figur, wird eine leere ArrayList zurückgegeben
     * @param field Feld, von dem geprüft werden soll, was für möglichkeiten es gibt. 
     * @return ArrayList mit allen möglichen ChessFields
     */
    public ArrayList<ChessField> getPossibleFields(ChessField field){
    
        if(field.getPiece() != null){
        	return field.getPiece().getPossibleFields();
        } else {
        	return new ArrayList<ChessField>();
        }
    }
    /**
     * Führt einen Zug durch und prüft, ob die Partie beendet ist. 
     * @param fromField Start-Feld
     * @param toField Ziel-Feld
     */
    public void doTurn(ChessField fromField, ChessField toField){    
    	if(viewModel.getPlayerWonColor() == null && fromField.getPiece().getPossibleFields().contains(toField)){
    		game.getChessBoard().movePiece(fromField, toField);
    		game.changeActivePlayer();
    		viewModel.resetRound();
    	}
    	//Schachmatt?
    	Player looser = game.getChessBoard().getPlayerInCheckMate();
    	if (looser != null){
    		viewModel.setPlayerWonColor(looser.getColor() == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE);
    	}
    }
    
    /**
     * Updated die Model Daten, initiiert Aktionen anhand der Daten (z.B. Computerspieler triggern).
     */
	public void update() {
    	if(game.getActivePlayer() instanceof ComputerPlayer){	
    		Turn computerTurn = ((ComputerPlayer)game.getActivePlayer()).getBestTurn();
    		if (computerTurn != null)
    			doTurn(computerTurn.getFromField(), computerTurn.getToField());
    	}
    	if ((game.getPlayerBlack() instanceof ComputerPlayer && !viewModel.isArtificalIntelligenceEnabled())
    			|| (!(game.getPlayerBlack() instanceof ComputerPlayer) && viewModel.isArtificalIntelligenceEnabled())){
    		game.changeBlackPlayer();
    	}
	}
	/**
	 * Neues Spiel starten
	 */
    public void startNewGame(){
    	viewModel.resetGame();
    	game.getChessBoard().initChessboard();
    	game.initialize();
    }
    
    /**
     * Gibt die Model-Informationen zurück.
     * @return Game-Model.
     */
	public Game getGame() {
		return game;
	}
}
