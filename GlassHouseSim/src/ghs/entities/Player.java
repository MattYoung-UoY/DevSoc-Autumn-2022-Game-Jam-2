package ghs.entities;

import java.util.ArrayList;

import dejavu.engine.graphics.Camera;
import dejavu.engine.graphics.Quad;
import dejavu.engine.io.Keys;
import ghs.GameConfig;
import ghs.Textures;
import ghs.tiles.Tile;

public class Player extends Entity{

	private Quad pQuad;
	private Camera cam;
	
	public Player(Quad pQuad) {
		this.pQuad = pQuad;
		cam = new Camera(pQuad.getX(), pQuad.getY());
	}
	
	public void update(ArrayList<Tile> worldTiles) {
		
		if(Keys.isKeyDown(Keys.KEY_W) || Keys.isKeyDown(Keys.KEY_UP)) {
			pQuad.setTextureID(Textures.PLAYER_BUTT);
			increasePos(0, GameConfig.PLAYER_SPEED, worldTiles);
			pQuad.setScaleX(GameConfig.PLAYER_SIZE);
		}else if(Keys.isKeyDown(Keys.KEY_S) || Keys.isKeyDown(Keys.KEY_DOWN)) {
			pQuad.setTextureID(Textures.PLAYER_TITS);
			increasePos(0, -GameConfig.PLAYER_SPEED, worldTiles);
			pQuad.setScaleX(GameConfig.PLAYER_SIZE);
		}
		
		if(Keys.isKeyDown(Keys.KEY_A) || Keys.isKeyDown(Keys.KEY_LEFT)) {
			increasePos(-GameConfig.PLAYER_SPEED, 0, worldTiles);
			pQuad.setTextureID(Textures.PLAYER_BUMPS_AND_HUMPS);
			pQuad.setScaleX(GameConfig.PLAYER_SIZE/2);
		}else if(Keys.isKeyDown(Keys.KEY_D) || Keys.isKeyDown(Keys.KEY_RIGHT)) {
			pQuad.setTextureID(Textures.PLAYER_HUMPS_AND_BUMPS);
			increasePos(GameConfig.PLAYER_SPEED, 0, worldTiles);
			pQuad.setScaleX(GameConfig.PLAYER_SIZE/2);
		}
		
	}
	
	public void increasePos(float x, float y, ArrayList<Tile> worldTiles) {
		pQuad.increasePosition(x, y);
		cam.increasePosition(x, y);
		pQuad.setScaleX(GameConfig.PLAYER_SIZE);
		if(!this.onGround(this.pQuad, worldTiles)){
			pQuad.increasePosition(-x, -y);
			cam.increasePosition(-x, -y);
		}
	}
	
	public Quad getQuad() {
		return pQuad;
	}
	
	public Camera getCamera() {
		return cam;
	}

}
