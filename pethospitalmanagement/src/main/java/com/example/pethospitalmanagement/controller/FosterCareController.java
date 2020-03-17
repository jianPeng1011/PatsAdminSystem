package com.example.pethospitalmanagement.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pethospitalmanagement.entity.MyLog;
import com.example.pethospitalmanagement.entity.UserLoginToken;
import com.example.pethospitalmanagement.service.FosterCareService;
import com.example.pethospitalmanagement.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@UserLoginToken
@RestController
@RequestMapping("/ptm/foster")
public class FosterCareController {

    @Autowired
    private FosterCareService fosterCareService;

    @MyLog(operationType="POST", operationName = "添加预约寄养信息")
    @RequestMapping("/insertFosterCare")
    public JSONObject insertFosterCare(String data) {
        JSONObject jo = JsonUtils.toJO(data);
        return fosterCareService.insertFosterCare(jo);
    }

    @MyLog(operationType="POST", operationName = "用户查询预约寄养信息")
    @RequestMapping("/selectAllFosterCareByUser")
    public JSONObject selectAllFosterCareByUser(String data) {
        JSONObject jo = JsonUtils.toJO(data);
        return fosterCareService.selectAllFosterCareByUser(jo);
    }


    @MyLog(operationType="POST", operationName = "查询预约寄养信息")
    @RequestMapping("/selectAllFosterCareByPrimaryKey")
    public JSONObject selectAllFosterCareByPrimaryKey(String data) {
        JSONObject jo = JsonUtils.toJO(data);
        return fosterCareService.selectAllFosterCareByPrimaryKey(jo);
    }

    @MyLog(operationType="POST", operationName = "修改预约寄养信息")
    @RequestMapping("/updateFosterCareByPrimaryKey")
    public JSONObject updateFosterCareByPrimaryKey(String data){
        JSONObject jo = JsonUtils.toJO(data);
        return fosterCareService.updateFosterCareByPrimaryKey(jo);
    }

    @MyLog(operationType="POST", operationName = "处理预约寄养信息为预约成功")
    @RequestMapping("/updateFosterCareToRegister")
    public JSONObject updateFosterCareToRegister(String data){
        JSONObject jo = JsonUtils.toJO(data);
        return fosterCareService.updateFosterCareToRegister(jo);
    }

    @MyLog(operationType="POST", operationName = "处理预约寄养信息为已完成")
    @RequestMapping("/updateFosterCareToArrange")
    public JSONObject updateFosterCareToArrange(String data){
        JSONObject jo = JsonUtils.toJO(data);
        return fosterCareService.updateFosterCareToArrange(jo);
    }

    @MyLog(operationType="POST", operationName = "预约寄养信息评价")
    @RequestMapping("/updateFosterCareEvaluation")
    public JSONObject updateFosterCareEvaluation(String data){
        JSONObject jo = JsonUtils.toJO(data);
        return fosterCareService.updateFosterCareEvaluation(jo);
    }

    @MyLog(operationType="POST", operationName = "处理预约寄养信息为拒绝")
    @RequestMapping("/updateRefuseFosterCare")
    public JSONObject updateRefuseFosterCare(String data){
        JSONObject jo = JsonUtils.toJO(data);
        return fosterCareService.updateRefuseFosterCare(jo);
    }

    @MyLog(operationType="POST", operationName = "删除预约寄养信息")
    @RequestMapping("/deleteFosterCareByPrimaryKey")
    public JSONObject deleteFosterCareByPrimaryKey(String data){
        JSONObject jo = JsonUtils.toJO(data);
        return fosterCareService.deleteFosterCareByPrimaryKey(jo);
    }
}
