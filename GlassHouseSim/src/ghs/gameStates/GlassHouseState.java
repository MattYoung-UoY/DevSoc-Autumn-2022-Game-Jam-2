package ghs.gameStates;

import java.awt.Color;
import java.util.ArrayList;

import dejavu.engine.GameManager;
import dejavu.engine.gameStates.GameState;
import dejavu.engine.graphics.Quad;
import dejavu.engine.graphics.guis.GuiImage;
import dejavu.engine.graphics.text.Font;
import dejavu.engine.graphics.text.Text;
import dejavu.engine.io.Keys;
import ghs.GameConfig;
import ghs.Textures;
import ghs.entities.Player;
import ghs.tiles.Tile;

public class GlassHouseState extends GameState {

	private Player player;

	private ArrayList<Tile> tiles;

	private boolean enterBarInfoOnScreen = false, goosFrenInfoOnScreen = false, duckFrenInfoOnScreen = false;

	private Text enterBarText, goosFrenText, duckFrenText;
	private GuiImage enterBarBackground, goosFrenBackground, duckFrenBackground;

	private Quad fren;

	public GlassHouseState() {
		super(new Color(0, 0, 0));

		tiles = new ArrayList<Tile>();

		int playerX = 5, playerY = 10;
		player = new Player(new Quad(playerX * GameConfig.TILE_SIZE, playerY * GameConfig.TILE_SIZE, 0f,
				GameConfig.PLAYER_SIZE, GameConfig.PLAYER_SIZE * 2f, Textures.PLAYER_BUTT));

		addTilesToList();

		addObjects();

		addCamera(player.getCamera());

		enterBarText = new Text("Press Enter to order food", 0.05f, -0.5f, -0.3f, Font.dejavuFont);
		enterBarBackground = new GuiImage(0.01f, -0.3f, 1.1f, 0.1f, Textures.BUTTON_TEX);

		goosFrenText = new Text("Press the G O 0 and S keys for a goos fren", 0.05f, -0.85f, -0.3f, Font.dejavuFont);
		goosFrenBackground = new GuiImage(0.025f, -0.3f, 1.85f, 0.1f, Textures.BUTTON_TEX);

		duckFrenText = new Text("Press the D U C and K keys for a duck fren", 0.05f, -0.85f, -0.3f, Font.dejavuFont);
		duckFrenBackground = new GuiImage(0.025f, -0.3f, 1.85f, 0.1f, Textures.BUTTON_TEX);

		fren = new Quad((playerX - 3) * GameConfig.TILE_SIZE, playerY * GameConfig.TILE_SIZE, 0f, GameConfig.TILE_SIZE * 1.2f,
				GameConfig.TILE_SIZE * 1.2f, Textures.NONE);

	}

	@Override
	public void cleanUp() {
	}

	@Override
	public ArrayList<Quad> getQuads() {
		ArrayList<Quad> toRet = new ArrayList<>();

		for (Tile tile : tiles)
			toRet.add(tile.getQuad());

		toRet.add(player.getQuad());
		toRet.add(fren);

		return toRet;
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
		if (Keys.isKeyDown(Keys.KEY_ESCAPE))
			GameManager.changeGameState(new MainMenuState());

		if (Keys.isKeyDown(Keys.KEY_D) && Keys.isKeyDown(Keys.KEY_U) && Keys.isKeyDown(Keys.KEY_C)
				&& Keys.isKeyDown(Keys.KEY_K))
			fren.setTextureID(Textures.DUCK_TEX);
		if (Keys.isKeyDown(Keys.KEY_G) && Keys.isKeyDown(Keys.KEY_O) && Keys.isKeyDown(Keys.KEY_0)
				&& Keys.isKeyDown(Keys.KEY_S))
			fren.setTextureID(Textures.GOOS_TEX);

		player.update(tiles);

		// 43, 30, 5x5
		if (player.getQuad().getX() < (43 + 2.5f) * GameConfig.TILE_SIZE
				&& player.getQuad().getX() > (43 - 2.5f) * GameConfig.TILE_SIZE) {
			if (player.getQuad().getY() > (30 - 2.5f) * GameConfig.TILE_SIZE
					&& player.getQuad().getY() < (30 + 2.5f) * GameConfig.TILE_SIZE) {

				if (!duckFrenInfoOnScreen) {

					addGui(duckFrenBackground);
					addText(duckFrenText);

					duckFrenInfoOnScreen = true;
				}

				if (Keys.isKeyDown(Keys.KEY_RETURN)) {
					GameManager.changeGameState(new FoodMenuState());
				}
			} else {
				if (duckFrenInfoOnScreen) {

					removeText(duckFrenText);
					removeGui(duckFrenBackground);

					duckFrenInfoOnScreen = false;
				}
			}
		} else {
			if (duckFrenInfoOnScreen) {

				removeText(duckFrenText);
				removeGui(duckFrenBackground);

				goosFrenInfoOnScreen = false;
			}
		}

		// 2, 2, 5x5
		if (player.getQuad().getX() < (2 + 2.5f) * GameConfig.TILE_SIZE
				&& player.getQuad().getX() > (2 - 2.5f) * GameConfig.TILE_SIZE) {
			if (player.getQuad().getY() > (2 - 2.5f) * GameConfig.TILE_SIZE
					&& player.getQuad().getY() < (2 + 2.5f) * GameConfig.TILE_SIZE) {

				if (!goosFrenInfoOnScreen) {

					addGui(goosFrenBackground);
					addText(goosFrenText);

					goosFrenInfoOnScreen = true;
				}

				if (Keys.isKeyDown(Keys.KEY_RETURN)) {
					GameManager.changeGameState(new FoodMenuState());
				}
			} else {
				if (goosFrenInfoOnScreen) {

					removeText(goosFrenText);
					removeGui(goosFrenBackground);

					goosFrenInfoOnScreen = false;
				}
			}
		} else {
			if (goosFrenInfoOnScreen) {

				removeText(goosFrenText);
				removeGui(goosFrenBackground);

				goosFrenInfoOnScreen = false;
			}
		}

		// X:26 Y:47.5 (10x4)
		if (player.getQuad().getX() < (26 + 5) * GameConfig.TILE_SIZE
				&& player.getQuad().getX() > (26 - 5) * GameConfig.TILE_SIZE) {
			if (player.getQuad().getY() > (47.5 - 2) * GameConfig.TILE_SIZE
					&& player.getQuad().getY() < (47.5 + 2) * GameConfig.TILE_SIZE) {

				if (!enterBarInfoOnScreen) {

					addGui(enterBarBackground);
					addText(enterBarText);

					enterBarInfoOnScreen = true;
				}

				if (Keys.isKeyDown(Keys.KEY_RETURN)) {
					GameManager.changeGameState(new FoodMenuState());
				}
			} else {
				if (enterBarInfoOnScreen) {

					removeText(enterBarText);
					removeGui(enterBarBackground);

					enterBarInfoOnScreen = false;
				}
			}
		} else {
			if (enterBarInfoOnScreen) {

				removeText(enterBarText);
				removeGui(enterBarBackground);

				enterBarInfoOnScreen = false;
			}
		}

		float dx = fren.getX() - player.getQuad().getX();
		float dy = fren.getY() - player.getQuad().getY();
		double dist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

		while (dist > 3 * GameConfig.TILE_SIZE) {

			dx = fren.getX() - player.getQuad().getX();
			dy = fren.getY() - player.getQuad().getY();
			dist = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

			float ammountX = (float) (dx / dist);
			float ammountY = (float) (dy / dist);

			fren.increasePosition(0.0001f * -ammountX, 0.0001f * -ammountY);
		}

	}

	private void addObjects() {

		// Table
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 7; j++)
				tiles.add(new Tile((3 + i) * GameConfig.TILE_SIZE, (18 + j) * GameConfig.TILE_SIZE, 1, true,
						Textures.TABLE, true));

		int poolX = 20, poolY = 8;
		// Pool Table
		tiles.add(
				new Tile(poolX * GameConfig.TILE_SIZE, poolY * GameConfig.TILE_SIZE, 2, false, Textures.PT_0_0, true));
		tiles.add(new Tile((poolX + 2) * GameConfig.TILE_SIZE, poolY * GameConfig.TILE_SIZE, 2, false, Textures.PT_1_0,
				true));
		tiles.add(new Tile(poolX * GameConfig.TILE_SIZE, (poolY - 2) * GameConfig.TILE_SIZE, 2, false, Textures.PT_0_1,
				true));
		tiles.add(new Tile((poolX + 2) * GameConfig.TILE_SIZE, (poolY - 2) * GameConfig.TILE_SIZE, 2, false,
				Textures.PT_1_1, true));
		tiles.add(new Tile((poolX + 4) * GameConfig.TILE_SIZE, poolY * GameConfig.TILE_SIZE, 2, false, Textures.PT_2_0,
				true));
		tiles.add(new Tile((poolX + 6) * GameConfig.TILE_SIZE, poolY * GameConfig.TILE_SIZE, 2, false, Textures.PT_3_0,
				true));
		tiles.add(new Tile((poolX + 4) * GameConfig.TILE_SIZE, (poolY - 2) * GameConfig.TILE_SIZE, 2, false,
				Textures.PT_2_1, true));
		tiles.add(new Tile((poolX + 6) * GameConfig.TILE_SIZE, (poolY - 2) * GameConfig.TILE_SIZE, 2, false,
				Textures.PT_3_1, true));
		tiles.add(new Tile((poolX + 1) * GameConfig.TILE_SIZE, (poolY - 5) * GameConfig.TILE_SIZE, 4, false,
				Textures.POOL_LEFT_LEGS, false));
		tiles.add(new Tile((poolX + 5) * GameConfig.TILE_SIZE, (poolY - 5) * GameConfig.TILE_SIZE, 4, false,
				Textures.POOL_RIGHT_LEGS, false));

		// Dartboard
		tiles.add(
				new Tile(4.5f * GameConfig.TILE_SIZE, 29 * GameConfig.TILE_SIZE, 2, false, Textures.DARTBOARD, false));

		// Sofas
		tiles.add(new Tile(5 * GameConfig.TILE_SIZE, 48 * GameConfig.TILE_SIZE, 4, 2, false, Textures.SOFA_TITS, true));
		tiles.add(new Tile(1 * GameConfig.TILE_SIZE, 44 * GameConfig.TILE_SIZE, 2, 4, false,
				Textures.SOFA_HUMPS_AND_BUMPS, true));
		tiles.add(new Tile(5 * GameConfig.TILE_SIZE, 40 * GameConfig.TILE_SIZE, 4, 2, false, Textures.SOFA_BUTT, true));
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 2; j++)
				tiles.add(new Tile((4.5f + j) * GameConfig.TILE_SIZE, (43 + i) * GameConfig.TILE_SIZE, 1, true,
						Textures.TABLE, true));

		// Cutlery Table
		for (int i = 0; i < 4; i++)
			tiles.add(new Tile((10 + i) * GameConfig.TILE_SIZE, 49 * GameConfig.TILE_SIZE, 1, true, Textures.TABLE,
					true));

		// Long Tables
		addTable(8, 3, 17, 16);
		addTable(8, 3, 26, 16);
		addTable(8, 3, 17, 25);

		// Square Tables
		addTable(3, 3, 24, 33);
		addTable(3, 3, 26, 41);
		addTable(3, 3, 32, 32);
		addTable(3, 3, 30, 24);
		addTable(3, 3, 34, 40);

		// Long Table
		addTable(8, 3, 44, 43);

		tiles.add(new Tile(12 * GameConfig.TILE_SIZE, 8 * GameConfig.TILE_SIZE, 1, 2, false, Textures.MIC_1_TEX, true));
		tiles.add(new Tile(12 * GameConfig.TILE_SIZE, 6 * GameConfig.TILE_SIZE, 1, 2, false, Textures.MIC_2_TEX, true));

		// Sofa
		tiles.add(
				new Tile(47 * GameConfig.TILE_SIZE, 34 * GameConfig.TILE_SIZE, 4, 2, false, Textures.SOFA_TITS, true));
		tiles.add(new Tile(50 * GameConfig.TILE_SIZE, 30 * GameConfig.TILE_SIZE, 2, 4, false,
				Textures.SOFA_BUMPS_AND_HUMPS, true));
		tiles.add(
				new Tile(47 * GameConfig.TILE_SIZE, 26 * GameConfig.TILE_SIZE, 4, 2, false, Textures.SOFA_BUTT, true));
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 2; j++)
				tiles.add(new Tile((46.5f + j) * GameConfig.TILE_SIZE, (29 + i) * GameConfig.TILE_SIZE, 1, true,
						Textures.TABLE, true));

	}

	private void addTable(int width, int height, float x, float y) {
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				tiles.add(new Tile((x + i) * GameConfig.TILE_SIZE, (y + j) * GameConfig.TILE_SIZE, 1, true,
						Textures.TABLE, true));
	}

	private void addTilesToList() {
		for (int i = 0; i < 40; i++) {
			for (int j = 0; j < 50; j++) {
				tiles.add(new Tile(i * GameConfig.TILE_SIZE, j * GameConfig.TILE_SIZE, 1, true, Textures.FLOOR_TEX,
						false));
			}
		}

		// DJ Booth
		for (int i = 0; i < 5; i++)
			tiles.add(new Tile(5 * GameConfig.TILE_SIZE, i * GameConfig.TILE_SIZE, 1, true, Textures.WALL_TEX, true));
		for (int i = 0; i < 3; i++)
			tiles.add(new Tile((3 + i) * GameConfig.TILE_SIZE, 5 * GameConfig.TILE_SIZE, 1, true, Textures.WALL_TEX,
					true));

		// Left Wall
		for (int i = 0; i < 10; i++) {
			tiles.add(new Tile(i * GameConfig.TILE_SIZE, 28 * GameConfig.TILE_SIZE, 1, true, Textures.WALL_TEX, true));
			tiles.add(new Tile(i * GameConfig.TILE_SIZE, 29 * GameConfig.TILE_SIZE, 1, true, Textures.WALL_TEX, true));
		}

		// Left External Wall
		for (int i = 0; i < 50; i++) {
			if (i < 10 || i > 15)
				tiles.add(new Tile(-1 * GameConfig.TILE_SIZE, i * GameConfig.TILE_SIZE, 1, true, Textures.WALL_TEX,
						true));
		}

		// Back-Left Exterior Wall
		for (int i = 0; i < 16; i++) {
			tiles.add(new Tile((i - 1) * GameConfig.TILE_SIZE, 50 * GameConfig.TILE_SIZE, 1, true, Textures.WALL_TEX,
					true));
		}

		// Bar Front
		for (int i = 0; i < 6; i++) {
			tiles.add(new Tile((20 + i) * GameConfig.TILE_SIZE, 50 * GameConfig.TILE_SIZE, 1, false, Textures.BAR_0_TEX,
					true));
		}
		tiles.add(new Tile(26 * GameConfig.TILE_SIZE, 50 * GameConfig.TILE_SIZE, 1, false, Textures.BAR_1_TEX, true));
		tiles.add(new Tile(27 * GameConfig.TILE_SIZE, 50 * GameConfig.TILE_SIZE, 1, false, Textures.BAR_2_TEX, true));
		tiles.add(new Tile(28 * GameConfig.TILE_SIZE, 50 * GameConfig.TILE_SIZE, 1, false, Textures.BAR_0_TEX, true));
		tiles.add(new Tile(29 * GameConfig.TILE_SIZE, 50 * GameConfig.TILE_SIZE, 1, false, Textures.BAR_1_TEX, true));
		tiles.add(new Tile(30 * GameConfig.TILE_SIZE, 50 * GameConfig.TILE_SIZE, 1, false, Textures.BAR_2_TEX, true));
		tiles.add(new Tile(31 * GameConfig.TILE_SIZE, 50 * GameConfig.TILE_SIZE, 1, false, Textures.BAR_3_TEX, true));
		tiles.add(new Tile(32 * GameConfig.TILE_SIZE, 50 * GameConfig.TILE_SIZE, 1, false, Textures.BAR_3_TEX, true));
		for (int i = 13; i < 15; i++) {
			tiles.add(new Tile((20 + i) * GameConfig.TILE_SIZE, 50 * GameConfig.TILE_SIZE, 1, false, Textures.BAR_0_TEX,
					true));
		}
		tiles.add(new Tile(35 * GameConfig.TILE_SIZE, 51 * GameConfig.TILE_SIZE, 1, false, Textures.BAR_0_TEX, true));
		tiles.add(new Tile(36 * GameConfig.TILE_SIZE, 51 * GameConfig.TILE_SIZE, 1, false, Textures.BAR_0_TEX, true));
		tiles.add(new Tile(37 * GameConfig.TILE_SIZE, 52 * GameConfig.TILE_SIZE, 1, false, Textures.BAR_3_TEX, true));
		tiles.add(new Tile(37 * GameConfig.TILE_SIZE, 53 * GameConfig.TILE_SIZE, 1, false, Textures.BAR_3_TEX, true));
		tiles.add(new Tile(38 * GameConfig.TILE_SIZE, 54 * GameConfig.TILE_SIZE, 1, false, Textures.BAR_3_TEX, true));
		for (int i = 1; i < 3; i++) {
			tiles.add(new Tile(38 * GameConfig.TILE_SIZE, (54 + i) * GameConfig.TILE_SIZE, 1, false, Textures.BAR_0_TEX,
					true));
		}

		for (int i = 0; i < 5; i++)
			tiles.add(new Tile((35 + i) * GameConfig.TILE_SIZE, 50 * GameConfig.TILE_SIZE, 1, true, Textures.FLOOR_TEX,
					false));
		for (int i = 0; i < 3; i++)
			tiles.add(new Tile((37 + i) * GameConfig.TILE_SIZE, 51 * GameConfig.TILE_SIZE, 1, true, Textures.FLOOR_TEX,
					false));
		for (int i = 0; i < 2; i++)
			tiles.add(new Tile(38 * GameConfig.TILE_SIZE, (52 + i) * GameConfig.TILE_SIZE, 1, true, Textures.FLOOR_TEX,
					false));
		for (int i = 0; i < 5; i++)
			tiles.add(new Tile(39 * GameConfig.TILE_SIZE, (52 + i) * GameConfig.TILE_SIZE, 1, true, Textures.FLOOR_TEX,
					false));

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 54; j++)
				tiles.add(new Tile((40 + i) * GameConfig.TILE_SIZE, (3 + j) * GameConfig.TILE_SIZE, 1, true,
						Textures.FLOOR_TEX, false));

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 50; j++)
				tiles.add(new Tile((43 + i) * GameConfig.TILE_SIZE, (6 + j) * GameConfig.TILE_SIZE, 1, true,
						Textures.FLOOR_TEX, false));

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 41; j++)
				tiles.add(new Tile((46 + i) * GameConfig.TILE_SIZE, (12 + j) * GameConfig.TILE_SIZE, 1, true,
						Textures.FLOOR_TEX, false));

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 30; j++)
				tiles.add(new Tile((49 + i) * GameConfig.TILE_SIZE, (18 + j) * GameConfig.TILE_SIZE, 1, true,
						Textures.FLOOR_TEX, false));

		// Bar Highlight
		tiles.add(new Tile(26 * GameConfig.TILE_SIZE, 47.5f * GameConfig.TILE_SIZE, 10, 4, false, Textures.HIGHLIGHT,
				false));

	}

}
