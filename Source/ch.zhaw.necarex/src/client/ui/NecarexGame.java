package client.ui;

import client.ui.drawing.BoardDrawer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GLCommon;

/**
 * LibGDX Main Klasse, übernimmt das Draw/Update.
 * @author sprengmeister
 */
public class NecarexGame implements ApplicationListener  {

	private BoardDrawer boardDrawer;
	
	@Override
	public void create() {
		if (boardDrawer == null){
			boardDrawer = new BoardDrawer(100, 100);
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		//Weisser Background
		GLCommon gl = Gdx.gl;
		gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT | GL10.GL_STENCIL_BUFFER_BIT);
		boardDrawer.draw();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
