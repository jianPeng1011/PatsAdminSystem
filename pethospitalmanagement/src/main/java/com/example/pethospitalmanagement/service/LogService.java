package com.example.pethospitalmanagement.service;

import com.alibaba.fastjson.JSONObject;

public interface LogService {
    JSONObject insertLog(JSONObject jo);

    JSONObject selectAllLogByPrimaryKey(JSONObject jo);

    JSONObject updateLogByPrimaryKey(JSONObject jo);

    JSONObject deleteLogByPrimaryKey(JSONObject jo);

    JSONObject selectAllLogByPaging(JSONObject jo);
}
