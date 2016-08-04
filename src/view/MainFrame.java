package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 2755100181976292843L;
	private Dimension preferred = Toolkit.getDefaultToolkit().getScreenSize();
	private Dimension frameDim = new Dimension(preferred.width - preferred.width/10, preferred.height - preferred.height/10);
	private OptionPanel panel;
	private ResultBox resultTextArea;
	private JScrollPane pane;
	
	public MainFrame(){
		this.setSize(frameDim);
		Point screenCenter = new Point((int)(preferred.getWidth()/2 - this.getWidth()/2), (int)(preferred.getHeight()/2 - this.getHeight()/2));
		this.setLocation(screenCenter);
		
		panel = new OptionPanel();
		panel.setTextListener(new StringListener(){

			public void printText(String s) {
				if(resultTextArea != null){
					resultTextArea.setText(s);
				}
			}
			
		});
		this.add(panel, BorderLayout.WEST);
		resultTextArea = new ResultBox();
		pane = new JScrollPane(resultTextArea);
		this.add(pane, BorderLayout.CENTER);
	}
}
