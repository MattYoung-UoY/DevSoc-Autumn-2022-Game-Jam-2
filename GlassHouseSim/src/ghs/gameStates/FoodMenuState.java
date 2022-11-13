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
import ghs.Dice;
import ghs.GlassHouseSim;
import ghs.Textures;
import ghs.guis.DiceButton;

public class FoodMenuState extends GameState {

	private DiceButton d6_1;
	private DiceButton d10_1, d10_2, d10_3;
	private boolean done = false, doneTextAdded = false;
	
	public FoodMenuState() {
		super(new Color(0, 200, 200));

		addGui(new GuiImage(-0.5f, 0, 1 * 0.65f, 1.64f * 0.65f, Textures.FOOD_MENU));

		Dice d10_1_dice = new Dice(0.5f, 0.3f, 0.1f, 0.1f);
		d10_1_dice.addFace(1, Textures.D10_1);
		d10_1_dice.addFace(2, Textures.D10_2);
		d10_1_dice.addFace(3, Textures.D10_3);
		d10_1_dice.addFace(4, Textures.D10_4);
		d10_1_dice.addFace(5, Textures.D10_5);
		d10_1_dice.addFace(6, Textures.D10_6);
		d10_1_dice.addFace(7, Textures.D10_7);
		d10_1_dice.addFace(8, Textures.D10_8);
		d10_1_dice.addFace(9, Textures.D10_9);
		d10_1_dice.addFace(10, Textures.D10_10);
		d10_1 = new DiceButton(0.2f, 0.3f, 0.4f, 0.1f, Textures.BUTTON_TEX, "Roll", Font.dejavuFont, d10_1_dice);
		addGui(d10_1);

		Dice d6_1_dice = new Dice(0.5f, 0.15f, 0.1f, 0.1f);
		d6_1_dice.addFace(1, Textures.D6_1);
		d6_1_dice.addFace(2, Textures.D6_2);
		d6_1_dice.addFace(3, Textures.D6_3);
		d6_1_dice.addFace(4, Textures.D6_4);
		d6_1_dice.addFace(5, Textures.D6_5);
		d6_1_dice.addFace(6, Textures.D6_6);
		d6_1 = new DiceButton(0.2f, 0.15f, 0.4f, 0.1f, Textures.BUTTON_TEX, "Roll", Font.dejavuFont, d6_1_dice);
		addGui(d6_1);

		Dice d10_2_dice = new Dice(0.5f, 0f, 0.1f, 0.1f);
		d10_2_dice.addFace(1, Textures.D10_1);
		d10_2_dice.addFace(2, Textures.D10_2);
		d10_2_dice.addFace(3, Textures.D10_3);
		d10_2_dice.addFace(4, Textures.D10_4);
		d10_2_dice.addFace(5, Textures.D10_5);
		d10_2_dice.addFace(6, Textures.D10_6);
		d10_2_dice.addFace(7, Textures.D10_7);
		d10_2_dice.addFace(8, Textures.D10_8);
		d10_2_dice.addFace(9, Textures.D10_9);
		d10_2_dice.addFace(10, Textures.D10_10);
		d10_2 = new DiceButton(0.2f, 0f, 0.4f, 0.1f, Textures.BUTTON_TEX, "Roll", Font.dejavuFont, d10_2_dice);
		addGui(d10_2);

		Dice d10_3_dice = new Dice(0.5f, -0.15f, 0.1f, 0.1f);
		d10_3_dice.addFace(1, Textures.D10_1);
		d10_3_dice.addFace(2, Textures.D10_2);
		d10_3_dice.addFace(3, Textures.D10_3);
		d10_3_dice.addFace(4, Textures.D10_4);
		d10_3_dice.addFace(5, Textures.D10_5);
		d10_3_dice.addFace(6, Textures.D10_6);
		d10_3_dice.addFace(7, Textures.D10_7);
		d10_3_dice.addFace(8, Textures.D10_8);
		d10_3_dice.addFace(9, Textures.D10_9);
		d10_3_dice.addFace(10, Textures.D10_10);
		d10_3 = new DiceButton(0.2f, -0.15f, 0.4f, 0.1f, Textures.BUTTON_TEX, "Roll", Font.dejavuFont, d10_3_dice);
		addGui(d10_3);
		
	}

	@Override
	public void cleanUp() {
	}

	@Override
	public ArrayList<Quad> getQuads() {
		ArrayList<Quad> quads = new ArrayList<Quad>();
		quads.add(d6_1.getDice().getFace());
		quads.add(d10_1.getDice().getFace());
		quads.add(d10_2.getDice().getFace());
		quads.add(d10_3.getDice().getFace());
		return quads;
	}

	@Override
	public void init() {
	}

	@Override
	public void update() {
		if (Keys.isKeyDown(Keys.KEY_SPACE))
			GameManager.changeGameState(GlassHouseSim.ghs);
		d6_1.getDice().update();
		d10_1.getDice().update();
		d10_2.getDice().update();
		d10_3.getDice().update();

		
		if (!done) {
			if (!d6_1.getDice().isRolling() && d6_1.getDice().hasBeenRolled()) {
				if (!d10_1.getDice().isRolling() && d10_1.getDice().hasBeenRolled()) {
					if (!d10_2.getDice().isRolling() && d10_2.getDice().hasBeenRolled()) {
						if (!d10_3.getDice().isRolling() && d10_3.getDice().hasBeenRolled()) {
							done = true;
						}
					}
				}
			}
		}

		if(done && !doneTextAdded) {
			addText(new Text("Press the spacebar to exit", 0.03f, 0f, -0.4f, Font.dejavuFont));
			addText(new Text("Food score is " + (
					d6_1.getDice().getValue() + d10_1.getDice().getValue() + d10_2.getDice().getValue() + d10_3.getDice().getValue()
					) + "/36", 0.03f, 0f, -0.3f, Font.dejavuFont));
			doneTextAdded = true;
		}
		
	}

}
