package com.hpz.common;

import com.hpz.out.model.MobileErrCode;

/**
 * Created by mao on 2015/6/4.
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    //错误提示码
    private Integer code;

    public ServiceException(Integer code,String msg){
        super(msg);
        this.code=code;
    }

    /***
     * 业务异常构造方法(当提供的错误说明和ResultCode中描述的一样的时候)
     * @param code
     */
    public ServiceException(MobileErrCode code){
        super(code.getDesc());
        this.code = code.getCode();
    }


    public ServiceException() {
        super();
    }

    public ServiceException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public ServiceException(String arg0) {
        super(arg0);
    }

    public ServiceException(Throwable arg0) {
        super(arg0);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString(){
        return "ServiceException(Code:"+this.code+";Message:"+super.getLocalizedMessage()+")";
    }
}
