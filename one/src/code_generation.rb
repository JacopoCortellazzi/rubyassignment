# code_generation.rb

class MyDSL
    
    def loadFile( filename )
        title = /title\s*:(.*)\n/
        file = File.open( filename, "r" )
        while( line = file.gets )
            if tmp = line.match( title )
                makeClass( tmp[1] )
            end
        end
        file.close   
	end
	
	def makeClass( args )
	    @myClass = Object.const_set( args, Class.new )
        puts @myClass
	end

end
