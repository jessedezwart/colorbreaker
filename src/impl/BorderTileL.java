package impl;


import behavior.behaviors.Collidable;
import game.Tile;

public class BorderTileL extends Tile implements Collidable {
    public BorderTileL() {
        super("/resources/BorderTileL.png");
    }

    @Override
    public void handleCollision(Collidable collidable) {

    }
}
