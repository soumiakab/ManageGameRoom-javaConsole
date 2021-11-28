package ENUMS;

public enum Month {
	JANVIER("01","JANVIER"),
	FEVRIER("02","FEVRIER"),
	MARS("03","MARS"),
	AVRIL("04","AVRIL"),
	MAI("05","MAI"),
	JUIN("06","JUIN"),
	JUILLET("07","JUILLET"),
	AUT("08","AUT"),
	SEPTEMBRE("09","SEPTEMBRE"),
	OCTOBRE("10","OCTOBRE"),
	NOVEMEBR("11","NOVEMEBR"),	
	DECEMBRE("12","DECEMBRE");
	
	
	
	private String month;
	private String value;
	
	private Month(String month, String value) {
		this.month = month;
		this.value = value;	
	}
	
	
	public String getMonth() {
		return this.month;
	}

	public String getMonthValue() {
		return this.value;
	}


}
