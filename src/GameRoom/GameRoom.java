package GameRoom;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import Classes.ManageRoom;
import ENUMS.PriceSchedule;
import helpers.HelpFunction;
import Classes.Game;
import Classes.GameType;

public class GameRoom {
	static Scanner scan= new Scanner(System.in);
	static ArrayList<GameType> GamesInRoom=new ArrayList<>();
	
	
	//avoir le choix de l utilisateur
	public static int getUserChoose(int maxValue) {
		String chooseTemp=scan.nextLine();
		while(!choose(chooseTemp,maxValue)) {
    		System.out.println("Please enter a valid choose");
    		chooseTemp=scan.nextLine();	    		 	    	
    	}
    	return Integer.parseInt(chooseTemp);
			
		
	}

	//verfier la validite du choix
	public static boolean choose(String choose,int max) {	
		
		try {
			Integer.parseInt(choose);
			if(Integer.parseInt(choose)>max) {
				return false;
			}
			
			return true;
			
		}catch(NumberFormatException e){
		
			return false;
		}				
	}

	
	//choose game
	public static void chooseGame() {
		ManageRoom.showGamesInRoom();//show game in room	
		System.out.println("entrer le numero du type du jeu");
		String gameT=scan.nextLine();
		ManageRoom.showGamesInType(Integer.parseInt(gameT)-1);
		System.out.println("entrer le numero du jeu");
		String game=scan.nextLine();
		System.out.println("How mush time do you want play");
		ManageRoom.showSchedule();
		System.out.println("\nwhat did you choose");
		String schd=scan.nextLine();
		Game searchG=ManageRoom.getGames().get(Integer.parseInt(gameT)-1).getgames().get(Integer.parseInt(game)-1);
		boolean available = ManageRoom.searchGameInAvailablePost(searchG);
		if(available==true) {
			System.out.println("choisir un post");
			int post=HelpFunction.getUserChoose(100);
			PriceSchedule scdP=ManageRoom.getSchedules()[Integer.parseInt(schd)-1];
			ManageRoom.addPlayer(post,scdP,searchG);
			takeChoose();
			//ManageRoom.showPlayers();
		}
		else if(available == false && ManageRoom.getplayers().size()<7) {
			System.out.println("the choose game is not in available post do you want to want to choose an other game or you want wait enter y to wait or N to change Game ");
			//take choose to continue
			String continueWithCoose=scan.nextLine();
			if(continueWithCoose=="o") {
				
			}
			else {
				
			}
			int post=HelpFunction.getUserChoose(100);
			PriceSchedule scdP=ManageRoom.getSchedules()[Integer.parseInt(schd)-1];
			ManageRoom.addPlayer(post,scdP,searchG);
			takeChoose();
			//ManageRoom.showPlayers();
		}
		else {
			if(ManageRoom.getwaitingplayers().size()<10) {
				System.out.println("you gonna wait there is no available post");
				ManageRoom.SortPostByFinishTime();
				System.out.println("choisir un post");
				String post=scan.nextLine();
			}
			else {
				System.out.println("you can not play ,all posts are reserved sorry");
			}
		}
	}
	//add to waiting list
	public static void addToWaitingList() {
		if(ManageRoom.getwaitingplayers().size()<10) {
			System.out.println("you gonna wait there is no available post");
			ManageRoom.SortPostByFinishTime();
			System.out.println("choisir un post");
			String post=scan.nextLine();
		}
		else {
			System.out.println("you can not play ,all posts are reserved sorry");
		}
	}
	
	//call choose method
	public static void chooseMethod(int choose) {
		switch(choose) {
		case 1:ManageRoom.getPlayerGameChoose();
			   showMenu();
			break;
		case 2:ManageRoom.showGamesInRoom();
		      showMenu();
			break;
			
		case 3:ManageRoom.showAvailablePosts();//show available post
				showMenu();
			break;
		case 4:ManageRoom.showPlayers();//list players
				showMenu();
			break;
		case 5:ManageRoom.showWaitingPlayers();//show players waiting
		showMenu();
			break;
		case 6:ManageRoom.SortPostByFinishTime(); //post and finish time for every one
		showMenu();
			break;
		case 7:System.out.println("****** Your day Amount is : "+ManageRoom.getDayAmount()+" DH ******");//day amount
		showMenu();
			break;
		case 8:System.out.println(" the amount of the Month :"+ManageRoom.getMonthAmount()+" DH");//month amount
		showMenu();
			break;
		case 9:System.out.println("close app");//month amount
			break;		
		default: Menu.showMenu();
			break;					
		}
		
	}
	
	public static void takeChoose(){
		int choose=getUserChoose(9);
		chooseMethod(choose);
	}
	
	//show menu
	public static void showMenu(){
		int maxCoose=Menu.showMenu();
		int choose=getUserChoose(maxCoose);
		chooseMethod(choose);
	}
	
	public static void main(String[] args) {
		ManageRoom.playerEndTime();
		ManageRoom.initGamesType();
		ManageRoom.initPosts();
		ManageRoom.initScreens();
		ManageRoom.monthAmount(0);
		showMenu();
    }
	
}
