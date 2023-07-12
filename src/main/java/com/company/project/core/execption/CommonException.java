package com.company.project.core.execption;

import com.company.project.entity.vo.BusinessResultCode;
import com.company.project.entity.vo.IResultCode;

public class CommonException extends RuntimeException {

    /**
     * 异常的返回码x
     */
    private IResultCode resultCode;

    /**
     * 不指定任何参数返回码默认为FAILED
     */
    public CommonException(){
        this.resultCode = BusinessResultCode.FAILED;
    }

    /**
     * 默认返回的FAILED可以自定义提示信息
     *
     * @param message a {@link java.lang.String} object.
     */
    public CommonException(String message){
        super(message);
        this.resultCode = BusinessResultCode.FAILED;
    }
    public CommonException(String message,Exception e){
        super(message,e);
        this.resultCode = BusinessResultCode.FAILED;
    }

    /**
     * 指定对应具体业务的返回码
     *
    public CommonException(IResultCode resultCode){
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    /**
     * 指定对应具体业务的返回码 可以自定义提示信息
     *
     * @param message a {@link java.lang.String} object.
     */
    public CommonException(IResultCode resultCode,String message){
        super(message);
        this.resultCode = resultCode;
    }

    public CommonException(IResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    /**
     * <p>Getter for the field <code>resultCode</code>.</p>
     *
     */
    public IResultCode getResultCode(){
        return this.resultCode;
    }
}
