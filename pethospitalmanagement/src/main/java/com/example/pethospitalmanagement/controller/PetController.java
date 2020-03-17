package com.example.pethospitalmanagement.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pethospitalmanagement.entity.MyLog;
import com.example.pethospitalmanagement.entity.UserLoginToken;
import com.example.pethospitalmanagement.service.PetService;
import com.example.pethospitalmanagement.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@UserLoginToken
@RestController
@RequestMapping("/ptm/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @MyLog(operationType="POST", operationName = "添加宠物信息")
    @RequestMapping("/insertPet")
    public JSONObject insertPet(String data) {
        JSONObject jo = JsonUtils.toJO(data);
        return petService.insertPet(jo);
    }

    @MyLog(operationType="POST", operationName = "查询宠物信息")
    @RequestMapping("/selectAllPetByPrimaryKey")
    public JSONObject selectAllPetByPrimaryKey(String data) {
        JSONObject jo = JsonUtils.toJO(data);
        return petService.selectAllPetByPrimaryKey(jo);
    }

    @MyLog(operationType="POST", operationName = "修改宠物信息")
    @RequestMapping("/updatePetByPrimaryKey")
    public JSONObject updatePetByPrimaryKey(String data) {
        JSONObject jo = JsonUtils.toJO(data);
        return petService.updatePetByPrimaryKey(jo);
    }

    @MyLog(operationType="POST", operationName = "删除宠物信息")
    @RequestMapping("/deletePetByPrimaryKey")
    public JSONObject deletePetByPrimaryKey(String data) {
        JSONObject jo = JsonUtils.toJO(data);
        return petService.deletePetByPrimaryKey(jo);
    }
}
