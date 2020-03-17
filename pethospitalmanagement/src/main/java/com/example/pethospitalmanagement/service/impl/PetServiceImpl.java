package com.example.pethospitalmanagement.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.pethospitalmanagement.entity.MyLog;
import com.example.pethospitalmanagement.entity.Pet;
import com.example.pethospitalmanagement.mapper.PetMapper;
import com.example.pethospitalmanagement.service.PetService;
import com.example.pethospitalmanagement.util.FormTools;
import com.example.pethospitalmanagement.util.JsonUtils;
import com.example.pethospitalmanagement.util.MyException;
import com.example.pethospitalmanagement.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetMapper petMapper;

    @Override
    @MyLog(operationType="POST", operationName = "添加宠物信息")
    public JSONObject insertPet(JSONObject jo) {
        Assert.notNull(jo.getString("name"),"name不允许为空");
        Assert.notNull(jo.getString("type"),"type不允许为空");
        Assert.notNull(jo.getString("breed"),"breed不允许为空");
        Assert.notNull(jo.getString("description"),"description不允许为空");

        Pet pet = new Pet();
        pet.setName(jo.getString("name"));
        pet.setType(jo.getString("type"));
        pet.setBreed(jo.getString("breed"));
        pet.setDescription(jo.getString("description"));
        if(!FormTools.isNull(jo.getString("note"))){
            pet.setNote(jo.getString("note"));
        }

        int i = petMapper.insertSelective(pet);
        if(1 != i){
            throw new MyException("E","新增失败");
        }

        return Result.done();
    }

    @Override
    @MyLog(operationType="POST", operationName = "查询宠物信息")
    public JSONObject selectAllPetByPrimaryKey(JSONObject jo) {
        Pet pet = new Pet();
        if(!jo.isEmpty()){
            if(!FormTools.isNull(jo.getString("name"))){
                pet.setName(jo.getString("name"));
            }
            if(!FormTools.isNull(jo.getString("type"))){
                pet.setType(jo.getString("type"));
            }
            if(!FormTools.isNull(jo.getString("breed"))){
                pet.setBreed(jo.getString("breed"));
            }
            if(!FormTools.isNull(jo.getString("description"))){
                pet.setDescription(jo.getString("description"));
            }
        }
        List<Pet> petList = petMapper.selectAllPetByPrimaryKey(pet);

        JSONArray ja_pet = JsonUtils.toJA(petList);

        return Result.done().fluentPut("res",ja_pet);
    }

    @Override
    @MyLog(operationType="POST", operationName = "修改宠物信息")
    public JSONObject updatePetByPrimaryKey(JSONObject jo) {
        Assert.notNull(jo.getString("id"),"id不允许为空");

        Pet pet = new Pet();
        if(!jo.isEmpty()){
            pet.setId(jo.getIntValue("id"));
            if(!FormTools.isNull(jo.getString("name"))){
                pet.setName(jo.getString("name"));
            }
            if(!FormTools.isNull(jo.getString("type"))){
                pet.setType(jo.getString("type"));
            }
            if(!FormTools.isNull(jo.getString("breed"))){
                pet.setBreed(jo.getString("breed"));
            }
            if(!FormTools.isNull(jo.getString("description"))){
                pet.setDescription(jo.getString("description"));
            }
            if(!FormTools.isNull(jo.getString("note"))){
                pet.setNote(jo.getString("note"));
            }
        }
        int i = petMapper.updateByPrimaryKeySelective(pet);
        if(1 != i){
            throw new MyException("E","修改失败");
        }
        return Result.done();
    }

    @Override
    @MyLog(operationType="POST", operationName = "删除宠物信息")
    public JSONObject deletePetByPrimaryKey(JSONObject jo) {
        Assert.notNull(jo.getString("id"),"id不允许为空");

        int i = petMapper.deleteByPrimaryKey(jo.getIntValue("id"));
        if(1 != i){
            throw new MyException("E","删除失败");
        }
        return Result.done();
    }
}
