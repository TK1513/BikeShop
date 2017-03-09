import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;


@SuppressWarnings("serial")
public class TransactionPanel extends JPanel {
	
	private JLabel name;
	private JLabel age;
	private JRadioButton male;
	private JRadioButton female;
	private JLabel address;
	private JLabel phoneNumber;
	private ButtonGroup bg;
	private JTextField nameField;
	private JTextField ageField;
	private JTextField addressField;
	private JTextField phoneField;
	private JButton transactionAdder;
	
	public TransactionPanel()
	{
		setLayout(new GridLayout(6,1));
		setBorder(BorderFactory.createTitledBorder("Transaction"));
		
		age = new JLabel("Customer's age:  ");
		name = new JLabel("Customer's name:  ");
		bg = new ButtonGroup();
		male = new JRadioButton("Male",true);
		female = new JRadioButton("Female");
		bg.add(male);
		bg.add(female);
		address = new JLabel("Customer's address:  ");
		phoneNumber = new JLabel("Customer's phone:   ");
		transactionAdder = new JButton("Add to File");
		
		phoneField = new JTextField("123456789",10);
		IntegerDocumentFilter phoneFilter = new IntegerDocumentFilter((AbstractDocument) phoneField.getDocument());
		phoneFilter.addTo(phoneField);
		
		phoneField.setToolTipText("Just Type in your number without dashes.");
		
		ageField = new JTextField("000",3);
		IntegerDocumentFilter ageFilter = new IntegerDocumentFilter((AbstractDocument) ageField.getDocument());
		ageFilter.addTo(ageField);
		
		nameField = new JTextField("John Doe");
		addressField = new JTextField("123 Address Lane");
		
		add(age);
		add(ageField);
		add(name);
		add(nameField);
		add(male);
		add(female);
		add(address);
		add(addressField);
		add(phoneNumber);
		add(phoneField);
		add(transactionAdder);
	}
	public JRadioButton getMale() {
		return male;
	}

	public void setMale(JRadioButton male) {
		this.male = male;
	}

	public JRadioButton getFemale() {
		return female;
	}

	public void setFemale(JRadioButton female) {
		this.female = female;
	}

	public JLabel getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(JLabel phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public JTextField getNameField() {
		return nameField;
	}

	public void setNameField(JTextField nameField) {
		this.nameField = nameField;
	}

	public JTextField getAgeField() {
		return ageField;
	}

	public void setAgeField(JTextField ageField) {
		this.ageField = ageField;
	}

	public JTextField getAddressField() {
		return addressField;
	}

	public void setAddressField(JTextField addressField) {
		this.addressField = addressField;
	}

	public JTextField getPhoneField() {
		return phoneField;
	}

	public void setPhoneField(JTextField phoneField) {
		this.phoneField = phoneField;
	}

	public JButton getTransactionAdder() {
		return transactionAdder;
	}

	public void setTransactionAdder(JButton transactionAdder) {
		this.transactionAdder = transactionAdder;
	}
}
