class Array
	def select_first(args)
		#p args
		#puts args.size
		if args.length == 1 
			select_single(:first, args)
		elsif args.length == 2
			if args[:interval].length == 2
				select_single(:first, args[:name] => (args[:interval][:min] .. args[:interval][:max]).to_a)
			elsif args[:interval].length == 1
				select_single(:first, args[:name] => (0 .. args[:interval][:max]).to_a)
			end
		else
			throw error
		end
	end

	def select_single(*args)
		print ('DEBUG: select_single, args: ')
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
		print ('DEBUG: select_all, args:')
		p args
		if args.length == 1
			p ('DEBUG: goto select_single')
			select_single(:all, args)
		elsif args.length == 2
			if args[:interval].length == 2
				select_single(:all, args[:name] => (args[:interval][:min] .. args[:interval][:max]).to_a)
			elsif args[:interval].length == 1
				select_single(:all, args[:name] => (-500 .. args[:interval][:max]).to_a)
			end
		else
			throw error
		end
	end
	
	def method_missing(method, *args)

     if attribute = method.to_s.match(/^select_first_where_(.*)_is$/)
		  eval %{def #{method.to_s}(params)
		  	print ('DEBUG: intern, params: ')
			p params
         select_first("#{attribute[1]}" => params)
			end}
			self.send(method, *args)
     elsif attribute = method.to_s.match(/^select_first_where_(.*)_is_in$/)
		  eval %{def #{method.to_s}(*params)
		  		if params.length == 1
	         	select_first(:name => "#{attribute[1]}", :interval => {:min => 0, :max => params[0]})
				elsif params.length == 2
	         	select_first(:name => "#{attribute[1]}", :interval => {:min => params[0], :max => params[1]})
				end
			end}
#			print(' DEBUG Call first: '+method.to_s+', ')
#			p args
			self.send(method, *args)

     elsif attribute = method.to_s.match(/^select_all_where_(.*)_is$/)
		  eval %{def #{method.to_s}(params)
         select_all("#{attribute[1]}" => params)
			end}
			self.send(method, *args)
     elsif attribute = method.to_s.match(/^select_all_where_(.*)_is_in$/)
		  eval %{def #{method.to_s}(*params)
		  		if params.length == 1
	         	select_all(:name => "#{attribute[1]}", :interval => {:min => 0, :max => params[0]})
				elsif params.length == 2
	         	select_all(:name => "#{attribute[1]}", :interval => {:min => params[0], :max => params[1]})
				end
			end}
			self.send(method, *args)
     end
    end
end

#	if what = method.to_s.match(/^select_(.*)$/)
#		if attribute = what.to_s.match(/^first$/)
#			select(:single, args)
#		elsif attribute = what.match(/^first_(.*)_is$/)
#			select(:single, attribute[1].to_sym => args)
#		elsif attribute = what.match(/^first_(.*)_is_in$/)
#			select(:single, attribute[1].to_sym => args)
#		elsif attribute = what.match(/^all$/)
#		elsif attribute = what.match(/^all_(.*)_is$/)
#		elsif attribute = what.match(/^all_(.*)_is_in$/)
#		end
#	end
#
