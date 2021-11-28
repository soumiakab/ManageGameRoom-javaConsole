package ENUMS;

public enum PriceSchedule {
	HALF_HOUR("2 min",2,5),
	ONE_HOUR("1 h",60,10),
	TWO_HOUR("2 h",120,18),
	FIVE_HOUR("5 h",300,40),
	LUX("ALL THE DAY",540,65);//9*60
	
	
	private String label;
	private int value;
	private int priceDH;
	
	private PriceSchedule(String label, int value,int priceDH ) {
		this.label = label;
		this.value = value;
		this.priceDH = priceDH;		
	}
	
	
	public String getLabel() {
		return this.label;
	}

	public int getValue() {
		return this.value;
	}
	
	public int getPrice() {
		return this.priceDH;
	}


}
