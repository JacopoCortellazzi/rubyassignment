class Array

	def select_first(args)
		stop = false
		self.each do |p|
			args.each_pair do |key, value|
				value.each do |v|
					if p.send(key) == v
						puts p.name
						puts p.age
						stop = true
					end
				end
			end
			if stop
				break
			end
		end
	end
	
	def select_all(args)
		self.each do |p|
			args.each_pair do |key, value|
				value.each do |v|
					if p.send(key) == v
						puts p.name
						puts p.age
					end
				end
			end
		end
	end
end