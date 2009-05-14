# code_generation.rb

class MyDSL
    
    def self.load( filename )
        dsl = new
        dsl.instance_eval( File.read( filename ) )
        dsl
    end
    
    def title( sym )
        @newClass = Object.const_set( sym, Class.new )
        puts @newClass
    end
    
    def attribute( sym, args )
        @newClass.class_eval( "attr_accessor #{sym}" )
    end
    
    #def method_missing( sym, args )
    #end
end

#    def loadFile( filename )
#        title = /title\s*:(.*)\n/
#        file = File.open( filename, "r" )
#        while( line = file.gets )
#            if tmp = line.match( title )
#                makeClass( tmp[1] )
#            end
#        end
#        file.close   
#	end
	
#	def makeClass( args )
#	    @myClass = Object.const_set( args, Class.new )
#        puts @myClass
#	end
#
#end
