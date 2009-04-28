class Array
	def select_first(args)
		#puts args
		#puts args.size
		if args.length == 1 
			select_single(:first, args)
		elsif args.length == 2
			if args[:interval].length == 2
				select_single(:first, args[:name] => (args[:interval][:min] .. args[:interval][:max]).to_a)
			elsif args[:interval][:max] != nil
				select_single(:first, args[:name] => (0 .. args[:interval][:max]).to_a)
			end
		else
			throw error
		end
	end

	def select_single(*args)
		p args
		key = args[1].keys[0]
		value = args[1][key]
		retarr = []
		self.each do |e|
			if value.class == Array
				value.each do |val|
					if e.send(key) == val
						if args[0] == :first
							return e
						elsif args[0] == :all
							retarr<<e
						end
					end
				end
			elsif e.send(key) == value
				if args[0] == :first
					return e
				elsif args[0] == :all
					retarr<<e
				end
			end
		end
		return retarr
	end

	def select_all(args)
		if args.length == 1 
			select_single(:all, args)
		elsif args.length == 2
			if args[:interval].length == 2
				select_single(:all, args[:name] => (args[:interval][:min] .. args[:interval][:max]).to_a)
			elsif args[:interval][:max] != nil
				select_single(:all, args[:name] => (0 .. args[:interval][:max]).to_a)
			end
		else
			throw error
		end
	end
	
	def method_missing(method, *args)
        if attribute = method.to_s.match(/^select_first_where_(.*)_is$/)
            select_first(attribute[1].to_sym => args)
        elsif attribute = method.to_s.match(/^select_first_where_(.*)_is_in$/)
            select_first(:name => attribute[1].to_sym, :interval => {:min => args[0], 
                            :max => args[1]})
        elsif attribute = method.to_s.match(/^select_all_where_(.*)_is$/)
            select_all(attribute[1].to_sym => args)
        elsif attribute = method.to_s.match(/^select_all_where_(.*)_is_in$/)
            select_all(:name => attribute[1].to_sym, :interval => {:min => args[0], 
                            :max => args[1]})
        end
    end
end
