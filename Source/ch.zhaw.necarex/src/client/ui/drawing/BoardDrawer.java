package client.ui.drawing;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.badlogic.gdx.utils.Disposable;

/**
 * Der BoardDrawer übernimmt alle Zeichnungsaufgaben bezogen auf das Schachbrett.
 * @author sprengmeister
 */
public class BoardDrawer implements Disposable {

	private float initX;
	private float initY;
	private final float FIELD_WIDTH = 30;
	private final float FIELD_HEIGHT = 30;
	private final float LABEL_HEIGHT = 30;
	private final float LABEL_WIDTH = 30;
	
	private ShapeRenderer renderer; 
	
	public BoardDrawer(Table window){
		renderer = new ShapeRenderer();
		
		this.initX = window.x;
		this.initY = window.y;
	}
	
    /**
     * Zeichnet das Schachfeld
     */
	public void draw(){
        SpriteBatch spriteBatch = new SpriteBatch();
        BitmapFont font = new BitmapFont();
        
		// A-H oberhalb und unterhalb des Bretts
		for(int i=0;i<8;i++){
	        CharSequence str = String.valueOf((char)(i+65));
	        spriteBatch.begin();
	        font.setColor(Color.BLACK);
	        font.draw(spriteBatch, str, (float) (i*FIELD_WIDTH+0.3*FIELD_WIDTH + this.initX + LABEL_WIDTH), this.initY);
	        font.draw(spriteBatch, str, (float) (i*FIELD_WIDTH+0.3*FIELD_WIDTH + this.initX + LABEL_WIDTH), (float) (this.initY + 1.5f*LABEL_HEIGHT + 8 * FIELD_HEIGHT));
	        spriteBatch.end();
		}
		// 8-1 links und rechts des Bretts
		for(int i=8,j=0;i>0;i--,j++){
			CharSequence str = String.valueOf(i);
			
			spriteBatch.begin();
			font.setColor(Color.BLACK);
			font.draw(spriteBatch, str,  this.initX, (float)(j*FIELD_HEIGHT+0.3*FIELD_HEIGHT + this.initY + LABEL_HEIGHT));
			font.draw(spriteBatch, str,  (float) (this.initX + 1.5*LABEL_WIDTH + 8 * FIELD_WIDTH), (float)(j*FIELD_HEIGHT+0.3*FIELD_HEIGHT + this.initY + LABEL_HEIGHT));
			spriteBatch.end();
		}
		
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				renderer.begin(ShapeType.FilledRectangle);
				renderer.setColor((j%2 == 0 && i%2 == 0) 
							  	|| (j%2 != 0 && i%2 != 0) 
									? Color.WHITE
									: Color.BLACK);
				renderer.filledRect(j*FIELD_WIDTH + this.initX + LABEL_WIDTH, i*FIELD_HEIGHT + this.initY + 0.5f*LABEL_HEIGHT, FIELD_WIDTH, FIELD_HEIGHT);
				renderer.end();
			}
		}
			
		renderer.begin(ShapeType.Rectangle);
		renderer.setColor(Color.BLACK);
		renderer.rect(this.initX + LABEL_WIDTH, this.initY + 0.5f * LABEL_HEIGHT, 8*FIELD_WIDTH, 8*FIELD_HEIGHT);
		renderer.end();
	}

	@Override
	public void dispose() {
		renderer.dispose();
	}

}
