package com.example.pethospitalmanagement.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pethospitalmanagement.entity.MyLog;
import com.example.pethospitalmanagement.entity.UserLoginToken;
import com.example.pethospitalmanagement.service.SeeDoctorService;
import com.example.pethospitalmanagement.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@UserLoginToken
@RestController
@RequestMapping("/ptm/sdr")
public class SeeDoctorController {

    @Autowired
    private SeeDoctorService seeDoctorService;

    @MyLog(operationType="POST", operationName = "添加预约就诊信息")
    @RequestMapping("/insertSeeDoctor")
    public JSONObject insertSeeDoctor(String data) {
        JSONObject jo = JsonUtils.toJO(data);
        return seeDoctorService.insertSeeDoctor(jo);
    }

    @MyLog(operationType="POST", operationName = "用户查询预约就诊信息")
    @RequestMapping("/selectAllSeeDoctorByUser")
    public JSONObject selectAllSeeDoctorByUser(String data) {
        JSONObject jo = JsonUtils.toJO(data);
        return seeDoctorService.selectAllSeeDoctorByUser(jo);
    }

    @MyLog(operationType="POST", operationName = "查询预约就诊信息")
    @RequestMapping("/selectAllSeeDoctorByPrimaryKey")
    public JSONObject selectAllSeeDoctorByPrimaryKey(String data) {
        JSONObject jo = JsonUtils.toJO(data);
        return seeDoctorService.selectAllSeeDoctorByPrimaryKey(jo);
    }

    @MyLog(operationType="POST", operationName = "查询留言板")
    @RequestMapping("/selectAllMessage")
    public JSONObject selectAllMessage(String data) {
        JSONObject jo = JsonUtils.toJO(data);
        return seeDoctorService.selectAllMessage(jo);
    }

    @MyLog(operationType="POST", operationName = "修改预约就诊信息")
    @RequestMapping("/updateSeeDoctorByPrimaryKey")
    public JSONObject updateSeeDoctorByPrimaryKey(String data){
        JSONObject jo = JsonUtils.toJO(data);
        return seeDoctorService.updateSeeDoctorByPrimaryKey(jo);
    }

    @MyLog(operationType="POST", operationName = "处理预约就诊信息为预约成功")
    @RequestMapping("/updateSeeDoctorToArrange")
    public JSONObject updateSeeDoctorToArrange(String data){
        JSONObject jo = JsonUtils.toJO(data);
        return seeDoctorService.updateSeeDoctorToArrange(jo);
    }

    @MyLog(operationType="POST", operationName = "处理预约就诊信息为已完成")
    @RequestMapping("/updateSeeDoctorToTreatment")
    public JSONObject updateSeeDoctorToTreatment(String data){
        JSONObject jo = JsonUtils.toJO(data);
        return seeDoctorService.updateSeeDoctorToTreatment(jo);
    }

    @MyLog(operationType="POST", operationName = "预约就诊信息评价")
    @RequestMapping("/updateSeeDoctorEvaluation")
    public JSONObject updateSeeDoctorEvaluation(String data){
        JSONObject jo = JsonUtils.toJO(data);
        return seeDoctorService.updateSeeDoctorEvaluation(jo);
    }

    @MyLog(operationType="POST", operationName = "处理预约就诊信息为拒绝")
    @RequestMapping("/updateRefuseTreatment")
    public JSONObject updateRefuseTreatment(String data){
        JSONObject jo = JsonUtils.toJO(data);
        return seeDoctorService.updateRefuseTreatment(jo);
    }

    @MyLog(operationType="POST", operationName = "删除处理预约就诊信息")
    @RequestMapping("/deleteSeeDoctorByPrimaryKey")
    public JSONObject deleteSeeDoctorByPrimaryKey(String data){
        JSONObject jo = JsonUtils.toJO(data);
        return seeDoctorService.deleteSeeDoctorByPrimaryKey(jo);
    }
}
