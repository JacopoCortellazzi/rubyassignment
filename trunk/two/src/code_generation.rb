require "yaml"
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
        createConstraints($1 => $2)
      end
    end
    file.close
    makeConstraintsMethod()
    return self
  end
  


  def load_from_file(file)
    attributes = []
    values = []
    f = YAML.load_file(file)
      f.each do |key, args|
      @arrayName = key
        args.each do |a|
          a.each do |key , value|
            attributes << key
            values << value
          end
          return @myClass.new(attributes, values)          
        end   
      end
  end

  def createNewClass(args)
    @myClass = Object.const_set(args,Class.new)
    @myClass.class_eval %{def self.load_from_file(fileName)
      YAML.load_file(fileName)
      end}
    @myClass.class_eval %{def initialize(attri, vali)
                          
                        end}
                         #self.method(#{attri[0]}).call(vali[0])
  end

  def createNewAttribute(args)
    set_attr_reader("#{args.keys}")
    set_attr_writer("#{args.keys}","#{args.values}")
    @myClass.class_eval %{class_variable_set :@@const, Array.new}
  end

  def createConstraints(args)
    @myClass.class_eval %{@@const << args} 
  end

  def set_attr_writer(sym, type)
    @myClass.class_eval %{def #{sym}=(val)
    if(val.kind_of? #{type})
      if(checkConstraint(:#{sym}, val))
        @#{sym} = val
      else
        raise "Invalid value"
      end
    else
      raise "Type Error"
    end
  end}    
end

def set_attr_reader(syms)
  syms.each do|sym|
    @myClass.class_eval %{def #{sym}
    @#{sym}
  end}
end
end

def makeConstraintsMethod()
  @myClass.class_eval %{def checkConstraint(attribute, value_to_be_checked)
    valid = true
    @@const.each do |p|
      p.each_pair do |key, value|
        key.each do |k|
          if(k.to_s == attribute.to_s)
            if(value.to_s.eql?("! = nil"))
              if(!(value_to_be_checked != nil))
                valid = false
                break
              end
            end
            if(value.to_s.eql?("size > 0"))
              if(!(value_to_be_checked.size > 0))
                valid = false
                break
              end
            end
            if(value.to_s.eql?("=~ /^[A-Z]/"))
              if(!(value_to_be_checked.match(/[A-Z]/)))
                valid = false
                break
              end
            end
            if(value.to_s.eql?(">= 0"))
              if(!(value_to_be_checked >= 0))
                valid = false
              end
            end
          end
        end
      end
    end
    return valid 
  end}
end  
end
