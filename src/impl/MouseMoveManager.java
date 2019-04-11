package impl;


import behavior.BehaviorManager;
import game.Element;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MouseMoveManager implements BehaviorManager {
    private boolean eventHandlerSet;

    public MouseMoveManager() {
        eventHandlerSet = false;
    }

    @Override
    public void handle(Element element) {
        if(!eventHandlerSet){
            element.getParent().getScene().setOnMouseMoved(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    MoveOnMouseMove moveOnMouse = (MoveOnMouseMove) element;
                    moveOnMouse.handleMouseMove(event);
                }
            });
        }
    }
}
