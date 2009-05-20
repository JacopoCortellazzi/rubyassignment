class CodeGenerator

  def loadFile(args)
    titleReg = /title\s*:(.*)\n/
    attributeReg = /\s*attribute\s*:(.*),\s*(.*)\n*/  
    constraintReg = /\s*constraint\s*:(.*),\s*'[a-z A-Z]*\.*(.*)'\n*/  
    file = File.new(args, "r")
    @constraints = Array.new
    while(line = file.gets)
      if(line.match(titleReg))
        createNewClass($1)
      end
      if(line.match(attributeReg))
        createNewAttribute($1 => $2)
      end
      if(line.match(constraintReg))
        @constraints << {$1 => $2}
      end
    end
    file.close
    makeConstraintMethod()
  end

  def createNewClass(args)
    @myClass = Object.const_set(args,Class.new)
  end

  def createNewAttribute(args)
    set_attr_reader("#{args.keys}")
    set_attr_writer("#{args.keys}","#{args.values}")
  end

  def set_attr_writer(sym, type)
      @myClass.class_eval %{def #{sym}=(val)
                    puts val
                    if(val.kind_of? #{type})
                      puts "Value är av samma typ"
                      if(checkConstraint(#{sym}, val))
                        @#{sym} = val
                      else
                        raise "Value did match not constraints"
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

  def checkConstraint(attribute, value_to_be_checked)
      valid = true
      @constraints.each do |p|
        p.each_pair do |key, value|
          key.each do |k|
            if(k == attribute)
              if(value.to_s.eql?("!= nil"))
                if(!value_to_be_checked != nil)
                  valid = false
                  break
                end
              end
              if(value.to_s.eql?("size > 0"))
                if(!value_to_be_checked.size > 0)
                  valid = false
                  break
                end
              end
              if(value.to_s.eql?("=~ /^[A-Z]/"))
                if(!value_to_be_checked.match(/[A-Z]/))
                  valid = false
                  break
                end
              end
              if(value.to_s.eql?(">= 0"))
                if(!value_to_be_checked >= 0)
                  valid = false
                  break
                end
              end
            end
          end
        end
      end   
    end
    # FIXA MED CLASS EVAL!!!!!!!
    def makeConstraintMethod()
      
      @myClass.class_eval %{def checkConstraint(attribute, value_to_be_checked)
      valid = true
      constraints.each do |p|
        p.each_pair do |key, value|
          key.each do |k|
            if(k == attribute)
              if(value.to_s.eql?("!= nil"))
                if(!value_to_be_checked != nil)
                  valid = false
                  break
                end
              end
              if(value.to_s.eql?("size > 0"))
                if(!value_to_be_checked.size > 0)
                  valid = false
                  break
                end
              end
              if(value.to_s.eql?("=~ /^[A-Z]/"))
                if(!value_to_be_checked.match(/[A-Z]/))
                  valid = false
                  break
                end
              end
              if(value.to_s.eql?(">= 0"))
                if(!value_to_be_checked >= 0)
                  valid = false
                  break
                end
              end
            end
          end
        end
      end 
                  end}
    
    
    end
  
end
