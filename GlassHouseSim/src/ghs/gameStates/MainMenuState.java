package ghs.gameStates;

import java.awt.Color;
import java.util.ArrayList;

import dejavu.engine.gameStates.GameState;
import dejavu.engine.graphics.Quad;
import dejavu.engine.graphics.guis.ExitGameButton;
import dejavu.engine.graphics.guis.StateChangeButton;
import dejavu.engine.graphics.text.Font;
import dejavu.engine.graphics.text.Text;
import ghs.GlassHouseSim;
import ghs.Textures;

public class MainMenuState extends GameState{

	private ExitGameButton exitButton;
	private StateChangeButton playButton;
	
	public MainMenuState() {
		super(new Color(255, 255, 30));
		
		exitButton = new ExitGameButton(-0.05f, -0.2f, 0.4f, 0.1f, Textures.BUTTON_TEX, "Exit", Font.dejavuFont);
		addGui(exitButton);
		
		Text titleText = new Text("GlassHouse Simulator", 0.075f, -0.55f, 0.3f, Font.dejavuFont);
		addText(titleText);
	}

	@Override
	public void cleanUp() {
	}

	@Override
	public ArrayList<Quad> getQuads() {
		return null;
	}

	@Override
	public void init() {
		if(playButton == null) {
			playButton = new StateChangeButton(-0.05f, -0.05f, 0.4f, 0.1f, Textures.BUTTON_TEX, GlassHouseSim.ghs, "Play", Font.dejavuFont);
			addGui(playButton);
		}
	}

	@Override
	public void update() {
	}

}
