class CodeGenerator
  
  def loadFile(args)
    titleReg = /title\s*:(.*)\n/
    attributeReg = /\s*attribute\s*:(.*),\s*(.*)\n*/  
    file = File.new(args, "r")
    while(line = file.gets)
      if(line.match(titleReg))
        createNewClass($1)
      end
      if(line.match(attributeReg))
        createNewAttribute($1)
      end
    end
    file.close
  end
  
  def createNewClass(args)
    @myClass = Object.const_set(args,Class.new)
    puts @myClass
  end
  
  def createNewAttribute(args)
    @myClass.class_eval("attr_accessor:#{args}")
  end
end