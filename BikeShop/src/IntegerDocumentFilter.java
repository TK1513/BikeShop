import java.awt.Toolkit;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.JTextComponent;

//class made to filter out the text fields to only allow integer inputs, 
//while looking for methods on how to fix the user input to only do as stated above,
//the recommendations stated document filters are the best way
public class IntegerDocumentFilter extends DocumentFilter {
	private AbstractDocument abstractDocument;

	public IntegerDocumentFilter(AbstractDocument abstractDocument)
	{
		this.abstractDocument = abstractDocument;
	}

	@Override
	public void replace(FilterBypass filterBypass, int offset,int length, String input, AttributeSet attributeSet)
			throws BadLocationException
	{
		int inputLength = input.length();

		String text = abstractDocument.getText(0, abstractDocument.getLength());
		int newLength = text.length() + inputLength;

		if (isNumeric(input) && newLength <= 10)
		{
			super.replace(filterBypass, offset, length, input, attributeSet);
		} else{
			Toolkit.getDefaultToolkit().beep();
		}
	}

	private boolean isNumeric(String input){
		String regularExpression = "[0-9]+";
		return input.matches(regularExpression);
	}

	public void addTo(JTextComponent textComponent){
		AbstractDocument abstractDocument = (AbstractDocument) textComponent.getDocument();
	    IntegerDocumentFilter integerDocumentFilter = new IntegerDocumentFilter(abstractDocument);
	    abstractDocument.setDocumentFilter(integerDocumentFilter);
	}
}
