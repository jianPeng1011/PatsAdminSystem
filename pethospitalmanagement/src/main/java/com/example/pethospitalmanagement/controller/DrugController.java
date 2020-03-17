package com.example.pethospitalmanagement.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pethospitalmanagement.entity.MyLog;
import com.example.pethospitalmanagement.entity.UserLoginToken;
import com.example.pethospitalmanagement.service.DrugService;
import com.example.pethospitalmanagement.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@UserLoginToken
@RestController
@RequestMapping("/ptm/drug")
public class DrugController {

    @Autowired
    private DrugService drugService;

    @MyLog(operationType="POST", operationName = "添加药品")
    @RequestMapping("/insertDrug")
    public JSONObject insertDrug(String data) {
        JSONObject jo = JsonUtils.toJO(data);
        return drugService.insertDrug(jo);
    }

    @MyLog(operationType="POST", operationName = "修改药品")
    @RequestMapping("/updateDrugByPrimaryKey")
    public JSONObject updateDrugByPrimaryKey(String data) {
        JSONObject jo = JsonUtils.toJO(data);
        return drugService.updateDrugByPrimaryKey(jo);
    }

    @MyLog(operationType="POST", operationName = "查询药品")
    @RequestMapping("/selectDrugByPrimaryKey")
    public JSONObject selectDrugByPrimaryKey(String data) {
        JSONObject jo = JsonUtils.toJO(data);
        return drugService.selectDrugByPrimaryKey(jo);
    }

    @MyLog(operationType="POST", operationName = "删除药品")
    @RequestMapping("/deleteDrugByPrimaryKey")
    public JSONObject deleteDrugByPrimaryKey(String data) {
        JSONObject jo = JsonUtils.toJO(data);
        return drugService.deleteDrugByPrimaryKey(jo);
    }

}
