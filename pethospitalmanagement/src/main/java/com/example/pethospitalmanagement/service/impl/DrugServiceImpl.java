package com.example.pethospitalmanagement.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.pethospitalmanagement.entity.Drug;
import com.example.pethospitalmanagement.entity.MyLog;
import com.example.pethospitalmanagement.mapper.DrugMapper;
import com.example.pethospitalmanagement.service.DrugService;
import com.example.pethospitalmanagement.util.FormTools;
import com.example.pethospitalmanagement.util.JsonUtils;
import com.example.pethospitalmanagement.util.MyException;
import com.example.pethospitalmanagement.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DrugServiceImpl implements DrugService {

    @Autowired
    private DrugMapper drugMapper;
    @Override
    @MyLog(operationType="POST", operationName = "添加药品")
    public JSONObject insertDrug(JSONObject jo) {
        Assert.notNull(jo.getString("drugId"),"drugId不允许为空");
        Assert.notNull(jo.getString("drugName"),"drugName不允许为空");
        Assert.notNull(jo.getString("unit"),"unit不允许为空");
        Assert.notNull(jo.getString("price"),"price不允许为空");
        Assert.notNull(jo.getString("sl"),"sl不允许为空");

        BigDecimal money = new BigDecimal(jo.getString("price")).multiply(new BigDecimal(jo.getString("sl")));

        Drug drug = new Drug();
        drug.setDrugid(jo.getIntValue("drugId"));
        drug.setDrugname(jo.getString("drugName"));
        drug.setUnit(jo.getString("unit"));
        drug.setPrice(jo.getBigDecimal("price"));
        drug.setSl(jo.getLongValue("sl"));
        if(!FormTools.isNull(jo.getString("description"))){
            drug.setDescription(jo.getString("description"));
        }
        drug.setMoney(money);

        try{
            int i = drugMapper.insertSelective(drug);
            if(1 != i){
                throw new MyException("E","新增失败");
            }
        }catch (Exception e){
            throw new MyException("E","新增失败");
        }

        return Result.done();
    }

    @Override
    @MyLog(operationType="POST", operationName = "修改药品")
    public JSONObject updateDrugByPrimaryKey(JSONObject jo) {
        Assert.notNull(jo.getString("drugId"),"drugId不允许为空");
        Drug drug = new Drug();
        if(!jo.isEmpty()){
            drug.setDrugid(jo.getIntValue("drugId"));
            if(!FormTools.isNull(jo.getString("drugName"))){
                drug.setDrugname(jo.getString("drugName"));
            }
            if(!FormTools.isNull(jo.getString("unit"))){
                drug.setUnit(jo.getString("unit"));
            }
            if(!FormTools.isNull(jo.getString("description"))){
                drug.setDescription(jo.getString("description"));
            }
            if(!FormTools.isNull(jo.getString("price"))){
                drug.setPrice(jo.getBigDecimal("price"));
            }
            if(!FormTools.isNull(jo.getString("sl"))){
                drug.setSl(jo.getLongValue("sl"));
            }
            if(!FormTools.isNull(jo.getString("sl")) && !FormTools.isNull(jo.getString("price"))) {
                BigDecimal money = new BigDecimal(jo.getString("price")).multiply(new BigDecimal(jo.getString("sl")));
                drug.setMoney(money);
            }

            int i = drugMapper.updateByPrimaryKeySelective(drug);
            if(1 != i){
                throw new MyException("E","修改失败");
            }
        }
        return Result.done();
    }

    @Override
    @MyLog(operationType="POST", operationName = "查询药品")
    public JSONObject selectDrugByPrimaryKey(JSONObject jo) {

        Drug drug = new Drug();
        if(!jo.isEmpty()){
            if(!FormTools.isNull(jo.getString("drugId"))){
                drug.setDrugid(jo.getIntValue("drugId"));
            }
            if(!FormTools.isNull(jo.getString("drugName"))){
                drug.setDrugname(jo.getString("drugName"));
            }
            if(!FormTools.isNull(jo.getString("unit"))){
                drug.setUnit(jo.getString("unit"));
            }
            if(!FormTools.isNull(jo.getString("description"))){
                drug.setDescription(jo.getString("description"));
            }
        }
        List<Drug> drugList = drugMapper.selectAllDrugByPrimaryKeySelective(drug);

        JSONArray ja_drug = JsonUtils.toJA(drugList);

        return Result.done().fluentPut("res",ja_drug);
    }

    @Override
    @MyLog(operationType="POST", operationName = "删除药品")
    public JSONObject deleteDrugByPrimaryKey(JSONObject jo) {
        Assert.notNull(jo.getString("drugId"),"drugId不允许为空");
        int i = drugMapper.deleteByPrimaryKey(jo.getIntValue("drugId"));
        if(1 != i){
            throw new MyException("E","删除失败");
        }
        return Result.done();
    }
}
