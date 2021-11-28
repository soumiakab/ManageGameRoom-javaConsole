package Classes;

import ENUMS.ScreenEnum;

public class Screen {


	private boolean available=true;
	//private  Screen screen;
	private  ScreenEnum screenBrand;
	
	public Screen(ScreenEnum screen) {
		this.screenBrand=screen;
		
	}
	
	//get screen brand 
	public ScreenEnum getscreen() {
		return this.screenBrand;
	}
	//verify screen availability
	public boolean isAvailablity() {
		return available;
	}
	
	//change screen Availability
	public void  changeScreenAvaiblity() {
		this.available=!this.available;
	}
}
