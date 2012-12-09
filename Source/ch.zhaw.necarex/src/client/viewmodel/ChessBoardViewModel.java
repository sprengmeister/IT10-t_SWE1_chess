package client.viewmodel;

import java.util.List;

import model.*;

/**
 * Model mit View-spezifischen Daten für das Schachbrett.
 * @author sprengmeister
 */
public class ChessBoardViewModel {

	private ChessField selectedField;
	private List<ChessField> reachableFields;
	private boolean isArtificalIntelligenceEnabled = false;
	private PlayerColor playerWonColor = null;
	
	/**
	 * Setzt die Daten der Runde zurück.
	 */
	public void resetRound(){
		this.selectedField = null;
		this.reachableFields = null;
	}
	
	/**
	 * Setzt die Daten des ganzen Spiels zurück.
	 */
	public void resetGame(){
		playerWonColor = null;
		resetRound();
	}
	
	/**
	 * Gibt die Farbe des Gewinners zurück.
	 * @return Farbe des Gewinners.
	 */
	public PlayerColor getPlayerWonColor() {
		return playerWonColor;
	}

	/**
	 * Setzt die Farbe des Gewinners.
	 * @param playerWonColor Farbe des Gewinners.
	 */
	public void setPlayerWonColor(PlayerColor playerWonColor) {
		this.playerWonColor = playerWonColor;
	}

	/**
	 * Gibt das vom Spieler angeklickte Feld zurück.
	 * @return angeklicktes Feld.
	 */
	public ChessField getSelectedField() {
		return selectedField;
	}
	/**
	 * Setzt das vom Spieler angeklickte Feld.
	 * @param selectedField angeklicktes Feld.
	 */
	public void setSelectedField(ChessField selectedField) {
		this.selectedField = selectedField;
	}
	
	/**
	 * Gibt die von der angewählten Figur erreichbaren Felder zurück.
	 * @return erreichbare Felder.
	 */
	public List<ChessField> getReachableFields() {
		return reachableFields;
	}
	
	/**
	 * Setzt die von der angewählten Figur erreichbaren Felder.
	 * @param reachableFields angewählte Felder.
	 */
	public void setReachableFields(List<ChessField> reachableFields) {
		this.reachableFields = reachableFields;
	}
	
	/**
	 * Gibt den Flag zurück, ob der Computer mitspielt.
	 * @return Flag ob Computer mitspielt.
	 */
	public boolean isArtificalIntelligenceEnabled() {
		return isArtificalIntelligenceEnabled;
	}
	
	/**
	 * Setzt den Flag ob der Computer mitspielt.
	 * @param isArtificalIntelligenceEnabled Flag ob Computer mitspielt
	 */
	public void setArtificalIntelligenceEnabled(
			boolean isArtificalIntelligenceEnabled) {
		this.isArtificalIntelligenceEnabled = isArtificalIntelligenceEnabled;
	}


}
