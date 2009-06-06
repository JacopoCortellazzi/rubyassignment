import org.python.core.*;

public class JythonTranslater extends java.lang.Object {
    static String[] jpy$mainProperties = new String[] {"python.modules.builtin", "exceptions:org.python.core.exceptions"};
    static String[] jpy$proxyProperties = new String[] {"python.modules.builtin", "exceptions:org.python.core.exceptions", "python.options.showJavaExceptions", "true"};
    static String[] jpy$packages = new String[] {"java.lang", null};
    
    public static class _PyInner extends PyFunctionTable implements PyRunnable {
        private static PyObject s$0;
        private static PyObject s$1;
        private static PyObject s$2;
        private static PyFunctionTable funcTable;
        private static PyCode c$0___init__;
        private static PyCode c$1_actionPerformed;
        private static PyCode c$2_setDYPL;
        private static PyCode c$3_Jtrans;
        private static PyCode c$4_main;
        private static void initConstants() {
            s$0 = Py.newString("Button clicked. Got event:");
            s$1 = Py.newString("Got a DYPL instance: ");
            s$2 = Py.newString("/Users/johan/Work/DYPL/assignment/JythonTranslater.py");
            funcTable = new _PyInner();
            c$0___init__ = Py.newCode(1, new String[] {"self"}, "/Users/johan/Work/DYPL/assignment/JythonTranslater.py", "__init__", false, false, funcTable, 0, null, null, 0, 1);
            c$1_actionPerformed = Py.newCode(2, new String[] {"self", "event"}, "/Users/johan/Work/DYPL/assignment/JythonTranslater.py", "actionPerformed", false, false, funcTable, 1, null, null, 0, 1);
            c$2_setDYPL = Py.newCode(2, new String[] {"self", "obj"}, "/Users/johan/Work/DYPL/assignment/JythonTranslater.py", "setDYPL", false, false, funcTable, 2, null, null, 0, 1);
            c$3_Jtrans = Py.newCode(0, new String[] {}, "/Users/johan/Work/DYPL/assignment/JythonTranslater.py", "Jtrans", false, false, funcTable, 3, null, null, 0, 0);
            c$4_main = Py.newCode(0, new String[] {}, "/Users/johan/Work/DYPL/assignment/JythonTranslater.py", "main", false, false, funcTable, 4, null, null, 0, 0);
        }
        
        
        public PyCode getMain() {
            if (c$4_main == null) _PyInner.initConstants();
            return c$4_main;
        }
        
        public PyObject call_function(int index, PyFrame frame) {
            switch (index){
                case 0:
                return _PyInner.__init__$1(frame);
                case 1:
                return _PyInner.actionPerformed$2(frame);
                case 2:
                return _PyInner.setDYPL$3(frame);
                case 3:
                return _PyInner.Jtrans$4(frame);
                case 4:
                return _PyInner.main$5(frame);
                default:
                return null;
            }
        }
        
        private static PyObject __init__$1(PyFrame frame) {
            // pass
            return Py.None;
        }
        
        private static PyObject actionPerformed$2(PyFrame frame) {
            Py.println(s$0);
            Py.println(frame.getlocal(1));
            return Py.None;
        }
        
        private static PyObject setDYPL$3(PyFrame frame) {
            Py.println(s$1);
            Py.println(frame.getlocal(1));
            return Py.None;
        }
        
        private static PyObject Jtrans$4(PyFrame frame) {
            frame.setlocal("__init__", new PyFunction(frame.f_globals, new PyObject[] {}, c$0___init__));
            frame.setlocal("actionPerformed", new PyFunction(frame.f_globals, new PyObject[] {}, c$1_actionPerformed));
            frame.setlocal("setDYPL", new PyFunction(frame.f_globals, new PyObject[] {}, c$2_setDYPL));
            return frame.getf_locals();
        }
        
        private static PyObject main$5(PyFrame frame) {
            frame.setglobal("__file__", s$2);
            
            frame.setlocal("Translater", org.python.core.imp.importOne("Translater", frame));
            frame.setlocal("java", org.python.core.imp.importOne("java.lang.Object", frame));
            frame.setlocal("Jtrans", Py.makeClass("Jtrans", new PyObject[] {frame.getname("Translater")}, c$3_Jtrans, null, Jtrans.class));
            return Py.None;
        }
        
    }
    public static class Jtrans extends java.lang.Object implements Translater, org.python.core.PyProxy, org.python.core.ClassDictInit {
        public void actionPerformed(java.awt.event.ActionEvent arg0) {
            PyObject inst = Py.jgetattr(this, "actionPerformed");
            inst._jcall(new Object[] {arg0});
        }
        
        public void setDYPL(java.lang.Object arg0) {
            PyObject inst = Py.jgetattr(this, "setDYPL");
            inst._jcall(new Object[] {arg0});
        }
        
        public Jtrans() {
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
            Py.initProxy(this, "JythonTranslater", "Jtrans", args, JythonTranslater.jpy$packages, JythonTranslater.jpy$proxyProperties, null, new String[] {"JythonTranslater"});
        }
        
        static public void classDictInit(PyObject dict) {
            dict.__setitem__("__supernames__", Py.java2py(new String[] {}));
        }
        
    }
    public static void moduleDictInit(PyObject dict) {
        dict.__setitem__("__name__", new PyString("JythonTranslater"));
        Py.runCode(new _PyInner().getMain(), dict, dict);
    }
    
    public static void main(String[] args) throws java.lang.Exception {
        String[] newargs = new String[args.length+1];
        newargs[0] = "JythonTranslater";
        System.arraycopy(args, 0, newargs, 1, args.length);
        Py.runMain(JythonTranslater._PyInner.class, newargs, JythonTranslater.jpy$packages, JythonTranslater.jpy$mainProperties, null, new String[] {"JythonTranslater"});
    }
    
}
