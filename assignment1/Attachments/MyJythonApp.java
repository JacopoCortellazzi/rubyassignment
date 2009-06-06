import org.python.core.*;

public class MyJythonApp extends JythonTranslater.Jtrans implements org.python.core.PyProxy, org.python.core.ClassDictInit {
    static String[] jpy$mainProperties = new String[] {"python.modules.builtin", "exceptions:org.python.core.exceptions"};
    static String[] jpy$proxyProperties = new String[] {"python.modules.builtin", "exceptions:org.python.core.exceptions", "python.options.showJavaExceptions", "true"};
    static String[] jpy$packages = new String[] {};
    
    public static class _PyInner extends PyFunctionTable implements PyRunnable {
        private static PyObject i$0;
        private static PyObject i$1;
        private static PyObject s$2;
        private static PyObject s$3;
        private static PyObject s$4;
        private static PyObject s$5;
        private static PyObject s$6;
        private static PyObject s$7;
        private static PyObject s$8;
        private static PyObject s$9;
        private static PyObject s$10;
        private static PyObject i$11;
        private static PyObject i$12;
        private static PyObject i$13;
        private static PyObject s$14;
        private static PyObject s$15;
        private static PyObject s$16;
        private static PyObject s$17;
        private static PyObject s$18;
        private static PyObject i$19;
        private static PyObject s$20;
        private static PyObject s$21;
        private static PyObject s$22;
        private static PyObject s$23;
        private static PyObject s$24;
        private static PyObject s$25;
        private static PyObject s$26;
        private static PyFunctionTable funcTable;
        private static PyCode c$0_calculateRadian;
        private static PyCode c$1_calculateAngle;
        private static PyCode c$2_penDown;
        private static PyCode c$3_penUp;
        private static PyCode c$4_moveForward;
        private static PyCode c$5_moveBackward;
        private static PyCode c$6_move;
        private static PyCode c$7_turnCw;
        private static PyCode c$8_turnCcw;
        private static PyCode c$9_put;
        private static PyCode c$10_actionPerformed;
        private static PyCode c$11_regMatching;
        private static PyCode c$12_forMethod;
        private static PyCode c$13_myMatching;
        private static PyCode c$14_setDYPL;
        private static PyCode c$15_MyJythonApp;
        private static PyCode c$16_main;
        private static void initConstants() {
            i$0 = Py.newInteger(100);
            i$1 = Py.newInteger(0);
            s$2 = Py.newString("\\s*pen_down\012*");
            s$3 = Py.newString("\\s*pen_up\012*");
            s$4 = Py.newString("\\s*move_forward\012*");
            s$5 = Py.newString("\\s*move_backward\012*");
            s$6 = Py.newString("\\s*move\\([0-9 A-Z]+\\+*\\**-*[0-9]*,[0-9 A-Z]+\\+*\\**-*[0-9]*\\)\012*");
            s$7 = Py.newString("\\s*turn_cw\\([0-9]+\\+*\\**-*[0-9]*\\)\012*");
            s$8 = Py.newString("\\s*turn_ccw\\([0-9]+\\+*\\**-*[0-9]*\\)\012*");
            s$9 = Py.newString("\\s*put\\([0-9]+\\,[0-9]+\\,[0-9]+\\)\012*");
            s$10 = Py.newString("\\s*for.*end\012*");
            i$11 = Py.newInteger(180);
            i$12 = Py.newInteger(360);
            i$13 = Py.newInteger(1);
            s$14 = Py.newString("\\s*move\\(\\,\\)\012");
            s$15 = Py.newString(",");
            s$16 = Py.newString("\\s*turn_cw\\(\\)\012");
            s$17 = Py.newString("\\s*turn_ccw\\(\\)\012");
            s$18 = Py.newString("\\s*put\\(\\,\\)\012");
            i$19 = Py.newInteger(2);
            s$20 = Py.newString("for\\s*.*\\s*.*\\s*.*\\s*do");
            s$21 = Py.newString("[0-9]+");
            s$22 = Py.newString("for X=");
            s$23 = Py.newString(" to ");
            s$24 = Py.newString(" do end\012");
            s$25 = Py.newString("X");
            s$26 = Py.newString("/home/paulsson/Documents/School/DYPL/Assignments/Assignment 1/Attachments/MyJythonApp.py");
            funcTable = new _PyInner();
            c$0_calculateRadian = Py.newCode(1, new String[] {"self"}, "/home/paulsson/Documents/School/DYPL/Assignments/Assignment 1/Attachments/MyJythonApp.py", "calculateRadian", false, false, funcTable, 0, null, null, 0, 17);
            c$1_calculateAngle = Py.newCode(2, new String[] {"self", "angle", "tmp"}, "/home/paulsson/Documents/School/DYPL/Assignments/Assignment 1/Attachments/MyJythonApp.py", "calculateAngle", false, false, funcTable, 1, null, null, 0, 17);
            c$2_penDown = Py.newCode(1, new String[] {"self"}, "/home/paulsson/Documents/School/DYPL/Assignments/Assignment 1/Attachments/MyJythonApp.py", "penDown", false, false, funcTable, 2, null, null, 0, 17);
            c$3_penUp = Py.newCode(1, new String[] {"self"}, "/home/paulsson/Documents/School/DYPL/Assignments/Assignment 1/Attachments/MyJythonApp.py", "penUp", false, false, funcTable, 3, null, null, 0, 17);
            c$4_moveForward = Py.newCode(1, new String[] {"self"}, "/home/paulsson/Documents/School/DYPL/Assignments/Assignment 1/Attachments/MyJythonApp.py", "moveForward", false, false, funcTable, 4, null, null, 0, 17);
            c$5_moveBackward = Py.newCode(1, new String[] {"self"}, "/home/paulsson/Documents/School/DYPL/Assignments/Assignment 1/Attachments/MyJythonApp.py", "moveBackward", false, false, funcTable, 5, null, null, 0, 17);
            c$6_move = Py.newCode(2, new String[] {"self", "l"}, "/home/paulsson/Documents/School/DYPL/Assignments/Assignment 1/Attachments/MyJythonApp.py", "move", false, false, funcTable, 6, null, null, 0, 17);
            c$7_turnCw = Py.newCode(2, new String[] {"self", "l"}, "/home/paulsson/Documents/School/DYPL/Assignments/Assignment 1/Attachments/MyJythonApp.py", "turnCw", false, false, funcTable, 7, null, null, 0, 17);
            c$8_turnCcw = Py.newCode(2, new String[] {"self", "l"}, "/home/paulsson/Documents/School/DYPL/Assignments/Assignment 1/Attachments/MyJythonApp.py", "turnCcw", false, false, funcTable, 8, null, null, 0, 17);
            c$9_put = Py.newCode(2, new String[] {"self", "l"}, "/home/paulsson/Documents/School/DYPL/Assignments/Assignment 1/Attachments/MyJythonApp.py", "put", false, false, funcTable, 9, null, null, 0, 17);
            c$10_actionPerformed = Py.newCode(2, new String[] {"self", "event"}, "/home/paulsson/Documents/School/DYPL/Assignments/Assignment 1/Attachments/MyJythonApp.py", "actionPerformed", false, false, funcTable, 10, null, null, 0, 17);
            c$11_regMatching = Py.newCode(2, new String[] {"self", "code", "r", "times", "partOfString", "x", "wholeString", "numbers"}, "/home/paulsson/Documents/School/DYPL/Assignments/Assignment 1/Attachments/MyJythonApp.py", "regMatching", false, false, funcTable, 11, null, null, 0, 17);
            c$12_forMethod = Py.newCode(4, new String[] {"self", "code", "x", "times", "tmp", "i"}, "/home/paulsson/Documents/School/DYPL/Assignments/Assignment 1/Attachments/MyJythonApp.py", "forMethod", false, false, funcTable, 12, null, null, 0, 17);
            c$13_myMatching = Py.newCode(2, new String[] {"self", "code"}, "/home/paulsson/Documents/School/DYPL/Assignments/Assignment 1/Attachments/MyJythonApp.py", "myMatching", false, false, funcTable, 13, null, null, 0, 17);
            c$14_setDYPL = Py.newCode(2, new String[] {"self", "obj"}, "/home/paulsson/Documents/School/DYPL/Assignments/Assignment 1/Attachments/MyJythonApp.py", "setDYPL", false, false, funcTable, 14, null, null, 0, 17);
            c$15_MyJythonApp = Py.newCode(0, new String[] {}, "/home/paulsson/Documents/School/DYPL/Assignments/Assignment 1/Attachments/MyJythonApp.py", "MyJythonApp", false, false, funcTable, 15, null, null, 0, 16);
            c$16_main = Py.newCode(0, new String[] {}, "/home/paulsson/Documents/School/DYPL/Assignments/Assignment 1/Attachments/MyJythonApp.py", "main", false, false, funcTable, 16, null, null, 0, 16);
        }
        
        
        public PyCode getMain() {
            if (c$16_main == null) _PyInner.initConstants();
            return c$16_main;
        }
        
        public PyObject call_function(int index, PyFrame frame) {
            switch (index){
                case 0:
                return _PyInner.calculateRadian$1(frame);
                case 1:
                return _PyInner.calculateAngle$2(frame);
                case 2:
                return _PyInner.penDown$3(frame);
                case 3:
                return _PyInner.penUp$4(frame);
                case 4:
                return _PyInner.moveForward$5(frame);
                case 5:
                return _PyInner.moveBackward$6(frame);
                case 6:
                return _PyInner.move$7(frame);
                case 7:
                return _PyInner.turnCw$8(frame);
                case 8:
                return _PyInner.turnCcw$9(frame);
                case 9:
                return _PyInner.put$10(frame);
                case 10:
                return _PyInner.actionPerformed$11(frame);
                case 11:
                return _PyInner.regMatching$12(frame);
                case 12:
                return _PyInner.forMethod$13(frame);
                case 13:
                return _PyInner.myMatching$14(frame);
                case 14:
                return _PyInner.setDYPL$15(frame);
                case 15:
                return _PyInner.MyJythonApp$16(frame);
                case 16:
                return _PyInner.main$17(frame);
                default:
                return null;
            }
        }
        
        private static PyObject calculateRadian$1(PyFrame frame) {
            return frame.getlocal(0).__getattr__("current_angle")._mul(frame.getglobal("math").__getattr__("pi")._div(i$11));
        }
        
        private static PyObject calculateAngle$2(PyFrame frame) {
            frame.setlocal(2, frame.getglobal("divmod").__call__(frame.getlocal(0).__getattr__("current_angle")._add(frame.getlocal(1)), i$12));
            frame.getlocal(0).__setattr__("current_angle", frame.getlocal(2).__getitem__(i$13));
            return Py.None;
        }
        
        private static PyObject penDown$3(PyFrame frame) {
            frame.getlocal(0).__setattr__("pen_is_down", frame.getglobal("True"));
            return Py.None;
        }
        
        private static PyObject penUp$4(PyFrame frame) {
            frame.getlocal(0).__setattr__("pen_is_down", frame.getglobal("False"));
            return Py.None;
        }
        
        private static PyObject moveForward$5(PyFrame frame) {
            // Temporary Variables
            PyObject t$0$PyObject;
            
            // Code
            t$0$PyObject = frame.getlocal(0);
            t$0$PyObject.__setattr__("x", t$0$PyObject.__getattr__("x").__iadd__(frame.getglobal("math").__getattr__("cos").__call__(frame.getlocal(0).invoke("calculateRadian"))));
            t$0$PyObject = frame.getlocal(0);
            t$0$PyObject.__setattr__("y", t$0$PyObject.__getattr__("y").__iadd__(frame.getglobal("math").__getattr__("sin").__call__(frame.getlocal(0).invoke("calculateRadian"))));
            if (frame.getlocal(0).__getattr__("pen_is_down").__nonzero__()) {
                frame.getlocal(0).__getattr__("obj").invoke("setPixel", frame.getglobal("int").__call__(frame.getlocal(0).__getattr__("x")), frame.getglobal("int").__call__(frame.getlocal(0).__getattr__("y")));
            }
            return Py.None;
        }
        
        private static PyObject moveBackward$6(PyFrame frame) {
            // Temporary Variables
            PyObject t$0$PyObject;
            
            // Code
            t$0$PyObject = frame.getlocal(0);
            t$0$PyObject.__setattr__("x", t$0$PyObject.__getattr__("x").__isub__(frame.getglobal("math").__getattr__("cos").__call__(frame.getlocal(0).invoke("calculateRadian"))));
            t$0$PyObject = frame.getlocal(0);
            t$0$PyObject.__setattr__("y", t$0$PyObject.__getattr__("y").__isub__(frame.getglobal("math").__getattr__("sin").__call__(frame.getlocal(0).invoke("calculateRadian"))));
            if (frame.getlocal(0).__getattr__("pen_is_down").__nonzero__()) {
                frame.getlocal(0).__getattr__("obj").invoke("setPixel", frame.getglobal("int").__call__(frame.getlocal(0).__getattr__("x")), frame.getglobal("int").__call__(frame.getlocal(0).__getattr__("y")));
            }
            return Py.None;
        }
        
        private static PyObject move$7(PyFrame frame) {
            frame.setlocal(1, frame.getlocal(1).invoke("strip", s$14));
            frame.setlocal(1, frame.getlocal(1).invoke("split", s$15));
            frame.getlocal(0).__setattr__("steps", frame.getglobal("int").__call__(frame.getglobal("eval").__call__(frame.getlocal(1).__getitem__(i$1))));
            frame.getlocal(0).invoke("calculateAngle", frame.getglobal("int").__call__(frame.getglobal("eval").__call__(frame.getlocal(1).__getitem__(i$13))));
            frame.getlocal(0).__setattr__("count", i$1);
            while (frame.getlocal(0).__getattr__("count")._lt(frame.getlocal(0).__getattr__("steps")).__nonzero__()) {
                frame.getlocal(0).invoke("moveForward");
                frame.getlocal(0).__setattr__("count", frame.getlocal(0).__getattr__("count")._add(i$13));
            }
            return Py.None;
        }
        
        private static PyObject turnCw$8(PyFrame frame) {
            frame.setlocal(1, frame.getlocal(1).invoke("strip", s$16));
            frame.getlocal(0).invoke("calculateAngle", frame.getglobal("int").__call__(frame.getglobal("eval").__call__(frame.getlocal(1))));
            return Py.None;
        }
        
        private static PyObject turnCcw$9(PyFrame frame) {
            frame.setlocal(1, frame.getlocal(1).invoke("strip", s$17));
            frame.getlocal(0).invoke("calculateAngle", frame.getglobal("int").__call__(frame.getglobal("eval").__call__(frame.getlocal(1))).__neg__());
            return Py.None;
        }
        
        private static PyObject put$10(PyFrame frame) {
            frame.setlocal(1, frame.getlocal(1).invoke("strip", s$18));
            frame.setlocal(1, frame.getlocal(1).invoke("split", s$15));
            frame.getlocal(0).__setattr__("x", frame.getglobal("int").__call__(frame.getlocal(1).__getitem__(i$1)));
            frame.getlocal(0).__setattr__("y", frame.getglobal("int").__call__(frame.getlocal(1).__getitem__(i$13)));
            frame.getlocal(0).__setattr__("current_angle", frame.getglobal("int").__call__(frame.getlocal(1).__getitem__(i$19)));
            return Py.None;
        }
        
        private static PyObject actionPerformed$11(PyFrame frame) {
            frame.getlocal(0).invoke("myMatching", frame.getlocal(0).__getattr__("obj").invoke("getCode"));
            return Py.None;
        }
        
        private static PyObject regMatching$12(PyFrame frame) {
            if (frame.getlocal(0).__getattr__("pen_down").invoke("match", frame.getlocal(1)).__nonzero__()) {
                frame.getlocal(0).invoke("penDown");
                frame.setlocal(1, frame.getlocal(1).__getslice__(frame.getlocal(0).__getattr__("pen_down").invoke("match", frame.getlocal(1)).invoke("end"), null, null));
            }
            else {
                if (frame.getlocal(0).__getattr__("pen_up").invoke("match", frame.getlocal(1)).__nonzero__()) {
                    frame.getlocal(0).invoke("penUp");
                    frame.setlocal(1, frame.getlocal(1).__getslice__(frame.getlocal(0).__getattr__("pen_up").invoke("match", frame.getlocal(1)).invoke("end"), null, null));
                }
                else {
                    if (frame.getlocal(0).__getattr__("move_forward").invoke("match", frame.getlocal(1)).__nonzero__()) {
                        frame.getlocal(0).invoke("moveForward");
                        frame.setlocal(1, frame.getlocal(1).__getslice__(frame.getlocal(0).__getattr__("move_forward").invoke("match", frame.getlocal(1)).invoke("end"), null, null));
                    }
                    else {
                        if (frame.getlocal(0).__getattr__("move_backward").invoke("match", frame.getlocal(1)).__nonzero__()) {
                            frame.getlocal(0).invoke("moveBackward");
                            frame.setlocal(1, frame.getlocal(1).__getslice__(frame.getlocal(0).__getattr__("move_backward").invoke("match", frame.getlocal(1)).invoke("end"), null, null));
                        }
                        else {
                            if (frame.getlocal(0).__getattr__("move_").invoke("match", frame.getlocal(1)).__nonzero__()) {
                                frame.getlocal(0).invoke("move", frame.getlocal(0).__getattr__("move_").invoke("match", frame.getlocal(1)).invoke("group"));
                                frame.setlocal(1, frame.getlocal(1).__getslice__(frame.getlocal(0).__getattr__("move_").invoke("match", frame.getlocal(1)).invoke("end"), null, null));
                            }
                            else {
                                if (frame.getlocal(0).__getattr__("turn_cw").invoke("match", frame.getlocal(1)).__nonzero__()) {
                                    frame.getlocal(0).invoke("turnCw", frame.getlocal(0).__getattr__("turn_cw").invoke("match", frame.getlocal(1)).invoke("group"));
                                    frame.setlocal(1, frame.getlocal(1).__getslice__(frame.getlocal(0).__getattr__("turn_cw").invoke("match", frame.getlocal(1)).invoke("end"), null, null));
                                }
                                else {
                                    if (frame.getlocal(0).__getattr__("turn_ccw").invoke("match", frame.getlocal(1)).__nonzero__()) {
                                        frame.getlocal(0).invoke("turnCcw", frame.getlocal(0).__getattr__("turn_ccw").invoke("match", frame.getlocal(1)).invoke("group"));
                                        frame.setlocal(1, frame.getlocal(1).__getslice__(frame.getlocal(0).__getattr__("turn_ccw").invoke("match", frame.getlocal(1)).invoke("end"), null, null));
                                    }
                                    else {
                                        if (frame.getlocal(0).__getattr__("put_").invoke("match", frame.getlocal(1)).__nonzero__()) {
                                            frame.getlocal(0).invoke("put", frame.getlocal(0).__getattr__("put_").invoke("match", frame.getlocal(1)));
                                            frame.setlocal(1, frame.getlocal(1).__getslice__(frame.getlocal(0).__getattr__("put_").invoke("match", frame.getlocal(1)).invoke("end"), null, null));
                                        }
                                        else {
                                            if (frame.getlocal(0).__getattr__("for_end").invoke("match", frame.getlocal(1)).__nonzero__()) {
                                                frame.setlocal(6, frame.getlocal(0).__getattr__("for_end").invoke("match", frame.getlocal(1)).invoke("group"));
                                                frame.setlocal(2, frame.getglobal("re").__getattr__("compile").__call__(s$20));
                                                frame.setlocal(4, frame.getlocal(2).invoke("match", frame.getlocal(6)));
                                                frame.setlocal(7, frame.getglobal("re").__getattr__("findall").__call__(s$21, frame.getlocal(4).invoke("group")));
                                                frame.setlocal(5, frame.getlocal(7).__getitem__(i$1));
                                                frame.setlocal(3, frame.getlocal(7).__getitem__(i$13));
                                                frame.setlocal(6, frame.getlocal(6).invoke("strip", s$22._add(frame.getlocal(5))._add(s$23)._add(frame.getlocal(3))._add(s$24)));
                                                frame.getlocal(0).invoke("forMethod", new PyObject[] {frame.getlocal(6), frame.getlocal(5), frame.getlocal(3)});
                                                frame.setlocal(1, frame.getlocal(1).__getslice__(frame.getlocal(0).__getattr__("for_end").invoke("match", frame.getlocal(1)).invoke("end"), null, null));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return frame.getlocal(1);
        }
        
        private static PyObject forMethod$13(PyFrame frame) {
            // Temporary Variables
            PyObject t$0$PyObject, t$1$PyObject;
            
            // Code
            Py.printComma(Py.None, frame.getlocal(1));
            Py.printComma(Py.None, frame.getlocal(2));
            Py.println(Py.None, frame.getlocal(3));
            if (frame.getlocal(1).invoke("find", s$25).__nonzero__()) {
                frame.setlocal(1, frame.getlocal(1).invoke("replace", s$25, frame.getglobal("str").__call__(frame.getlocal(2))));
            }
            t$0$PyObject = frame.getglobal("range").__call__(frame.getglobal("int").__call__(frame.getlocal(2)), frame.getglobal("int").__call__(frame.getlocal(3))).__iter__();
            while ((t$1$PyObject = t$0$PyObject.__iternext__()) != null) {
                frame.setlocal(5, t$1$PyObject);
                frame.setlocal(4, frame.getlocal(1));
                while (frame.getlocal(4).__nonzero__()) {
                    frame.getlocal(0).invoke("regMatching", frame.getlocal(4));
                    frame.setlocal(4, frame.getlocal(0).invoke("regMatching", frame.getlocal(4)));
                }
            }
            return Py.None;
        }
        
        private static PyObject myMatching$14(PyFrame frame) {
            while (frame.getlocal(1).__nonzero__()) {
                frame.setlocal(1, frame.getlocal(0).invoke("regMatching", frame.getlocal(1)));
            }
            return Py.None;
        }
        
        private static PyObject setDYPL$15(PyFrame frame) {
            frame.getlocal(0).__setattr__("obj", frame.getlocal(1));
            return Py.None;
        }
        
        private static PyObject MyJythonApp$16(PyFrame frame) {
            frame.setlocal("x", i$0);
            frame.setlocal("y", i$0);
            frame.setlocal("current_angle", i$1);
            frame.setlocal("pen_is_down", frame.getname("False"));
            frame.setlocal("pen_down", frame.getname("re").__getattr__("compile").__call__(s$2));
            frame.setlocal("pen_up", frame.getname("re").__getattr__("compile").__call__(s$3));
            frame.setlocal("move_forward", frame.getname("re").__getattr__("compile").__call__(s$4));
            frame.setlocal("move_backward", frame.getname("re").__getattr__("compile").__call__(s$5));
            frame.setlocal("move_", frame.getname("re").__getattr__("compile").__call__(s$6));
            frame.setlocal("turn_cw", frame.getname("re").__getattr__("compile").__call__(s$7));
            frame.setlocal("turn_ccw", frame.getname("re").__getattr__("compile").__call__(s$8));
            frame.setlocal("put_", frame.getname("re").__getattr__("compile").__call__(s$9));
            frame.setlocal("for_end", frame.getname("re").__getattr__("compile").__call__(s$10, frame.getname("re").__getattr__("DOTALL")));
            frame.setlocal("calculateRadian", new PyFunction(frame.f_globals, new PyObject[] {}, c$0_calculateRadian));
            frame.setlocal("calculateAngle", new PyFunction(frame.f_globals, new PyObject[] {}, c$1_calculateAngle));
            frame.setlocal("penDown", new PyFunction(frame.f_globals, new PyObject[] {}, c$2_penDown));
            frame.setlocal("penUp", new PyFunction(frame.f_globals, new PyObject[] {}, c$3_penUp));
            frame.setlocal("moveForward", new PyFunction(frame.f_globals, new PyObject[] {}, c$4_moveForward));
            frame.setlocal("moveBackward", new PyFunction(frame.f_globals, new PyObject[] {}, c$5_moveBackward));
            frame.setlocal("move", new PyFunction(frame.f_globals, new PyObject[] {}, c$6_move));
            frame.setlocal("turnCw", new PyFunction(frame.f_globals, new PyObject[] {}, c$7_turnCw));
            frame.setlocal("turnCcw", new PyFunction(frame.f_globals, new PyObject[] {}, c$8_turnCcw));
            frame.setlocal("put", new PyFunction(frame.f_globals, new PyObject[] {}, c$9_put));
            frame.setlocal("actionPerformed", new PyFunction(frame.f_globals, new PyObject[] {}, c$10_actionPerformed));
            frame.setlocal("regMatching", new PyFunction(frame.f_globals, new PyObject[] {}, c$11_regMatching));
            frame.setlocal("forMethod", new PyFunction(frame.f_globals, new PyObject[] {}, c$12_forMethod));
            frame.setlocal("myMatching", new PyFunction(frame.f_globals, new PyObject[] {}, c$13_myMatching));
            frame.setlocal("setDYPL", new PyFunction(frame.f_globals, new PyObject[] {}, c$14_setDYPL));
            return frame.getf_locals();
        }
        
        private static PyObject main$17(PyFrame frame) {
            frame.setglobal("__file__", s$26);
            
            // Temporary Variables
            PyObject[] t$0$PyObject__;
            
            // Code
            frame.setlocal("re", org.python.core.imp.importOne("re", frame));
            frame.setlocal("math", org.python.core.imp.importOne("math", frame));
            t$0$PyObject__ = org.python.core.imp.importFrom("JythonTranslater", new String[] {"Jtrans"}, frame);
            frame.setlocal("Jtrans", t$0$PyObject__[0]);
            t$0$PyObject__ = null;
            frame.setlocal("MyJythonApp", Py.makeClass("MyJythonApp", new PyObject[] {frame.getname("Jtrans")}, c$15_MyJythonApp, null, MyJythonApp.class));
            return Py.None;
        }
        
    }
    public static void moduleDictInit(PyObject dict) {
        dict.__setitem__("__name__", new PyString("MyJythonApp"));
        Py.runCode(new _PyInner().getMain(), dict, dict);
    }
    
    public static void main(String[] args) throws java.lang.Exception {
        String[] newargs = new String[args.length+1];
        newargs[0] = "MyJythonApp";
        java.lang.System.arraycopy(args, 0, newargs, 1, args.length);
        Py.runMain(MyJythonApp._PyInner.class, newargs, MyJythonApp.jpy$packages, MyJythonApp.jpy$mainProperties, null, new String[] {"MyJythonApp"});
    }
    
    public void super__actionPerformed(java.awt.event.ActionEvent arg0) {
        super.actionPerformed(arg0);
    }
    
    public void actionPerformed(java.awt.event.ActionEvent arg0) {
        PyObject inst = Py.jfindattr(this, "actionPerformed");
        if (inst != null) inst._jcall(new Object[] {arg0});
        else super.actionPerformed(arg0);
    }
    
    public java.lang.Object clone() throws java.lang.CloneNotSupportedException {
        return super.clone();
    }
    
    public void finalize() throws java.lang.Throwable {
        super.finalize();
    }
    
    public void super__setDYPL(java.lang.Object arg0) {
        super.setDYPL(arg0);
    }
    
    public void setDYPL(java.lang.Object arg0) {
        PyObject inst = Py.jfindattr(this, "setDYPL");
        if (inst != null) inst._jcall(new Object[] {arg0});
        else super.setDYPL(arg0);
    }
    
    public MyJythonApp() {
        super();
        __initProxy__(new Object[] {});
    }
    
    private PyInstance __proxy;
    public void _setPyInstance(PyInstance inst) {
        __proxy = inst;
    }
    
    public PyInstance _getPyInstance() {
        return __proxy;
    }
    
    private PySystemState __sysstate;
    public void _setPySystemState(PySystemState inst) {
        __sysstate = inst;
    }
    
    public PySystemState _getPySystemState() {
        return __sysstate;
    }
    
    public void __initProxy__(Object[] args) {
        Py.initProxy(this, "MyJythonApp", "MyJythonApp", args, MyJythonApp.jpy$packages, MyJythonApp.jpy$proxyProperties, null, new String[] {"MyJythonApp"});
    }
    
    static public void classDictInit(PyObject dict) {
        dict.__setitem__("__supernames__", Py.java2py(new String[] {"super__actionPerformed", "super__setDYPL", "finalize", "clone"}));
    }
    
}
