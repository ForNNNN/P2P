package com.yyw.p2p.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.HashMap;
import java.util.Map;


@Data
@AllArgsConstructor
public class R {
    private Integer code;
    private String message;
    private Map<String,Object> data = new HashMap();

    private R(){};


    public static R ok(){
        R r = new R();
        r.setCode(ResponseEnum.SUCCESS.getCode());
        r.setMessage(ResponseEnum.SUCCESS.getMessage());
        return r;
    }

    public static R fail(){
        R r = new R();
        r.setCode(ResponseEnum.FAILURE.getCode());
        r.setMessage(ResponseEnum.FAILURE.getMessage());
        return r;
    }

    public static R setR(ResponseEnum responseEnum){
        R r = new R();
        r.setCode(responseEnum.getCode());
        r.setMessage(responseEnum.getMessage());
        return r;
    }


    /*
    对R对象定制化进行定制化操作
     */
    public R data(String key, Object object){
        this.data.put(key,object);
        return this;
    }

    //如果data本身就是Map类型的，则直接设置
    public R data(Map<String,Object> map){
        this.setData(map);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

}
