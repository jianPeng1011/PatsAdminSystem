package com.example.pethospitalmanagement.service;

import com.alibaba.fastjson.JSONObject;

public interface UserService {

    JSONObject insertUserForDoctor(JSONObject jo);

    JSONObject insertUserForCustomer(JSONObject jo);

    JSONObject selectAllDoctor();

    JSONObject selectAllCustomer();

    JSONObject selectMyMoney(JSONObject jo);

    JSONObject updateMyPassword(JSONObject jo);

    JSONObject resetPassword(JSONObject jo);

    JSONObject updateUserByPrimaryKey(JSONObject jo);

    JSONObject updateMyMoney(JSONObject jo);

    JSONObject deleteByPrimaryKey(JSONObject jo);
}

