# code_generation.rb

#require 'dslhelper_attr_writer'

class MyDSL
    
    def self.load(filename)
        dsl = new
        dsl.instance_eval(File.read(filename))
        dsl
    end
    
    def title(sym)
        @newClass = Object.const_set(sym, Class.new)
        puts @newClass
    end
    
    def attribute(sym, args)
		@newClass.instance_eval("@#{sym} = 0")
      set_attr_writer( sym )
      set_attr_reader( sym )
    end

	def set_initializer(sym)
		@newClass.class_eval("alias_method :old_initialize, :initialize")
		def initialize(arg)
			@#{sym} = 0
			old_initialize(
		

   def set_attr_writer(sym)
        @newClass.class_eval %{def #{sym}= (val)
                        #if val.class == #{sym}.class
                            @#{sym} = val
                        #end
                     end}
   end
   
   def set_attr_reader(sym)
        @newClass.class_eval %{def #{sym}
                        @#{sym}
                     end}
    end
  
#def constraint(sym, args)
#	p "constraint"
#end

	def constraint(sym, args)
	@newClass.class_eval("alias_method :old_attr_writer, :#{sym}=")
	@newClass.class_eval %{def #{sym}= (val)
			if #{args}
				old_attr_writer(val)
			else raise "value out of bounds: #{args}"
			end
		end}
  	
   	p sym.to_s+args
  end
		#		@#{sym} = val
   
   #alias_method :old_attr_writer, ?
    
    #def method_missing( sym, args )
    #end
end
