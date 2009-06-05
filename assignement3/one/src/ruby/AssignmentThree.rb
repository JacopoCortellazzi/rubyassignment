class AssignmentThree

    attr_accessor :numbers, :words, :combinations

    def initialize
        readNumbers
        readWords
    end
    
    def readNumbers
        @numbers = []
        f = File.open( "../doc/tele.txt", "r" )
        f.each do | line |
            @numbers << line.strip.to_s
        end
    end
    
    def readWords
        @words = {}
        f = File.open( "../doc/dict.txt", "r" )
        f.each do | line |
            @words[ line.strip.downcase ] = matchWordToNumber( line.downcase )
        end
    end
    
    def matchWordToNumber( word )
        current = ""
        @combinations = ["e", "jnq", "rwx", "dsy", "ft", "am", "civ", "bku", 
                                                                  "lop", "ghz"]
        word.each_char do | char |
            @combinations.each do | s |
                if( s.include? char )
                    current += @combinations.index( s ).to_s
                end
            end
        end
        return current
    end
    
    def startEncoding
        @numbers.each do | number |
            encodeNumber( number, "", number )
        end
    end
    
    def encodeNumber( num, result, original )
        if( num.size == 0 )
            puts "#{original}: #{result}"
        end
        x = 1
        substring = ""
        num.size.times do
            substring = num.slice( 0, x )
            @words.each_pair do | key ,value |
                if( substring == value )
                    encodeNumber( num.sub( substring, "" ), result+key+" ", original )
                end
            end
            x += 1
        end
    end
end
