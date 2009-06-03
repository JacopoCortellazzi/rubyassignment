import java.io.*;
import java.lang.*;
import java.util.*;

class MyMatch{
	boolean numeric = false;
	private String str = null;
	private String str2 = null;
	private String numberToSave;
	private String wordAsText = "";
	private ArrayList<String> words = new ArrayList<String>();
	private ArrayList<String> numbers = new ArrayList<String>();
	private	ArrayList<String> wordsToCheck = new ArrayList<String>();
	private HashMap<String, String> numberRepresentation = new HashMap<String, String>(); 
	private static final String[] combos = {"e","jnq", "rwx", "dsy", "ft", 
		"am", "civ", "bku", "lop", "ghz"};
	ArrayList<String> matchingWords = new ArrayList<String>();

	public MyMatch(){
		readFromFile();
	}

	public void readFromFile(){
		try{
			BufferedReader br = new BufferedReader(new FileReader("dictionary.txt"));
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

		System.out.println(numberRepresentation.keySet());


		for(int i = 0; i < numbers.size(); i++){
			checkNumber(numbers.get(i).trim());
		}
	}

	public String wordsToNumbers(String word){
		String s = "";
		for(int i = 0; i < str.length(); i++){
			for(int y = 0; y < combos.length; y++){
				if(combos[y].contains(String.valueOf(str.charAt(i)))){
					s += y;
				}
			}
		}
		return s;
	}

	public void checkNumber(String number){
		ArrayList<String> tmpWords = new ArrayList<String>();
		ArrayList<String> tmpWords2 = new ArrayList<String>();

		HashMap<String, String> tmpHash = new HashMap<String, String>(); 
		String secondNumber = number;
		matchingWords.clear();
		String tmp = "";
		String newNumber = "";
		int count = 0;
		numberToSave = number; 

		while(!number.equals("")){
			for(String s : numberRepresentation.keySet()){

				number.trim();
				if(number.startsWith(numberRepresentation.get(s))){

					for(String s2 : numberRepresentation.keySet()){
						if(number.startsWith(numberRepresentation.get(s2))){
							if(s2.length() == numberToSave.length() && !tmpWords2.contains(s2))
								tmpWords2.add(s2);
							if(s2.length() < numberToSave.length() && !wordsToCheck.contains(s2)){
								wordsToCheck.add(s2);									
							}
						}
					}					
					tmp = numberRepresentation.get(s);
					int len = (tmp.length())-1;
					Character x = tmp.charAt(len);
					String y = x.toString();
					int inde = number.indexOf(y);

					if(inde+1 != number.length()){
						newNumber = "";
						int l = number.length()-len;						
						for(int ii = 1 ; ii<l; ii++){

							Character newCharacter = number.charAt(inde+ii);							
							newNumber += newCharacter.toString();
						}						
						number = newNumber;						
					}
					else
						number = "";
				}
			}
			count++;
			if(count == numberRepresentation.size())
				number = "";
		}
		matchingWords = tmpWords2;
		
		for(int g = 0; g < wordsToCheck.size(); g++){
			numeric = false;
			checkWords(wordsToCheck.get(g));
		}

		printMatches(numberToSave);
	}


	
	public void checkWords(String word){
		String partOfWord = "";
		String s5 = "";
		String space = " ";
		if(!numeric){
			wordAsText += word;
			for(int o = 0; o < word.length(); o++){
				numeric = true;
				for(int t = 0; t < combos.length; t++){
					if(combos[t].contains(String.valueOf(word.charAt(o)))){
						s5 += t;						
					}
				}
			}
		}
		else
			s5 = word;
		for(int i = 0; i < wordsToCheck.size(); i++){	
			String s3 = "";
			for(int z = 0; z < wordsToCheck.get(i).length(); z++){
				for(int xx = 0; xx < combos.length; xx++){
					if(combos[xx].contains(String.valueOf(wordsToCheck.get(i).charAt(z)))){
						s3 += xx;
					}
				}
			}
			if(numberToSave.startsWith(s5.concat(s3))){
				wordAsText += space += wordsToCheck.get(i);
				
				if(s5.concat(s3).length() == 5){
					System.out.println("Whole: "+s5.concat(s3));
					matchingWords.add(wordAsText);
					
				}
				else{
					partOfWord = s5.concat(s3);
					checkWords(partOfWord);
				}
			}
		}
	}

public void printMatches(String number){
	System.out.print(number+": ");
	if(matchingWords.size() > 0){
		for(int i = 0; i < matchingWords.size(); i++){
			System.out.print(matchingWords.get(i)+" ");
		}
	}
	System.out.println();		
}

public static void main(String[] args){
	MyMatch m = new MyMatch();
}
}