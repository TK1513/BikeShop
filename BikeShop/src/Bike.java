public abstract class Bike {
	private String bikeNumber;
	private String make;
	private String model;
	
	public Bike(String bikeNum, String make, String model)
	{
		bikeNumber = bikeNum;
		this.make = make;
		this.model = model;
	}
	public Bike(){}

	public String getBikeNumber() {
		return bikeNumber;
	}

	public void setBikeNumber(String bikeNumber) {
		this.bikeNumber = bikeNumber;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
