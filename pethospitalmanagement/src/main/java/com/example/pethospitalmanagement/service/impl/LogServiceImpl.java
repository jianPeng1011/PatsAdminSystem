package com.example.pethospitalmanagement.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.pethospitalmanagement.entity.Log;
import com.example.pethospitalmanagement.mapper.LogMapper;
import com.example.pethospitalmanagement.service.LogService;
import com.example.pethospitalmanagement.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public JSONObject insertLog(JSONObject jo) {
        Log log = new Log();
        if(!jo.isEmpty()){
            log.setLogid(RandomUtils.createRandomStrUseTime("LOG"));
            if(!FormTools.isNull(jo.getString("description"))){
                log.setDescription(jo.getString("description"));
            }
            if(!FormTools.isNull(jo.getString("method"))){
                log.setMethod(jo.getString("method"));
            }
            if(!FormTools.isNull(jo.getString("logType"))){
                log.setLogtype(jo.getString("logType"));
            }
            if(!FormTools.isNull(jo.getString("requestIp"))){
                log.setRequestip(jo.getString("requestIp"));
            }
            if(!FormTools.isNull(jo.getString("exceptionCode"))){
                log.setExceptioncode(jo.getString("exceptionCode"));
            }
            if(!FormTools.isNull(jo.getString("exceptionDetail"))){
                log.setExceptiondetail(jo.getString("exceptionDetail"));
            }
            if(!FormTools.isNull(jo.getString("createBy"))){
                log.setCreateby(jo.getString("createBy"));
            }
            if(!FormTools.isNull(jo.getString("createDate"))){
                log.setCreatedate(jo.getDate("createDate"));
            }

            int i = logMapper.insertSelective(log);
            if(i != i){
                throw new MyException("E","日志记录异常");
            }
        }
        return Result.done();
    }

    @Override
    public JSONObject selectAllLogByPrimaryKey(JSONObject jo) {

        Log log = new Log();
        if(!jo.isEmpty()){
            if(!FormTools.isNull(jo.getString("logId"))){
                log.setLogid(jo.getString("logId"));
            }
            if(!FormTools.isNull(jo.getString("description"))){
                log.setDescription(jo.getString("description"));
            }
            if(!FormTools.isNull(jo.getString("method"))){
                log.setDescription(jo.getString("method"));
            }
            if(!FormTools.isNull(jo.getString("logType"))){
                log.setLogtype(jo.getString("logType"));
            }
            if(!FormTools.isNull(jo.getString("requestIp"))){
                log.setRequestip(jo.getString("requestIp"));
            }
            if(!FormTools.isNull(jo.getString("excrptionCode"))){
                log.setExceptioncode(jo.getString("excrptionCode"));
            }
            if(!FormTools.isNull(jo.getString("exceptionDetail"))){
                log.setExceptiondetail(jo.getString("exceptionDetail"));
            }
            if(!FormTools.isNull(jo.getString("createBy"))){
                log.setCreateby(jo.getString("createBy"));
            }
            if(!FormTools.isNull(jo.getString("createDate"))){
                log.setCreatedate(jo.getDate("createDate"));
            }
        }
        List<Log> logList = logMapper.selectAllLogByPrimaryKey(log);
        JSONArray ja_log = JsonUtils.toJA(logList);

        return Result.done().fluentPut("res",ja_log);
    }

    @Override
    public JSONObject updateLogByPrimaryKey(JSONObject jo) {
        Assert.notNull(jo.getString("logId"),"logId不允许为空");
        Log log = new Log();
        if(!jo.isEmpty()){
            log.setLogid(jo.getString("logId"));
            if(!FormTools.isNull(jo.getString("description"))){
                log.setDescription(jo.getString("description"));
            }
            if(!FormTools.isNull(jo.getString("method"))){
                log.setDescription(jo.getString("method"));
            }
            if(!FormTools.isNull(jo.getString("logType"))){
                log.setLogtype(jo.getString("logType"));
            }
            if(!FormTools.isNull(jo.getString("requestIp"))){
                log.setRequestip(jo.getString("requestIp"));
            }
            if(!FormTools.isNull(jo.getString("excrptionCode"))){
                log.setExceptioncode(jo.getString("excrptionCode"));
            }
            if(!FormTools.isNull(jo.getString("exceptionDetail"))){
                log.setExceptiondetail(jo.getString("exceptionDetail"));
            }
            if(!FormTools.isNull(jo.getString("createBy"))){
                log.setCreateby(jo.getString("createBy"));
            }
            if(!FormTools.isNull(jo.getString("createDate"))){
                log.setCreatedate(jo.getDate("createDate"));
            }
        }
        int i = logMapper.updateByPrimaryKey(log);
        if(1 != i){
            throw new MyException("E","修改失败");
        }

        return Result.done();
    }

    @Override
    public JSONObject deleteLogByPrimaryKey(JSONObject jo) {
        Assert.notNull(jo.getString("logId"),"logId不允许为空");
        int i = logMapper.deleteByPrimaryKey(jo.getString("logId"));
        if(1 != i){
            throw new MyException("E","删除失败");
        }
        return Result.done();
    }

    @Override
    public JSONObject selectAllLogByPaging(JSONObject jo) {
        Assert.notNull(jo.getString("pageNum"),"pageNum不允许为空");
        Assert.notNull(jo.getString("pageSize"),"pageSize不允许为空");
        Log log = new Log();
        if(!jo.isEmpty()){
            if(!FormTools.isNull(jo.getString("logId"))){
                log.setLogid(jo.getString("logId"));
            }
            if(!FormTools.isNull(jo.getString("description"))){
                log.setDescription(jo.getString("description"));
            }
            if(!FormTools.isNull(jo.getString("method"))){
                log.setDescription(jo.getString("method"));
            }
            if(!FormTools.isNull(jo.getString("logType"))){
                log.setLogtype(jo.getString("logType"));
            }
            if(!FormTools.isNull(jo.getString("requestIp"))){
                log.setRequestip(jo.getString("requestIp"));
            }
            if(!FormTools.isNull(jo.getString("excrptionCode"))){
                log.setExceptioncode(jo.getString("excrptionCode"));
            }
            if(!FormTools.isNull(jo.getString("exceptionDetail"))){
                log.setExceptiondetail(jo.getString("exceptionDetail"));
            }
            if(!FormTools.isNull(jo.getString("createBy"))){
                log.setCreateby(jo.getString("createBy"));
            }
            if(!FormTools.isNull(jo.getString("createDate"))){
                log.setCreatedate(jo.getDate("createDate"));
            }
        }
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(jo.getIntValue("pageNum"));
        pageRequest.setPageSize(jo.getIntValue("pageSize"));
        PageInfo<Log> pageInfo = getPageInfo(pageRequest,log);
        PageResult pageResult = PageUtils.getPageResult(pageRequest,pageInfo);
        return Result.done().fluentPut("res",pageResult);
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<Log> getPageInfo(PageRequest pageRequest,Log log) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Log> logList = logMapper.selectAllLogByPrimaryKey(log);
        return new PageInfo<Log>(logList);
    }
}
