/**
 * Project Name:netty-zkp-common
 * File Name:RpcResponse.java
 * Package Name:cn.forp.netty_zkp_common
 * Date:2019年3月14日上午9:34:42
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package cn.forp.netty_zkp_common;
/**
 * ClassName:RpcResponse 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月14日 上午9:34:42 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
public class RpcResponse {

    private String requestId;
    private Throwable error;
    private Object result;
    public boolean isError() {
        return error != null;
    }
    public String getRequestId() {
        return requestId;
    }
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    public Throwable getError() {
        return error;
    }
    public void setError(Throwable error) {
        this.error = error;
    }
    public Object getResult() {
        return result;
    }
    public void setResult(Object result) {
        this.result = result;
    }

    
}