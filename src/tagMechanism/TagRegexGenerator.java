package tagMechanism;

public class TagRegexGenerator {
	public static String regexTagOpen(String tagName){
		String resultRegex = "<\\s*" + tagName + "(\\s*.*)*>";
		return resultRegex;
	}
	
	public static String regexTagClose(String tagName){
		String resultRegex = "</" + tagName + ">";
		return resultRegex;
	}
	
	public static String regexTagValues(String tagName){
		String resultRegex = "<\\s*" + tagName + "(\\s*.*)*>";
		return resultRegex;
	}
}
