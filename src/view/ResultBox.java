package view;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ResultBox extends JTextArea{

	private static final long serialVersionUID = -1952493512845283375L;
	
	JTextArea textArea;
	
	public ResultBox(){
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setMinimumSize(new Dimension(400,400));
		JScrollPane pane = new JScrollPane();
		pane.add(textArea);
		textArea.setEditable(false);
		this.add(pane);
	}
}
