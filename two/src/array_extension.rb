class Array

	def select_first(key, value)
		puts key
		@inre_key = key
		@inre_value = value
		self.each do |p|
			puts @inre_key
			puts p.name
			#puts p.@inre_x
			#if p.x.length == y
			#	puts p.x
			#end
		end
	end
end