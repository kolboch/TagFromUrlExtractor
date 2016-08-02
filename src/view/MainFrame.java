package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 2755100181976292843L;
	private Dimension preferred = Toolkit.getDefaultToolkit().getScreenSize();
	private Dimension frameDim = new Dimension(preferred.width - preferred.width/10, preferred.height - preferred.height/10);
	private OptionPanel panel;
	private ResultBox resultTextArea;
	public MainFrame(){
		this.setSize(frameDim);
		Point screenCenter = new Point((int)(preferred.getWidth()/2 - this.getWidth()/2), (int)(preferred.getHeight()/2 - this.getHeight()/2));
		this.setLocation(screenCenter);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.weightx = 1;
		gbc.weighty = 1;
		
		panel = new OptionPanel();
		panel.setTextListener(new StringListener(){

			public void printText(String s) {
				if(resultTextArea != null){
					resultTextArea.setText(s);
				}
			}
			
		});
		this.add(panel, gbc);
		gbc.gridx++;
		gbc.weightx = 3;
		resultTextArea = new ResultBox();
		this.add(resultTextArea, gbc);
	}
}
