require 'yaml'

class Model
    
    def Model.generate(filename)
      @newClass = new
      @newClass.instance_eval(File.read(filename))
			create_load_from_file(filename)
			return @newClass
			
    end
    
    def Model.create_load_from_file(filename)
      @newClass.instance_eval %{
                  def load_from_file(filename)
										@myClass.load_from_file(filename)
									end}
									
    end
    
    def load_from_file(filename)
      @myClass.class_eval %{
									def self.load_from_file(filename)
									  attributes = []
									  ret = []									
										attributes = YAML::load(File.open(filename)).values[0]
										attributes.each do |n|
											begin
												obj = self.new(n)
												ret << obj
											rescue
											end
										end
										return ret
									end}
    end
    
        
    def title(sym)
		@myClass = Object.const_set(sym, Class.new)
		load_from_file(sym)
		@myClass.class_eval %{
									def initialize(args)
										args.each do |arg|
											self.method("#\{arg[0]\}=").call(arg[1])
										end
									end
									}
    end
    
    def set_attr_reader(sym)
      @myClass.class_eval %{def #{sym}
                        @#{sym}
                     end}
    end

	def constraint(sym, args)
		@myClass.class_eval %{
									@@#{sym}_constraints << %(#{args})
									}
	end
    
    def attribute(sym, args)
      set_attr_writer(sym, args)
      set_attr_reader(sym)
		@myClass.class_eval %{
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
        @myClass.class_eval %{def #{sym}= (val)
		  						if val.kind_of? #{args}
		  							old_#{sym} = @#{sym}
									@#{sym} = val
									if @@#{sym}_constraints.nil?
									  puts "No set constraints"
									else 
										@@#{sym}_constraints.each do |con|
											if !eval(con) || @#{sym} == nil
												@#{sym} = old_#{sym}
												raise "Invalid value!"
											end
										end
									end
								else
										raise "Invalid type!"
								end
                     end
       }
   end
   
end
