package Classes;
import java.util.Random;

import ENUMS.PriceSchedule;


public class Player {
    private String prenom;
    private String nom;
    private int poste_number;
    private String start_time;
    private String finishTime;
    private PriceSchedule game_time;
    private Game game;
    private String player_code;
    private double reduction=0;
    private  Screen screen;
    
    public Player() {
    	
    }
    
    public Player(String prenom,String nom,int poste_number,Screen sc,String start_time,String finishTime,PriceSchedule game_time,Game game) {
    	  this.prenom=prenom;
    	  this.nom=nom;
    	  this.poste_number=poste_number;
    	  this.start_time=start_time;
    	  this.finishTime=finishTime;
    	  this.game_time=game_time;
    	  this.game=game;
    	  this.player_code=this.generatePlayerCode();
    	  this.screen=sc;
    }
    
    public String generatePlayerCode () {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 7;
        for(int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }
        String randomString = sb.toString();
        return randomString;
        //System.out.println("Random String is: " + randomString);

    }
    
    public String getFinishTime() {
    	return this.finishTime;
    }
    
    public String getStartTime() {
    	return this.start_time;
    }
    
    public PriceSchedule getGameTime() {
    	return this.game_time;
    }
    
    public int getpostNum() {
    	return this.poste_number;
    }
    
    public String getPlayerCode() {
    	return this.player_code;
    }
    
    public double getReduction() {
    	return this.reduction;
    }
    
    
    public void setReduction(double x) {
    	this.reduction +=x;
    }
    
    
	//set screen
	public void setScreen(Screen sc) {
		this.screen=sc;
	}
	
	//get screen
	public Screen getScreen() {
		return this.screen;
	}
    
    @Override
	public String toString() {
    	
		String playerInfo;		
		playerInfo="player with code : \""+this.player_code +"\" play: "+this.game.getGameName()+" in post: "+this.poste_number+" for: "+this.game_time.getLabel()+" start at "+this.start_time+" and will be finish at "+this.finishTime;		
		return playerInfo;
		
	}
}
