import engine.Camera;
import engine.Engine;
import engine.GameLoader;
import engine.Renderer;
import game.Element;
import game.Game;
import game.Tile;
import impl.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.FirebaseService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Runner extends Application {
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        Button startGameButton = new Button("Start Game");
        Button highScoreButton = new Button("Highscores");
        Label label = new Label("inloggen");

        startGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                startGame(primaryStage);
            }
        });


        VBox vBox = new VBox();
        vBox.getChildren().addAll(startGameButton,highScoreButton, label);


        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBox);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public void startGame(Stage primaryStage){
    	
        GameLoader gameLoader = new GameLoader();

        HashMap<Integer, Class<? extends Tile>> tileHashMap = new HashMap<>();
        tileHashMap.put(0,BackgroundTile.class);
        tileHashMap.put(1,BorderTileL.class);
        tileHashMap.put(2,BorderTileR.class);
        tileHashMap.put(3,BorderTileT.class);
        tileHashMap.put(4,BorderTileCL.class);
        tileHashMap.put(5,BorderTileCR.class);
        gameLoader.addTileConfiguration(tileHashMap);

        HashMap<Integer, Class<? extends Element>> elementHashMap = new HashMap<>();
        elementHashMap.put(0, Ball.class);
        elementHashMap.put(2, BreakBlockRed.class);
        elementHashMap.put(3, Plankje.class);
        elementHashMap.put(4, Lava.class);
        gameLoader.addElementsConfiguration(elementHashMap);

        gameLoader.addLevel(1,"/resources/level1Tiles.txt","/resources/level1Elements.txt");

        Game game = gameLoader.load();

        game.getLevels().get(0).setFocusedElement(game.getLevels().get(0).getElements().get(0));
        game.setActiveLevel(game.getLevels().get(0));
        
        Engine engine = new Engine(game);
        
        engine.addBehavior(MoveOnMouseMove.class,new MouseMoveManager());
        
        engine.start(primaryStage);
        engine.focusOnElement(null);
        
        Scoreboard player1 = new Scoreboard(450, 50, 0, primaryStage);
        player1.setScore(0);
    }
}
