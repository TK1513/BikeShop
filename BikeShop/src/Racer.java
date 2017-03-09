import java.util.ArrayList;

public class Racer extends Bike {
	
	private ArrayList <Racer> racerHolder = new ArrayList<Racer>();
	
	public Racer(String bikeNum, String make, String model) {
		super(bikeNum, make, model);
	}
	public Racer() {}
	public ArrayList <Racer> getRacerHolder() {
		return racerHolder;
	}

	public void setRacerHolder(ArrayList <Racer> racerHolder) {
		this.racerHolder = racerHolder;
	}

}
