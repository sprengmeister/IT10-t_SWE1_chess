package client.ui.drawing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;

public class MenuDrawer {
	
	private Skin skin;

	public void draw(Table window) {
        Button startGameButton = new Button(getSkin());
        startGameButton.x = 0;
        startGameButton.y = 0;
        startGameButton.width = 500;
        startGameButton.height = 500;
        startGameButton.add("Start");
        Label lbl = new Label(getSkin());
        lbl.width = 100;
        lbl.height = 100;
        lbl.x = 0;
        lbl.y = 0;
        lbl.setColor(Color.GREEN);
        lbl.setText("test");
        window.add(lbl);
        window.add(startGameButton );
	}
	
    protected Skin getSkin()
    {
        if( skin == null ) {
            skin = new Skin( Gdx.files.internal("assets/skin/uiskin.json" ), Gdx.files.local("assets/skin/uiskin.png" ) );
        }
        return skin;
    }
}
