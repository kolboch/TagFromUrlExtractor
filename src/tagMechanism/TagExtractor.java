package tagMechanism;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class TagExtractor {
	
	public static String extractTagContent(URL url, String tag){
		StringBuilder sb = new StringBuilder();
		String regexOpen = TagRegexGenerator.regexTagOpen(tag);
		String regexClose = TagRegexGenerator.regexTagClose(tag);
		Pattern p1 = Pattern.compile(regexOpen);
		Pattern p2 = Pattern.compile(regexClose);
		Matcher m1;
		Matcher m2;
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			String nextLine;
			int tagCounter = 0; // tags can be nested
			while((nextLine = in.readLine()) != null){
				m1 = p1.matcher(nextLine);
				if(m1.find()){
					tagCounter++;
					sb.append(nextLine + "\n");
					
					m2 = p2.matcher(nextLine);
					if(m2.find()){
						tagCounter--;
					}
					
					while((nextLine = in.readLine()) != null && tagCounter > 0){
						m1 = p1.matcher(nextLine);
						if(m1.find()){
							tagCounter++;
						}
						m2 = p2.matcher(nextLine);
						if(m2.find()){
							tagCounter--;
						}
						sb.append(nextLine + "\n");
					}
				}
			}
			in.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return sb.toString();
	}
	
	public static String extractTagValues(URL url, String tag){
		StringBuilder sb = new StringBuilder();
		String regex = TagRegexGenerator.regexTagValues(tag);
		Pattern p1 = Pattern.compile(regex);
		Matcher m1;
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			String nextLine;
			while((nextLine = in.readLine()) != null){
				m1 = p1.matcher(nextLine);
				if(m1.find()){
					sb.append(nextLine + "\n");
				}
			}
			in.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return sb.toString();
	}
}
