package com.example.pethospitalmanagement.service;

import com.alibaba.fastjson.JSONObject;
import com.example.pethospitalmanagement.entity.User;

public interface LoginService {
    User login(JSONObject jo);
}
