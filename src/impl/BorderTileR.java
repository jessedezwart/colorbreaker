package impl;


import behavior.behaviors.Collidable;
import game.Tile;

public class BorderTileR extends Tile implements Collidable {
    public BorderTileR() {
        super("/resources/BorderTileR.png");
    }

    @Override
    public void handleCollision(Collidable collidable) {

    }
}
