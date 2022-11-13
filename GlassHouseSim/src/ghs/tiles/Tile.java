package ghs.tiles;

import java.util.Random;

import dejavu.engine.graphics.Quad;
import ghs.GameConfig;

public class Tile {

	private Quad quad;
	private boolean collidable = false;
	private static Random rand = new Random();

	public Tile(float x, float y, float scaleX, float scaleY, boolean randomRot, int texID, boolean collidable) {
		quad = new Quad(x, y, (randomRot ? 1 : 0) * (rand.nextInt(2) * 180.0f), scaleX * GameConfig.TILE_SIZE * 1.001f, scaleY * GameConfig.TILE_SIZE * 1.001f, texID);
		this.collidable = collidable;
	}
	
	public Tile(float x, float y, float scale, boolean randomRot, int texID, boolean collidable) {
		quad = new Quad(x, y, (randomRot ? 1 : 0) * (rand.nextInt(2) * 180.0f), scale * GameConfig.TILE_SIZE * 1.001f, scale * GameConfig.TILE_SIZE * 1.001f, texID);
		this.collidable = collidable;
	}
	
	public boolean isCollidable() {
		return collidable;
	}
	
	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}

	public Quad getQuad() {
		return quad;
	}
	
}
