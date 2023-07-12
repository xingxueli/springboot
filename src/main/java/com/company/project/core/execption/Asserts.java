package com.company.project.core.execption;


import com.company.project.entity.vo.BusinessResultCode;
import com.company.project.entity.vo.IResultCode;

/**
 * 断言类，抛出业务异常
 *
 * @author mike
 * @version $Id: $Id
 */
public class Asserts {

    /**
     * <p>fail.</p>
     */
    public static void fail(){
        throw new CommonException();
    }

    /**
     * <p>fail.</p>
     *
     * @param message a {@link String} object.
     */
    public static void fail(String message){
        throw new CommonException(message);
    }

    /**
     * <p>fail.</p>
     *
     */
    public static void fail(IResultCode resultCode){
        throw new CommonException(resultCode);
    }

    /**
     * <p>fail.</p>
     *
     * @param message a {@link String} object.
     */
    public static void fail(IResultCode resultCode,String message){
        throw new CommonException(resultCode,message);
    }

    /**
     * <p>notNull.</p>
     *
     * @param object a {@link Object} object.
     */
    public static void notNull(Object object){
        if(object == null) {
            throw new CommonException(BusinessResultCode.INVALID_PARAM);
        }
    }

    /**
     * <p>notNull.</p>
     *
     * @param object a {@link Object} object.
     * @param message a {@link String} object.
     */
    public static void notNull(Object object,String message){
        if(object == null) {
            throw new CommonException(BusinessResultCode.INVALID_PARAM,message);
        }
    }
}
