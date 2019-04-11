package impl;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Scoreboard {
	private static int score;
	private String naam;
	private static Label scoreLabel;
	private Stage stage;
	
	public static int getScore() { 
		return score; 
	}
	
	public static void setScore(int score) { 
		scoreLabel.setText("SCORE " + score); 
	}
	
	public static void plusScore() {
		score+=100;
		scoreLabel.setText("SCORE " + score);
	}
	
	public Scoreboard(int x, int y, int score, Stage stage) {
		this.stage = stage;
		scoreLabel = new Label("SCORE: " + score);
		scoreLabel.setFont(new Font("Arial", 25));
		scoreLabel.setLayoutX(x);
		scoreLabel.setLayoutY(y);
		scoreLabel.setTextFill(Color.WHITE);
		
		((Group) stage.getScene().getRoot()).getChildren().add(scoreLabel);
		this.score = score;
	}
	
	
}
