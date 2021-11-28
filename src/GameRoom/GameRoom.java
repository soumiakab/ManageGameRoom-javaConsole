package GameRoom;

import Classes.ManageRoom;
import helpers.HelpFunction;

public class GameRoom {

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
	
	
	//show menu
	public static void showMenu(){
		int maxCoose=Menu.showMenu();
		int choose=HelpFunction.getUserChoose(maxCoose);
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
