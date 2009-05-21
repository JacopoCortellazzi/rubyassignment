# code_generation.rb

#require 'dslhelper_attr_writer'

class Model
    
    def self.generate(filename)
        dsl = new
        dsl.instance_eval(File.read(filename))
        dsl
		  #return dsl.newClass
    end

#	def self.generate(filename)
#	 	self.instance_eval(File.read(filename))
#	end
    
    def title(sym)
		@newClass = Object.const_set(sym, Class.new)
		@newClass.class_eval %{require 'yaml'
									def initialize(args)
										args.each do |arg|
											self.method("#\{arg[0]\}=").call(arg[1])
										end
									end

									def self.load_from_file(filename)
										arr = YAML::load(File.open(filename)).values[0]
										ret = Array.new
										arr.each do |n|
											begin
												obj = self.new(n)
												ret << obj
											rescue
											end
										end
										return ret
									end

									def self.method_missing(method, *args)
										p method
										p args
										return nil
									end
									}
		puts @newClass
    end
    
    def attribute(sym, args)
      set_attr_writer(sym)
      set_attr_reader(sym)
		#@newClass.class_eval %{instance_variable_set :@#{sym}, nil}
		@newClass.class_eval %{class_variable_set :@@#{sym}_constraints, Array.new}

    end

   def set_attr_writer(sym)
        @newClass.class_eval %{def #{sym}= (val)
		  						old_#{sym} = @#{sym}
								@#{sym} = val
								if @@#{sym}_constraints.nil?
									p "no constraints"
								else 
									@@#{sym}_constraints.each do |c|
										if !eval(c) || @#{sym} == nil
											@#{sym} = old_#{sym}
											#return @#{sym}
											raise "Value out of range: "+val.to_s+" is not in "+c.to_s
										else
											return @#{sym}
										end
									end
								end
                     end
       }
   end
   
   def set_attr_reader(sym)
        @newClass.class_eval %{def #{sym}
                        @#{sym}
                     end}
    end

	def constraint(sym, args)
		@newClass.class_eval %{@@#{sym}_constraints << %(#{args})}
	end

end
