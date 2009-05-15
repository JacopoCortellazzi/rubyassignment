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
        @newClass.class_eval("attr_writer :#{sym}")
        @newClass.class_eval("attr_reader :#{sym}")
    end

   def attr_writer(sym)
        class_eval %{def {sym}= (val)
                        #if val.kind_of? == {sym}.class
                            @{sym} = val
                        #end
                     end}
   end
   
   def attr_reader(sym)
        class_eval %{def {sym}
                        @{sym}
                     end}
    end
   
   def constraint(sym, args)
	 	alias_method @newClass.old_attribute_writer, @newclass.attribute_writer
	 	p sym.to_s+args
   end
   
   #alias_method :old_attr_writer, ?
    
    #def method_missing( sym, args )
    #end
end
