class Array
	def select_first(args)
		puts args
		puts args.size
		if args.length == 1 
			select_single(args)
		elsif args.length == 2
			if args[:interval].length == 2
				select_single(args[:name] => (args[:interval][:min] .. args[:interval][:max]).to_a)
			elsif args[:interval][:max] != nil
				select_single(args[:name] => (0 .. args[:interval][:max]).to_a)
			end
		else
			throw error
		end
	end

	def select_single(args)
		key = args.keys[0]
		value = args[key]
		self.each do |e|
			if value.class == Array
				value.each do |val|
					if e.send(key) == val
						return e
						break
					end
				end
			elsif e.send(key) == value
				return e
			end
		end	
	end

	def select_all
	end
	
	def method_missing(method, *args)
        attribute = method.match(/select_first_where_(.*)_is/)[1]
        if attribute
            select_first(attribute.to_sym => *args)
        end
    end
end
