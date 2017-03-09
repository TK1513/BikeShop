import java.awt.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument;

@SuppressWarnings("serial")
public class RestockPanel extends JPanel implements RestockCost{
	private JLabel helmet;
	private JLabel kneePads;
	private JLabel gloves;
	private JLabel trainingWheels;
	private JLabel basket;
	private JLabel racer;
	private JLabel mountain;
	private JLabel tandem;
	private double buyCost = 0.0;
	private JButton restockButton;
	private JTextField fieldHelmet;
	private JTextField fieldPads;
	private JTextField fieldGloves;
	private JTextField fieldWheels;
	private JTextField fieldBasket;
	private JTextField fieldRacer;
	private JTextField fieldMountain;
	private JTextField fieldTandem;
	
	public RestockPanel(){
		setBorder(BorderFactory.createTitledBorder("Restock"));
		setLayout(new GridLayout(9,1));
		helmet = new JLabel("Helmet");
		kneePads = new JLabel("KneePads");
		gloves = new JLabel("Gloves");
		trainingWheels = new JLabel("Training Wheels");
		basket = new JLabel("Basket");
		racer = new JLabel("Racer-Style Bike");
		mountain = new JLabel("Offroad-Style Bike");
		tandem = new JLabel("Tandem-Style Bike");
		
		fieldHelmet = new JTextField("0");
		IntegerDocumentFilter helmetFilter = new IntegerDocumentFilter((AbstractDocument) fieldHelmet.getDocument());
		helmetFilter.addTo(fieldHelmet);
		
		fieldPads = new JTextField("0");
		IntegerDocumentFilter padsFilter = new IntegerDocumentFilter((AbstractDocument) fieldPads.getDocument());
		padsFilter.addTo(fieldPads);
		
		fieldGloves = new JTextField("0");
		IntegerDocumentFilter glovesFilter = new IntegerDocumentFilter((AbstractDocument) fieldGloves.getDocument());
		glovesFilter.addTo(fieldGloves);
		
		fieldWheels = new JTextField("0");
		IntegerDocumentFilter wheelFilter = new IntegerDocumentFilter((AbstractDocument) fieldWheels.getDocument());
		wheelFilter.addTo(fieldWheels);
		
		fieldBasket = new JTextField("0");
		IntegerDocumentFilter basketFilter = new IntegerDocumentFilter((AbstractDocument) fieldBasket.getDocument());
		basketFilter.addTo(fieldBasket);
		
		fieldRacer = new JTextField("0");
		IntegerDocumentFilter racerFilter = new IntegerDocumentFilter((AbstractDocument) fieldRacer.getDocument());
		racerFilter.addTo(fieldRacer);
		
		fieldMountain = new JTextField("0");
		IntegerDocumentFilter mountainFilter = new IntegerDocumentFilter((AbstractDocument) fieldMountain.getDocument());
		mountainFilter.addTo(fieldMountain);
		
		fieldTandem = new JTextField("0");
		IntegerDocumentFilter tandemFilter = new IntegerDocumentFilter((AbstractDocument) fieldTandem.getDocument());
		tandemFilter.addTo(fieldTandem);
		
		
		restockButton = new JButton("Restock Shop");
		
		add(gloves);
		add(fieldGloves);
		add(helmet);
		add(fieldHelmet);
		add(kneePads);
		add(fieldPads);
		add(basket);
		add(fieldBasket);
		add(trainingWheels);
		add(fieldWheels);
		add(racer);
		add(fieldRacer);
		add(mountain);
		add(fieldMountain);
		add(tandem);
		add(fieldTandem);
		add(restockButton);
	}
	
	public void inventoryAdd(){
		double cost = 0.00;
		int gloveBuyNum = Integer.parseUnsignedInt(fieldGloves.getText());
		int helmBuyNum = Integer.parseUnsignedInt(fieldPads.getText());
		int padBuyNum = Integer.parseUnsignedInt(fieldPads.getText());
		int basketBuyNum = Integer.parseUnsignedInt(fieldBasket.getText());
		int wheelBuyNum = Integer.parseUnsignedInt(fieldWheels.getText());
		int racerBuyNum = Integer.parseUnsignedInt(fieldRacer.getText());
		int mountainBuyNum = Integer.parseUnsignedInt(fieldMountain.getText());
		int tandemBuyNum = Integer.parseUnsignedInt(fieldTandem.getText());
		
		cost = (gloveBuyNum * GLOVES) + (helmBuyNum * HELMET) + (padBuyNum * KNEEPADS) 
				+ (basketBuyNum * BASKET) + (wheelBuyNum * TRAINING_WHEELS) + (racerBuyNum * RACER) 
				+ (mountainBuyNum * MOUNTAIN) + (tandemBuyNum * TANDEM);
		setBuyCost(cost);
	}
	
	public JTextField getFieldHelmet() {
		if(fieldHelmet.getText().equals("")){
			fieldHelmet.setText("0");
		}
		return fieldHelmet;
	}

	public void setFieldHelmet(JTextField fieldHelmet) {
		this.fieldHelmet = fieldHelmet;
	}

	public JTextField getFieldGloves() {
		if(fieldGloves.getText().equals("")){
			fieldGloves.setText("0");
		}
		return fieldGloves;
	}

	public void setFieldGloves(JTextField fieldGloves) {
		this.fieldGloves = fieldGloves;
	}

	public JTextField getFieldPads() {
		if(fieldPads.getText().equals("")){
			fieldPads.setText("0");
		}
		return fieldPads;
	}

	public void setFieldPads(JTextField fieldPads) {
		this.fieldPads = fieldPads;
	}

	public JTextField getFieldWheels() {
		if(fieldWheels.getText().equals("")){
			fieldWheels.setText("0");
		}
		return fieldWheels;
	}

	public void setFieldWheels(JTextField fieldWheels) {
		this.fieldWheels = fieldWheels;
	}

	public JTextField getFieldBasket() {
		if(fieldBasket.getText().equals("")){
			fieldBasket.setText("0");
		}
		return fieldBasket;
	}

	public void setFieldBasket(JTextField fieldBasket) {
		this.fieldBasket = fieldBasket;
	}

	public JTextField getFieldRacer() {
		if(fieldRacer.getText().equals("")){
			fieldRacer.setText("0");
		}
		return fieldRacer;
	}

	public void setFieldRacer(JTextField fieldRacer) {
		this.fieldRacer = fieldRacer;
	}

	public JTextField getFieldMountain() {
		if(fieldMountain.getText().equals("")){
			fieldMountain.setText("0");
		}
		return fieldMountain;
	}

	public void setFieldMountain(JTextField fieldMountain) {
		this.fieldMountain = fieldMountain;
	}

	public JTextField getFieldTandem() {
		if(fieldTandem.getText().equals("")){
			fieldTandem.setText("0");
		}
		return fieldTandem;
	}

	public void setFieldTandem(JTextField fieldTandem) {
		this.fieldTandem = fieldTandem;
	}

	public JButton getRestockButton() {
		return restockButton;
	}

	public void setRestockButton(JButton restockButton) {
		this.restockButton = restockButton;
	}

	public double getBuyCost() {
		return buyCost;
	}

	public void setBuyCost(double buyCost) {
		this.buyCost = buyCost;
	}

}
