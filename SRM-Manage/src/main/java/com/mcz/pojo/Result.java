package com.mcz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T>{
    public Integer code;
    public String message;
    public T data;
    public static <E> Result<E> success(E data){
        return new Result<>(0, "操作成功",data);
    }
    public static <E> Result<E> success(){
        return new Result<>(0, "操作成功",null);
    }

    public static <E> Result<E> error(String message){
        return new Result<>(1, message,null);
    }
}
