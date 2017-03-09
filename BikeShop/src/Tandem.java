import java.util.ArrayList;

public class Tandem extends Bike {
	
	private ArrayList <Tandem> tandemHolder = new ArrayList<Tandem>();

	public Tandem(String bikeNum, String make, String model) {
		super(bikeNum, make, model);	
	}

	public Tandem() {}

	public ArrayList <Tandem> getTandemHolder() {
		return tandemHolder;
	}

	public void setTandemHolder(ArrayList <Tandem> tandemHolder) {
		this.tandemHolder = tandemHolder;
	}
}
