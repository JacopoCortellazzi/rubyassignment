class Array

	def select(args)
		retarr = []
		params = args.values[0]

		self.each do |element|
			if params[:interval]
				if params[:interval][:min]
					if (element.send(params.values[0]) >= params[:interval][:min] &&
						element.send(params.values[0]) <= params[:interval][:max])
						if args[:all]
							retarr << element
						else
							return element
						end
					end
				else
					if element.send(params.values[0]) <= params[:interval][:max]
						if args[:all]
							retarr << element
						else
							return element
						end
					end
				end

			elsif params.values[0].class == Array
				params.values[0].each do |value|
					if value == element.send(params.keys[0].to_s)
						if args[:all]
							retarr << element
						else
							return element
						end
					end
				end
			else
				if element.send(params.keys[0].to_s) == params.values[0]
					if args[:all]
						retarr << element
					else
						return element
					end
				end
			end
		end
		return retarr
	end
	
	def method_missing(method, *args)
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
			eval %{def #{method.to_s}(params)
         		    return select("#{$1}".to_sym => {"#{$2}".to_sym => params})
				end
			}
		elsif method.to_s.match(/^select_(.*)_where_(.*)_is_in$/)
		  eval %{def #{method.to_s}(*params)
		  			if params.length == 1
	         		return select("#{$1}".to_sym => {:name => "#{$2}".to_sym, :interval => {:max => params[0]}})
					elsif params.length == 2
	         		return select("#{$1}".to_sym => {:name => "#{$2}".to_sym, :interval => {:min => params[0], :max => params[1]}})
					end
				end
		  }
		end
		self.send(method, *args)
	end
end
