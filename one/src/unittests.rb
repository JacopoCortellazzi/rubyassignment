##
#  This file contains unittests for the DYPL Ruby assignment. All
#  assigments handed in must pass these tests or be failed. We
#  will then scrutinize your code to judge its quality.
#
#  Unsurprisingly, more elegant solutions will receive a higher
#  mark. Examples of elegant things include not wasting memory or
#  CPU, intelligent solutions and observations properly exploited
#  to make the code e.g., shorter, easily maintained and
#  extendable. An example of an unelegant thing is to implement
#  select_first as returning the first element of select_all.
##

require 'test/unit'
require 'yaml'

##----------------------------------------------------------------------------------

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

##----------------------------------------------------------------------------------

PERSON_SOURCE = <<EOF
title  :Test
attribute  :name, String
attribute  :age,  Fixnum
constraint :name, 'name != nil'
constraint :name, %{name.size > 0}
constraint :name, "name =~ /^[A-Z]/"
constraint :age,  %(-30 < age && age < 75)
EOF

YAML_SOURCE = <<EOF
tests:
   - name        : FauxJohan
     age         : -260
   - name        : Johan
     age         : 26
   - name        : Tobias
     age         : 29
   - name        : Beatrice
     age         : 32
   - name        : FauxBeatrice
     age         : 400
   - name        : Tobias
     age         : -29
   - name        : Marve Flexnes
     misc        : Deeeeo
EOF

##----------------------------------------------------------------------------------

class ArrayTest < Test::Unit::TestCase
  def setup
    load 'array_extension.rb'
    @johan = TestPerson.new('Johan', 26)
    @tobias = TestPerson.new('Tobias', 29)
    @beatrice = TestPerson.new('Beatrice', 32)
    @tobias_again = TestPerson.new('Tobias', -29)
    @array = [@johan, @tobias, @beatrice, @tobias_again]  
  end
  
  def teardown
  end

  def test_select_first
    assert_equal(@array.select_first( :name => 'Tobias' ), @tobias )
    assert_equal(@array.select_first( :name => ['Tobias', 'Johan'] ), @johan )
    assert_equal(@array.select_first( :name => :age, :interval => { :min => 30, :max => 32 } ), @beatrice )
    assert_equal(@array.select_first( :name => :age, :interval => { :max => 32 } ), @johan )
  end

  def test_select_all
    assert_equal(@array.select_all( :name => 'Tobias' ), [@tobias, @tobias_again] )
    assert_equal(@array.select_all( :name => ['Tobias', 'Johan'] ), [@johan, @tobias, @tobias_again] )
    assert_equal(@array.select_all( :name => :age, :interval => { :min => 30, :max => 32 } ), [@beatrice] )
    assert_equal(@array.select_all( :name => :age, :interval => { :max => 32 } ), @array )
  end

  def test_select_first_where_name_is
    assert_equal(@array.methods.include?("select_first_where_name_is"), false, 
		 "Possible cheating? select_first_where_name_is exists in Array")
    assert_equal(@array.select_first_where_name_is( 'Tobias' ), @tobias )
    assert(@array.methods.include?("select_first_where_name_is"), 
	   "select_first_where_name_is not added to Array after first use")
    assert_equal(@array.select_first_where_name_is( ['Tobias', 'Johan'] ), @johan )
  end

  def test_select_first_where_age_is_in
    assert_equal(@array.methods.include?("select_first_where_age_is_in"), false,
		 "Possible cheating? select_first_where_age_is_in exists in Array")
    assert_equal(@array.select_first_where_age_is_in( 30, 32 ), @beatrice )
    assert(@array.methods.include?("select_first_where_age_is_in"),
	   "select_first_where_age_is_in not added to Array after first use")
  end

  def test_select_all_where_name_is
    assert_equal(@array.methods.include?("select_all_where_name_is"), false,
		 "Possible cheating? select_all_where_name_is exists in Array")
    assert_equal(@array.select_all_where_name_is( 'Tobias' ), [@tobias, @tobias_again] )
    assert(@array.methods.include?("select_all_where_name_is"),
	   "select_first_where_name_is not added to Array after first use")
    assert_equal(@array.select_all_where_name_is( ['Tobias', 'Johan'] ), [@johan, @tobias, @tobias_again] )
    assert_equal(@array.select_all_where_name_is( ['FauxJohan', 'FauxBeatrice'] ), [] )
    assert_equal(@array.select_all_where_name_is( 'Marve Flexnes' ), [] )
  end
end

class GeneratorTest < Test::Unit::TestCase
  def setup
    # Create the input files
    File::open("personage.txt", "w") { |f| f << PERSON_SOURCE }
    File::open("entries.yml", "w") { |f| f << YAML_SOURCE }
    
    # Load the code generation lib
    load 'code_generation.rb'

    # Create a class from personage.txt
    @person_class = Model.generate( './personage.txt' )
    assert_not_equal(@person_class, nil, "Model::generate returned nil")

    # Load entries from file using the newly created class
    @array = @person_class.load_from_file( './entries.yml' )

    # Make sure only the correct elements were loaded
    assert_equal(@array.size, 4, "Faulty elements from entries not removed")

    # Make sure order is preserved by loading
    @johan, @tobias, @beatrice, @tobias_again = *@array
    errmsg = "Seems like loading from YAML file is not order preserving"
    assert_equal(@johan.name, "Johan", errmsg)
    assert_equal(@tobias.name, "Tobias", errmsg)
    assert_equal(@beatrice.name, "Beatrice", errmsg)
    assert_equal(@tobias_again.name, "Tobias", errmsg)
  end
  
  def teardown
    # Delete input files
    File::delete("personage.txt")
    File::delete("entries.yml")
  end
  
  def test_cheating_misunderstanding
    assert_raise NameError do
      Object::Person
    end
    ["array_extension.rb", "code_generation.rb"].each do |fn|
      File::open(fn, "r") do |f|
  assert_equal(f.readlines.any? {|l| l =~ /personclass/ }, false, "Looks like you've looked to closely at the unit test suite!")
      end
    end
  end

  def test_select_first
    assert_equal(@array.select_first( :name => 'Tobias' ), @tobias )
    assert_equal(@array.select_first( :name => ['Tobias', 'Johan'] ), @johan )
    assert_equal(@array.select_first( :name => :age, :interval => { :min => 30, :max => 32 } ), @beatrice )
    assert_equal(@array.select_first( :name => :age, :interval => { :max => 32 } ), @johan )
  end

  def test_select_all
    assert_equal(@array.select_all( :name => 'Tobias' ), [@tobias, @tobias_again] )
    assert_equal(@array.select_all( :name => ['Tobias', 'Johan'] ), [@johan, @tobias, @tobias_again] )
    assert_equal(@array.select_all( :name => :age, :interval => { :min => 30, :max => 32 } ), [@beatrice] )
    assert_equal(@array.select_all( :name => :age, :interval => { :max => 32 } ), @array )
  end

  def test_select_first_where_name_is
    assert_equal(@array.select_first_where_name_is( 'Tobias' ), @tobias )
    assert_equal(@array.select_first_where_name_is( ['Tobias', 'Johan'] ), @johan )
  end

  def test_select_first_where_age_is_in
    assert_equal(@array.select_first_where_age_is_in( 30, 32 ), @beatrice )
  end

  def test_select_all_where_name_is
    assert_equal(@array.select_all_where_name_is( 'Tobias' ), [@tobias, @tobias_again] )
    assert_equal(@array.select_all_where_name_is( ['Tobias', 'Johan'] ), [@johan, @tobias, @tobias_again] )
    assert_equal(@array.select_all_where_name_is( ['FauxJohan', 'FauxBeatrice'] ), [] )
    assert_equal(@array.select_all_where_name_is( 'Marve Flexnes' ), [] )
  end

  def test_indata_checking
    assert_raise RuntimeError do
      @johan.name = 345678 # Type checking
    end
    assert_raise RuntimeError do
      @johan.age = "30" # Type checking
    end
    assert_raise RuntimeError do
      @johan.name = nil # Not nil checking
    end
    assert_raise RuntimeError do
      @johan.name = "" # size > 0 checking
    end
    assert_raise RuntimeError do
      @johan.name = "johan" # ^[A-Z] checking
    end
    assert_raise RuntimeError do
      @johan.age = -30 # Bounds checking
    end
    assert_raise RuntimeError do
      @johan.age = 75 # Bounds checking
    end
    # Use other valid values and see that they are updated properly
    @johan.name = @tobias.name
    assert_equal(@johan.name, @tobias.name)
    @johan.age = @tobias.age
    assert_equal(@johan.age, @tobias.age)
  end
  
end
