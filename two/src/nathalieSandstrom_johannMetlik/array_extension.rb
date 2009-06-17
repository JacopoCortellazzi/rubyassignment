class Array
  def select_first(args)
    if args.size == 1
      stop = false
      self.each do |p|
        args.each_pair do |key, value|
        puts key
        puts value
          value.each do |v| #BUGGAR UT NÄR DET ÄR FIXNUM .each FINNS INTE PÅ FIXNUM!
            if p.send(key) == v
              #puts p.name
              #puts p.age
              return p
              stop = true
            end
          end
        end
        if stop
          break
        end
      end
    else
      attribute = args.fetch(:name)
      interval = args.fetch(:interval)
      max = interval.fetch(:max)
      if(interval.has_key?(:min))
        min = interval.fetch(:min)
        self.each do |p|
          if p.send(attribute) && p.send(attribute)>=min && p.send(attribute) <= max
            #puts p.name,p.age
            return p
            stop = true
          end
          if stop
            break
          end
        end
      else
        self.each do |p|
          if p.send(attribute) && p.send(attribute) <= max
            #puts p.name,p.age
            return p
            stop = true
          end
          if stop
            break
          end
        end
      end
    end
  end
  def select_all(args)
    tmp_arr = []
    if args.size == 1
      self.each do |p|
        args.each_pair do |key, value|
          value.each do |v|
            if p.send(key) == v
              tmp_arr << p
              #puts p.name
              #puts p.age
            end
          end
        end
      end
    else
      attribute = args.fetch(:name)
      interval = args.fetch(:interval)
      max = interval.fetch(:max)
      if(interval.has_key?(:min))
        min = interval.fetch(:min)
        self.each do |p|
          if p.send(attribute) && p.send(attribute) >= min && p.send(attribute) <= max
            tmp_arr << p
            #puts p.name,p.age
          end
        end
      else
        self.each do |p|
          if p.send(attribute) && p.send(attribute) <= max
            tmp_arr << p
            #puts p.name,p.age
          end
        end
      end
    end
    return tmp_arr
  end

  def method_missing(method_name, *args)
    method_name = method_name.to_s
    if(method_name.match(/select_first_where_(.*)_is$/) )
      Array.class_eval %{def select_first_where_#{$1}_is(param); return select_first("#{$1}" => param); end}
      return select_first($1.to_sym => args)
    elsif(method_name.match(/select_first_where_(.*)_is_in/))
      Array.class_eval %{def select_first_where_#{$1}_is_in(*param); if(param.size  > 1);return select_first(:name => "#{$1}", :interval => {:min => param[0], :max => param[1]}) ;end; end}
      if(args.size  > 1)
        return select_first(:name => $1.to_sym, :interval => {:min => args[0], :max => args[1]})
      else
        return select_first(:name => $1.to_sym, :interval => {:max => args[0]}) 
      end
    elsif(method_name.match(/select_all_where_(.*)_is$/) )
      Array.class_eval %{def select_all_where_#{$1}_is(param); return select_all("#{$1}" => param); end}
      return select_all($1.to_sym => args)
    elsif(method_name.match(/select_all_where_(.*)_is_in/))
      Array.class_eval %{def select_all_where_#{$1}_is_in(*param); if(param.size  > 1);return select_all(:name => "#{$1}", :interval => {:min => param[0], :max => param[1]}) ;end; end}
      if(args.size > 1)
        return select_all(:name => $1.to_sym, :interval=> {:min => args[0], :max => args[1]})
      else
        return select_all(:name => $1.to_sym, :interval => {:max => args[0]}) 
      end
    end
  end
end
