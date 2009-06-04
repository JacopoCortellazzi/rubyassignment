#!/usr/bin/python

import fileinput

class MatchMaker:
	combos = ["e","jnq", "rwx", "dsy", "ft", 
		"am", "civ", "bku", "lop", "ghz"]
	numberRepresentation = {}
	matchingWords = []
	
	def loadFromFile(self):
		wordFile = open("newdict.txt")
		numberFile = open("numbers.txt")
		wordLine = wordFile.readline().lower().strip()
		while wordLine:
			self.wordsToNumber(wordLine)
			wordLine = wordFile.readline().lower().strip()
		wordFile.close()
		numberLine = numberFile.readline().strip()
		while numberLine:
			self.translate(numberLine, "", 0)
			self.printWords(numberLine)
			numberLine = numberFile.readline().strip()
		numberFile.close()
	
	def printWords(self, number):
		for match in self.matchingWords:
			print number+": "+match
			self.matchingWords = []
	
	def wordsToNumber(self, word):
		self.numberRepresentation[word] = ""
		for l in word:
			for c in self.combos:
				if l in c:			
					self.numberRepresentation[word] += str(self.combos.index(c))
					
	def translate(self, number, translation, index):
		if(len(number) == 0):
			self.matchingWords.append(translation)
		while index < len(number):
			index = index+1
			firstPart = number[0 : index]
			for k in self.numberRepresentation:
				if(firstPart == self.numberRepresentation[k]):
					restPart = number[len(firstPart):]
					self.translate(restPart, translation+k+" ",0)

#from MatchMaker import MatchMaker
m = MatchMaker()
m.loadFromFile()
