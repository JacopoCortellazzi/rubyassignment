class CodeGenerator
  
  def loadFile(args)
    titleReg = /title\s*:(.*)\n/
    attributeReg = /\s*attribute\s*:(.*),\s*(.*)\n*/  
    constraintReg = /\s*constraint\s*:(.*),\s*(.*)\n*/  
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
    puts @myClass
  end
  
  def createNewAttribute(args)
    @myClass.class_eval("attr_accessor:#{args.keys}")
  end
  
  def addConstraint(args)
    
  end
end