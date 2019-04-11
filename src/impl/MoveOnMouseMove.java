package impl;


import behavior.Behavior;
import javafx.scene.input.MouseEvent;


public interface MoveOnMouseMove extends Behavior {
    void handleMouseMove(MouseEvent mouseEvent);
}
