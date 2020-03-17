package com.example.pethospitalmanagement.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pethospitalmanagement.entity.MyLog;
import com.example.pethospitalmanagement.entity.User;
import com.example.pethospitalmanagement.entity.UserLoginToken;
import com.example.pethospitalmanagement.service.LoginService;
import com.example.pethospitalmanagement.service.impl.TokenService;
import com.example.pethospitalmanagement.util.JsonUtils;
import com.example.pethospitalmanagement.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/ptm/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private TokenService tokenService;

    /**
     * 用户登录
     * @param data
     * @return
     */
    @MyLog(operationType="POST", operationName = "用户登录")
    @PostMapping("/login")
    public JSONObject login(String data, HttpServletRequest req){

        JSONObject jo = JsonUtils.toJO(data);

        User user = loginService.login(jo);

        String token = tokenService.getToken(user);

        return Result.done().fluentPut("user",user)
                .fluentPut("token",token);
    }
}
