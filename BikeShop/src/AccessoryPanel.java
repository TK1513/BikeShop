import javax.swing.*;
import javax.swing.text.AbstractDocument;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class AccessoryPanel extends JPanel implements AccessoryCost {
	private ArrayList <String> currentItems = new ArrayList <String>();
	private JRadioButton noAccessory;
	private JLabel helmet;
	private JLabel kneePads;
	private JLabel gloves;
	private JLabel trainingWheels;
	private JLabel basket;
	private JLabel buy;
	private JLabel rent;
	private JTextField fieldHelmet;
	private JTextField fieldBuyHelmet;
	private JTextField fieldPads;
	private JTextField fieldBuyPads;
	private JTextField fieldGloves;
	private JTextField fieldBuyGloves;
	private JTextField fieldWheels;
	private JTextField fieldBuyWheels;
	private JTextField fieldBasket;
	private JTextField fieldBuyBasket;
	
	public AccessoryPanel()
	{
		setLayout(new GridLayout(6,2));
		
		noAccessory = new JRadioButton("No Accessory   ");
		noAccessory.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				fieldHelmet.setText("0");
				fieldBuyHelmet.setText("0");
				fieldPads.setText("0");
				fieldBuyPads.setText("0");
				fieldGloves.setText("0");
				fieldBuyGloves.setText("0");
				fieldWheels.setText("0");
				fieldBuyWheels.setText("0");
				fieldBasket.setText("0");
				fieldBuyBasket.setText("0");
			}
		});
		
		buy = new JLabel("        Buy item");
		rent = new JLabel("        Rent item ($0.50)");
		helmet = new JLabel("Helmet ($10.00)");
		kneePads = new JLabel("KneePads ($6.00)");
		gloves = new JLabel("Gloves ($3.00)");
		trainingWheels = new JLabel("Training Wheels ($5.00)");
		basket = new JLabel("Basket ($8.00)");
			
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
		
		fieldBuyHelmet = new JTextField("0");
		IntegerDocumentFilter helmetBuyFilter = new IntegerDocumentFilter((AbstractDocument) fieldBuyHelmet.getDocument());
		helmetBuyFilter.addTo(fieldBuyHelmet);
		
		fieldBuyPads = new JTextField("0");
		IntegerDocumentFilter padsBuyFilter = new IntegerDocumentFilter((AbstractDocument) fieldBuyPads.getDocument());
		padsBuyFilter.addTo(fieldBuyPads);
		
		fieldBuyGloves = new JTextField("0");
		IntegerDocumentFilter glovesBuyFilter = new IntegerDocumentFilter((AbstractDocument) fieldBuyGloves.getDocument());
		glovesBuyFilter.addTo(fieldBuyGloves);
		
		fieldBuyWheels = new JTextField("0");
		IntegerDocumentFilter wheelBuyFilter = new IntegerDocumentFilter((AbstractDocument) fieldBuyWheels.getDocument());
		wheelBuyFilter.addTo(fieldBuyWheels);
		
		fieldBuyBasket = new JTextField("0");
		IntegerDocumentFilter basketBuyFilter = new IntegerDocumentFilter((AbstractDocument) fieldBuyBasket.getDocument());
		basketBuyFilter.addTo(fieldBuyBasket);
			
		setBorder(BorderFactory.createTitledBorder("Accessory"));	
		
		add(noAccessory);
		add(rent);
		add(buy);
		add(gloves);
		add(fieldGloves);
		add(fieldBuyGloves);
		add(helmet);
		add(fieldHelmet);
		add(fieldBuyHelmet);
		add(kneePads);
		add(fieldPads);
		add(fieldBuyPads);
		add(basket);
		add(fieldBasket);
		add(fieldBuyBasket);
		add(trainingWheels);
		add(fieldWheels);
		add(fieldBuyWheels);	
	}
	
	public double getBuyCost(){
		double buyCost = 0.00;
		int gloveBuyNum = 0;
		int helmBuyNum = 0;
		int padBuyNum = 0;
		int basketBuyNum = 0;
		int wheelBuyNum = 0;
		
		// fill in the buy fields with their values
		helmBuyNum = Integer.parseUnsignedInt(fieldBuyHelmet.getText());
		padBuyNum = Integer.parseUnsignedInt(fieldBuyPads.getText());
		gloveBuyNum = Integer.parseUnsignedInt(fieldBuyGloves.getText());
		basketBuyNum = Integer.parseUnsignedInt(fieldBuyBasket.getText());
		wheelBuyNum = Integer.parseUnsignedInt(fieldBuyWheels.getText());
		
		buyCost = (gloveBuyNum * BUY_GLOVES)+ (helmBuyNum * BUY_HELMET) + (padBuyNum * BUY_KNEEPADS) + 
				(basketBuyNum * BUY_BASKET) + (wheelBuyNum * BUY_TRAINING_WHEELS);
		
		//Return the cost of buying accessories
		return buyCost;
		
	}
	public double getItemCost(ArrayList<String> itemHolder)
	{
		double itemCost = 0.0;
		int gloveNum = 0;
		int helmNum = 0;
		int padNum = 0;
		int basketNum = 0;
		int wheelNum = 0;
		
		
		//fill in the rent fields with their values
		gloveNum = Integer.parseUnsignedInt(fieldGloves.getText());
		helmNum = Integer.parseUnsignedInt(fieldHelmet.getText());
		padNum = Integer.parseUnsignedInt(fieldPads.getText());
		basketNum = Integer.parseUnsignedInt(fieldBasket.getText());
		wheelNum = Integer.parseUnsignedInt(fieldWheels.getText());	
		
		if(gloveNum >0)
		{
			for(int i= 0; i < gloveNum; i++)
			{
				int slot =i;
				while(!(itemHolder.get(slot).equals("Gloves"))){
					slot++;
				}
				if(slot <= itemHolder.size()-1){
					currentItems.add(i, itemHolder.get(slot));
					itemHolder.remove(slot);
				}
			}
		}
		if(helmNum > 0)
		{
			for(int i= 0; i < helmNum; i++)
			{
				int slot =i;
				while(!(itemHolder.get(slot).equals("Helmet"))){
					slot++;
				}
				if(slot <= itemHolder.size()-1){
					currentItems.add(i, itemHolder.get(slot));
					itemHolder.remove(slot);
				}
			}
		}
		if(padNum >0)
		{
			for(int i= 0; i < padNum; i++)
			{
				int slot =i;
				while(!(itemHolder.get(slot).equals("Knee Pads"))){
					slot++;
				}
				if(slot <= itemHolder.size()-1){
					currentItems.add(i, itemHolder.get(slot));
					itemHolder.remove(slot);
				}
			}
		}
		if(basketNum >0)
		{
			for(int i= 0; i < basketNum; i++)
			{
				int slot =i;
				while(!(itemHolder.get(slot).equals("Basket"))){
					slot++;
				}
				if(slot <= itemHolder.size()-1){
					currentItems.add(i, itemHolder.get(slot));
					itemHolder.remove(slot);
				}
			}
		}
		if(wheelNum >0)
		{
			for(int i= 0; i < wheelNum; i++)
			{
				int slot =i;
				while(!(itemHolder.get(slot).equals("Training Wheels"))){
					slot++;
				}
				if(slot <= itemHolder.size()-1){
					currentItems.add(i, itemHolder.get(slot));
					itemHolder.remove(slot);
				}
			}
		}
		
		itemCost = (gloveNum * GLOVES) + (helmNum * HELMET) + (padNum * KNEEPADS) + (basketNum * BASKET) + (wheelNum * TRAINING_WHEELS);
		
		//Return the cost of a certain item
		return itemCost;
	}
	
	public JTextField getFieldHelmet() {
		if(fieldHelmet.getText().equals(""))
		{
			fieldHelmet.setText("0");
		}
		return fieldHelmet;
	}

	public void setFieldHelmet(JTextField fieldHelmet) {
		this.fieldHelmet = fieldHelmet;
	}

	public JTextField getFieldBuyHelmet() {
		if(fieldBuyHelmet.getText().equals(""))
		{
			fieldBuyHelmet.setText("0");
		}
		return fieldBuyHelmet;
	}

	public void setFieldBuyHelmet(JTextField fieldBuyHelmet) {
		this.fieldBuyHelmet = fieldBuyHelmet;
	}

	public JTextField getFieldPads() {
		if(fieldPads.getText().equals(""))
		{
			fieldPads.setText("0");
		}
		return fieldPads;
	}

	public void setFieldPads(JTextField fieldPads) {
		this.fieldPads = fieldPads;
	}

	public JTextField getFieldBuyPads() {
		if(fieldBuyPads.getText().equals(""))
		{
			fieldBuyPads.setText("0");
		}
		return fieldBuyPads;
	}

	public void setFieldBuyPads(JTextField fieldBuyPads) {
		this.fieldBuyPads = fieldBuyPads;
	}

	public JTextField getFieldGloves() {
		if(fieldGloves.getText().equals(""))
		{
			fieldGloves.setText("0");
		}
		return fieldGloves;
	}

	public void setFieldGloves(JTextField fieldGloves) {
		this.fieldGloves = fieldGloves;
	}

	public JTextField getFieldBuyGloves() {
		if(fieldBuyGloves.getText().equals(""))
		{
			fieldBuyGloves.setText("0");
		}
		return fieldBuyGloves;
	}

	public void setFieldBuyGloves(JTextField fieldBuyGloves) {
		this.fieldBuyGloves = fieldBuyGloves;
	}

	public JTextField getFieldWheels() {
		if(fieldWheels.getText().equals(""))
		{
			fieldWheels.setText("0");
		}
		return fieldWheels;
	}

	public void setFieldWheels(JTextField fieldWheels) {
		this.fieldWheels = fieldWheels;
	}

	public JTextField getFieldBuyWheels() {
		if(fieldBuyWheels.getText().equals(""))
		{
			fieldBuyWheels.setText("0");
		}
		return fieldBuyWheels;
	}

	public void setFieldBuyWheels(JTextField fieldBuyWheels) {
		this.fieldBuyWheels = fieldBuyWheels;
	}

	public JTextField getFieldBasket() {
		if(fieldBasket.getText().equals(""))
		{
			fieldBasket.setText("0");
		}
		return fieldBasket;
	}

	public void setFieldBasket(JTextField fieldBasket) {
		this.fieldBasket = fieldBasket;
	}

	public JTextField getFieldBuyBasket() {
		if(fieldBuyHelmet.getText().equals(""))
		{
			fieldBuyHelmet.setText("0");
		}
		return fieldBuyBasket;
	}

	public void setFieldBuyBasket(JTextField fieldBuyBasket) {
		this.fieldBuyBasket = fieldBuyBasket;
	}
	public ArrayList <String> getCurrentItems() {
		return currentItems;
	}

	public void setCurrentItems(ArrayList <String> currentItems) {
		this.currentItems = currentItems;
	}


}