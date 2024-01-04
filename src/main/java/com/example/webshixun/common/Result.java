package com.example.webshixun.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code; //编码：0成功，1为失败

    private String message; //响应信息

    private T data; //数据

    private Map map = new HashMap(); //动态数据

    private Long total; //记录总条数

    public Result(T data) {
        this.data = data;
    }
    public static <T> Result<T> success(String message) {
        Result<T> r = new Result<T>();
        r.message = message;
        r.code = 0;
        return r;
    }

    public static <T> Result<T> success() {
        Result<T> r = new Result<T>();
        r.message = "success";
        r.code = 0;
        return r;
    }

    public static <T> Result<T> success(T data,String message) {
        Result<T> r = new Result<>();
        r.data = data;
        r.message = message;
        r.code = 0;
        return r;
    }


    public static <T> Result<T> success(T data,String message,Long total) {
        Result<T> r = new Result<>();
        r.data = data;
        r.message = message;
        r.code = 0;
        r.total = total;
        return r;
    }

    public static <T> Result<T> error(String str) {
        Result r = new Result();
        r.message = str;
        r.code = 1;
        return r;
    }

    public Result<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
