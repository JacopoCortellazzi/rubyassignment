# code_generation.rb

class Model

    def self.generate(filename)
        dsl = new
        dsl.instance_eval(File.read(filename))
        dsl.instance_eval %{
                        require "yaml"  
									def self.load_from_file(filename)
										arr = YAML::load(File.open(filename)).values[0]
										ret = Array.new
										arr.each do |n|
											begin
												obj = @newClass.new(n)
												ret << obj
											rescue
											end
										end
										p ret
										return ret
									end
		}
        return dsl
    end

    def title(sym)
        @newClass = Object.const_set(sym, Class.new)
        @newClass.class_eval %{
								def initialize(args)
									args.each do |arg|
										self.method("#\{arg[0]\}=").call(arg[1])
									end
								end
        }
    end
    
    def attribute(sym, args)
      set_attr_writer( sym, args )
      set_attr_reader( sym, args )
	  @newClass.class_eval %{class_variable_set :@@#{sym}_constraints, Array.new}
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
