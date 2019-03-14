/**
 * Project Name:netty-zkp-common
 * File Name:RpcRequest.java
 * Package Name:cn.forp.netty_zkp_common
 * Date:2019年3月14日上午9:34:29
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package cn.forp.netty_zkp_common;
/**
 * ClassName:RpcRequest 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月14日 上午9:34:29 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public class RpcRequest {
    private String requestId;
    private String className;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] parameters;
    public String getRequestId() {
        return requestId;
    }
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
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
    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }
    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
    public Object[] getParameters() {
        return parameters;
    }
    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

}

