package Classes;

import java.util.ArrayList;

import ENUMS.PostEnum;

public class Post {
	
	private ArrayList<GameType> gameList=new ArrayList<>(); 
	private  int postNumber;
	private boolean available=true;
	private  PostEnum postName;
	
	public Post() {
		
	}
	
	public Post(int num,PostEnum postName) {
		this.postNumber=num;
		this.postName=postName;
		
	}
	
	
	//get post brand
	public PostEnum getPostBrand() {
		return this.postName;
	}
	
	
	//get post number
	public  int getPostNumber() {
		return postNumber;
	}
	
	public  String getPostName() {
		return this.postName.toString();
	}
	
	//add gameType to post
	public void  addGameType(GameType type) {
		gameList.add(type);
	}

	
	//verify post availability
	public boolean verifyAvailablity() {
		
			return available;
	}
	
	
	//change Availability
	public void  changeAvaiblity() {
		this.available=!this.available;
	}
	
	//get post games
	@Override
	public String toString() {
		String showListTypeGmaes;
		showListTypeGmaes="\n -post"+postNumber +": "+this.postName+"\n";
		for(int i=0; i<gameList.size(); i++) {
			showListTypeGmaes +="\n \t"+(i+1)+"-"+gameList.get(i);
			
		}
		
		return showListTypeGmaes;
	}
	
		
	
	//verify if post contains game
	
	public boolean verifyGameAvailablity(Game game) {
		boolean contain=false;
		for(GameType gameType:gameList) {
			if(gameType.getgames().contains(game)) {
				contain=true;
				break;
			}
		}
		
		return contain;
		
	}
	
	//start post
	public void startPost(String player) {
		System.out.println("Welcome "+player);
	}
	
	
	//switch off post
	public void switchOffPost(String player,Game game) {
		
		System.out.println(player+" you're terminate your time tank you for playing in "+game);
		
	}
	
	
}
