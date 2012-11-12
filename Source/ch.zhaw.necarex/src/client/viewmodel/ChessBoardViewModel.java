package client.viewmodel;

import java.util.List;

import model.*;

public class ChessBoardViewModel {

	private ChessField selectedField;
	private List<ChessField> reachableFields;
	
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

}
