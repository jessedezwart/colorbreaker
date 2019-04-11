import engine.Engine;
import engine.GameLoader;
import game.Element;
import game.Game;
import game.Tile;
import impl.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.HashMap;

public class Runner extends Application {
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    	Label usernameLbl = new Label("Username:");
    	TextField usernameTxt = new TextField();
    	Label passwordLbl = new Label("Password:");
    	TextField passwordTxt = new TextField();
        Button startGameButton = new Button("Start Game");
        Button highScoreButton = new Button("Highscores");

       
        startGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	String username = usernameTxt.getText();
            	String password = passwordTxt.getText();
            	
            	if (usernameTxt.getText().isEmpty() || passwordTxt.getText().isEmpty()) {
            		Alert alert = new Alert(AlertType.INFORMATION);
            		alert.setTitle("Oops!");
            		alert.setHeaderText(null);
            		alert.setContentText("It seems you got incorrect credentials!");
            		alert.showAndWait();
            	} else {
            		User user = new User();
                    if (user.checkCredentials(username, password) == 0) {
                    	startGame(primaryStage);
                    } else {
                    	Alert alert = new Alert(AlertType.INFORMATION);
                		alert.setTitle("Oops!");
                		alert.setHeaderText(null);
                		alert.setContentText("It seems you got incorrect credentials!");
                		alert.showAndWait();
                    }
            	}
            	
            	
            }
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(usernameLbl,usernameTxt,passwordLbl,passwordTxt,startGameButton,highScoreButton);


        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBox);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public void startGame(Stage primaryStage){
    	
        GameLoader gameLoader = new GameLoader();

        //init tiles
        HashMap<Integer, Class<? extends Tile>> tileHashMap = new HashMap<>();
        tileHashMap.put(0,BackgroundTile.class);
        tileHashMap.put(1,BorderTileL.class);
        tileHashMap.put(2,BorderTileR.class);
        tileHashMap.put(3,BorderTileT.class);
        tileHashMap.put(4,BorderTileCL.class);
        tileHashMap.put(5,BorderTileCR.class);
        gameLoader.addTileConfiguration(tileHashMap);

        //init elements
        HashMap<Integer, Class<? extends Element>> elementHashMap = new HashMap<>();
        elementHashMap.put(0, Ball.class);
        elementHashMap.put(2, BreakBlockRed.class);
        elementHashMap.put(3, PlankjeL.class);
        elementHashMap.put(4, Lava.class);
        elementHashMap.put(5, PlankjeR.class);
        elementHashMap.put(6, PlankjeM.class);
        elementHashMap.put(7, PlankjeLM.class);
        elementHashMap.put(8, PlankjeRM.class);
        gameLoader.addElementsConfiguration(elementHashMap);

        gameLoader.addLevel(1,"/resources/level1Tiles.txt","/resources/level1Elements.txt");

        Game game = gameLoader.load();

        game.getLevels().get(0).setFocusedElement(game.getLevels().get(0).getElements().get(0));
        game.setActiveLevel(game.getLevels().get(0));
        
        Engine engine = new Engine(game);
        engine.start(primaryStage);
        engine.focusOnElement(null);
        
        Scoreboard player1 = new Scoreboard(420, 50, 0, primaryStage);
        player1.setScore(0);
    }
}
