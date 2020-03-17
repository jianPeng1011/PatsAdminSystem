package com.example.pethospitalmanagement.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.pethospitalmanagement.entity.MyLog;
import com.example.pethospitalmanagement.entity.User;
import com.example.pethospitalmanagement.mapper.UserMapper;
import com.example.pethospitalmanagement.service.LoginService;
import com.example.pethospitalmanagement.util.FormTools;
import com.example.pethospitalmanagement.util.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @MyLog(operationType="POST", operationName = "用户登录")
    public User login(JSONObject jo) {

        Assert.notNull(jo.getString("phone"),"请输入手机号");
        /*Assert.notNull(jo.getString("password"),"请输入密码");*/


        User user = new User();
        user.setPhone(jo.getString("phone"));
        user.setPassword(jo.getString("password"));

        user = userMapper.login(user);

        if(FormTools.isNull(user)) {
            throw new MyException("E","请输入正确的手机号和密码");
        }

        return user;
    }

}
