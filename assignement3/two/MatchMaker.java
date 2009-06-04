import java.io.*;
import java.lang.*;
import java.util.*;

class MatchMaker{
	private ArrayList<String> words = new ArrayList<String>();
	private ArrayList<String> numbers = new ArrayList<String>();
	private HashMap<String, String> numberRepresentation = new HashMap<String, String>(); 
	private static final String[] combos = {"e","jnq", "rwx", "dsy", "ft", 
		"am", "civ", "bku", "lop", "ghz"};
	private ArrayList<String> matchingWords = new ArrayList<String>();

	public MatchMaker(){
		readFromFile();
	}

	public void readFromFile(){
		String str = "";
		String str2 = "";
		try{
			BufferedReader br = new BufferedReader(new FileReader("newdict.txt"));
			BufferedReader br2 = new BufferedReader(new FileReader("numbers.txt"));
			while ((str = br.readLine()) != null){
				words.add(str.toLowerCase());
				numberRepresentation.put(str.toLowerCase(), wordsToNumbers(str.toLowerCase()));
			}


			while ((str2 = br2.readLine()) != null){
				try{
					numbers.add(str2);
				}
				catch(NumberFormatException nbe){
					System.out.println("Wrong");
				}
			}
		}
		catch(FileNotFoundException fileNotFoundException){
			System.out.println("File not found");
		}
		catch(IOException e){
			System.out.println(e);
		}



		for(int i = 0; i < numbers.size(); i++){
			String number = numbers.get(i).trim();
			translateNumber(number, "", 0);
			printMatches(number);
		}
	}

	public String wordsToNumbers(String word){
		String s = "";
		for(int i = 0; i < word.length(); i++){
			for(int y = 0; y < combos.length; y++){
				if(combos[y].contains(String.valueOf(word.charAt(i)))){
					s += y;
				}
			}
		}
		return s;
	}


	public void translateNumber(String number, String translation, int index) {
		if (number.length() == 0) {
			matchingWords.add(translation);
		}
		String firstpart = "";
		String restpart = "";
		while (index < number.length()) {
			firstpart = number.substring(0, ++index);
			for (String match : numberRepresentation.keySet()) {
				if (firstpart.equals(numberRepresentation.get(match))) {
					restpart = number.replaceFirst(firstpart, "");
					translateNumber(restpart, translation+match+" ", 0);
				}
			}
		}
	}

	public void printMatches(String number){
		if(matchingWords.size() > 0){
			for(int i = 0; i < matchingWords.size(); i++){
				System.out.print(number+": "+matchingWords.get(i)+"\n");
			}
		}
		matchingWords.clear();
	}

	public static void main(String[] args){
		MatchMaker m = new MatchMaker();
	}
}