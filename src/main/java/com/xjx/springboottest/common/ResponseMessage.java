package com.xjx.springboottest.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("结果数据")
public class ResponseMessage<T> {

    @ApiModelProperty(value = "服务器返回状态码", name = "code", example = "0", required = true)
    private int code;
    @ApiModelProperty(value = "服务状态码中文说明", name = "message", example = "请求成功", required = true)
    private String message;


    @ApiModelProperty(value = "请求id", name = "requestId", example = "数据对象", required = true)
    private T data;

    ResponseMessage(){}
    ResponseMessage(int code, String message){
        this.code = code;
        this.message = message;
    }
    ResponseMessage(int code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public static ResponseMessage success(){
        return new ResponseMessage(0,"请求成功");
    }
    public static ResponseMessage success(int code,String message){
        return new ResponseMessage(0,"请求成功");
    }
    public static ResponseMessage success(String message){
        return new ResponseMessage(0,message);
    }
    public static ResponseMessage success(Object t){
        return new ResponseMessage(0,"请求成功",t);
    }


    public static ResponseMessage error(){
        return new ResponseMessage(-1,"请求失败");
    }
    public static ResponseMessage error(String message){
        return new ResponseMessage(-1,message);
    }
    public static ResponseMessage error(String message, Object data){
        return new ResponseMessage(-1,message,data);
    }
    public static ResponseMessage error(int code,String message){
        return new ResponseMessage(code,message);
    }

    public static ResponseMessage isSuccess(boolean isSuccess){
        return isSuccess?success():error();
    }


}
