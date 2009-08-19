class Array
	#def select_first(args)
	#	select(:first => args)
	#end

	def select(args)
		retarr = []
		params = args.values[0]

		self.each do |element|
			if params[:interval]
				if params[:interval][:min]
					if (element.send(params.values[0]) >= params[:interval][:min] &&
						element.send(params.values[0]) <= params[:interval][:max])
						if args[:all]
							#p (args.keys[0])
							retarr << element
						else
							return element
						end
					end
				else
					if element.send(params.values[0]) <= params[:interval][:max]
						if args[:all]
							#p (args.keys[0])
							retarr << element
						else
							return element
						end
					end
				end

			elsif params.values[0].class == Array
				#puts ('ALL: ARRAY')
				params.values[0].each do |value|
					if value == element.send(params.keys[0].to_s)
						if args[:all]
							#p (args.keys[0])
							retarr << element
						else
							return element
						end
					end
				end
			else
				#puts('ALL: SINGLE')
				puts('key: '+params.keys[0].to_s+', value: '+params.values[0].to_s)
				if element.send(params.keys[0].to_s) == params.values[0]
					if args[:all]
						retarr << element
						print('SINGLE inlooop ')
						p(retarr)
					else
						return element
					end
				end
			end
		end
		#print('calling args: ')
		#p(args)
		print('return: ')
		p(retarr)
		return retarr
	end

	#def select_all(args)
	#	return select(:all => args)
	#end
	
	def method_missing(method, *args)
		#puts('DEBUG: bla: '+method.to_s)
		#p(*args)
		#p(args)
		if method.to_s.match(/^select_first$/)
			eval %{def #{method.to_s}(params)
					return select(:first => params)
				end
			}
		elsif method.to_s.match(/^select_all$/)
			eval %{def #{method.to_s}(params)
					return select(:all => params)
				end
			}
		elsif method.to_s.match(/^select_(.*)_where_(.*)_is$/)
			p('DEBUG: case two: ')
			p(*args)
			eval %{def #{method.to_s}(params)
#					puts "$1 = "+"#{$1}"
#					puts "$2 = "+"#{$2}"
#					puts "params = "+params
#					p(self)
					#TODO här är (var) felet!
         		    return select("#{$1}".to_sym => {"#{$2}" => params})
				end
			}
		elsif method.to_s.match(/^select_(.*)_where_(.*)_is_in$/)
		  eval %{def #{method.to_s}(*params)
		  			if params.length == 1
	         		return select("#{$1}" => {:name => "#{$2}", :interval => {:max => params[0]}})
					elsif params.length == 2
	         		return select("#{$1}" => {:name => "#{$2}", :interval => {:min => params[0], :max => params[1]}})
					end
				end
		  }
		end
		self.send(method, *args)
	end
end
