package com.example.pethospitalmanagement.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pethospitalmanagement.entity.MyLog;
import com.example.pethospitalmanagement.entity.PassToken;
import com.example.pethospitalmanagement.entity.UserLoginToken;
import com.example.pethospitalmanagement.service.UserService;
import com.example.pethospitalmanagement.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@UserLoginToken
@RestController
@RequestMapping("/ptm/user")
public class UserController {

    @Autowired
    private UserService userService;

    @MyLog(operationType="POST", operationName = "添加医生信息")
    @RequestMapping("/insertUserForDoctor")
    public JSONObject insertUserForDoctor(String data){
        JSONObject jo = JsonUtils.toJO(data);
        return userService.insertUserForDoctor(jo);
    }

    @PassToken
    @MyLog(operationType="POST", operationName = "添加用户信息")
    @RequestMapping("/insertUserForCustomer")
    public JSONObject insertUserForCustomer(String data){
        JSONObject jo = JsonUtils.toJO(data);
        return userService.insertUserForCustomer(jo);
    }

    @MyLog(operationType="POST", operationName = "查询所有医生信息")
    @RequestMapping("/selectAllDoctor")
    public JSONObject selectAllDoctor() {
        return userService.selectAllDoctor();
    }

    @MyLog(operationType="POST", operationName = "查询所有用户信息")
    @RequestMapping("/selectAllCustomer")
    public JSONObject selectAllCustomer() {
        return userService.selectAllCustomer();
    }

    @MyLog(operationType="POST", operationName = "查询我的钱包")
    @RequestMapping("/selectMyMoney")
    public JSONObject selectMyMoney(String data){
        JSONObject jo = JsonUtils.toJO(data);
        return userService.selectMyMoney(jo);
    }

    @MyLog(operationType="POST", operationName = "修改医生/用户信息")
    @RequestMapping("/updateUserByPrimaryKey")
    public JSONObject updateUserByPrimaryKey(String data){
        JSONObject jo = JsonUtils.toJO(data);
        return userService.updateUserByPrimaryKey(jo);
    }

    @MyLog(operationType="POST", operationName = "修改个人密码")
    @RequestMapping("/updateMyPassword")
    public JSONObject updateMyPassword(String data) {
        JSONObject jo = JsonUtils.toJO(data);
        return userService.updateMyPassword(jo);
    }

    @MyLog(operationType="POST", operationName = "重置密码")
    @RequestMapping("/resetPassword")
    public JSONObject resetPassword(String data) {
        JSONObject jo = JsonUtils.toJO(data);
        return userService.resetPassword(jo);
    }

    @MyLog(operationType="POST", operationName = "修改我的钱包金额")
    @RequestMapping("/updateMyMoney")
    public JSONObject updateMyMoney(String data) {
        JSONObject jo = JsonUtils.toJO(data);
        return userService.updateMyMoney(jo);
    }

    @MyLog(operationType="POST", operationName = "删除用户/医生")
    @RequestMapping("/deleteUserByPrimaryKey")
    public JSONObject deleteUserByPrimaryKey(String data){
        JSONObject jo = JsonUtils.toJO(data);
        return userService.deleteByPrimaryKey(jo);
    }

}
