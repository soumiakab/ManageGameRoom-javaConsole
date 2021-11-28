package Classes;

import java.util.ArrayList;

public class GameType {

	private String gameTypeName;
	private  ArrayList<Game> games= new ArrayList<>();
	
	public GameType() {
		
	}
	
	public GameType(String gameTypeName) {
		this.gameTypeName=gameTypeName;
	}
	
	public GameType(String gameTypeName,Game game) {
		this.gameTypeName=gameTypeName;
		games.add(game);
	}
	
	
	//get game type name
	public String getGameTypeName() {
		return this.gameTypeName;
	}
	
	//get game type name
	public ArrayList<Game> getgames() {
		return games;
	}
	
	
	//get type with Games
	
	@Override
	public String toString() {
		String showListGmaes;
		showListGmaes=this.gameTypeName+" : ";
		for(int i=0; i<games.size(); i++) {
			showListGmaes +="\n \t\t-"+games.get(i).getGameName();
			
		}
		
		return showListGmaes;
	}
	
	//add game to type
	public void addGame(Game game) {
		games.add(game);
	}
	
	
	//remove game from type
	public void removeGame(Game game) {
		games.remove(game);
	}
	
	

}
