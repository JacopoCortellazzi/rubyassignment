class AssignmentThree

    attr_accessor :numbers, :words, :combinations

    def initialize
        readNumbers
        readWords
    end
    
    def readNumbers
        @numbers = []
        f = File.open( "../doc/tele.txt", "r" )
        f.each do |line|
            @numbers << line.strip.to_s
        end
    end
    
    def readWords
        @words = {}
        f = File.open( "../doc/dict.txt", "r" )
        f.each do | line |
            @words[line.strip.downcase] = matchWordToNumber( line.downcase )
        end
    end
    
    def matchWordToNumber( word )
        tmp = ""
        @combinations = ["e", "jnq", "rwx", "dsy", "ft", "am", "civ", "bku", 
                                                                  "lop", "ghz"]
        word.each_char do | char |
            @combinations.each do | s |
                if( s.include? char )
                    tmp += @combinations.index( s ).to_s
                end
            end
        end
        return tmp
    end
    
    def startEncoding( num )
        encodeNumber(num, "")
    end
    
    def encodeNumber( num, result )
        if(num.size == 0)
            puts result
        end
        x = 1
        substring = ""
        restOfNum = ""
        unless x > num.size
            substring = num.slice( 0, x+=1 )
            @words.each_pair do | key ,value |
                if( substring == key )
                    restOfNum = num.sub( substring, "" )
                    encodeNumber( restOfNum, result+key+" " )
                end
            end
        end
    end
end
