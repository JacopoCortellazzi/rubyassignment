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
        set_attr_writer(sym)
        set_attr_reader(sym)
        @newClass.class_eval %{class_variable_set :@@#{sym}_constraints, Array.new}
    end

   def set_attr_writer(sym)
        @newClass.class_eval %{def #{sym}= (val)
                        p "attr_writer for #{sym}"
		  						old_#{sym} = @#{sym}
								@#{sym} = val
		  						p #{sym}.class
								p val.class
								if @@#{sym}_constraints.nil?
									p "no constraints"
								else p @@#{sym}_constraints.to_s
									@@#{sym}_constraints.each do |c|
										#p "constraint: "+c+", value: "+val.to_s+", sym: "+#{sym}.to_s
										if !eval(c)
											@#{sym} = old_#{sym}
											raise "Value not allowed: "+val.to_s+" rule "+c
										end
									end
								end
                     end
       }
   end
   
   def set_attr_reader(sym)
        @newClass.class_eval %{def #{sym}
                        p "attr_reader for #{sym}"
                        @#{sym}
                     end}
    end

	def constraint(sym, args)
		@newClass.class_eval %{	p "adding constraint "+sym.to_s+" "+%(#{args})
											@@#{sym}_constraints << %(#{args})
		}
	end

end
