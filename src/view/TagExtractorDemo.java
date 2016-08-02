package view;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TagExtractorDemo {
	public static void main(String[]args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				MainFrame frame = new MainFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("Tag Extractor beta");
				frame.setVisible(true);
			}
		});
	}
}
