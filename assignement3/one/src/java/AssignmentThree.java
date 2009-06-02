import java.io.*;
import java.util.*;
import java.lang.*;

class AssignmentThree {
    private String dictionary = "../doc/dictionary.txt";
    private String phonenumbers = "../doc/phonenumbers.txt";
    private ArrayList< String > words = new ArrayList< String >();
    private ArrayList< String > numbers = new ArrayList< String >();
    private String matches = "";
    private String tmp_matches = "";
    private HashMap< String, String > wordsByNumbers = 
                                                new HashMap< String, String >();
    private String[] combinations = { "e","jnq","rwx","dsy","ft","am","civ",
                                                            "bku","lop","ghz" };
    public AssignmentThree () {
        readFromFiles();
    }
    
    public void readFromFiles() {
        try {
            BufferedReader brWords = new BufferedReader( new FileReader
                                                               ( dictionary ) );
            while ( brWords.ready() ) {
                String line = brWords.readLine().toLowerCase();
                words.add( line );
                wordsByNumbers.put( line, ( matchWordToNumbers( line ) ) );
            }
            BufferedReader brNumbers = new BufferedReader( new FileReader
                                                             ( phonenumbers ) );
            while ( brNumbers.ready() ) {
                numbers.add( brNumbers.readLine() );
            }
            brWords.close();
            brNumbers.close();
        } catch ( IOException ie ) {
            ie.printStackTrace();
        } catch ( NullPointerException ne ) { 
            System.out.println( "Krasch! " );
            ne.printStackTrace();
        }
        //Skiver ut hashMappen
        System.out.println( wordsByNumbers.toString() );   
    }
    
    public String matchWordToNumbers( String word ) {
        String tmp = "";
        for( int i = 0; i < word.length(); i++ ) {
            for( int y = 0; y < combinations.length ; y++ ) {
                if( combinations[ y ].contains( String.valueOf
                                                        ( word.charAt( i ) ) ) )
                    tmp += Integer.toString( y );
            }
        }
        return tmp;   
    }
    
    
    
    public void startEncoding( String num ) {
        matches = "";
        tmp_matches = "";
        encodeNumber( num );
        printResult( num, tmp_matches );
    }
    
    public void encodeNumber( String num ) {
        String tmp_substring;
        String tmp = num;
        int x;
        //foreach key in wordsByNumbers
        for ( String n : wordsByNumbers.keySet() ) {
            x=1;
            while ( x <= ( tmp.length() ) ) {
                tmp_substring = tmp.substring( 0, x++ );
                if( tmp_substring.equals( wordsByNumbers.get( n ) ) ) {
                    tmp = tmp.replaceFirst(tmp_substring, "");
                    if( tmp.length() == 0 ) {
                        matches = tmp_matches;
                    }
                    else
                        encodeNumber( tmp );
                    tmp_matches += n+" ";
                }
            }
        }
    }
    
    public void printResult( String num, String result ) {
        System.out.println(num+": "+result);
    }

    public static void main( String[] args ) {
        AssignmentThree app = new AssignmentThree();
        System.out.println();
        app.startEncoding( "253302" );
    }
}
