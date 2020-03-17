package com.example.pethospitalmanagement.util;

public class MyException extends RuntimeException{

    private String code;
    private String msg;

    public MyException(String msg){
        super(msg);
        this.msg = msg;
    }

    public MyException(String code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
