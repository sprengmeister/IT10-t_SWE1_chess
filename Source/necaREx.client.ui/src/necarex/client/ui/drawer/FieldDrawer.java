package necarex.client.ui.drawer;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class FieldDrawer implements Drawable {

	private float _initX;
	private float _initY;
	private final float FIELD_WIDTH = 30;
	private final float FIELD_HEIGHT = 30;
	private final float LABEL_HEIGHT = 30;
	private final float LABEL_WIDTH = 30;
	
	public FieldDrawer(float initX, float initY){
		_initX = initX;
		_initY = initY;
	}
	
	@Override
	public void draw(Graphics g) {
		// A-H oberhalb und unterhalb des Bretts
		for(int i=0;i<8;i++){
			g.drawString(String.valueOf((char)(i+65)), (float) (i*FIELD_WIDTH+0.3*FIELD_WIDTH + _initX + LABEL_WIDTH), _initY);
			g.drawString(String.valueOf((char)(i+65)), (float) (i*FIELD_WIDTH+0.3*FIELD_WIDTH + _initX + LABEL_WIDTH), (float) (_initY + 1.5*LABEL_HEIGHT + 8 * FIELD_HEIGHT));
		}
		// 8-1 links und rechts des Bretts
		for(int i=8,j=0;i>0;i--,j++){
			g.drawString(String.valueOf(i), _initX, (float)(j*FIELD_HEIGHT+0.3*FIELD_HEIGHT + _initY + LABEL_HEIGHT));
			g.drawString(String.valueOf(i), (float) (_initX + 1.5*LABEL_WIDTH + 8 * FIELD_WIDTH), (float)(j*FIELD_HEIGHT+0.3*FIELD_HEIGHT + _initY + LABEL_HEIGHT));
		}
		
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				g.setColor((j%2 == 0 && i%2 == 0) 
					  	|| (j%2 != 0 && i%2 != 0) 
							? Color.white
							: Color.black);
				g.fillRect(j*FIELD_WIDTH + _initX + LABEL_WIDTH, i*FIELD_HEIGHT + _initY + LABEL_HEIGHT, FIELD_WIDTH, FIELD_HEIGHT);
			}
		}
		
		g.setColor(Color.black);
		g.drawRect(_initX + LABEL_WIDTH, _initY + LABEL_HEIGHT, 8*FIELD_WIDTH, 8*FIELD_HEIGHT);
	}

}
