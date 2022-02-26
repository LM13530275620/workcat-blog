package com.vuelmblog.common.lang;

import lombok.Data;

import java.io.Serializable;
//数据结果异常封装
@Data
public class Result implements Serializable {

    private int code;  //200正常  非200异常
    private String msg;
    private  Object data;

    //数据正常接口
    public static Result succ(Object data){
        return succ(200,"操作成功",data);

    }

    //同时传 code msg data结果接口
    public static Result succ(int code,String msg,Object data){
        Result r=new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
    //数据400封装接口
    public static Result fail(String msg){
        return fail(400,msg,null);
    }

    public static Result fail(String msg,Object data){
        return fail(400,msg,data);
    }
    public static Result fail(int code,String msg,Object data){
        Result r=new Result();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }


}
