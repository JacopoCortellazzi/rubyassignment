import re
import math
from JythonTranslater import Jtrans

class MyJythonApp( Jtrans ):
    x = 100
    y = 100
    current_angle = 0
    pen_is_down = False
    pen_down = re.compile("\s*pen_down\n*")
    pen_up = re.compile("\s*pen_up\n*")
    move_forward = re.compile("\s*move_forward\n*")
    move_backward = re.compile("\s*move_backward\n*")
    move_ = re.compile("\s*move\([0-9 A-Z]+\+*\**-*[0-9]*,[0-9 A-Z]+\+*\**-*[0-9]*\)\n*")
    turn_cw = re.compile("\s*turn_cw\([0-9]+\+*\**-*[0-9]*\)\n*")
    turn_ccw = re.compile("\s*turn_ccw\([0-9]+\+*\**-*[0-9]*\)\n*")
    put_ = re.compile("\s*put\([0-9]+\,[0-9]+\,[0-9]+\)\n*")
    for_end = re.compile("\s*for.*end\n*", re.DOTALL)

    def calculateRadian( self ):
        return self.current_angle * (math.pi/180)

    def calculateAngle( self, angle ):
        tmp = divmod((self.current_angle + angle), 360)
        self.current_angle = tmp[1]
        
    def penDown( self ):
        self.pen_is_down = True

    def penUp( self ):
        self.pen_is_down = False

    def moveForward( self ):
        self.x += math.cos(self.calculateRadian())
        self.y += math.sin(self.calculateRadian())
        if self.pen_is_down:
            self.obj.setPixel(int(self.x), int(self.y))

    def moveBackward( self ):
        self.x -= math.cos(self.calculateRadian())
        self.y -= math.sin(self.calculateRadian())
        if self.pen_is_down:
            self.obj.setPixel(int(self.x), int(self.y))

    def move( self, l ):
        l = l.strip("\s*move\(\,\)\n")
        l = l.split(',')
        self.steps = int( eval(l[0]) )
        self.calculateAngle( int( eval(l[1]) ) )
        self.count = 0
        while( self.count < self.steps ):
            self.moveForward()
            self.count = self.count + 1
            
    def turnCw( self, l ):
        l = l.strip("\s*turn_cw\(\)\n")
        #print "In trunCw: "+l
        self.calculateAngle( int(eval(l)) )

    def turnCcw( self, l ):
        l = l.strip("\s*turn_ccw\(\)\n")
        #print "In trunCcw: "+l
        #self.temp = 360 - int(l)
        self.calculateAngle( -int(eval(l)) )
        
    def put( self, l ):
        l = l.strip("\s*put\(\,\)\n")
        l = l.split(',')
        self.x = int(l[0])
        self.y = int(l[1])
        self.current_angle = int(l[2])

    def actionPerformed( self, event ):
        self.myMatching(self.obj.getCode())
    
    def regMatching(self, code):
        if self.pen_down.match( code ):
            self.penDown()
            code = code[self.pen_down.match( code ).end():]        
        elif self.pen_up.match( code ):
            self.penUp()
            code = code[self.pen_up.match( code ).end():]
        elif self.move_forward.match( code ):
            self.moveForward()
            code = code[self.move_forward.match( code ).end():]
        elif self.move_backward.match( code ):
            self.moveBackward()
            code = code[self.move_backward.match( code ).end():]
        elif self.move_.match( code ):
            self.move( self.move_.match( code ).group() )
            code = code[self.move_.match( code ).end():]
        elif self.turn_cw.match( code ):
            self.turnCw( self.turn_cw.match( code ).group() )
            code = code[self.turn_cw.match( code ).end():]
        elif self.turn_ccw.match( code ):
            self.turnCcw( self.turn_ccw.match( code ).group() )
            code = code[self.turn_ccw.match( code ).end():]
        elif self.put_.match( code ):
            self.put( self.put_.match( code ) )
            code = code[self.put_.match( code ).end():]
        elif self.for_end.match( code ):
            wholeString = self.for_end.match( code ).group()
            r = re.compile("for\s*.*\s*.*\s*.*\s*do")
            partOfString = r.match(wholeString)
            numbers = re.findall("[0-9]+", partOfString.group())
            x = numbers[0]
            times = numbers[1]
            wholeString = wholeString.strip("for X="+x+" to "+times+" do end\n")
            self.forMethod(wholeString, x, times)
            code = code[self.for_end.match( code ).end():]
        return code

    def forMethod(self, code, x, times):
        print code, x, times
        if code.find("X"):
            code = code.replace("X", str(x))
        for i in range(int(x), int(times)):
            tmp = code
            while(tmp):
                self.regMatching(tmp)
                tmp = self.regMatching(tmp)

    def myMatching(self, code):
        while(code):
            code = self.regMatching(code)

    def setDYPL( self, obj ):
	    self.obj = obj
