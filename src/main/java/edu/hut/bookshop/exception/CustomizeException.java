package edu.hut.bookshop.exception;


import edu.hut.bookshop.util.ResultCode;

/**
 * @Description: 自定义异常
 */
public class CustomizeException extends RuntimeException{
    private int code;
    private String msg;

    public CustomizeException(int code, String msg) {
        super(msg);
        this.code=code;
        this.msg=msg;
    }
    public CustomizeException(ResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMsg());
    }

    /**
     * 自定义消息
     * @param resultCode
     * @param msg  自定义消息
     */
    public CustomizeException(ResultCode resultCode, String msg) {
        this(resultCode.getCode(), msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
