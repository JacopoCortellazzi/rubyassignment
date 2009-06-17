require "yaml"
class Model

  def Model.generate(args)
    titleReg = /title\s*:(.*)\n/
    attributeReg = /\s*attribute\s*:(.*),\s*(.*)\n*/  
    constraintReg = /\s*constraint\s*:(.*),\s*(.*)\n*/  
    file = File.new(args, "r")
    while(line = file.gets)
      if(line.match(titleReg))
        Model.createNewClass($1)
      end
      if(line.match(attributeReg))
        Model.createNewAttribute($1 => $2)
      end
      if(line.match(constraintReg))
        Model.createConstraints($1 => $2)
      end
    end
    file.close
    Model.makeConstraintsMethod()
    return self
  end

  def Model.load_from_file(file)
    attributes = []
    att = []
    values = []
    ret = []
    tmp = []
    f = YAML.load_file(file)
      f.each do |key, args|
      @arrayName = key
        args.each do |a|
          a.each do |key , value|
            attributes << key
            values << value
            if (!att.include?(key))
              att << key
            end
          end
          ret << @myClass.new(attributes, values)
          attributes = []
          values = []
        end
      end
      ret.each do |item|
        ok = true  
        puts att      
        att.each do |a|
          begin
            if(item.send("#{a}") == nil)
              ok = false
            end
          rescue
            puts "hade inte metoden"
          end
        end
        if (ok)
          tmp << item
        end
      end
      return tmp
  end

  def Model.createNewClass(args)
    @myClass = Object.const_set(args,Class.new)
    @myClass.class_eval %{def self.load_from_file(fileName)
      YAML.parse_file(fileName)
      end}
    @myClass.class_eval %{def initialize(attri, vali)
                           begin
                             for i in 1..attri.size() do
                               self.method("#\{attri[i-1]\}=").call(vali[i-1])
                             end
                           rescue
                             puts "Invalid value on: "+attri[i-1]+" where value is: "+vali[i-1].to_s
                           end
                         end}
  end

  def Model.createNewAttribute(args)
    Model.set_attr_reader("#{args.keys}")
    Model.set_attr_writer("#{args.keys}","#{args.values}")
    @myClass.class_eval %{class_variable_set :@@const, Array.new}
  end

  def Model.createConstraints(args)
    @myClass.class_eval %{@@const << args} 
  end

  def Model.set_attr_writer(sym, type)
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

def Model.set_attr_reader(syms)
  syms.each do|sym|
    @myClass.class_eval %{def #{sym}
    @#{sym}
  end}
end
end

def Model.makeConstraintsMethod()
  @myClass.class_eval %{def checkConstraint(attribute, value_to_be_checked)
    valid = true
    @@const.each do |p|
      p.each_pair do |key, value|
        key.each do |k|
          if(k.to_s == attribute.to_s)
            if(value.to_s.include?("! = nil"))
              if(!(value_to_be_checked != nil))
                valid = false
                break
              end
            end
            if(value.to_s.include?("size > 0"))
              if(!(value_to_be_checked.size > 0))
                valid = false
                break
              end
            end
            if(value.to_s.include?("=~ /^[A-Z]/"))
              if(!(value_to_be_checked.match(/[A-Z]/)))
                valid = false
                break
              end
            end
            if(value.to_s.include?("-30 < age && age < 75"))
              if(!(value_to_be_checked > -30 && value_to_be_checked < 75))
                valid = false
                break
              end
            end
            if(value.to_s.include?(">= 0"))
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
