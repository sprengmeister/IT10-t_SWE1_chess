package model.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.ChessBoard;
import model.ChessField;
import model.Game;
import model.Player;
import model.PlayerColor;
import model.pieces.Piece;

/**
 * Stellt eine mögliche Schachsituation dar. Diese Klasse nimmt die Bewertung der Schachsituation vor. 
 *
 */
public class PossibleSituation implements Comparable<PossibleSituation> {
	private ChessBoard board;
	private ChessField from;
	private ChessField to;
	private ArrayList<PossibleSituation> possibleSituationChilds;
	private static final int MAX_HOPS = 2;
	
	private int score;
	
	private Game game;

	/**
	 * Erzeugt die erste PossibleSituation. Es ist noch kein Zug gemacht werden - somit entspricht die Situation der Berechnungsgrundlage. Deshalb werden keine Felder from und to übergeben
	 * @param game aktuelles Spiel
	 * @param board aktueller Stand auf dem Board
	 */
	public PossibleSituation(Game game, ChessBoard board){
		this.board = board;
		this.game = game;
		
		possibleSituationChilds = new ArrayList<PossibleSituation>();
	}
	/**
	 * Erzeugt eine neue PossibleSituation, die nach einem Zug (from,to) entstanden ist vom parent-Node der Liste possibleSituationChilds.
	 * @param game aktuelles Spiel
	 * @param board möglicher Stand nach dem Zug
	 * @param from Zug der zu dieser Situation geführt hat: Start-Feld
	 * @param to Zug der zu dieser Situation geführt hat: Ziel-Feld
	 */
	public PossibleSituation(Game game, ChessBoard board, ChessField from, ChessField to){
		this.from = from;
		this.to = to;
		this.game = game;
		this.board = board;
		this.possibleSituationChilds = new ArrayList<PossibleSituation>();
	}
	/**
	 * Berechnet die Bewertung einer Situation mittels der Summe der Figurenwerte (Piece.getPieceValue()).
	 * <brr />Anwendung des Min/Max Algorithmus: Eigene Figuren werden positiv gewertet, gegnerische Figuren negativ. 
	 * @param hop Rekursionstiefe
	 */
	public void calcScore(int hop) {
		ChessField field;
		Piece piece;
		for (int col = 0; col < 8; col++) {
			for (int row = 0; row < 8; row++) {
				field = board.getField(col, row);
				piece = field.getPiece();
				if (piece != null && piece.getOwner().getColor() != this.getActivePlayer(hop).getColor()){
					if (hop%2!=0)
						 this.score += piece.getPieceValue();
					else this.score -= piece.getPieceValue();
				}
			}
		}
	}
	/**
	 * Findet alle möglichen Situationen, die auf die aktuelle Situation finden können, und fügt diese in die Liste possibleSituationChilds ein. 
	 * @param hop Rekursionstiefe
	 */
	public void findPossibleSituationChilds(int hop){
	   	for(int col=0;col<8;col++){
	   		for(int row = 0;row < 8; row++){
	   			ChessField fromField = board.getField(col, row);
	   			if(fromField.getPiece() != null && fromField.getPiece().getOwner().getColor() == this.getActivePlayer(hop).getColor()){
	   				ArrayList<ChessField> possibleFieldList = fromField.getPiece().getPossibleFields();
	   				
	   				for (ChessField chessField : possibleFieldList) {
		   				ChessBoard cbAfterTurn = board.clone();
		   				ChessField from = cbAfterTurn.getField(fromField.getCol(), fromField.getRow());
		   				ChessField to = cbAfterTurn.getField(chessField.getCol(), chessField.getRow());
		   				
		   				to.exchangePiece(from.movePieceAway());
		   				
		   				PossibleSituation possibleSituation = new PossibleSituation(this.game, cbAfterTurn, from, to);
		   				possibleSituation.calcScore(hop);
		   				//Abbruch-Bedingung, für mehr als 2 Hops, dauert der Vorgang zu lange
		   				if (hop < MAX_HOPS)
		   					possibleSituation.findPossibleSituationChilds(hop+1);
		   				possibleSituationChilds.add(possibleSituation);
					}
	   			}
	   		}
	   	}
	}
	/**
	 * Gibt den aktiven Spieler im aktuellen Rekursionschritt zurück
	 * @param hop Rekursionstiefe
	 * @return Aktiver Spieler
	 */
	private Player getActivePlayer(int hop){
		return hop%2==0 ? game.getActivePlayer() : game.getInactivePlayer();
	}
	/**
	 * Der Baum wird rekursiv traversiert und die beste Situation herausgeholt. Gleichwertige Situationen (= Gleiche Bewertungen/Scores) werden zufällig ausgewählt. 
	 * @param i Rekursionstiefe 
	 * @return
	 */
	public PossibleSituation getBestSituation(int i){
		for (PossibleSituation posSit : possibleSituationChilds) {
			PossibleSituation bestChildSituation = posSit.getBestSituation(i+1);
			if (bestChildSituation != null){
				posSit.setScore(posSit.getScore()+bestChildSituation.getScore());
				System.out.println(posSit.hashCode() + ";"+i + ";" + posSit.getScore() + ";" + posSit.to.getPiece().getOwner().getColor());
			}
		}
		if (possibleSituationChilds.size() > 0){
			if (i%2==0) {
				//Zug vom Computer -> Score aufsteigend sortieren
				//Sein Score wird negativ gerechnet. Je kleiner die die Punktzahl, umso mehr Schaden wurde verursacht
				Collections.shuffle(possibleSituationChilds);
				Collections.sort(possibleSituationChilds);
			} else {
				//Zug vom Gegner -> Score absteigend sortieren
				//Computer Score wird positiv gerechnet. Je grösser die Punktzahl, umso weniger Schaden musste eingesteckt werden
				Comparator<PossibleSituation> reversedComparator = Collections.reverseOrder();
				Collections.shuffle(possibleSituationChilds);
				Collections.sort(possibleSituationChilds, reversedComparator);
			}
			return possibleSituationChilds.get(0);
		} else if (i <= MAX_HOPS){
			//Schachmatt
			if (getActivePlayer(i).getColor()!=PlayerColor.BLACK){
				this.score += 99999;
			} else {
				this.score -= 99999;
			}
		}
		return null;
	}
	/**
	 * Gibt das Start-Feld des in diesem Objekt ausprobierten Zugs zurück
	 * @return Start-Feld
	 */
	public ChessField getFrom() {
		return from;
	}
	/**
	 * Gibt das Ziel-Feld des in diesem Objekt ausprobierten Zugs zurück
	 * @return
	 */
	public ChessField getTo() {
		return to;
	}
	/**
	 * Gibt die Bewertung der aktuellen Situation zurück
	 * @return Bewertung der aktuellen Situation 
	 */
	public int getScore(){
		return score;
	}
	/**
	 * Setzt die Bewertung
	 * @param score zu setzende Bewertung
	 */
	public void setScore(int score){
		this.score = score;
	}

	/**
	 * Vergleicht die Bewertung dieser Situation mit der übergebenen. So kann die Methode Collection.sort() verwendet werden. 
	 * @param situation zu vergleichende Situation
	 * @return 1: der Score der übergebenen Situation ist grösser, <br /> -1: der Score der Situation dieses Objekts ist grösser, <br/> ansonsten 0
	 */
	@Override
	public int compareTo(PossibleSituation situation) {
		if (situation.getScore() > this.getScore()) return 1;
		if (situation.getScore() < this.getScore()) return -1;
		return 0;
	}
	
}
