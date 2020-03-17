package com.example.pethospitalmanagement.service;

import com.alibaba.fastjson.JSONObject;

public interface DrugService {

    JSONObject insertDrug(JSONObject jo);

    JSONObject updateDrugByPrimaryKey(JSONObject jo);

    JSONObject selectDrugByPrimaryKey(JSONObject jo);

    JSONObject deleteDrugByPrimaryKey(JSONObject jo);
}
