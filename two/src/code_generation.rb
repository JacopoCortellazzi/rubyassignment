class CodeGenerator
  
  def loadFile(args)
    classReg = /title\s*:(.*)\n/
    file = File.new(args, "r")
    while(line = file.gets)
      if(line.match(classReg))
        #skapa ny klass här
        createNewClass($1)
      end
    end
    file.close
  end
  
  def createNewClass(args)
    
  end
end