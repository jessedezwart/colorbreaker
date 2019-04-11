package impl;


import behavior.behaviors.Collidable;
import game.Tile;

public class BackgroundTile extends Tile implements Collidable {
    public BackgroundTile() {
        super("/resources/BackgroundTile.png");
    }

    public void handleCollision(Collidable collidable) {
    }
}
