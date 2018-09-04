package com.bluebooth.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2006-2010, ChengDu Lovo info. Co., Ltd.
 *
 * @BelongsPackage: com.bluebooth.common.util
 * @author: LX
 * @CreateDate: 2018/09/04/14:54
 * @version: 1.0
 * @Description: 枚举工具类
 */
public class EnumUtil {

    /**
     * 从变长的枚举参数列表中, 获取指定字段的集合 (eg:   enum list -> status list )
     * @param  需要获取的字段名
     * @param  枚举类 可变参数
     * @return field list
     * @throws NoSuchFieldException 枚举类中没有 fieldName时, 抛出此异常
     * @throws NoSuchMethodException 枚举类中没有 fieldName的 get方法时, 抛出此异常
     * @throws InvocationTargetException 当被调用的方法的内部抛出了异常而没有被捕获时，将由此异常接收。
     * @throws IllegalAccessException 枚举类的构造方法访问权限时 private时, 抛出此异常
     */

        private static <T> Object getMethodValue(String methodName, T obj, Object... args) {
        Object resut = "";
        // boolean isHas = false;
        try {
            /********************************* start *****************************************/
            Method[] methods = obj.getClass().getMethods(); //获取方法数组，这里只要共有的方法
            if (methods.length <= 0) {
                return resut;
            }
            // String methodstr=Arrays.toString(obj.getClass().getMethods());
            // if(methodstr.indexOf(methodName)<0){ //只能粗略判断如果同时存在 getValue和getValues可能判断错误
            // return resut;
            // }
            // List<Method> methodNamelist=Arrays.asList(methods); //这样似乎还要循环取出名称
            Method method = null;
            for (int i = 0, len = methods.length; i < len; i++) {
                if (methods[i].getName().equalsIgnoreCase(methodName)) { //忽略大小写取方法
                    // isHas = true;
                    methodName = methods[i].getName(); //如果存在，则取出正确的方法名称
                    method = methods[i];
                    break;
                }
            }
            // if(!isHas){
            // return resut;
            // }
            /*************************** end ***********************************************/
            // Method method = obj.getClass().getDeclaredMethod(methodName); ///确定方法
            if (method == null) {
                return resut;
            }
            resut = method.invoke(obj, args); //方法执行
            if (resut == null) {
                resut = "";
            }
            return resut; //返回结果
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resut;
    }

        public static <T> Map<Object, String> EnumToMap(Class<T> enumT, String... methodNames) {
            Map<Object, String> enummap = new HashMap<Object, String>();
            if (!enumT.isEnum()) {
                return enummap;
            }
            T[] enums = enumT.getEnumConstants();
            if (enums == null || enums.length <= 0) {
                return enummap;
            }
            int count = methodNames.length;
            String valueMathod = "getValue"; //默认接口value方法
            String desMathod = "getDescription";//默认接口description方法
            if (count >= 1 && !"".equals(methodNames[0])) { //扩展方法
                valueMathod = methodNames[0];
            }
            if (count == 2 && !"".equals(methodNames[1])) {
                desMathod = methodNames[1];
            }
            for (int i = 0, len = enums.length; i < len; i++) {
                T tobj = enums[i];
                try {
                    Object resultValue = getMethodValue(valueMathod, tobj); //获取value值
                    if ("".equals(resultValue)) {
                        continue;
                    }
                    Object resultDes = getMethodValue(desMathod, tobj); //获取description描述值
                    if ("".equals(resultDes)) { //如果描述不存在获取属性值
                        resultDes = tobj;
                    }
                    enummap.put(resultValue, resultDes + "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return enummap;
        }

        public static List<Object> getListByEnum(String fieldName, Enum... enums) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InvocationTargetException {

        List<Object> list = new ArrayList<Object>();

        if (enums == null) {
            return list;
        }

        for (Enum e : enums) {
            Class<? extends Enum> clazz = e.getClass();

            Field field = clazz.getDeclaredField(fieldName);
            String methodName = getFieldGetMethodName(field);
            if (methodName != null) {
                Method method = clazz.getMethod(methodName);
                Object returnValue = method.invoke(e);
                list.add(returnValue);
            }
        }
        return list;
    }

        public static String getFieldGetMethodName(Field field) {
        if (field == null) {
            return null;
        }

        String name = field.getName();
        return  "get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
    }

        public static String getFieldSetMethodName(Field field) {
        if (field == null) {
            return null;
        }

        String name = field.getName();
        return "set" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
    }

        /**
         * 通过value值获取对应的描述信息
        * @param value
        * @param enumT
        * @return enum description
        */
         public static <T> Object getEnumDescriotionByValue(Object value, Class<T> enumT, String... methodNames) {
        if (!enumT.isEnum()) { //不是枚举则返回""
            return "";
        }
        T[] enums = enumT.getEnumConstants();  //获取枚举的所有枚举属性，似乎这几句也没啥用，一般既然用枚举，就一定会添加枚举属性
        if (enums == null || enums.length <= 0) {
            return "";
        }
        int count = methodNames.length;
        String valueMathod = "getValue";    //默认获取枚举value方法，与接口方法一致
        String desMathod = "getDescription"; //默认获取枚举description方法
        if (count >= 1 && !"".equals(methodNames[0])) {
            valueMathod = methodNames[0];
        }
        if (count == 2 && !"".equals(methodNames[1])) {
            desMathod = methodNames[1];
        }
        for (int i = 0, len = enums.length; i < len; i++) {
            T t = enums[i];
            try {
                Object resultValue = getMethodValue(valueMathod, t);//获取枚举对象value
                if (resultValue.toString().equals(value + "")) {
                    Object resultDes = getMethodValue(desMathod, t); //存在则返回对应描述
                    return resultDes;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * 通过枚举value或者自定义值及方法获取枚举属性值
     * @param value
     * @param enumT
     * @return enum key
     */
    public static <T> String getEnumKeyByValue(Object value, Class<T> enumT,
                                               String... methodNames) {
        if (!enumT.isEnum()) {
            return "";
        }
        T[] enums = enumT.getEnumConstants();
        if (enums == null || enums.length <= 0) {
            return "";
        }
        int count = methodNames.length;
        String valueMathod = "getValue"; //默认方法
        if (count >= 1 && !"".equals(methodNames[0])) { //独立方法
            valueMathod = methodNames[0];
        }
        for (int i = 0, len = enums.length; i < len; i++) {
            T tobj = enums[i];
            try {
                Object resultValue = getMethodValue(valueMathod, tobj);
                if (resultValue != null
                        && resultValue.toString().equals(value + "")) { //存在则返回对应值
                    return tobj + "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    }
