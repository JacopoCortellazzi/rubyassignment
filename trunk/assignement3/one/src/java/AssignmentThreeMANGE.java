import java.io.*;
import java.util.*;
import java.lang.*;

class AssignmentThreeMANGE {
    private String dictionary = "../doc/dictionary.txt";
    private String phonenumbers = "../doc/phonenumbers.txt";
    private ArrayList< String > words = new ArrayList< String >();
    private ArrayList< String > numbers = new ArrayList< String >();
    private String matches = "";
    private String tmp_matches = "";
    private StringBuffer phonenumber = new StringBuffer();
    private HashMap< String, String > wordsByNumbers = 
                                                new HashMap< String, String >();
    private String[] combinations = { "e","jnq","rwx","dsy","ft","am","civ",
                                                            "bku","lop","ghz" };
    public AssignmentThreeMANGE () {
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
        System.out.println( num+" : "+encodeNumber( num, 0 ) );
    }
    
    public String encodeNumber( String num, int x  ) {
        int length = num.length();
        String substring = "";
        String restOfNum = "";
        while ( x < length ) {
            substring = num.substring( 0, ++x );
            for ( String n : wordsByNumbers.keySet() ) {
                System.out.println("x = "+x+" key: "+n);
                
                if( substring.equals( wordsByNumbers.get( n ) ) ) {
                    System.out.println(substring+" : "+n);
                    restOfNum = num.replaceFirst( substring, "" );
                    System.out.println("Rest of string: "+restOfNum);
                    if( restOfNum.length() == 0 )
                        System.out.println("Match: "+n);
                    else
                        System.out.println(encodeNumber( restOfNum, 0 ));
                }
            }
        }
        return "";
    }
    
    public void printResult( String num, String result ) {
        System.out.println(num+": "+result);
    }

    public static void main( String[] args ) {
        AssignmentThreeMANGE app = new AssignmentThreeMANGE();
        System.out.println();
        app.startEncoding( "562482" );
    }
}
