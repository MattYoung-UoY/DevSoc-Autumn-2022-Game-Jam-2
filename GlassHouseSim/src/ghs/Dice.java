package ghs;

import java.util.HashMap;
import java.util.Random;

import dejavu.engine.graphics.guis.GuiImage;

public class Dice {
	
	private HashMap<Integer, GuiImage> dice = new HashMap<Integer, GuiImage>();

	private Random rand = new Random();
	
	private int currentValue = -1;

	private boolean rolling = false, rolled = false;
	private int counter = -1;
	private double lastTime = 0;
	
	private float x, y, scaleX, scaleY;
	
	public Dice(float x, float y, float scaleX, float scaleY) {
		this.x = x;
		this.y = y;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}

	public void addFace(int value, int texID) {
		if(currentValue == -1) currentValue = value;
		dice.put(value, new GuiImage(x, y, scaleX, scaleY, texID));
	}
	
	public void roll() {
		rolling = true;
		rolled = true;
	}
	
	public void update() {
		if(rolling) {
			
			if(counter == -1) {
				counter = 1;
				lastTime = System.currentTimeMillis();
			}else if(counter == 25) {
				rolling = false;
				return;
			}
			
			if(System.currentTimeMillis() - lastTime >= counter * 10) {
				lastTime = System.currentTimeMillis();
				counter++;
				currentValue = 1 + rand.nextInt(dice.size() - 1);
			}
			
		}
	}
	
	public GuiImage getFace() {
		return dice.get(currentValue);
	}
	
	public int getValue() {
		return currentValue;
	}
	
	public boolean isRolling() {
		return rolling;
	}
	
	public boolean hasBeenRolled() {
		return rolled;
	}
	
}
