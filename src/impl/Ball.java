package impl;


import behavior.behaviors.Collidable;
import behavior.behaviors.KeyBehavior;
import game.Element;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.ArrayList;
import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

public class Ball extends Element implements Collidable, KeyBehavior {

    private double deltaY;
    private double deltaX;
    private double ballSpeed;
    public double directionDegrees = Math.random()*360;
    ControllerManager controllers = new ControllerManager();
    

    public Ball() {
        super("/resources/ball.png");
        this.deltaY = 0;
        this.deltaX = 0;
        this.autosize();
        this.resetBall();
        controllers.initSDLGamepad();
    }
    

    public void resetBall() {
    	ballSpeed = 0;
    	this.setX(100);
        this.setY(100);
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
        this.deltaX = directionY;
        
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
        if(currState.b) {
        	if (ballSpeed == 0) {
        		ballSpeed = 15;
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
  
    		if (((BreakBlockRed) collidable).active == true) {
    			System.out.println(System.getProperty("user.dir"));
    		    String musicFile = System.getProperty("user.dir") + "\\src\\resources\\bump.wav";
    		    Media sound = new Media(new File(musicFile).toURI().toString());
    		    MediaPlayer mediaPlayer = new MediaPlayer(sound);
    			mediaPlayer.play();
    			this.reverseBall("horizontal");
    			((BreakBlockRed) collidable).active = false;
    			((BreakBlockRed) collidable).setVisible(false);
    			Scoreboard.plusScore();
    			
    			
    		}
	    }
    	if(collidable instanceof Plankje){
    		this.reverseBall("horizontal");
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
