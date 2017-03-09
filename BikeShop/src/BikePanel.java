import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class BikePanel extends JPanel implements BikeCost{
	// Radio buttons for the available bikes.
	private ArrayList<Bike> currentBikes = new ArrayList <Bike>();
	private JLabel racerLabel;
	private JLabel mountainLabel;
	private JLabel tandemLabel;
	private JLabel lengthLabel;
	private JRadioButton noBike;
	private JTextField numRacer;
	private JTextField numMountain;
	private JTextField numTandem;
	private JTextField numLength;
	

	public BikePanel()
	{
		setLayout(new GridLayout(5,2));
		
		racerLabel = new JLabel("Racer-style bike ($4.50)");
		mountainLabel = new JLabel("Mountain bike ($5.50) ");
		tandemLabel = new JLabel("Tandem bike (2 seats) ($7.50)");
		lengthLabel = new JLabel("Renting Period (in Days)  ");
		
		noBike = new JRadioButton("No bike");
		noBike.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				numRacer.setText("0");
				numMountain.setText("0");
				numTandem.setText("0");
			}
		});
		
		//text fields to hold the amount of bikes in integers with their IntegerDocumentFilterObjects
		numRacer = new JTextField("0");
		IntegerDocumentFilter racerFilter = new IntegerDocumentFilter((AbstractDocument) numRacer.getDocument());
		racerFilter.addTo(numRacer);
						
		numMountain = new JTextField("0");
		IntegerDocumentFilter mountainFilter = new IntegerDocumentFilter((AbstractDocument) numMountain.getDocument());
		mountainFilter.addTo(numMountain);
		
		numTandem = new JTextField("0");
		IntegerDocumentFilter tandemFilter = new IntegerDocumentFilter((AbstractDocument) numTandem.getDocument());
		tandemFilter.addTo(numTandem);
				
		numLength = new JTextField("1");
		IntegerDocumentFilter lengthFilter = new IntegerDocumentFilter ((AbstractDocument) numLength.getDocument());
		lengthFilter.addTo(numLength);
		
		setBorder(BorderFactory.createTitledBorder("Bike"));
		
		add(racerLabel);
		add(numRacer);
		add(mountainLabel);
		add(numMountain);
		add(tandemLabel);
		add(numTandem);
		add(lengthLabel);
		add(numLength);
		add(noBike);
	}
	public double getBikeCost(ArrayList<Bike> bikeHolder)
	{
		double bikeCost = 0.0;
		int racerNum = 0;
		int mountainNum = 0;
		int tandemNum = 0;
		int timeLength = 0;
		
		racerNum = Integer.parseUnsignedInt(numRacer.getText());
		mountainNum = Integer.parseUnsignedInt(numMountain.getText());
		tandemNum = Integer.parseUnsignedInt(numTandem.getText());
		timeLength = Integer.parseUnsignedInt(numLength.getText());
		ArrayList <Racer> racerStyle = new ArrayList<Racer>(0);
		ArrayList <Mountain> mountainStyle = new ArrayList <Mountain>(0);
		ArrayList <Tandem> tandemStyle = new ArrayList <Tandem>(0);
		
		if(racerNum != 0){
			for(int i = 0; i < racerNum; i++){
				int slotRacer = i;
				while(!(bikeHolder.get(slotRacer).getModel().equals("Road"))){
					slotRacer++;
				}
				if(slotRacer <= (bikeHolder.size()-1)){
					racerStyle.add(i, (Racer) bikeHolder.get(slotRacer));
					bikeHolder.remove(slotRacer);
				}
			}
			
		}
		if(mountainNum != 0){
			for(int i = 0; i < mountainNum; i++){
				int slotMountain = i;
				while(!(bikeHolder.get(slotMountain).getModel().equals("Off-Road"))){
					slotMountain++;
				}
				if(slotMountain <= (bikeHolder.size()-1)){
					mountainStyle.add(i, (Mountain) bikeHolder.get(slotMountain));
					bikeHolder.remove(slotMountain);
				}
			}
		}
		if(tandemNum != 0){
			for(int i = 0; i < tandemNum; i++){
				int slotTandem = i;
				while(!(bikeHolder.get(slotTandem).getModel().equals("Tandem"))){
					slotTandem++;
				}	
				if(slotTandem <= (bikeHolder.size()-1)){
					tandemStyle.add(i, (Tandem) bikeHolder.get(slotTandem));
					bikeHolder.remove(slotTandem);
				}
			}
		}
		
		if(!(racerStyle.isEmpty()))
		{	int i =0;
			for(Racer racer: racerStyle){
				currentBikes.add(i, racer);
				i++;
			}
		}
		if(!(mountainStyle.isEmpty())){	
			int i =0;
			for(Mountain offroad: mountainStyle){
				currentBikes.add(i, offroad);
				i++;
			}
		}
		if(!(tandemStyle.isEmpty())){	
			int i =0;
			for(Tandem tandem: tandemStyle){
				currentBikes.add(i, tandem);
				i++;
			}
		}
		
		bikeCost = (((racerNum * RACER) + (mountainNum * MOUNTAIN) + (tandemNum * TANDEM)) * timeLength);
		
		//Return the cost of a certain bike
		return bikeCost;
	}

	public JTextField getNumRacer() {
		return numRacer;
	}

	public void setNumRacer(JTextField numRacer) {
		this.numRacer = numRacer;
	}

	public JTextField getNumMountain() {
		return numMountain;
	}

	public void setNumMountain(JTextField numMountain) {
		this.numMountain = numMountain;
	}

	public JTextField getNumTandem() {
		return numTandem;
	}

	public void setNumTandem(JTextField numTandem) {
		this.numTandem = numTandem;
	}

	public JTextField getNumLength() {
		return numLength;
	}

	public void setNumLength(JTextField numLength) {
		this.numLength = numLength;
	}
	public ArrayList <Bike> getCurrentBikes() {
		return currentBikes;
	}

	public void setCurrentBikes(ArrayList <Bike> currentBikes) {
		this.currentBikes = currentBikes;
	}
	
}
