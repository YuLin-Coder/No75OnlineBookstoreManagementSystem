package edu.hut.bookshop.util;

/**
 * @Description: 统一响应Json数据格式封装
 */
public class ResultVO<T> {
    private int code;    //消息码
    private String msg;  //响应消息
    private int count;   //数据总数，一般只用在分页的数据响应
    private T data;      //要响应的数据

    public ResultVO(int code, String msg, T data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }
    public ResultVO(int code,String msg,int count,T data){
        this(code,msg,data);
        this.count=count;
    }
    public ResultVO(ResultCode resultCode, T data){
        this(resultCode.getCode(),resultCode.getMsg(),data);
    }
    public ResultVO(ResultCode resultCode,int count ,T data){
        this(resultCode.getCode(),resultCode.getMsg(),count,data);
    }
    public ResultVO(ResultCode resultCode){
        this(resultCode.getCode(),resultCode.getMsg(),null);
    }
    public ResultVO(ResultCode resultCode,String msg,T data){
        this(resultCode.getCode(),msg,data);
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
