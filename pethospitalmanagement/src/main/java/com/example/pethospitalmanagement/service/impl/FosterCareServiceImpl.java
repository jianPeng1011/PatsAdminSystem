package com.example.pethospitalmanagement.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.pethospitalmanagement.constast.Constast;
import com.example.pethospitalmanagement.entity.FosterCare;
import com.example.pethospitalmanagement.entity.MyLog;
import com.example.pethospitalmanagement.entity.User;
import com.example.pethospitalmanagement.mapper.FosterCareMapper;
import com.example.pethospitalmanagement.mapper.UserMapper;
import com.example.pethospitalmanagement.service.FosterCareService;
import com.example.pethospitalmanagement.util.*;
import com.example.pethospitalmanagement.vo.FosterCareVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class FosterCareServiceImpl implements FosterCareService {

    @Autowired
    private FosterCareMapper fosterCareMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    @MyLog(operationType="POST", operationName = "添加预约寄养信息")
    public JSONObject insertFosterCare(JSONObject jo) {
        Assert.notNull(jo.get("userId"),"userId不允许为空");
        Assert.notNull(jo.getString("userName"),"userName不允许为空");
        Assert.notNull(jo.getString("petName"),"petName不允许为空");
        Assert.notNull(jo.getString("phone"),"phone不允许为空");
        Assert.notNull(jo.get("startTime"),"startTime不允许为空");
        Assert.notNull(jo.get("endTime"),"endTime不允许为空");
        Assert.notNull(jo.getString("details"),"details不允许为空");
        Assert.notNull(jo.getString("payMoney"),"payMoney不允许为空");

        //顾客支付订单金额
        User user = userMapper.selectByPrimaryKey(jo.getIntValue("userId"));
        BigDecimal money = user.getMoney();
        BigDecimal payMoney = jo.getBigDecimal("payMoney");
        BigDecimal accountBalance = money.subtract(payMoney);
        BigDecimal zero = new BigDecimal(0);
        if(accountBalance.compareTo(zero) == -1){
            throw new MyException("E","账户余额不足");
        }
        user.setMoney(accountBalance);
        try{
            int i = userMapper.updateByPrimaryKeySelective(user);
            if(1 != i){
                throw new MyException("E","支付失败");
            }
        }catch (Exception e){
            throw new MyException("E","支付失败");
        }

        FosterCare fosterCare = new FosterCare();
        fosterCare.setFosterid(RandomUtils.createRandomStrUseTime("JY"));
        fosterCare.setUserid(jo.getIntValue("userId"));
        fosterCare.setUsername(jo.getString("userName"));
        fosterCare.setPetname(jo.getString("petName"));
        fosterCare.setPhone(jo.getString("phone"));
        fosterCare.setStarttime(jo.getDate("startTime"));
        fosterCare.setEndtime(jo.getDate("endTime"));
        fosterCare.setCreatetime(new Date());
        fosterCare.setDetails(jo.getString("details"));
        fosterCare.setState(Constast.SUBSCRIBE_STATE_DEFAULT);

        int i = fosterCareMapper.insertSelective(fosterCare);
        if(1 != i){
            throw new MyException("E","新增失败");
        }

        return Result.done().fluentPut("money",accountBalance);
    }

    @Override
    @MyLog(operationType="POST", operationName = "用户查询预约寄养信息")
    public JSONObject selectAllFosterCareByUser(JSONObject jo) {
        Assert.notNull(jo.get("userId"),"userId不允许为空");

        FosterCareVo fosterCareVo = new FosterCareVo();
        if(!jo.isEmpty()){
            fosterCareVo.setUserid(jo.getIntValue("userId"));
            if(!FormTools.isNull(jo.getString("fosterId"))){
                fosterCareVo.setFosterid(jo.getString("fosterId"));
            }
            if(!FormTools.isNull(jo.getString("petName"))){
                fosterCareVo.setPetname(jo.getString("petName"));
            }
            if(!FormTools.isNull(jo.getString("phone"))){
                fosterCareVo.setPhone(jo.getString("phone"));
            }
            if(!FormTools.isNull(jo.getString("startTime_s"))){
                fosterCareVo.setStartTime_s(jo.getDate("startTime_s"));
            }
            if(!FormTools.isNull(jo.getString("startTime_e"))){
                fosterCareVo.setStartTime_e(jo.getDate("startTime_e"));
            }
            if(!FormTools.isNull(jo.getString("endTime_s"))){
                fosterCareVo.setEndTime_s(jo.getDate("endTime_s"));
            }
            if(!FormTools.isNull(jo.getString("endTime_e"))){
                fosterCareVo.setEndTime_e(jo.getDate("endTime_e"));
            }
            if(!FormTools.isNull(jo.getString("createTime_s"))){
                fosterCareVo.setCreateTime_s(jo.getDate("createTime_s"));
            }
            if(!FormTools.isNull(jo.getString("createTime_e"))){
                fosterCareVo.setCreateTime_e(jo.getDate("createTime_e"));
            }
            if(!FormTools.isNull(jo.getString("details"))){
                fosterCareVo.setDetails(jo.getString("details"));
            }
            if(!FormTools.isNull(jo.getString("state"))){
                fosterCareVo.setState(jo.getString("state"));
            }
        }

        List<FosterCare> fosterCareList = fosterCareMapper.selectAllFosterCareByPrimaryKeySelective(fosterCareVo);

        JSONArray ja_fosterCare = JsonUtils.toJA(fosterCareList);

        return Result.done().fluentPut("res",ja_fosterCare);
    }

    @Override
    @MyLog(operationType="POST", operationName = "查询预约寄养信息")
    public JSONObject selectAllFosterCareByPrimaryKey(JSONObject jo) {

        FosterCareVo fosterCareVo = new FosterCareVo();
        if(!jo.isEmpty()){
            if(!FormTools.isNull(jo.getString("userName"))){
                fosterCareVo.setUsername(jo.getString("userName"));
            }
            if(!FormTools.isNull(jo.getString("fosterId"))){
                fosterCareVo.setFosterid(jo.getString("fosterId"));
            }
            if(!FormTools.isNull(jo.getString("petName"))){
                fosterCareVo.setPetname(jo.getString("petName"));
            }
            if(!FormTools.isNull(jo.getString("phone"))){
                fosterCareVo.setPhone(jo.getString("phone"));
            }
            if(!FormTools.isNull(jo.getString("startTime_s"))){
                fosterCareVo.setStartTime_s(jo.getDate("startTime_s"));
            }
            if(!FormTools.isNull(jo.getString("startTime_e"))){
                fosterCareVo.setStartTime_e(jo.getDate("startTime_e"));
            }
            if(!FormTools.isNull(jo.getString("endTime_s"))){
                fosterCareVo.setEndTime_s(jo.getDate("endTime_s"));
            }
            if(!FormTools.isNull(jo.getString("endTime_e"))){
                fosterCareVo.setEndTime_e(jo.getDate("endTime_e"));
            }
            if(!FormTools.isNull(jo.getString("createTime_s"))){
                fosterCareVo.setCreateTime_s(jo.getDate("createTime_s"));
            }
            if(!FormTools.isNull(jo.getString("createTime_e"))){
                fosterCareVo.setCreateTime_e(jo.getDate("createTime_e"));
            }
            if(!FormTools.isNull(jo.getString("details"))){
                fosterCareVo.setDetails(jo.getString("details"));
            }
            if(!FormTools.isNull(jo.getString("state"))){
                fosterCareVo.setState(jo.getString("state"));
            }
        }

        List<FosterCare> fosterCareList = fosterCareMapper.selectAllFosterCareByPrimaryKeySelective(fosterCareVo);

        JSONArray ja_fosterCare = JsonUtils.toJA(fosterCareList);

        return Result.done().fluentPut("res",ja_fosterCare);
    }

    @Override
    @MyLog(operationType="POST", operationName = "修改预约寄养信息")
    public JSONObject updateFosterCareByPrimaryKey(JSONObject jo) {
        Assert.notNull(jo.get("fosterId"),"fosterId不允许为空");

        FosterCare fosterCare = new FosterCare();
        if(!jo.isEmpty()){
            fosterCare.setFosterid(jo.getString("fosterId"));
            if(!FormTools.isNull(jo.getString("userId"))){
                fosterCare.setUserid(jo.getIntValue("userId"));
            }
            if(!FormTools.isNull(jo.getString("userName"))){
                fosterCare.setUsername(jo.getString("userName"));
            }
            if(!FormTools.isNull(jo.getString("petName"))){
                fosterCare.setPetname(jo.getString("petName"));
            }
            if(!FormTools.isNull(jo.getString("phone"))){
                fosterCare.setPhone(jo.getString("phone"));
            }
            if(!FormTools.isNull(jo.getString("startTime"))){
                fosterCare.setStarttime(jo.getDate("startTime"));
            }
            if(!FormTools.isNull(jo.getString("endTime"))){
                fosterCare.setEndtime(jo.getDate("endTime"));
            }
            if(!FormTools.isNull(jo.getString("createTime"))){
                fosterCare.setCreatetime(jo.getDate("createTime"));
            }
            if(!FormTools.isNull(jo.getString("details"))){
                fosterCare.setDetails(jo.getString("details"));
            }
            if(!FormTools.isNull(jo.getString("state"))){
                fosterCare.setState(jo.getString("state"));
            }
        }
        int i = fosterCareMapper.updateByPrimaryKeySelective(fosterCare);
        if(1 != i){
            throw new MyException("E","修改失败");
        }

        return Result.done();
    }

    @Override
    @MyLog(operationType="POST", operationName = "处理预约寄养信息为预约成功")
    public JSONObject updateFosterCareToRegister(JSONObject jo) {
        Assert.notNull(jo.get("fosterId"),"fosterId不允许为空");

        FosterCare fosterCare = new FosterCare();
        fosterCare.setFosterid(jo.getString("fosterId"));
        fosterCare.setState(Constast.SUBSCRIBE_STATE_REGISTER);
        int i = fosterCareMapper.updateByPrimaryKeySelective(fosterCare);
        if(1 != i){
            throw new MyException("E","修改失败");
        }

        return Result.done();
    }

    @Override
    @MyLog(operationType="POST", operationName = "处理预约寄养信息为已完成")
    public JSONObject updateFosterCareToArrange(JSONObject jo) {
        Assert.notNull(jo.get("fosterId"),"fosterId不允许为空");

        FosterCare fosterCare = new FosterCare();
        fosterCare.setFosterid(jo.getString("fosterId"));
        fosterCare.setState(Constast.SUBSCRIBE_STATE_ARRANGE);
        int i = fosterCareMapper.updateByPrimaryKeySelective(fosterCare);
        if(1 != i){
            throw new MyException("E","修改失败");
        }

        return Result.done();
    }

    @Override
    @MyLog(operationType="POST", operationName = "预约寄养信息评价")
    public JSONObject updateFosterCareEvaluation(JSONObject jo) {
        Assert.notNull(jo.get("fosterId"),"fosterId不允许为空");
        Assert.notNull(jo.get("evaluation"),"evaluation不允许为空");


        FosterCareVo fosterCareVo = new FosterCareVo();
        fosterCareVo.setFosterid(jo.getString("fosterId"));
        fosterCareVo.setState(Constast.SUBSCRIBE_STATE_ARRANGE);
        List<FosterCare> fosterCareList = fosterCareMapper.selectAllFosterCareByPrimaryKeySelective(fosterCareVo);
        if(fosterCareList.isEmpty()){
            throw new MyException("E","该单据未完成，暂不能评价");
        }else{
            fosterCareVo.setEvaluation(jo.getString("evaluation"));
            int i = fosterCareMapper.updateFosterCareEvaluation(fosterCareVo);
            if(1 != i){
                throw new MyException("E","评价失败");
            }
        }
        return Result.done();
    }

    @Override
    @MyLog(operationType="POST", operationName = "处理预约寄养信息为拒绝")
    public JSONObject updateRefuseFosterCare(JSONObject jo) {
        Assert.notNull(jo.getString("fosterId"),"fosterId不允许为空");
        Assert.notNull(jo.getString("evaluation"),"evaluation不允许为空");

        FosterCare fosterCare = fosterCareMapper.selectByPrimaryKey(jo.getString("fosterId"));

        //计算原单据金额
        Date startTime = fosterCare.getStarttime();
        Date endTime = fosterCare.getEndtime();
        double diff = endTime.getTime() - startTime.getTime();//这样得到的差值是毫秒级别
        double msDay = (1000 * 60 * 60 * 24);
        double days = Arith.div(diff,msDay);
        BigDecimal money = Arith.mulForBigDecimal(days,Constast.SUBSCRIBE_DAY_MONEY);
        //原单用户
        User user = userMapper.selectByPrimaryKey(fosterCare.getUserid());
        user.setMoney(user.getMoney().add(money));
        //----->返回用户金额
        try{
            int i = userMapper.updateByPrimaryKeySelective(user);
            if(1 != i){
                throw new MyException("E","资金原路返回失败，请联系系统管理员");
            }
        }catch (Exception e) {
            throw new MyException("E","资金原路返回失败，请联系系统管理员");
        }

        FosterCare fosterCareNew = new FosterCare();
        fosterCareNew.setFosterid(jo.getString("fosterId"));
        fosterCareNew.setEvaluation(jo.getString("evaluation"));
        fosterCareNew.setState(Constast.SUBSCRIBE_STATE_REFUSE);
        int i = fosterCareMapper.updateFosterCareEvaluation(fosterCareNew);

        if(1 != i){
            throw new MyException("E","删除失败");
        }

        return Result.done();
    }

    @Override
    @MyLog(operationType="POST", operationName = "删除预约寄养信息")
    public JSONObject deleteFosterCareByPrimaryKey(JSONObject jo) {
        Assert.notNull(jo.getString("fosterId"),"fosterId不允许为空");

        int i = fosterCareMapper.deleteByPrimaryKey(jo.getString("fosterId"));
        if(1 != i){
            throw new MyException("E","删除失败");
        }
        return Result.done();
    }
}
