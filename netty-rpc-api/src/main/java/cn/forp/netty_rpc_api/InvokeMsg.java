/**
 * Project Name:netty-rpc-api
 * File Name:InvokeMsg.java
 * Package Name:cn.forp.netty_rpc_api
 * Date:2019年3月13日下午5:37:49
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package cn.forp.netty_rpc_api;
/**
 * ClassName:InvokeMsg 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月13日 下午5:37:49 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */

import java.io.Serializable;
 
public class InvokeMsg implements Serializable{
    private static final long serialVersionUID = 1L;
    private String className;
    private String methodName;
    private Class<?>[] paramTypes;
    private Object[] values;
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    public Class<?>[] getParamTypes() {
        return paramTypes;
    }
    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
    }
    public Object[] getValues() {
        return values;
    }
    public void setValues(Object[] values) {
        this.values = values;
    }
    
}