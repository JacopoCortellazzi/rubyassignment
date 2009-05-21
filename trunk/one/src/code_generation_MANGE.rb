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
	  @newClass.class_eval %{class_variable_set :@@#{sym}_constraints, Array.new}
    end

   def set_attr_writer(sym)
        @newClass.class_eval %{def #{sym}= (val)
                        p "attr_writer for #{sym}"
		  						old_#{sym} = @#{sym}
								@#{sym} = val
		  						p #{sym}.class
								p val.class
								#
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


   def set_attr_writer(sym, args)
        p sym.to_s+", "+args.to_s
        @newClass.class_eval %{def #{sym}= (val)
                        if val.kind_of? #{args}
                            #{sym} = val
                            if @@#{sym}_constraints
                                @@#{sym}_constraints.each do |c|
							        if !eval(c)
							            raise "Value nor allowed: "+val.to_s+" rule "+c
							            break
							        end
							     end
							     @#{sym} = val
							  end
					    else
					        raise val.to_s+" is not #{args}"
				        end
				    end
	    }
    end
   
   def set_attr_reader(sym, args)
        @newClass.class_eval %{def #{sym}
                        @#{sym}
                     end
        }
    end

    def constraint(sym, args)
        if !@const
            @const = []
        end
        @const << {sym, args}
        @const.each do |c| puts c end
    	def constraint(sym, args)
		    @newClass.class_eval %{	p "adding constraint "+sym.to_s+" "+%(#{args})
		    									@@#{sym}_constraints << %(#{args})
		    }
		 end
    end
end
