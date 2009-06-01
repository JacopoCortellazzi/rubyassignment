import java.io.*;
import java.util.*;
import java.lang.*;

class AssignmentThree {
    private FileReader phonenumberReader, wordReader;
    private BufferedReader in = null;
    private ArrayList<String> words = new ArrayList<String>();
    private ArrayList<String> numbers = new ArrayList<String>();

    public AssignmentThree () {
        try {
            phonenumberReader = new FileReader( "../doc/phonenumbers.txt" );
            wordReader = new FileReader( "../doc/dictionary.txt" );
            //in = new BufferedReader(wordReader);
            } catch ( FileNotFoundException e ) {
                System.out.println( "File not found "+e );
            }
    }
    
    public void printWords() {
        try {
            in = new BufferedReader( wordReader );
            while ( in.ready() ) {
                words.add( in.readLine() );
            }
        } catch (Exception e) {}
        
        try {
            in = new BufferedReader( phonenumberReader );
            while ( in.ready() ) {
                numbers.add( in.readLine() );
            }
        } catch (IOException ie) {}
        
        for (int i = 0; i < words.size(); i++) {
            System.out.println( words.get(i) );
        }
        System.out.println();
        for (int i = 0; i < numbers.size(); i++) {
            System.out.println( numbers.get(i) );
        }
    }

    public static void main( String[] args ) {
        AssignmentThree app = new AssignmentThree();
        app.printWords();
    }

}
