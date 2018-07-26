package com.jnshu.utils;

public class CAM {
    private int code =200;
    private String message="连接成功。";

    public CAM(){}

    public CAM(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode(){
        return this.code;
    }

    public void setCode(int code){
        this.code = code;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    @Override
    public String toString() {
        return "CAM：" +
                "code=" + code +
                ", message='" + message + '\'' +
                '。';
    }
}
