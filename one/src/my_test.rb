require 'test/unit'

class Array
  def dd(other)
    return false unless other.kind_of? Array
    return false unless other.size == self.size
    return self.all? { |e| other.include?( e ) }
  end
end

class TestPerson
  attr_accessor :name, :age
  def initialize(name, age)
    @name, @age = name, age
  end
  def <=>(other); @age <=> other.age; end
  def inspect
    "#{@name}(#{@age})"
  end
end

class ArrayTest < Test::Unit::TestCase
  def setup
    load 'array_extension.rb'
    @johan = TestPerson.new('Johan', 26)
    @tobias = TestPerson.new('Tobias', 29)
    @beatrice = TestPerson.new('Beatrice', 32)
    @tobias_again = TestPerson.new('Tobias', -29)
    @array = [@johan, @tobias, @beatrice, @tobias_again]  
  end

  def test_select_all_where_name_is
    assert_equal(@array.select_all(:name => 'Tobias' ), [@tobias, @tobias_again] )
    assert_equal(@array.select_all_where_name_is( 'Tobias' ), [@tobias, @tobias_again] )
  end
end
