package impl;


import behavior.behaviors.Collidable;
import behavior.behaviors.KeyBehavior;
import game.Element;
import java.util.ArrayList;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

public class PlankjeR extends Element implements Collidable, KeyBehavior {

    private double deltaY;
    private double deltaX;
    ControllerManager controllers = new ControllerManager();

    public PlankjeR() {
        super("/resources/plankjeR.png");
        this.deltaY = 0;
        this.deltaX = 0;
        this.autosize();
        controllers.initSDLGamepad();
    }


    @Override
    public void handleKeyPresses(ArrayList<String> arrayList) {
        this.deltaX = 0;
        this.deltaY = 0;
        ControllerState currState = controllers.getState(0);
        if (arrayList.contains("LEFT") || currState.dpadLeft){
            moveLeft();
        }
        else if (arrayList.contains("RIGHT") || currState.dpadRight){
            moveRight();
        }
    }
    
    public void moveLeft() {
    	super.setX(super.getX()-15);
        this.deltaX = -10;
        if (this.getX() < 260) {
        	super.setX (260);
        }
    }
    
    public void moveRight() {
    	super.setX(super.getX()+15);
        this.deltaX = 10;
        if (this.getX() > 890) {
        	super.setX (890);
        }
    }

    @Override
    public void handleCollision(Collidable collidable) {
    }

    public double getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }

    public double getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(double deltaX) {
        this.deltaX = deltaX;
    }
}
