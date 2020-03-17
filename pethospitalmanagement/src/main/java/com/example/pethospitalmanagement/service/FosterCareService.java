package com.example.pethospitalmanagement.service;

import com.alibaba.fastjson.JSONObject;

public interface FosterCareService {

    JSONObject insertFosterCare(JSONObject jo);

    JSONObject selectAllFosterCareByUser(JSONObject jo);

    JSONObject selectAllFosterCareByPrimaryKey(JSONObject jo);

    JSONObject updateFosterCareByPrimaryKey(JSONObject jo);

    JSONObject updateFosterCareToRegister(JSONObject jo);

    JSONObject updateFosterCareToArrange(JSONObject jo);

    JSONObject updateFosterCareEvaluation(JSONObject jo);

    JSONObject updateRefuseFosterCare(JSONObject jo);

    JSONObject deleteFosterCareByPrimaryKey(JSONObject jo);
}
