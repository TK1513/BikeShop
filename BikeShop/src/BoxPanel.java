import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class BoxPanel extends JPanel{
	private JTextArea resultList;
	
	public BoxPanel(){
		resultList = new JTextArea(10,50);
        resultList.setEditable(false);
        JScrollPane scrollArea =
                new JScrollPane(resultList,
                                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setLayout(new BorderLayout());
        add(scrollArea, BorderLayout.CENTER);
	}
	public JTextArea getResultList() {
		return resultList;
	}

	public void setResultList(JTextArea resultList) {
		this.resultList = resultList;
	}
}
