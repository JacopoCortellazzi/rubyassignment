import java.io.*;
import java.util.*;
import java.lang.*;

class AssignmentThree {
    private String dictionary = "../doc/dictionary.txt";
    private String phonenumbers = "../doc/phonenumbers.txt";
    private ArrayList<String> numbers = new ArrayList<String>();
    private String originalNum;
    private StringBuffer phonenumber = new StringBuffer();
    private HashMap<String, String> wordsByNumbers = 
                                                new HashMap<String, String>();
    private String[] combinations = {"e","jnq","rwx","dsy","ft","am","civ",
                                                            "bku","lop","ghz"};
    public AssignmentThree () {
        readFromFiles();
    }
    
    public ArrayList<String> getNumbers() {
        return numbers;
    }
    
    public void readFromFiles() {
        try {
            BufferedReader brWords = new BufferedReader( new FileReader
                                                               ( dictionary ) );
            while ( brWords.ready() ) {
                String line = brWords.readLine().toLowerCase();
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
    }
    
    public String matchWordToNumbers( String word ) {
        String correspondingNumber = "";
        for( int i = 0; i < word.length(); i++ ) {
            for( int y = 0; y < combinations.length ; y++ ) {
                if( combinations[ y ].contains( String.valueOf
                                                        ( word.charAt( i ) ) ) )
                    correspondingNumber += Integer.toString( y );
            }
        }
        return correspondingNumber;
    }
    
    public void startEncoding( String num ) {
        originalNum = num;
        encodeNumber( num, "", 0 );
    }
    
    public void encodeNumber( String num, String result, int x  ) {
        if ( num.length() == 0 )
            System.out.println( originalNum+": "+result );
        int length = num.length();
        String substring = "";
        String restOfNum = "";
        while ( x < length ) {
            substring = num.substring( 0, ++x );
            for ( String n : wordsByNumbers.keySet() ) {
                if( substring.equals( wordsByNumbers.get( n ) ) ) {
                    restOfNum = num.replaceFirst( substring, "" );
                    encodeNumber( restOfNum, result+n+" ", 0 );
                }
            }
        }
    }
    
    public static void main( String[] args ) {
        AssignmentThree app = new AssignmentThree();
        ArrayList<String> telephonenumbers = app.getNumbers();
        for( int i = 0; i < telephonenumbers.size(); i++ )
            app.startEncoding( telephonenumbers.get(i) );
    }
}
