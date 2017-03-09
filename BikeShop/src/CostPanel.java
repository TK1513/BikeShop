import javax.swing.*;
import java.awt.*;


@SuppressWarnings("serial")
public class CostPanel extends JPanel{
	private JLabel totalCost, payCost, subBikeCost, buyCost, subItemCost, buyField, totalField, payField, subBikeField, subItemField;
	private JButton calcButton, exitButton;
	
	public CostPanel()
	{
		subBikeCost = new JLabel("Bike Rental:  ");
		subItemCost = new JLabel("Item Rental:  ");
		payCost = new JLabel("Down Payment:  ");
		buyCost = new JLabel("Buy:  ");
		totalCost = new JLabel("Total:  ");
		
		subBikeCost.setAlignmentX(LEFT_ALIGNMENT);
		subItemCost.setAlignmentX(LEFT_ALIGNMENT);
		payCost.setAlignmentX(LEFT_ALIGNMENT);
		totalCost.setAlignmentX(LEFT_ALIGNMENT);
		buyCost.setAlignmentX(LEFT_ALIGNMENT);
		
		totalField = new JLabel("");
		payField = new JLabel("");
		subBikeField = new JLabel("");
		buyField = new JLabel("");
		subItemField = new JLabel("");
		
		totalField.setAlignmentX(RIGHT_ALIGNMENT);
		payField.setAlignmentX(RIGHT_ALIGNMENT);
		subBikeField.setAlignmentX(RIGHT_ALIGNMENT);
		subItemField.setAlignmentX(RIGHT_ALIGNMENT);
		buyField.setAlignmentX(RIGHT_ALIGNMENT);
		
		Box costBox = Box.createHorizontalBox();
		costBox.setBorder(BorderFactory.createBevelBorder(1));
		costBox.add(subBikeCost);
		costBox.add(subBikeField);
		costBox.add(subItemCost);
		costBox.add(subItemField);
		costBox.add(payCost);
		costBox.add(payField);
		costBox.add(buyCost);
		costBox.add(buyField);
		costBox.add(totalCost);
		costBox.add(totalField);		
	
		calcButton = new JButton("Calculate");
		exitButton = new JButton("Exit");
		
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(calcButton);
		buttonBox.add(Box.createRigidArea(new Dimension(5,0)));
		buttonBox.add(exitButton);
		buttonBox.add(Box.createRigidArea(new Dimension(5,0)));
		
		this.setLayout(new BorderLayout());
		this.add(costBox, BorderLayout.WEST);
		this.add(buttonBox, BorderLayout.EAST);
	}

	public JLabel getSubItemField() {
		return subItemField;
	}

	public void setSubItemField(JLabel subItemField) {
		this.subItemField = subItemField;
	}

	public JLabel getBuyField() {
		return buyField;
	}

	public void setBuyField(JLabel buyField) {
		this.buyField = buyField;
	}

	public JLabel getTotalField() {
		return totalField;
	}

	public void setTotalField(JLabel totalField) {
		this.totalField = totalField;
	}

	public JLabel getPayField() {
		return payField;
	}

	public void setPayField(JLabel taxField) {
		this.payField = taxField;
	}

	public JLabel getSubBikeField() {
		return subBikeField;
	}

	public void setSubBikeField(JLabel subField) {
		this.subBikeField = subField;
	}
	public JButton getCalcButton() {
		return calcButton;
	}

	public void setCalcButton(JButton calcButton) {
		this.calcButton = calcButton;
	}

	public JButton getExitButton() {
		return exitButton;
	}

	public void setExitButton(JButton exitButton) {
		this.exitButton = exitButton;
	}
}
