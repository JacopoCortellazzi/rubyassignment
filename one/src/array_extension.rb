class Array
	def select_first(args)
		puts args.class
		self.each do |e|
			args.each_pair do |key, value|
				if e.send(key) == value
					puts e.name
				end
			end
		end
	end
	
	def select_all
	end
end

