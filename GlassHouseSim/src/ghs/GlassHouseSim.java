package ghs;

import dejavu.engine.GameLauncher;
import ghs.gameStates.FoodMenuState;
import ghs.gameStates.GlassHouseState;
import ghs.gameStates.MainMenuState;

public class GlassHouseSim {
	
	public static MainMenuState mms;
	public static GlassHouseState ghs;
	public static FoodMenuState fms;
	
	public static void main(String[] args) {
		GameLauncher.initGame(args, "GlassHouse Simulator");
		
		mms = new MainMenuState();
		GameLauncher.setInitialGameState(mms);
		
		ghs = new GlassHouseState();
		fms = new FoodMenuState();
		
		GameLauncher.startGame();
	}
	
}
