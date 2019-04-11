package impl;


import behavior.behaviors.Collidable;
import behavior.behaviors.KeyBehavior;
import game.Element;
import java.util.ArrayList;

public class BreakBlockRed extends Element implements Collidable, KeyBehavior {
	public boolean active = true;
	
    public BreakBlockRed() {
        super("/resources/pixil-frame-red.png");
    }
    
    @Override
    public void handleKeyPresses(ArrayList<String> arrayList) {
    }
    
    @Override
    public void handleCollision(Collidable collidable) {
    }
}