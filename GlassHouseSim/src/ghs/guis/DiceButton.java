package ghs.guis;

import dejavu.engine.graphics.guis.GuiButton;
import dejavu.engine.graphics.text.Font;
import ghs.Dice;

public class DiceButton extends GuiButton{
	
	private Dice dice;
	
	public DiceButton(float x, float y, float scaleX, float scaleY, int textureID, String buttonText, Font font, Dice dice) {
		super(x, y, scaleX, scaleY, textureID, buttonText, font);
		this.dice = dice;
	}

	@Override
	public void onClick() {
		dice.roll();
	}
	
	public Dice getDice() {
		return dice;
	}
	
}
