package necarex.client.ui.drawer;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class FieldDrawer implements Drawable {

	private float _initX;
	private float _initY;
	
	public FieldDrawer(float initX, float initY){
		_initX = initX;
		_initY = initY;
	}
	
	@Override
	public void draw(Graphics g) {
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				g.setColor((j%2 == 0 && i%2 == 0) 
					  	|| (j%2 != 0 && i%2 != 0) 
							? Color.black 
							: Color.white);
				g.fillRect(j*20 + _initX, i*20 + _initY, 20, 20);
			}
		}
	}

}
