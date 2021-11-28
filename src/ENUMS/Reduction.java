package ENUMS;

public enum Reduction {
	
	FirstPlayer("2%",0.02),
	FIFA("5%",0.05),
	PS5_SAMSUNG("10%",0.1);
	
	private String reduction;
	private double value;
	
	private Reduction(String reduction, double value) {
		this.reduction = reduction;
		this.value = value;	
	}
	
	
	public String getReduction() {
		return this.reduction;
	}

	public double getValue() {
		return this.value;
	}
}
