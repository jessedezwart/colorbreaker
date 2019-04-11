package impl;


import behavior.behaviors.Collidable;
import behavior.behaviors.KeyBehavior;
import game.Element;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

public class Ball extends Element implements Collidable, KeyBehavior {

    private double deltaY;
    private double deltaX;
    private double ballSpeed;
    private int lives;
    public double directionDegrees = 270;
    ControllerManager controllers = new ControllerManager();
    

    public Ball() {
        super("/resources/ball.png");
        this.deltaY = 0;
        this.deltaX = 0;
        this.lives = 3;
        this.autosize();
        this.resetBall();
        controllers.initSDLGamepad();
    }
    

    public void resetBall() {
    	ballSpeed = 0;
    	this.setX(450);
        this.setY(835);
    }

    @Override
    public void handleKeyPresses(ArrayList<String> arrayList) {
        this.deltaX = 0;
        this.deltaY = 0;
        
        
        //ball richting is bepaald door directionDegrees
        double directionRadians = Math.toRadians(this.directionDegrees);
        double directionX = Math.cos(directionRadians) * ballSpeed;
        double directionY = Math.sin(directionRadians) * ballSpeed;        
        
        
        super.setX(super.getX()+directionX);
        this.deltaX = directionX;
        
        super.setY(super.getY()+directionY);
        this.deltaY = directionY;
        
        //reverse ball en speel geluid
        if (this.getX() > 940 || this.getX() < 60) {
        	this.reverseBall("vertical");
        	String musicborder = System.getProperty("user.dir") + "\\src\\resources\\border.wav";
		    Media sound = new Media(new File(musicborder).toURI().toString());
		    MediaPlayer mediaPlayer = new MediaPlayer(sound);
		    mediaPlayer.play();
        }
        
        if (this.getY() < 60 || this.getY() > 940) {
        	this.reverseBall("horizontal");
        	String musicborder = System.getProperty("user.dir") + "\\src\\resources\\border.wav";
		    Media sound = new Media(new File(musicborder).toURI().toString());
		    MediaPlayer mediaPlayer = new MediaPlayer(sound);
		    mediaPlayer.play();
        }
        
        ControllerState currState = controllers.getState(0);
        if(currState.b || arrayList.contains("SPACE")) {
        	if (ballSpeed == 0) {
        		ballSpeed = 5;
        	}
        }
        
        //System.out.println(this.getX() + "," + this.getY());
    }
    
    @Override
    public void handleCollision(Collidable collidable) {
    	if(collidable instanceof BreakBlockRed){
    		ballSpeed *= 1.002;
    		if (ballSpeed > 15) {
    			ballSpeed = 15;
    		}
  
    		// check of breakblock wel bestaat met if statement
    		if (((BreakBlockRed) collidable).active == true) {
    			//speel break geluid
    			System.out.println(System.getProperty("user.dir"));
    		    String musicFile = System.getProperty("user.dir") + "\\src\\resources\\bump.wav";
    		    Media sound = new Media(new File(musicFile).toURI().toString());
    		    MediaPlayer mediaPlayer = new MediaPlayer(sound);
    			mediaPlayer.play();
    			
    			//draai de ball
    			reverseBall("horizontal");
    			
    			//zet het blokje uit
    			((BreakBlockRed) collidable).active = false;
    			((BreakBlockRed) collidable).setVisible(false);
    			Scoreboard.plusScore();
    		}
	    }
    	
    	if(collidable instanceof PlankjeR){
    		this.reverseBall("right");
	    }
    	if(collidable instanceof PlankjeM){
    		this.reverseBall("middle");
	    }
    	if(collidable instanceof PlankjeLM){
    		this.reverseBall("lmiddle");
	    }
    	if(collidable instanceof PlankjeRM){
    		this.reverseBall("rmiddle");
	    }
    	
    	if(collidable instanceof Lava){
    		resetBall();
    		this.lives -= 1;
    		if (lives == 0) {
    			//game over!
    			Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("You dead!");
        		alert.setHeaderText(null);
        		alert.setContentText("Nice try. Score: " + Scoreboard.getScore());
        		alert.show();
        		try {
					User.setScore(Scoreboard.getScore(), User.getUsername());
				} catch (Exception e) {
					e.printStackTrace();
				}
        		try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        		System.exit(0);
    		}
	    }
    }

    public void reverseBall(String direction) {
    	double directionDegreesNew = 0;
    	if (direction == "vertical") {
    		directionDegreesNew = this.directionDegrees -= (this.directionDegrees-270)*2;
    	}
    	if (direction == "horizontal") {
    		directionDegreesNew = this.directionDegrees -= (this.directionDegrees-180)*2;
    	}
    	if (direction == "left") {
    		directionDegreesNew = this.directionDegrees = Math.random()*10 + 200;
    	}
    	if (direction == "right") {
    		directionDegreesNew = this.directionDegrees = Math.random()*10 + 330;
    	}
    	if (direction == "middle") {
    		directionDegreesNew = this.directionDegrees = Math.random()*10 + 265;
    	}
    	if (direction == "lmiddle") {
    		directionDegreesNew = this.directionDegrees = Math.random()*10 + 240;
    	}
    	if (direction == "rmiddle") {
    		directionDegreesNew = this.directionDegrees = Math.random()*10 + 290;
    	}
    	
        this.directionDegrees = directionDegreesNew;
    }
    
    public double getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }

    public double getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(double deltaX) {
        this.deltaX = deltaX;
    }
}
