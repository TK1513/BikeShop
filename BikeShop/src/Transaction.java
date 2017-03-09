public class Transaction {
	private Bike bike;
	private Person person;
	private String time;
	private String date;
	private double cost;
	
	public Transaction(Bike bike, Person person, String time, String date, double cost)
	{
		this.bike = bike;
		this.person = person;
		this.time = time;
		this.date= date;
		this.cost = cost;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
}
