package impl;


import behavior.behaviors.Collidable;
import game.Tile;

public class BorderTileCR extends Tile implements Collidable {
    public BorderTileCR() {
        super("/resources/BorderTileCR.png");
    }

    @Override
    public void handleCollision(Collidable collidable) {

    }
}
