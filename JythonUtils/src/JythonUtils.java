import java.util.*;

import org.apache.poi.ss.formula.functions.T;
import org.python.core.*;
import org.python.util.PythonInterpreter;
/**
 * @ClassName:  JythonUtils
 * @Description:TODO(jython 工具类)
 * 注意：
 */
public class JythonUtils {

    /**
     * @Title: jythonInit
     * @Description: TODO(初始化jython)
     * @param: @return
     * @return: PythonInterpreter
     * @throws
     */
    public static PythonInterpreter jythonInit(){
        //初始化site 配置
        Properties props = new Properties();
        props.put("python.home", ""); //python Lib 或 jython Lib,根据系统中该文件目录路径
        props.put("python.console.encoding", "UTF-8");
        props.put("python.security.respectJavaAccessibility", "false");
        props.put("python.import.site", "false");
        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);
        //创建PythonInterpreter 对象
        PythonInterpreter interp = new PythonInterpreter();
        return interp;
    }

    /**
     * @Title: loadPythonFile
     * @Description: TODO(加载python 源码文件，)
     * @param: @param interp
     * @param: @param filePath ，比如：F:\\jpython_jar\\jpythonTest\\pythonTest.py  或/testpython/test.py
     * @param: @return
     * @return: PythonInterpreter
     * @throws
     */
    public static PythonInterpreter loadPythonFile(PythonInterpreter interp, String filePath){
        interp.execfile(filePath);
        return interp;
    }

    /**
     * @Title: loadPythonFunc
     * @Description: TODO(加载python 源码文件中的某个方法)
     * @param: @param interp
     * @param: @param functionName
     * @param: @return
     * @return: PyFunction
     * @throws
     */
    public static PyFunction loadPythonFunc(PythonInterpreter interp, String functionName){
        PyFunction func = (PyFunction) interp.get(functionName,PyFunction.class);
        return func;
    }


    /**
     * @Title: execFunc
     * @Description: TODO(执行无参方法,返回PyObject)
     * @param: @param func
     * @return: PyObject
     * @throws
     */
    public static PyObject execFunc(PyFunction func){
        PyObject pyobj = func.__call__();
        return pyobj;
    }

    /**
     * @Title: execFuncToString
     * @Description: TODO(执行无参方法,返回一个字符串)
     * @param: @param func
     * @param: @return
     * @return: String
     * @throws
     */
    public static String execFuncToString(PyFunction func){
        PyObject pyobj = execFunc(func);
        return (String) pyobj.__tojava__(String.class);
    }

    /**
     * @Title: execFuncToString
     * @Description: TODO(执行有参方法,返回一个字符串)
     * @param: @param func
     * @param: @param paramName ，参数名
     * @param: @return
     * @return: String
     * @throws
     */
    public static String execFuncToString2(PyFunction func, String paramName){
        PyObject pyobj = func.__call__(new PyString(paramName));
        return (String) pyobj.__tojava__(String.class);
    }

    /**
     * @Title: execFuncToInteger
     * @Description: TODO(执行无参方法,返回一个Integer)
     * @param: @param func
     * @param: @return
     * @return: Integer
     * @throws
     */
    public static Integer execFuncToInteger(PyFunction func){
        PyObject pyobj = execFunc(func);
        return (Integer) pyobj.__tojava__(Integer.class);
    }

    /**
     * @Title: execFuncToList
     * @Description: TODO(执行无参方法,返回一个List)
     * @param: @param func
     * @param: @return
     * @return: List<T>
     * @throws
     */
    public static List<T> execFuncToList(PyFunction func){
        PyObject pyobj = execFunc(func);
        return (List<T>) pyobj.__tojava__(List.class);
    }
    /**
     * @Title: execFuncToMap
     * @Description: TODO(执行有参方法,返回一个List<T>)
     * @param: @param func
     * @param: @return
     * @return: List
     * @throws
     */
    public static List<T> execFuncToByParamsList(PyFunction func, List<Double> paramsList){
        PyObject pyobj = func.__call__(new PyList(paramsList));
        return (List<T>) pyobj.__tojava__(List.class);
    }

    /**
     * @Title: execFuncToMap
     * @Description: TODO(执行无参方法,返回一个Map<String, Object>)
     * @param: @param func
     * @param: @return
     * @return: Map<String,Object>
     * @throws
     */
    public static Map<String, Object> execFuncToMap(PyFunction func){
        PyObject pyobj = execFunc(func);
        return (Map<String, Object>) pyobj.__tojava__(Map.class);
    }
    /**
     * @Title: execFuncToMap
     * @Description: TODO(执行有参方法,返回一个Map<String, Object>)
     * @param: @param func
     * @param: @return
     * @return: Map<String,Object>
     * @throws
     */
    public static Map<Object, PyObject>  execFuncToMap2(PyFunction func,Map<Object, PyObject>  paramsMap){
        PyObject pyobj = func.__call__(new PyStringMap(paramsMap));
        return (Map<Object, PyObject>) pyobj.__tojava__(Map.class);
    }

    public static void main(String[] args){
        PythonInterpreter interp  = jythonInit();
        //文件名
        String filePath = "python\\python.py";
        interp = loadPythonFile(interp, filePath);

        //执行无参方法，返回integer
        String functionName1 = "Print_int";
        PyFunction func1 = loadPythonFunc(interp, functionName1);
        int resultInt = execFuncToInteger(func1);
        System.out.println(resultInt);

        //执行无参方法，返回List
        String functionName2 = "Print_list";
        PyFunction func2 = loadPythonFunc(interp, functionName2);
        List resultList = execFuncToList(func2);
        System.out.println(resultList);
        for (Object x:resultList) {
            System.out.print(x+" ");
        }
        System.out.println();

        //执行无参方法，返回Map
        String functionName5 = "Print_map";
        PyFunction func5 = loadPythonFunc(interp, functionName5);
        Map<String, Object> resultList5 = execFuncToMap(func5);
        System.out.println(resultList5);
        for (Object o1:resultList5.values()) {
            System.out.print(o1 + " ");
        }
        System.out.println();

        //执行有参方法，返回String
        String functionName="Print_string";
        PyFunction func = loadPythonFunc(interp, functionName);
        String paramName = "name";
        String resultStr2 = execFuncToString2(func, paramName);
        System.out.println(resultStr2);

        //执行有参方法，返回List
        String functionName3 = "Print_List";
        PyFunction func3 = loadPythonFunc(interp, functionName3);
        List<Double> paras = new ArrayList<>();
        paras.add(0.1);
        paras.add(0.2);
        paras.add(0.3);
        paras.add(0.4);
        List<T> resultList2 = execFuncToByParamsList(func3, paras);
        System.out.println(resultList2);
        for (Object o2:resultList2) {
            System.out.print(o2+" ");
        }
        System.out.println();
    }
}