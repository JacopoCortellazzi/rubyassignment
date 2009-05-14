class CodeGenerator

  def loadFile(args)
    titleReg = /title\s*:(.*)\n/
    attributeReg = /\s*attribute\s*:(.*),\s*(.*)\n*/  
    constraintReg = /\s*constraint\s*:(.*),\s*'[a-z A-Z]*\.*(.*)'\n*/  
    file = File.new(args, "r")
    while(line = file.gets)
      if(line.match(titleReg))
        createNewClass($1)
      end
      if(line.match(attributeReg))
        createNewAttribute($1 => $2)
      end
      if(line.match(constraintReg))
        addConstraint($1 => $2)
      end
    end
    file.close
  end

  def createNewClass(args)
    @myClass = Object.const_set(args,Class.new)
  end

  def createNewAttribute(args)
    @myClass.class_eval("attr_accessor:#{args.keys}")
  end

  def addConstraint(args)
    key = args.keys.to_s
    value = args.values
    puts key.class
    if(@myClass.method_defined?(key))
      if(value.to_s.eql?("!= nil"))
        #if("#{@myClass}.#{key}" != nil)
        #  puts "OK"
        #end
      end
      if(value.to_s.eql?("size > 0"))
        #puts args.values
      end
      if(value.to_s.eql?("=~ /^[A-Z]/"))
        #puts args.values
      end
      puts "Value: ",value
      if(value.to_s.eql?(">= 0"))
        #puts args.values
      end
    end
  end
end