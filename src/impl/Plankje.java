package impl;


import behavior.behaviors.Collidable;
import behavior.behaviors.KeyBehavior;
import game.Element;
import java.util.ArrayList;
import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

public class Plankje extends Element implements Collidable, KeyBehavior {

    private double deltaY;
    private double deltaX;

    public Plankje() {
        super("/resources/plankje.png");
        this.deltaY = 0;
        this.deltaX = 0;
        this.autosize();
        
    }


    @Override
    public void handleKeyPresses(ArrayList<String> arrayList) {
        this.deltaX = 0;
        this.deltaY = 0;
        if (arrayList.contains("RIGHT")){
            super.setX(super.getX()+10);
            this.deltaX = 10;
        }
        else if (arrayList.contains("LEFT")){
            super.setX(super.getX()-10);
            this.deltaX = -10;
        }
        
        ControllerManager controllers = new ControllerManager();
        controllers.initSDLGamepad();
        ControllerState currState = controllers.getState(0);
        
        if(currState.dpadRight) {
        	super.setX(super.getX()+10);
            this.deltaX = 10;
        }
        if(currState.dpadLeft) {
        	super.setX(super.getX()-10);
            this.deltaX = 10;
        }
        
        
        System.out.println(deltaX + "," + deltaY);
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
