package impl;


import behavior.behaviors.Collidable;
import behavior.behaviors.KeyBehavior;
import game.Element;
import java.util.ArrayList;

public class BreakBlock extends Element implements Collidable, KeyBehavior {
	public boolean active = true;
	
    public BreakBlock() {
        super("/resources/pixil-frame-0 (4).png");
    }
    
    @Override
    public void handleKeyPresses(ArrayList<String> arrayList) {
    }
    
    @Override
    public void handleCollision(Collidable collidable) {
    }
}