import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class NoTransactionException extends Exception {
	public NoTransactionException() {
		JOptionPane.showMessageDialog(null, "A transaction needs to occur before updating our records with a new transaction.", 
				"Alert", JOptionPane.ERROR_MESSAGE);
	}
}
