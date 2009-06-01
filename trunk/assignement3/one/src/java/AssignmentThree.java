import java.io.*;
import java.util.*;
import java.lang.*;

class AssignmentThree {
    private FileReader phonenumberReader, wordReader;
    private BufferedReader in = null;
    private ArrayList<String> words = new ArrayList<String>();
    private ArrayList<String> numbers = new ArrayList<String>();
    private Hashtable<String, char[]> mapping = 
                            new Hashtable<String, char[]>();
    //private Hashtable numbers = new Hashtable();

    public AssignmentThree () {
        char[] zero = {'e'};
        mapping.put("0", zero);
        char[] one = {'j','n','q'};
        mapping.put("1", one);
        char[] two = {'r','w','x'};
        mapping.put("2", two);
        char[] three = {'t','s','y'};
        mapping.put("3", three);
        char[] four = {'f','t'};
        mapping.put("4", four);
        char[] five = {'a','m'};
        mapping.put("5", five);
        char[] six = {'c','i','v'};
        mapping.put("6", six);
        char[] seven = {'b','k','u'};
        mapping.put("7", seven);
        char[] eight = {'l','o','p'};
        mapping.put("8", eight);
        char[] nine = {'g','h','z'};
        mapping.put("9", nine);        
        try {
            phonenumberReader = new FileReader( "../doc/phonenumbers.txt" );
            wordReader = new FileReader( "../doc/dictionary.txt" );
            //in = new BufferedReader(wordReader);
            } catch ( FileNotFoundException e ) {
                System.out.println( "File not found "+e );
            }
        readFromFiles();
    }
    
    public void readFromFiles() {
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
    }

}
