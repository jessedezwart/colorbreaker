package impl;


import behavior.behaviors.Collidable;
import behavior.behaviors.KeyBehavior;
import game.Element;
import java.util.ArrayList;

public class PlankjeL extends Element implements Collidable, KeyBehavior {

    private double deltaY;
    private double deltaX;

    public PlankjeL() {
        super("/resources/plankjeL.png");
        this.deltaY = 0;
        this.deltaX = 0;
        this.autosize();
    }


    @Override
    public void handleKeyPresses(ArrayList<String> arrayList) {
        this.deltaX = 0;
        this.deltaY = 0;
        if (arrayList.contains("RIGHT")){
            super.setX(super.getX()+15);
            this.deltaX = 10;
            if (this.getX() > 690) {
            	super.setX (690);
            	}
        }
        else if (arrayList.contains("LEFT")){
            super.setX(super.getX()-15);
            this.deltaX = -10;
            if (this.getX() < 60) {
            	super.setX (60);
            	}
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
