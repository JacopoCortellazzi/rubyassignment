import java.io.*;
import java.lang.*;
import java.util.*;

class SlinkMatch{
	private String str = null;
	private String str2 = null;
	private ArrayList<String> words = new ArrayList<String>();
	private ArrayList<String> numbers = new ArrayList<String>();
	private HashMap<String, String> numberRepresentation = new HashMap<String, String>(); 
	private static final String[] combos = {"e","jnq", "rwx", "dsy", "ft", 
		"am", "civ", "bku", "lop", "ghz"};
	ArrayList<String> matchingWords = new ArrayList<String>();

	public SlinkMatch(){
		readFromFile();
	}

	public void readFromFile(){
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

		System.out.println(numberRepresentation.keySet());


		for(int i = 0; i < numbers.size(); i++){
			translateNumber(numbers.get(i).trim(), words, 1);
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


	// for loop på nummerlängden? 
	// Specialfall med korta ord; ny loop med rest nummer + full dict
	//

	public void translateNumber(String number, String translation, int index) {
		if (index >= no.length()) {
			//System.err.println("Break!"+dict.toString());
			System.out.println(translation);
		} else {
			for (int i = 0; i < translation.size(); ++i) {
				String word = translation.get(i);
				if ((word.length() > index) && (word.length() <= number.length())) {
					String numString = combos[Integer.parseInt(String.valueOf(number.charAt(index)))];
					//System.err.println("DEBUG: "+word+", index: "+index);
					if (numString.contains((new Character(word.charAt(index))).toString())) {
						mem.add(word);
						if (word.length() == index+1) {
							for (String s : translateNumber(number.substring(index+1), words, 1)) {
								s = word+" "+s;
								System.err.println("Dubbel! "+s);
							}
							return mem;
						}
						System.err.println("Add word to "+mem.toString());
					}
				}
			}
			index++;
			System.err.println("Ny vända! index =="+index);
			//return mem;
			return translateNumber(number, mem, index);
		}
		//System.err.println("END!"+dict.toString()+"iter "+index);
		//return dict;
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
		SlinkMatch m = new SlinkMatch();
	}
}

