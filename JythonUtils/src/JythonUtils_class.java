import java.util.*;

import org.apache.poi.ss.formula.functions.T;
import org.python.core.*;
import org.python.util.PythonInterpreter;
/**
 * @ClassName:  JythonUtils
 * @Description:TODO(jython 工具类)
 * 注意：
 */
public class JythonUtils_class {

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
    /*
    public static PyFunction loadPythonFunc(PythonInterpreter interp, String functionName){

        //加载方法
        PyFunction func = (PyFunction) interp.get(functionName,PyFunction.class);
        return func;
    }

    */


    /**
     * @Title: execFunc
     * @Description: TODO(执行无参方法,返回PyObject)
     * @param: @param func
     * @return: PyObject
     * @throws
     */
    /*
    public static PyObject execFunc(PyFunction func){
        PyObject pyobj = func.__call__();
        return pyobj;
    }

     */

    /**
     * @Title: execFuncToString
     * @Description: TODO(执行无参方法,返回一个字符串)
     * @param: @param func
     * @param: @return
     * @return: String
     * @throws
     */
    /*
    public static String execFuncToString(PyFunction func){
        PyObject pyobj = execFunc(func);
        return (String) pyobj.__tojava__(String.class);
    }

     */

    /**
     * @Title: execFuncToString
     * @Description: TODO(执行有参方法,返回一个字符串)
     * @param: @param func
     * @param: @param paramName ，参数名
     * @param: @return
     * @return: String
     * @throws
     */
    public static String execFuncToString2(PyObject pyObj,String func, String paramName){
        PyObject pyobj = pyObj.invoke(func,new PyString(paramName));
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
    public static Integer execFuncToInteger(PyObject pyCls,String func){
        PyObject pyobj = pyCls.invoke(func);
        return (Integer) pyobj.__tojava__(Integer.class);
    }
    /**
     * @Title: execFuncToByParamDouble
     * @Description: TODO(执行无参方法,返回一个Integer)
     * @param: @param func
     * @param: @return
     * @return: Double
     * @throws
     */
    public static Double execFuncToByParamDouble(PyObject pyCls,String func,Double param){
        PyObject pyobj = pyCls.invoke(func,new PyFloat(param));
        return (Double) pyobj.__tojava__(Double.class);
    }

    /**
     * @Title: execFuncToByParamBoolean
     * @Description: TODO(执行无参方法,返回一个Integer)
     * @param: @param func
     * @param: @return
     * @return: Boolean
     * @throws
     */
    public static Boolean execFuncToByParamBoolean(PyObject pyCls,String func,Boolean param){
        PyObject pyobj = pyCls.invoke(func,new PyBoolean(param));
        return (Boolean) pyobj.__tojava__(Boolean.class);
    }



    /**
     * @Title: execFuncToList
     * @Description: TODO(执行无参方法,返回一个List)
     * @param: @param func
     * @param: @return
     * @return: List<T>
     * @throws
     */
    public static List<T> execFuncToList(PyObject pyCls,String func){
        PyObject pyobj = pyCls.invoke(func);
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

    public static List<Double> execFuncToByParamsList(PyObject pyCls,String func, List<Double> paramsList){
        PyObject pyobj = pyCls.invoke(func,new PyList(paramsList));
        return (List<Double>) pyobj.__tojava__(List.class);
    }
    /**
     * @Title: execFuncToByParamsList2
     * @Description: TODO(执行有参方法,传入Map,返回一个List<T>)
     * @param: @param func
     * @param: @return
     * @return: List<T>
     * @throws
     */
    public static List<T> execFuncToByParamsList2(PyObject pyCls,String func, Map<Object, PyObject>  paramsListMap) {
        PyObject pyobj = pyCls.invoke(func, new PyStringMap(paramsListMap));
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

    public static Map<String, Object> execFuncToMap(PyObject pyCls,String func){
        PyObject pyobj = pyCls.invoke(func);
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

    public static Map<Object, PyObject>  execFuncToMap2(PyObject pyCls,String func,Map<Object, PyObject>  paramsMap){
        PyObject pyobj = pyCls.invoke(func,new PyStringMap(paramsMap));
        return (Map<Object, PyObject>) pyobj.__tojava__(Map.class);
    }


    public static void main(String[] args){
        PythonInterpreter interp  = jythonInit();
        //文件名
        String filePath = "python\\pythonclass.py";
        interp = loadPythonFile(interp, filePath);

        PyObject pyClass = interp.get("jython");
        PyObject pyCls = pyClass.__call__();

        //执行无参方法，返回integer
        String functionName1 = "Print_int";
        int resultInt = execFuncToInteger(pyCls,functionName1);
        System.out.println(resultInt);
        //执行有参方法，返回String
        String functionName2 = "Print_string";
        String paramName = "name";
        String resultStr2 = execFuncToString2(pyCls, functionName2,paramName);
        System.out.println(resultStr2);

        //执行无参方法，返回List
        String functionName3 = "Print_list";
        //PyFunction func2 = loadPythonFunc(interp, functionName3);
        List resultList = execFuncToList(pyCls,functionName3);
        System.out.println(resultList);
        for (Object x:resultList) {
            System.out.print(x+" ");
        }
        System.out.println();
        //执行无参方法，返回Map
        String functionName4 = "Print_map";
        //PyFunction func5 = loadPythonFunc(interp, functionName5);
        Map<String, Object> resultMap = execFuncToMap(pyCls,functionName4);
        System.out.println(resultMap);
        for (Object o1:resultMap.values()) {
            System.out.print(o1 + " ");
        }
        System.out.println();


        //执行有参方法，返回List
        String functionName5 = "Print_List";
        //PyFunction func3 = loadPythonFunc(interp, functionName3);
        List<Double> paras = new ArrayList<>();
        paras.add(0.1);
        paras.add(0.2);
        paras.add(0.3);
        paras.add(0.4);
        List<Double> resultList2 = execFuncToByParamsList(pyCls,functionName5 , paras);
        System.out.println(resultList2);
        for (Object o2:resultList2) {
            System.out.print(o2+" ");
        }
        System.out.println();

        /*
        //执行有参方法，返回Map
        String functionName6 = "Print_Map";
        //PyFunction func4 = loadPythonFunc(interp, functionName4);
        Map<Object, PyObject>  paramsMap = new HashMap<Object, PyObject>();
        paramsMap.put("Google", new PyInteger(1));
        paramsMap.put("Runoob", new PyInteger(2));
        paramsMap.put("Taobao", new PyInteger(3));
        paramsMap.put("Zhihu",  new PyInteger(4));
        Map<Object, PyObject> resultMap2 = execFuncToMap2(pyCls,functionName6, paramsMap);
        System.out.println(resultMap2);
        for (Object o3:resultMap2.values()) {
            System.out.print(o3+" ");
        }
        */

        //执行有参方法，传入Map,返回List
        String functionName7 = "Print_Map2";
        //PyFunction func4 = loadPythonFunc(interp, functionName4);
        Map<String, List<Double>>  M = new HashMap<>();
        List<Double> L = new ArrayList();
        L.add(0.0001);
        M.put("Google", L);
        M.put("Runoob", L);
        M.put("Taobao", L);
        Map<Object,PyObject>paramsMap2 =new HashMap<>();
        for (String key :M.keySet()){
            paramsMap2.put(key,new PyList(M.get(key)));
        }
        List<T>  resultList3 = execFuncToByParamsList2(pyCls,functionName7, paramsMap2);
        System.out.println(resultList3);
        for (Object o4:resultList3) {
            System.out.print(o4+" ");
        }
        System.out.println();
        //执行有参方法，返回Double
        String functionName8 = "Print_double";
        //PyFunction func4 = loadPythonFunc(interp, functionName4);
        double paramDouble=0.1;
        double resultDouble = execFuncToByParamDouble(pyCls,functionName8, paramDouble);
        System.out.println(resultDouble);
        //执行有参方法，返回Boolean
        String functionName9 = "Print_boolean";
        //PyFunction func4 = loadPythonFunc(interp, functionName4);
        boolean paramBoolean=true;
        boolean resultBoolean = execFuncToByParamBoolean(pyCls,functionName9, paramBoolean);
        System.out.println(resultBoolean);
    }
}