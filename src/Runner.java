import engine.Engine;
import engine.GameLoader;
import game.Element;
import game.Game;
import game.Tile;
import impl.*;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import service.FirebaseService;

import java.util.HashMap;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

public class Runner extends Application {
	
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    	Label usernameLbl = new Label("Username:");
    	usernameLbl.setFont(Font.font("ARIAL", FontWeight.BOLD, 16));
    	TextField usernameTxt = new TextField();
    	Label passwordLbl = new Label("Password:");
    	passwordLbl.setFont(Font.font("ARIAL", FontWeight.BOLD, 16));
    	TextField passwordTxt = new TextField();
        Button startGameButton = new Button("Start Game");
        Button highScoreButton = new Button("Highscores");
        
        Button backButton = new Button("Back");
       
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

        highScoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	//read firebase json
            	String firebaseJSON = null;
            	FirebaseService firebaseService = new FirebaseService();
            	try {
            		firebaseJSON = "[" + firebaseService.getHighscores("Donald Knuth") + "]";
				} catch (Exception e) {
					e.printStackTrace();
				}
            	

            	TableView table = new TableView();
                TableColumn nameCol = new TableColumn("Name");
                TableColumn scoreCol = new TableColumn("Highscore");
                table.getColumns().addAll(nameCol, scoreCol);
                
                
                
                nameCol.setCellValueFactory(c -> new SimpleStringProperty(new String("123")));
                scoreCol.setCellValueFactory(c -> new SimpleStringProperty(new String("456")));

                table.getItems().addAll("Column one's data", "Column two's data");
            	
            	Label title = new Label("Highscores: ");
                title.setTextFill(Color.BLACK);
                title.setFont(Font.font("ARIAL", FontWeight.BOLD, 36));
                
                
            	VBox vBox = new VBox();
                vBox.getChildren().addAll(title, table);
                vBox.setPadding(new Insets(10, 10, 10, 10));
                vBox.setSpacing(10);

                BorderPane borderPane = new BorderPane();
                borderPane.setCenter(vBox);
                Scene scene = new Scene(borderPane);
                primaryStage.setScene(scene);
                primaryStage.show();                                           	
            	
            }
        });
        
        VBox vBox = new VBox();
        vBox.getChildren().addAll(usernameLbl,usernameTxt,passwordLbl,passwordTxt,startGameButton,highScoreButton);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setSpacing(10);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBox);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Colorbreaker");
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
        elementHashMap.put(3, Plankje.class);
        elementHashMap.put(4, Lava.class);
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
