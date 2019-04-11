package impl;


import game.Element;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


public class MouseCursor extends Element implements MoveOnMouseMove {
    public MouseCursor() {
        super("/resources/cursor.png");
//        super.getParent().setOnMouseMoved(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                System.out.println(event.getX());
//                MouseCursor.super.setX(event.getX());
//                MouseCursor.super.setY(event.getY());
//            }
//        });
    }


    @Override
    public void handleMouseMove(MouseEvent mouseEvent) {
        super.setX(mouseEvent.getX());
        super.setY(mouseEvent.getY());
    }
}
