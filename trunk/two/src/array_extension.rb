class Array
	
	def select_first(args)
	
		#KOLLA aarGS
		puts args
		if args.size == 1
	
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
		else
			attribute = args.fetch(:name)
			interval = args.fetch(:interval)
			puts interval
			max = interval.fetch(:max)
			min = interval.fetch(:min)
			puts max,min
			puts max.class
			self.each do |p|
				if p.send(attribute) && p.send(attribute)>min && p.send(attribute) < max
					puts p.name,p.age
					stop = true
				end
				if stop
					break
				end
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