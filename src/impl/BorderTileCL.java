package impl;


import behavior.behaviors.Collidable;
import game.Tile;

public class BorderTileCL extends Tile implements Collidable {
    public BorderTileCL() {
        super("/resources/BorderTileCL.png");
    }

    @Override
    public void handleCollision(Collidable collidable) {

    }
}
