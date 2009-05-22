# code_generation.rb

#require 'dslhelper_attr_writer'

class Model
    
    def self.generate(filename)
        dsl = new
        dsl.instance_eval(File.read(filename))
			dsl.instance_eval %{
									def load_from_file(filename)
										p #{@newClass}
										@newClass.load_from_file(filename)
									end
									}
			dsl
    end

#	def self.generate(filename)
#	 	self.instance_eval(File.read(filename))
#	end
    
    def title(sym)
		@newClass = Object.const_set(sym, Class.new)
		@newClass.class_eval %{require 'yaml'
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
										p ret
										return ret
									end
			}
		@newClass.class_eval %{
									def initialize(args)
										args.each do |arg|
											self.method("#\{arg[0]\}=").call(arg[1])
										end
									end

									}
		puts @newClass
    end
    
    def attribute(sym, args)
      set_attr_writer(sym, args)
      set_attr_reader(sym)
		#@newClass.class_eval %{instance_variable_set :@#{sym}, nil}
		@newClass.class_eval %{
									class_variable_set :@@#{sym}_constraints, Array.new
									def self.#{sym}_constraints
										@@#{sym}_constraints
									end
									def #{sym}_constraints
										@@#{sym}_constraints
									end
									}

    end

   def set_attr_writer(sym, args)
        @newClass.class_eval %{def #{sym}= (val)
		  						if val.kind_of? #{args}
		  							old_#{sym} = @#{sym}
									@#{sym} = val
									if @@#{sym}_constraints.nil?
										p "no constraints"
									else 
										p "attr_writer for "+#{sym}.to_s
										@@#{sym}_constraints.each do |c|
											if !eval(c) || @#{sym} == nil
												@#{sym} = old_#{sym}
												raise "Value out of range: "+val.to_s+" is not in "+c.to_s
											end
										end
									end
								else
										raise "Type error, "+val.to_s+" is not #{args}"
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
		@newClass.class_eval %{
									@@#{sym}_constraints << %(#{args})
									p @@#{sym}_constraints
									}
	end

end
