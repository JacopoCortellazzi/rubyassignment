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
      set_attr_writer( sym, args )
      set_attr_reader( sym, args )
    end

   def set_attr_writer(sym, args)
        p sym.to_s+", "+args.to_s
        @newClass.class_eval %{def #{sym}= (val)
                        p "attr_writer for #{sym}, #{args}, "+val.to_s
                        if val.kind_of? #{args}
                            @#{sym} = val
                        else
                            raise val.to_s+" is not #{args}"
                        end
                     end}
   end
   
   def set_attr_reader(sym, args)
        @newClass.class_eval %{def #{sym}
                        p "attr_reader for #{sym}"
                        @#{sym}
                     end}
    end

	def constraint(sym, args)
	    @newClass.class_eval("alias_method :old_#{sym}=, :#{sym}=")
        @newClass.class_eval %{def #{sym}=(val)
                                 old_#{sym}=(val)
                               end
        }
	end
#	@newClass.class_eval("alias_method :old_#{sym}_writer, :#{sym}=")
#	@newClass.class_eval %{def #{sym}= (val)
#			old_#{sym}_writer(val)
#            if @#{sym} != nil
#			    if #{args}
#                    #old_#{sym}_writer(val)
#			    	@#{sym} = val
#			    else 
#			        raise "value out of bounds: #{args}"
#			    end
#			else
#			    @#{sym} = val
#			end
#		end}
# 	
#   	p sym.to_s+args
#  end
		#		@#{sym} = val
   
   #alias_method :old_attr_writer, ?
    
    #def method_missing( sym, args )
    #end
end
