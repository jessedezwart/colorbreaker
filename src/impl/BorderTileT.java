package impl;


import behavior.behaviors.Collidable;
import game.Tile;

public class BorderTileT extends Tile implements Collidable {
    public BorderTileT() {
        super("/resources/BorderTileT.png");
    }

    @Override
    public void handleCollision(Collidable collidable) {

    }
}
