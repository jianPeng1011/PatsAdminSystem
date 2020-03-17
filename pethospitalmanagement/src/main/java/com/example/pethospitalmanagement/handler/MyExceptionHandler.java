package com.example.pethospitalmanagement.handler;

import com.alibaba.fastjson.JSONObject;
import com.example.pethospitalmanagement.util.JsonUtils;
import com.example.pethospitalmanagement.util.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class MyExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public JSONObject myExceptionHandler(MyException e) {
        JSONObject jo = JsonUtils.JO();
        jo.put("ID", e.getCode());
        jo.put("MSG",e.getMsg());
        return jo;
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public JSONObject exceptionHandler(Exception e) {
        JSONObject jo = JsonUtils.JO();
        jo.put("ID", "E");
        jo.put("MSG",e.getMessage());
        return jo;
    }
}
