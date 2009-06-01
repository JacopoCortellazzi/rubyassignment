import java.io.*;
import java.util.*;
import java.lang.*;

class AssignmentThree {
    private ArrayList<String> words = new ArrayList<String>();
    private ArrayList<String> numbers = new ArrayList<String>();
    private HashMap<String, String> wordsByNumbers = new HashMap<String, String>();
    private String[] combinations = {"e","jnq","rwx","dsy","ft","am","civ",
                                                            "bku","lop","ghz"};
    public AssignmentThree () {
        readFromFiles();
    }
    
    public void readFromFiles() {
        try {
            BufferedReader brWords = new BufferedReader( new FileReader(
                                                    "../doc/dictionary.txt") );
            while ( brWords.ready() ) {
                String line = brWords.readLine().toLowerCase();
                words.add( line );
                wordsByNumbers.put(line, (matchWordToNumbers( line )));

            }
            BufferedReader brNumbers = new BufferedReader( new FileReader(
                                                "../doc/phonenumbers.txt") );
            while ( brNumbers.ready() ) {
                numbers.add( brNumbers.readLine() );
                System.out.println(brNumbers.readLine());
            }
            brWords.close();
            brNumbers.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (NullPointerException ne) { 
            System.out.println("Krasch! ");
            ne.printStackTrace();
        }
        
        System.out.println(wordsByNumbers.toString());   
        for (int i = 0; i < words.size(); i++) {
            System.out.println( words.get(i) ); 
        }
    }
    
    public String matchWordToNumbers( String word ) {
        String tmp = "";
        for( int i = 0; i < word.length(); i++ ) {
            for( int y = 0; y < combinations.length ; y++ ) {
                if( combinations[y].contains( 
                                    String.valueOf(word.charAt(i)) ) )
                    tmp += Integer.toString(y);
            }
        }
        //System.out.println(tmp);
        return tmp;   
    }

    public static void main( String[] args ) {
        AssignmentThree app = new AssignmentThree();
        System.out.println();
        //System.out.println(app.matchWordToNumbers("jemand"));
    }

}
