class Array
	def select_first(args)
		puts args.class
		args.each_key do |key|
			self.each do |e|
				puts e.#{key}
			end
		end
	end
	
	def select_all
	end
end

