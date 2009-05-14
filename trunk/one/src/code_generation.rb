# code_generation.rb

#module Model

	#def generate( path )

	#end

class MyDSL
	
	def self.load( filename )
	    title = /title\s*:(.*)\n/
	    file = File.open( filename, "r" )
	    #while(line = file.gets)
	    file.each { |line| do if line.match( title ) makeClass( title[1] ) }
	end
	
	def makeClass( args )
	    @myClass = Object.const_set( args, Class.new )
        puts @myClass
	end

end
