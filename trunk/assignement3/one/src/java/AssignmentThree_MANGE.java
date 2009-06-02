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
    private StringBuffer phonenumber = new StringBuffer();
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
        char[] partOfString = num.toCharArray();
        System.out.println( num+" : "+encodeNumber( partOfString, 0 ) );
    }
    
    public String encodeNumber( char[] partOfString, int x  ) {
        int length = partOfString.length;
        
        if( x < (partOfString.length) )
            phonenumber = phonenumber.insert( phonenumber.length(), 
                                                          partOfString[ x++ ] );
        
        for ( String n : wordsByNumbers.keySet() ) {
            System.out.println(n+": "+phonenumber);
                
            
            if( phonenumber.toString().equals( wordsByNumbers.get( n ) ) ) {
                tmp_matches += n+" ";
                System.out.println("--------->Match!<-------->"+n);
                //TODO måste rekursivt anropa med resterande siffror...
                char[] tmptmp = {'4','8','2'};
                return encodeNumber( tmptmp, x );
                //phonenumber = phonenumber.delete(0, x);
                //System.out.println( tmp_matches );
                //return encodeNumber( phonenumber.toString().toCharArray(), x );
            }
        
        }
        if( x >= partOfString.length )
            return tmp_matches;
        else
            return encodeNumber( partOfString, x );
        
        
        
        /***********************************************
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
        ************************************************/
    }
    
    public void printResult( String num, String result ) {
        System.out.println(num+": "+result);
    }

    public static void main( String[] args ) {
        AssignmentThree app = new AssignmentThree();
        System.out.println();
        app.startEncoding( "562482" );
    }
}
