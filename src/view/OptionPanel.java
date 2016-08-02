package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tagMechanism.TagExtractor;

public class OptionPanel extends JPanel {

	private static final long serialVersionUID = 4714933485302002099L;
	private JTextField protocolField;
	private JTextField hostField;
	private JTextField portField;
	private JTextField fileField;
	private JTextField tagField;
	private StringListener listener;
	private JLabel tagResult;
	private JLabel urlResult;
	private JCheckBox valuesOnly;
	
	public OptionPanel(){
		
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createDashedBorder(null));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		Insets labelInsets = new Insets(10,0,0,5);
		gbc.insets = labelInsets;
		
		JLabel exampleInput = new JLabel("protocol://hostname.hn:portNumber/fileName");
		gbc.weightx = 2;
		this.add(exampleInput, gbc);
		
		gbc.gridy += 2;
		gbc.weightx = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_END;
		JLabel protocolLabel = new JLabel("protocol name:");
		protocolField = new JTextField(15);
		gbc.gridx = 0;
		this.add(protocolLabel, gbc);
		gbc.gridx++;
		this.add(protocolField, gbc);
		gbc.gridy++;
		
		
		JLabel hostLabel = new JLabel("host name:");
		hostField = new JTextField(15);
		gbc.gridx = 0;
		this.add(hostLabel, gbc);
		gbc.gridx++;
		this.add(hostField, gbc);
		gbc.gridy++;
		
		gbc.gridx = 0;
		JLabel fileName = new JLabel("file name:");
		this.add(fileName, gbc);
		gbc.gridx = 1;
		fileField = new JTextField(15);
		this.add(fileField, gbc);
		gbc.gridy++;
		
		JLabel portLabel = new JLabel("port number:");
		portField = new JTextField(15);
		portLabel.setVisible(false);
		portField.setVisible(false);
		gbc.gridx = 0;
		this.add(portLabel, gbc);
		gbc.gridx++;
		this.add(portField, gbc);
		gbc.gridx = 0;
		gbc.weightx = 2;
		
		JCheckBox portButton = new JCheckBox("I want to include port number");
		this.add(portButton, gbc);
		portButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(portButton.isSelected()){
					portButton.setVisible(false);
					portLabel.setVisible(true);
					portField.setVisible(true);
				}
			}
		});
		gbc.weightx = 0;
		gbc.gridy++;
		
		JLabel tagName = new JLabel("tag to find:");
		this.add(tagName, gbc);
		gbc.gridx++;
		tagField = new JTextField(15);
		this.add(tagField, gbc);
		gbc.gridx = 0;
		gbc.gridy++;
		
		JLabel labelValues = new JLabel("extract only values");
		this.add(labelValues, gbc);
		gbc.gridx++;
		valuesOnly = new JCheckBox();
		this.add(valuesOnly, gbc);
		gbc.gridy++;
		gbc.gridx = 0;
		
		JButton submit = new JButton("Search");
		submit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				URL generated;
				try {
					if(!portField.getText().equals("")){
						generated = new URL(protocolField.getText(), hostField.getText(), Integer.parseInt(portField.getText()), fileField.getText());
					}
					else{
						generated = new URL(protocolField.getText(), hostField.getText(), fileField.getText());
					}
					if(listener != null){
						if(valuesOnly.isSelected()){
							listener.printText(TagExtractor.extractTagValues(generated, tagField.getText()));
						}
						else{
							listener.printText(TagExtractor.extractTagContent(generated, tagField.getText()));
						}
					}
					tagResult.setText("<"+tagField.getText() + ">");
					urlResult.setText(generated.toString());
				}
				catch (NumberFormatException | MalformedURLException e1) {
					JOptionPane.showMessageDialog(((JButton)e.getSource()).getParent(), "Warning data input. Try again! \n" + e1.getMessage(), "Error occured", JOptionPane.ERROR_MESSAGE);
					if(listener != null){
						listener.printText(e1.getMessage());
					}
				}
			}
		});
		this.add(new JLabel(), gbc);
		gbc.gridx++;
		this.add(submit, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		
		JLabel dataSum = new JLabel("Data summary");
		dataSum.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(dataSum, gbc);
		
		gbc.anchor = GridBagConstraints.LINE_END;
		JLabel urlLabel = new JLabel("based on input url is: ");
		gbc.gridy++;
		this.add(urlLabel, gbc);
		gbc.gridx++;
		urlResult = new JLabel();
		this.add(urlResult, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		JLabel tagResultName = new JLabel("tag you want to find:");
		this.add(tagResultName, gbc);
		gbc.gridx++;
		tagResult = new JLabel();
		this.add(tagResult, gbc);
		gbc.gridy++;
		gbc.gridx = 0;
	}
	
	public void setTextListener(StringListener listener){
		this.listener = listener;
	}
}
