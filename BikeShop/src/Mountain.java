import java.util.ArrayList;

public class Mountain extends Bike {
	private ArrayList <Mountain> mountainHolder = new ArrayList<Mountain>();
	
	public Mountain(String bikeNum, String make, String model) {
		super(bikeNum, make, model);
	}

	public Mountain() {}

	public ArrayList <Mountain> getMountainHolder() {
		return mountainHolder;
	}

	public void setMountainHolder(ArrayList <Mountain> mountainHolder) {
		this.mountainHolder = mountainHolder;
	}

}
