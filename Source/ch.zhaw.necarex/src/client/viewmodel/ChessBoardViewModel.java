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
	
	public void reset(){
		this.selectedField = null;
		this.reachableFields = null;
	}
	
	public ChessField getSelectedField() {
		return selectedField;
	}
	public void setSelectedField(ChessField selectedField) {
		this.selectedField = selectedField;
	}
	public List<ChessField> getReachableFields() {
		return reachableFields;
	}
	public void setReachableFields(List<ChessField> reachableFields) {
		this.reachableFields = reachableFields;
	}
	public boolean isArtificalIntelligenceEnabled() {
		return isArtificalIntelligenceEnabled;
	}
	public void setArtificalIntelligenceEnabled(
			boolean isArtificalIntelligenceEnabled) {
		this.isArtificalIntelligenceEnabled = isArtificalIntelligenceEnabled;
	}


}
