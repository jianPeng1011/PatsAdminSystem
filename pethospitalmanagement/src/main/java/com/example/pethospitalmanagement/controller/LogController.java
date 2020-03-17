package com.example.pethospitalmanagement.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pethospitalmanagement.entity.MyLog;
import com.example.pethospitalmanagement.entity.UserLoginToken;
import com.example.pethospitalmanagement.service.LogService;
import com.example.pethospitalmanagement.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@UserLoginToken
@RestController
@RequestMapping("/ptm/log")
public class LogController {
    @Autowired
    private LogService logService;

    @MyLog(operationType="POST", operationName = "查询日志记录")
    @RequestMapping("/selectAllLogByPrimaryKey")
    public JSONObject selectAllLogByPrimaryKey(String data) {
        JSONObject jo = JsonUtils.toJO(data);
        return logService.selectAllLogByPrimaryKey(jo);
    }

    @MyLog(operationType="POST", operationName = "查询日志记录")
    @RequestMapping("/selectAllLogByPaging")
    public JSONObject selectAllLogByPaging(String data) {
        JSONObject jo = JsonUtils.toJO(data);
        return logService.selectAllLogByPaging(jo);
    }
}
