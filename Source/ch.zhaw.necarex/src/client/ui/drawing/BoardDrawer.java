package client.ui.drawing;

import java.awt.Point;

import model.ChessField;
import client.viewmodel.ChessBoardViewModel;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;

/**
 * Der BoardDrawer übernimmt alle Zeichnungsaufgaben bezogen auf das Schachbrett.
 * @author sprengmeister
 */
public class BoardDrawer implements Disposable {

	private float initX;
	private float initY;
	public static final float FIELD_WIDTH = 30;
	public static final float FIELD_HEIGHT = 30;
	public static final float LABEL_HEIGHT = 30;
	public static final float LABEL_WIDTH = 30;
	
	private final BitmapFont font = new BitmapFont();
	private final ShapeRenderer renderer = new ShapeRenderer();
	
	private ChessBoardViewModel viewModel;
	
	public BoardDrawer(Table window, ChessBoardViewModel viewModel){		
		this.initX = window.getX();
		this.initY = window.getY();
		this.viewModel = viewModel;
	}

	/**
     * Zeichnet das Schachfeld
     */
	public void draw(SpriteBatch spriteBatch){
		//SpriteBatch für die Schriften starten
		spriteBatch.begin();
		
		// A-H oberhalb und unterhalb des Bretts
		for(int i=0;i<8;i++){
	        CharSequence str = String.valueOf((char)(i+65));
	        font.setColor(Color.WHITE);
	        font.draw(spriteBatch, str, (float) (i*FIELD_WIDTH+0.3*FIELD_WIDTH + this.initX + LABEL_WIDTH), this.initY);
	        font.draw(spriteBatch, str, (float) (i*FIELD_WIDTH+0.3*FIELD_WIDTH + this.initX + LABEL_WIDTH), (float) (this.initY + 1.5f*LABEL_HEIGHT + 8 * FIELD_HEIGHT));
		}
		// 8-1 links und rechts des Bretts
		for(int i=1,j=0;i<9;i++,j++){
			CharSequence str = String.valueOf(i);
			font.setColor(Color.WHITE);
			font.draw(spriteBatch, str,  this.initX, (float)(j*FIELD_HEIGHT+0.3*FIELD_HEIGHT + this.initY + LABEL_HEIGHT));
			font.draw(spriteBatch, str,  (float) (this.initX + 1.5*LABEL_WIDTH + 8 * FIELD_WIDTH), (float)(j*FIELD_HEIGHT+0.3*FIELD_HEIGHT + this.initY + LABEL_HEIGHT));
		}
		//SpriteBatch mit Schriften abschliessen
		spriteBatch.end();
		
		//SpriteBatch für die einzelnen Schachfelder starten
		renderer.begin(ShapeType.FilledRectangle);
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				
				if (isSelectedField(i, j)){
					renderer.setColor(Color.RED);
				} else if(isReachableField(i, j)){
					renderer.setColor((j%2 == 0 && i%2 == 0) 
								   || (j%2 != 0 && i%2 != 0) 
										? Color.LIGHT_GRAY
										: Color.DARK_GRAY);
				}else {
					renderer.setColor((j%2 == 0 && i%2 == 0) 
								  	|| (j%2 != 0 && i%2 != 0) 
										? Color.WHITE
										: Color.BLACK);
				}
				renderer.filledRect(j*FIELD_WIDTH + this.initX + LABEL_WIDTH, i*FIELD_HEIGHT + this.initY + 0.5f*LABEL_HEIGHT, FIELD_WIDTH, FIELD_HEIGHT);
			}
		}
		renderer.end();
		//SpriteBatch mit den Schachfeldern abschliessen
		
		//SpriteBatch mit Rahmen um die Schachfelder starten
		renderer.begin(ShapeType.Rectangle);
		renderer.setColor(Color.WHITE);
		renderer.rect(this.initX + LABEL_WIDTH, this.initY + 0.5f * LABEL_HEIGHT, 8*FIELD_WIDTH, 8*FIELD_HEIGHT);
		renderer.end();
	}

	private boolean isSelectedField(int row, int column) {
		return viewModel.getSelectedField() != null
				&& viewModel.getSelectedField().getRow() == row
				&& viewModel.getSelectedField().getCol() == column;
	}

	private boolean isReachableField(int row, int column) {
		if (viewModel.getReachableFields()!=null)
			for(ChessField field : viewModel.getReachableFields()){
				if (field.getRow()==row && field.getCol()==column)
					return true;
			}
		return false;
	}

	@Override
	public void dispose() {
		renderer.dispose();
	}

	/**
	 * Gibt die X/Y-Koordinate des selektierten Schachfeldes zurück
	 * @param inputX X-Koordinate des geklickten Punktes
	 * @param inputY Y-Koordinate des geklickten Punktes
	 * @return X/Y Koordinate des Feldes
	 */
	public Point getFieldCoordinates(int inputX, int inputY) {
		int fieldX = (int)((inputX - this.initX - LABEL_WIDTH)/FIELD_WIDTH);
		int fieldY = Math.round((inputY - this.initY - LABEL_HEIGHT)/FIELD_HEIGHT);
		if (fieldX >= 0 && fieldX < 8 && fieldY >= 0 && fieldY < 8)
			return new Point(fieldX, fieldY);
		else 
			return null;
	}

}
