package ENUMS;

public enum ScreenEnum {
	Screen1("DELL","1"),
	Screen2("DELL","2"),
	Screen3("HP","3"),
	Screen4("ASUS","4"),
	Screen5("ASUS","5"),
	Screen6("ASUS","6"),
	Screen7("SAMSUNG","7");

	private String screenBrand;
	private String reference;
	
	private ScreenEnum(String brand,String ref) {
		this.screenBrand = brand;
		this.reference=ref;
	}
	
	
	public String getScreenBrand() {
		return this.screenBrand;
	}
	
	
	public String getReference() {
		return this.reference;
	}
}
