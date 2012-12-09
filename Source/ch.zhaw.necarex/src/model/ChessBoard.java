package model;

import model.pieces.*;

/**
 * Das ChessBoard kontrolliert die Schachfelder und macht die Anfangsaufstellung zu Beginn des Spiels. 
 */
public class ChessBoard implements Cloneable{
    private ChessField[][] chessField;
    
    private Game game;
   
    /**
     * Konstruktor erstellt ein neues Schachfeld
     * @param game Referenz auf die Klasse Game, die die Spieler, geschlagenen Figuren etc. verwaltet. 
     */
    public ChessBoard(Game game){
        this.game = game;
        this.initChessboard();        
    } 
    /**
     * Gibt die Figur in der Startaufstellung für ein bestimmtes Feld zurück 
     * @param col Spalte
     * @param row Zeile
     * @return Figur, die in der Startaufstellung auf diesem Feld steht. 
     */
    private Piece getInitialPiece(int col, int row){
    	Player player;
        if(row < 3 ){
            player = game.getPlayerWhite();
        } else {
            player = game.getPlayerBlack();
        }
        
        //Aus Row/Column Werten eine Zahl machen -> Zehner = Row, Einer = Column
        int fieldValue = row*10+col;
        // Reihe 0 -> Weisse Grundlinie 
        // Reihe 7 -> Schwarze Grundlinie
        switch (fieldValue){
	        case 0:
	        case 7:
	        case 70:
	        case 77:
	        	// Türme
	        	return new Rook(player, this);
	        case 1:
	        case 6:
	        case 71:
	        case 76:
	        	// Springer
	        	return new Knight(player, this);
	        case 2:
	        case 5:
	        case 72:
	        case 75:
	        	// Läufer
	        	return new BiShop(player, this);
	        case 3:
	        case 73:
	        	// Dame
	        	return new Queen(player, this);
	        case 4:
	        case 74:
	        	// König
	        	return new King(player, this);
        }
        
        // Bauern separat Abfragen (um sich die ganzen case-Statements zu sparen)
        if(row == 1 || row == 6 ){
            return new Pawn(player, this);
        }
        
        return null;
    }
    
    /**
     * Setzt das Schachfeld in die Startaufstellung. 
     */
    public void initChessboard(){
        chessField = new ChessField[8][8];
        for(int col = 0;col<8;col++){
            for(int row = 0;row<8;row++){
                Piece initialPiece = this.getInitialPiece(col, row);
                chessField[col][row] = new ChessField(initialPiece, col, row);
                if( initialPiece != null) {
                    initialPiece.setChessField(chessField[col][row]);
                }
            }
        }
    }

    /**
     * Setzt das Schachfeld von ausserhalb. Kann so zum Beispiel in Testfällen benutzt werden um eine bestimmte Situation zu erzeugen. 
     * @param chessField Zweidimensionales Array von Schachfeldern
     */
    public void initChessboard(ChessField[][] chessField){
        this.chessField = chessField;
    }
    /**
     * Gibt ein Schachfeld zurück <br />
     * Achtung: Spalten 0 bis 7, nicht 1 bis 8
     * @param col Spalte
     * @param row Zeile
     * @throws ArrayOutOfBoundsException wenn eine Zeile / Spalte angegeben, dass nicht auf dem Schachfeld ist (zwischen 0 und 7)
     * @return Schachfeld an der Position Spalte / Zeile
     */
    public ChessField getField(int col, int row){
        return chessField[col][row];
    }
    
    /**
     * Bewegt eine Figur vom from-Feld zum to-Feld. Der Zug wird geloggt in der TurnList (Game). Falls die Figur geschlagen wird, wird auch das im Game geloggt. 
     * @param from Alter Ort der Figur
     * @param to Neuer Ort der Figur. 
     */
    public void movePiece(ChessField from, ChessField to){
    	Turn turn = new Turn(from, to);
    	game.addToTurnList(turn);
    	Piece capturedPiece = to.exchangePiece(from.movePieceAway());
    	if(capturedPiece != null){
    		game.addToCapturedPieces(capturedPiece);
    	}
    }
    /**
     * Prüft, ob es zur Zeit ein König im Schach steht. 
     * @return true, wenn ein König im Schach steht. 
     */
    public boolean isCheck(){
    	for(int col=0;col<8;col++){
    		for(int row = 0;row < 8; row++){
    			if(this.getField(col, row).getPiece() != null && this.getField(col, row).getPiece().daresOpponentKing()){
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    /**
     * Gibt den Spieler zurück der Schachmatt ist, oder NULL.
     * @return Besiegter Spieler oder NULL
     */
    public Player getPlayerInCheckMate(){
    	if (isPlayerCheckMate(PlayerColor.WHITE)) return this.game.getPlayerWhite();
    	if (isPlayerCheckMate(PlayerColor.BLACK)) return this.game.getPlayerBlack();
    	return null;
    }
    
    /**
     * Prüft ob ein Spieler Schachmatt ist.
     * @return true, wenn der Spieler Schachmatt ist. 
     */
    private boolean isPlayerCheckMate(PlayerColor color){
    	boolean isCheckMate = true;
    	for (int col = 0; col < 8 && isCheckMate; col++) {
			for (int row = 0; row < 8 && isCheckMate; row++) {
				Piece p = this.getField(col, row).getPiece();
				if (p != null && p.getOwner().getColor()==color && p.getPossibleFields().size() != 0){
					isCheckMate = false;
				}
			}
		}
    	return isCheckMate;
    }
    /**
     * Klont das Schachbrett mit allen Schachfeldern und Figuren. 
     * @return geklontes Schachbrett
     */
    public ChessBoard clone(){
    	ChessBoard cb ; 
    	try {
			cb =  (ChessBoard) super.clone();
			cb.chessField = null;
			cb.chessField = new ChessField[8][8];
			for(int col=0;col<8;col++){
	    		for(int row = 0;row < 8; row++){
	    			cb.chessField[col][row] = this.chessField[col][row].clone(cb);
	    		}
	    	}	
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
    	return cb;
    }
    
}