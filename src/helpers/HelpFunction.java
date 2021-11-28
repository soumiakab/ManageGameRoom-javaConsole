package helpers;

import java.util.Scanner;

public class HelpFunction {
	static Scanner scan= new Scanner(System.in);
	
	//clear console (50 line empty)
	public static void clearConsole() {
		for (int i = 0; i < 50; ++i) System.out.println();
	}

	
	//write on screen
	public static void show(String sentence) {
		System.out.println(sentence);
	}
	
	
	//verify if integer
	public static boolean integerNumber(String choose,int max) {			
		try {				
				Integer.parseInt(choose);
				if(Integer.parseInt(choose)>max || Integer.parseInt(choose) == 0) {
					return false;
				}
			
				return true;
			
		}
		catch(NumberFormatException e){
		
				return false;
		}				
	}
	
	
	//avoir le choix de l utilisateur
		public static int getUserChoose(int maxValue) {
			String chooseTemp=scan.nextLine();
			while(!integerNumber(chooseTemp,maxValue)) {
	    		System.out.println("Please enter a valid choose");
	    		chooseTemp=scan.nextLine();	    		 	    	
	    	}
	    	return Integer.parseInt(chooseTemp);
				
			
		}
	
	
}
