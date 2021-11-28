package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import ENUMS.Month;
import ENUMS.PostEnum;
import ENUMS.PriceSchedule;
import ENUMS.Reduction;
import ENUMS.ScreenEnum;
import helpers.HelpFunction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ManageRoom {
	static Scanner scan= new Scanner(System.in);
	private static  ArrayList<GameType> GamesInRoom= new ArrayList<>();
	private static  ArrayList<Post> posts= new ArrayList<>();
	
	private static  ArrayList<Player> players= new ArrayList<>();
	private static  ArrayList<Player> waitaingplayers= new ArrayList<>();
	
	private static PriceSchedule[] scheduleprices=PriceSchedule.values();
	
	private static  Map<String,Double> MonthsAmounts= new HashMap<String, Double>();
	private static double dayAmount=0;
	private static boolean firstPlayer=true;
	private static  ArrayList<Screen> screens= new ArrayList<>();
	
	
	//add Month if not exist else add dayAmount
	public static void monthAmount(double amount){
		
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");  
	     LocalDateTime now = LocalDateTime.now();  
	     String cMonth =dtf.format(now);
		 boolean find=false;
		 
		if(MonthsAmounts.size()>0) {
			for (Map.Entry<String, Double> entry : MonthsAmounts.entrySet()) {
			   
			    if( entry.getKey().equals(cMonth)) {
			    	MonthsAmounts.replace(entry.getKey(),entry.getValue()+amount);
			    	find=true;
			    }
			}
			
			if(find == false) {
				MonthsAmounts.put(cMonth,amount);
			}
			
		}
		else {
			MonthsAmounts.put(cMonth,amount);
		}
	}
	
	
	//get Month amount 
	public static double getMonthAmount() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM");  
		   LocalDateTime now = LocalDateTime.now();  
		   String cMonth =dtf.format(now);
		   double mAmount =0;
		   if(MonthsAmounts.size()>0) {
				for (Map.Entry<String, Double> entry : MonthsAmounts.entrySet()) {				   
				    if( entry.getKey().equals(cMonth)) {
				    	mAmount=entry.getValue();
				    }
				}				
			}
		   return mAmount;			
	}
	
	
	
	//init gameType
	public static void initGamesType(){
		GameType gameType1=new GameType("Football");
		gameType1.addGame(new Game("Fifa"));
		gameType1.addGame(new Game("PES"));
		
		GameType gameType2=new GameType("Guerre");
		gameType2.addGame(new Game(" Counter-Strike"));
		gameType2.addGame(new Game("Assassin's Creed"));
				
		GamesInRoom.add(gameType1);
		GamesInRoom.add(gameType2);
	}
	
	
	
	//init posts
	public static void initPosts(){
		Post post1=new Post(1,PostEnum.NINTENDO_SWITCH);
		post1.addGameType(GamesInRoom.get(0));
		post1.addGameType(GamesInRoom.get(1));
		Post post2=new Post(2,PostEnum.XBOX);
		post2.addGameType(GamesInRoom.get(0));
		Post post3=new Post(3,PostEnum.XBOX);
		post3.addGameType(GamesInRoom.get(0));
		Post post4=new Post(4,PostEnum.XBOX);
		post4.addGameType(GamesInRoom.get(0));
		post4.addGameType(GamesInRoom.get(1));
		Post post5=new Post(5,PostEnum.NINTENDO_SWITCH);
		post5.addGameType(GamesInRoom.get(0));
		Post post6=new Post(6,PostEnum.PlayStation5);
		post6.addGameType(GamesInRoom.get(0));
		Post post7=new Post(7,PostEnum.PlayStation5);
		post7.addGameType(GamesInRoom.get(0));
		post6.addGameType(GamesInRoom.get(1));			
		posts.add(post7);
		posts.add(post6);
		posts.add(post5);
		posts.add(post4);
		posts.add(post3);
		posts.add(post2);
		posts.add(post1);
	}
	
	
	//init screen
	public static void initScreens(){
		for(ScreenEnum sc:ScreenEnum.values()) {
			screens.add(new Screen(sc));
		}		
	}
	
	//get all screen
	public static void getAllScreens() {
		for(int i=0;i<screens.size();i++) {
			System.out.println((i+1)+"-"+screens.get(i).getscreen().getScreenBrand());
		}
	}
	
	
	//get all screen
	public static void getAvailableScreens() {
		for(int i=0;i<screens.size();i++) {
			if(screens.get(i).isAvailablity())
			System.out.println((i+1)+"-"+screens.get(i).getscreen().getScreenBrand());
		}
	}
	
	//verify player time end
	public static void playerEndTime() {
		Timer timer = new Timer();
        int begin = 0;
        int timeInterval = 1000;
        timer.schedule(new TimerTask() {
           @Override
           public void run() {
        	   if(players.size() >= 1) {
        		   for(int k=0;k<players.size();k++) {
        				 if(players.get(k).getFinishTime().equals(getCurrentTime())) {
        					 System.out.println("\n"+players.get(k).getFinishTime()+" time finshed for  player in post "+players.get(k).getpostNum());
        					 boolean playerIsWaiting=moveToPlayersList(players.get(k).getpostNum(),k);
        					 
        					 if(playerIsWaiting) {
        						 System.out.println("\nplayer: "+players.get(k).getPlayerCode()+" go to post: "+players.get(k).getpostNum()+"to play ");
        						 if(k>0) {
        							 k--;
        						 }
        						 else {
        							 k=0;
        						 }
        					 }
        					 else {
        						 System.out.println("\tat: "+players.get(k).getFinishTime()+" no player waiting for post "+players.get(k).getpostNum());  
        						 posts.get(players.get(k).getpostNum()-1).changeAvaiblity();
        						 screens.get(players.get(k).getpostNum()-1).changeScreenAvaiblity();
        						 //players.remove(player);
        						if(players.contains(players.get(k))) {
        							 players.remove(players.get(k));
        						}
        					 }           					
        				 }
        				 if(players.size() ==0) {
        					 break;
        				 }
        			}
        	   }
             
           }
        }, begin, timeInterval);
	}
	
	//get player from waiting list  and add it to player list
	public static boolean moveToPlayersList(int NumPost,int playerNum) {
		boolean playerIsWaiting=false;
		if(waitaingplayers.size()>0) {
			for(Player player:waitaingplayers) {
				if(player.getpostNum()== NumPost &&  player.getStartTime() == players.get(playerNum).getFinishTime() ) {
					players.set(playerNum, player);
					dayAmount+=player.getGameTime().getPrice();
					monthAmount(player.getGameTime().getPrice());
					waitaingplayers.remove(player);
					playerIsWaiting=true;
					break;
				}		  
			}	
		}
		return playerIsWaiting;
	}
	//get Day amount
	public static double getDayAmount() {
		return dayAmount;
	}
	
	//show schedule with prices
	public static void showSchedule() {
		for(int i=0;i<scheduleprices.length; i++) {
			System.out.println("\t"+(i+1)+"- "+scheduleprices[i].getLabel()+" : "+scheduleprices[i].getPrice()+" DH");
		}
	}
	
	//get current date 

	public static String getCurrentTime() {
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");  
		   LocalDateTime now = LocalDateTime.now();  
		   return dtf.format(now);
	}
	
	//add game Available in room
	public void addGameToType(GameType gameType,Game game) {
		int indexType=GamesInRoom.indexOf(gameType);
		GamesInRoom.get(indexType).addGame(game);		
	}
		
		
	
	//add game Type in room
	public static void addGameType(GameType gameType) {
		GamesInRoom.add(gameType);			
	}
		
	
		
	//show games with type
	public static void showGamesInRoom() {
		int i=1;
		for(GameType gmtype:GamesInRoom) {
			System.out.println(i+"-"+gmtype);
			System.out.println("\n");
			i++;
		}
		if(GamesInRoom.isEmpty()) {
			System.out.println("no game available");
		}
	}
		
	//get game in room
	public static ArrayList<GameType> getGames() {
		return GamesInRoom;
	}
	
	//get players in posts
		public static ArrayList<Player> getplayers() {
			return players;
		}
		
	//get waiting players 
	public static ArrayList<Player> getwaitingplayers() {
		return waitaingplayers;
	}
	
	//get sh price
		public static  PriceSchedule[] getSchedules() {
			return scheduleprices;
		}
	
	//show games in type
	public static void showGamesInType(int index) {
		int i=1;
		for(Game game:GamesInRoom.get(index).getgames()) {
			System.out.println(i+"-"+game.getGameName());
			System.out.println("\n");
			i++;
		}
		if(GamesInRoom.isEmpty()) {
			System.out.println("no game available");
		}
	}
		
	
	//show available post 
	public static void showAvailablePosts() {
		for(Post post:posts) {
			if(post.verifyAvailablity()== true)
				System.out.println(post);
		}
		
	}
		
	//change  post availability
		public static void changePostAvailability(int postNum) {
			for(Post post:posts) {
				if(post.getPostNumber()==postNum) {
						post.changeAvaiblity();
						break;
				}
			}			
		}
	
	//get player info
	public static Player getPlayerInfo(int postNumber,PriceSchedule gameTime,Game g,String startTime) {
		System.out.println("Choose Screen Number");
		getAvailableScreens();
		System.out.println("your Choose ??");
		int screenP=HelpFunction.getUserChoose(screens.size());	
		Screen chooseScreen =screens.get(screenP-1);
		screens.get(screenP-1).changeScreenAvaiblity();
		System.out.println("enter player First name");
		String fName=scan.nextLine();
		System.out.println("enter player Last name");
		String lName=scan.nextLine();
		String finishTime=FinishTime(startTime,gameTime);	
		Player p=new Player(fName,lName,postNumber,chooseScreen,startTime,finishTime,gameTime,g);
		
		if(firstPlayer) {	
			p.setReduction(Reduction.FirstPlayer.getValue());
			firstPlayer=false;
		} 
		
		if(g.getGameName().equals("Fifa") && gameTime.getValue()>=120) {
			p.setReduction(Reduction.FIFA.getValue());
		}
		
		if(gameTime.getValue()>300 && chooseScreen.getscreen().getScreenBrand().equals("SAMSUNG") && getPostByNum(postNumber).getPostBrand() == PostEnum.PlayStation5) {
			p.setReduction(Reduction.PS5_SAMSUNG.getValue());
		}
		
		changePostAvailability(postNumber);
		System.out.println("\n add successfully "+p); 
		System.out.println("\n***player must pay :"+(gameTime.getPrice()-(gameTime.getPrice() * p.getReduction()))+" DH ***"); 
		dayAmount+=gameTime.getPrice()-(gameTime.getPrice() * p.getReduction());
		monthAmount(gameTime.getPrice());
		return p;
	}
	
	
	
	//get player Game choose
	public static void getPlayerGameChoose() {
		showGamesInRoom();//show game in room	
		
		System.out.println("Enter Game Category Number");
		int gameCategory=HelpFunction.getUserChoose(GamesInRoom.size());
		
		showGamesInType(gameCategory-1);//show Games in choose Category 
		
		System.out.println("Enter Game Number");
		int game=HelpFunction.getUserChoose(GamesInRoom.get(gameCategory-1).getgames().size());
		
		System.out.println("How mush time do you want play");
		showSchedule();//show schedule
		
		System.out.println("\nwhat did you choose");
		int schd=HelpFunction.getUserChoose(5);
		
		Game searchG=ManageRoom.getGames().get(gameCategory-1).getgames().get(game-1);
		
		boolean available =searchGameInAvailablePost(searchG);
		
		PriceSchedule scdP=ManageRoom.getSchedules()[schd-1];
		if(available==true) {
			System.out.println("choisir un post");
			int post=HelpFunction.getUserChoose(posts.size());
			addPlayer(post,scdP,searchG);			
		}
		else if(available == false && getplayers().size()<7) {
			System.out.println("the choose game is not in available posts do you want to want to choose an other game or you want wait enter y to wait or any press any button to change Game ");			
			//take choose to continue
			String continueWithCoose=scan.nextLine();
			if(continueWithCoose.equals("y") || continueWithCoose.equals("Y")) {
					addWaitingPlayer(scdP,searchG);
			}
			else {
				System.out.println(continueWithCoose);
				getPlayerGameChoose();
			}
			
		}
		else {
			if(ManageRoom.getwaitingplayers().size()<10) {
				addWaitingPlayer(scdP,searchG);
			}
			else {
				System.out.println("you can not play ,all posts are reserved sorry");
			}
		}
		
	}
	
	
	
	//assign player to post		
	public static void addPlayer(int postNumber,PriceSchedule scdP,Game g) {
		String startTime=getCurrentTime();
		players.add(getPlayerInfo(postNumber,scdP,g,startTime));		
	}
		
	
	
	
	//add player to waiting list
	public static void addWaitingPlayer(PriceSchedule gameTime,Game g ) {
		if(ManageRoom.getwaitingplayers().size()<10) {			
			System.out.println("you gonna wait ");
			searchGame(g);
			System.out.println("choose a post");
			int post=HelpFunction.getUserChoose(100);
		    String startTime=getPostFinishTimeByNum(post);
			waitaingplayers.add(getPlayerInfo(post,gameTime,g,startTime));	
		}
		else {
			System.out.println("you can not play ,all posts are reserved sorry");
		}	
	}
	
	
	
	
    //show players 
	public static void showPlayers() {
		for(Player player:players) {
		  System.out.println(player);			  
		}
		if (players.isEmpty()){
			System.out.println("\tyou don't have any player for the moment");
		}
	}
	
	
	
	
   //show waiting players 
	public static void showWaitingPlayers() {
		for(Player player:waitaingplayers) {
		  System.out.println(player);			  
		}
		if (waitaingplayers.isEmpty()){
			System.out.println("\tyou don't have any waiting player for the moment");
		}
	}
	
	//search game in posts 
	public static void searchGame(Game game) {
		for(Post post:posts) {
			if(post.verifyGameAvailablity(game)) {
				if(post.verifyAvailablity()) {
					System.out.println("post :"+post.getPostNumber()+": "+post.getPostName()+" is available");
				}
				else {
					System.out.println("post :"+post.getPostNumber()+": "+post.getPostName()+" will be available at: "+getPostFinishTimeByNum(post.getPostNumber()));
				}
				
			}
		}
	}
	
	
	//search game in available post
		public static boolean searchGameInAvailablePost(Game game) {
			boolean available = false;
			for(Post post:posts) {
				if(post.verifyGameAvailablity(game) && post.verifyAvailablity()==true) {
					System.out.println("post :"+post.getPostNumber()+" "+post.getPostName());
					available=true;
				}
			}
			return available;
		}
		
		//get post by num
		public static Post getPostByNum(int Num) {
			for(Post post:posts) {
				if(post.getPostNumber()==Num) {
					return post;
				}		  
			}
			return new Post();
		}
		
		
		//get post finish time  from player by Numb
		public static String getPostFinishTimeByNum(int Num) {
			for(Player player:players) {
				if(player.getpostNum()== Num) {
					return player.getFinishTime();
				}		  
			}
			return getCurrentTime();
		}
		
		
		//show posts
		public static void showPosts() {
			for(Post post:posts) {
			  System.out.println(post);			  
			}
		}
		
		//Calculate finish date
		
		public static String FinishTime(String startTime,PriceSchedule shd) {
			String[] times = startTime.split(":");
			int hour=Integer.parseInt(times[0]);
			int minites=Integer.parseInt(times[1]);
			int ChooseTime=shd.getValue();
			if(ChooseTime/60>=1) {
				hour +=ChooseTime/60;
				ChooseTime=ChooseTime % 60;
			}
			
			minites +=ChooseTime;
			if(minites>=60) {
				hour++;
				minites -=60;
			}
			if(minites<10) {
				return hour+":0"+minites;
			}
			return hour+":"+minites;			
			
		}
	
		
		//sort post by finish Time DESC
		public static void SortPostByFinishTime() {
			if(players.size()>0) {
				HashMap<Integer, Integer> postFTime = new LinkedHashMap<Integer, Integer>();
				String currentTime=getCurrentTime();
				int currentTimeInMinutes=ConvertToMinutes(currentTime);
				int i=0;
				for(Player player:players) {
					String finishPTime= player.getFinishTime();
					postFTime.put(i, ConvertToMinutes(finishPTime)-currentTimeInMinutes);
					i++;
				}
				 Map<Integer, Integer> hm1=sortByValue(postFTime);
				
				 for (Map.Entry<Integer, Integer> en : hm1.entrySet()) {
			           // System.out.println("Key = " + en.getKey() +", Value = " + en.getValue());
			            Player player=players.get(en.getKey());
			            System.out.println("post: "+player.getpostNum()+" gonna be available at "+player.getFinishTime());		            
			        }
			 }
			else {
				System.out.println("******** all posts are available ********");
			}
			 //showPlayers();
		}
		
		
		
		//sort with values
		 public static HashMap<Integer, Integer> sortByValue(HashMap<Integer, Integer> hm)
		    {
		        // Create a list from elements of HashMap
		        List<Map.Entry<Integer, Integer> > list =
		               new LinkedList<Map.Entry<Integer, Integer> >(hm.entrySet());
		 
		        // Sort the list
		        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer> >() {
		            public int compare(Map.Entry<Integer, Integer> o1,
		                               Map.Entry<Integer, Integer> o2)
		            {
		                return (o1.getValue()).compareTo(o2.getValue());
		            }
		        });
		         
		        // put data from sorted list to hashmap
		        HashMap<Integer, Integer> temp = new LinkedHashMap<Integer, Integer>();
		        for (Map.Entry<Integer, Integer> aa : list) {
		            temp.put(aa.getKey(), aa.getValue());
		        }
		        return temp;
		    }
		
		 
		//convertTime to minutes
		public static int ConvertToMinutes(String Time) {
			String[] times = Time.split(":");
			int hour=Integer.parseInt(times[0]);
			int minites=Integer.parseInt(times[1]);
			int TimeInMinutes= hour * 60+ minites;
			return TimeInMinutes;
		}

}
