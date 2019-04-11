package impl;


import behavior.behaviors.Collidable;
import behavior.behaviors.KeyBehavior;
import game.Element;
import java.util.ArrayList;

public class Lava extends Element implements Collidable, KeyBehavior {
	public boolean active = true;
	
    public Lava() {
        super("/resources/lava.png");
    }
    
    @Override
    public void handleKeyPresses(ArrayList<String> arrayList) {
    }
    
    @Override
    public void handleCollision(Collidable collidable) {
    }
}