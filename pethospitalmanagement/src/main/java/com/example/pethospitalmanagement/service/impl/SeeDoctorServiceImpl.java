package com.example.pethospitalmanagement.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.pethospitalmanagement.constast.Constast;
import com.example.pethospitalmanagement.entity.MyLog;
import com.example.pethospitalmanagement.entity.SeeDoctor;
import com.example.pethospitalmanagement.entity.User;
import com.example.pethospitalmanagement.mapper.SeeDoctorMapper;
import com.example.pethospitalmanagement.mapper.UserMapper;
import com.example.pethospitalmanagement.service.SeeDoctorService;
import com.example.pethospitalmanagement.util.*;
import com.example.pethospitalmanagement.vo.SeeDoctorVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class SeeDoctorServiceImpl implements SeeDoctorService {

    @Autowired
    private SeeDoctorMapper seeDoctorMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    @MyLog(operationType="POST", operationName = "添加预约就诊信息")
    public JSONObject insertSeeDoctor(JSONObject jo) {
        Assert.notNull(jo.get("doctorId"),"doctorId不允许为空");
        Assert.notNull(jo.get("userId"),"userId不允许为空");
        Assert.notNull(jo.getString("userName"),"userName不允许为空");
        Assert.notNull(jo.getString("petName"),"petName不允许为空");
        Assert.notNull(jo.getString("phone"),"phone不允许为空");
        Assert.notNull(jo.get("doctorTime"),"doctorTime不允许为空");
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


        SeeDoctor seeDoctor = new SeeDoctor();
        seeDoctor.setSeedoctorid(RandomUtils.createRandomStrUseTime("JZ"));
        seeDoctor.setDoctorid(jo.getIntValue("doctorId"));
        //通过doctorId查询name
        User doctor = userMapper.selectByPrimaryKey(jo.getIntValue("doctorId"));
        if(FormTools.isNull(doctor)){
            throw new MyException("E","该医生不存在");
        }
        seeDoctor.setDoctorname(doctor.getUsername());
        seeDoctor.setUserid(jo.getIntValue("userId"));
        seeDoctor.setUsername(jo.getString("userName"));
        seeDoctor.setPetname(jo.getString("petName"));
        seeDoctor.setPhone(jo.getString("phone"));
        seeDoctor.setDoctortime(jo.getDate("doctorTime"));
        seeDoctor.setCreatetime(new Date());
        seeDoctor.setDetails(jo.getString("details"));
        seeDoctor.setState(Constast.SEEDOCTOR_STATE_DEFAULT);

        int i = seeDoctorMapper.insertSelective(seeDoctor);
        if(1 != i){
            throw new MyException("E","新增失败");
        }

        return Result.done().fluentPut("money",accountBalance);
    }

    @Override
    @MyLog(operationType="POST", operationName = "用户查询预约就诊信息")
    public JSONObject selectAllSeeDoctorByUser(JSONObject jo) {
        Assert.notNull(jo.get("userId"),"userId不允许为空");

        SeeDoctorVo seeDoctorVo = new SeeDoctorVo();
        if(!jo.isEmpty()){
            seeDoctorVo.setUserid(jo.getIntValue("userId"));
            if(!FormTools.isNull(jo.getString("seeDoctorId"))){
                seeDoctorVo.setSeedoctorid(jo.getString("seeDoctorId"));
            }
            if(!FormTools.isNull(jo.getString("doctorName"))){
                seeDoctorVo.setDoctorname(jo.getString("doctorName"));
            }
            if(!FormTools.isNull(jo.getString("petName"))){
                seeDoctorVo.setPetname(jo.getString("petName"));
            }
            if(!FormTools.isNull(jo.getString("phone"))){
                seeDoctorVo.setPhone(jo.getString("phone"));
            }
            if(!FormTools.isNull(jo.getString("doctorTime_s"))){
                seeDoctorVo.setDoctorTime_s(jo.getDate("doctorTime_s"));
            }
            if(!FormTools.isNull(jo.getString("doctorTime_e"))){
                seeDoctorVo.setDoctorTime_e(jo.getDate("doctorTime_e"));
            }
            if(!FormTools.isNull(jo.getString("createTime_s"))){
                seeDoctorVo.setCreateTime_s(jo.getDate("createTime_s"));
            }
            if(!FormTools.isNull(jo.getString("createTime_e"))){
                seeDoctorVo.setCreateTime_e(jo.getDate("createTime_e"));
            }
            if(!FormTools.isNull(jo.getString("details"))){
                seeDoctorVo.setDetails(jo.getString("details"));
            }
            if(!FormTools.isNull(jo.getString("state"))){
                seeDoctorVo.setState(jo.getString("state"));
            }
        }

        List<SeeDoctor> SeeDoctorList = seeDoctorMapper.selectAllSeeDoctorByPrimaryKeySelective(seeDoctorVo);

        JSONArray ja_seeDoctor = JsonUtils.toJA(SeeDoctorList);

        return Result.done().fluentPut("res",ja_seeDoctor);
    }

    @Override
    @MyLog(operationType="POST", operationName = "查询预约就诊信息")
    public JSONObject selectAllSeeDoctorByPrimaryKey(JSONObject jo) {

        SeeDoctorVo seeDoctorVo = new SeeDoctorVo();
        if(!jo.isEmpty()){
            if(!FormTools.isNull(jo.getString("seeDoctorId"))){
                seeDoctorVo.setSeedoctorid(jo.getString("seeDoctorId"));
            }
            if(!FormTools.isNull(jo.getString("userName"))){
                seeDoctorVo.setUsername(jo.getString("userName"));
            }
            if(!FormTools.isNull(jo.getString("doctorId"))){
                seeDoctorVo.setDoctorid(jo.getIntValue("doctorId"));
            }
            if(!FormTools.isNull(jo.getString("doctorName"))){
                seeDoctorVo.setDoctorname(jo.getString("doctorName"));
            }
            if(!FormTools.isNull(jo.getString("petName"))){
                seeDoctorVo.setPetname(jo.getString("petName"));
            }
            if(!FormTools.isNull(jo.getString("phone"))){
                seeDoctorVo.setPhone(jo.getString("phone"));
            }
            if(!FormTools.isNull(jo.getString("doctorTime_s"))){
                seeDoctorVo.setDoctorTime_s(jo.getDate("doctorTime_s"));
            }
            if(!FormTools.isNull(jo.getString("doctorTime_e"))){
                seeDoctorVo.setDoctorTime_e(jo.getDate("doctorTime_e"));
            }
            if(!FormTools.isNull(jo.getString("createTime_s"))){
                seeDoctorVo.setCreateTime_s(jo.getDate("createTime_s"));
            }
            if(!FormTools.isNull(jo.getString("createTime_e"))){
                seeDoctorVo.setCreateTime_e(jo.getDate("createTime_e"));
            }
            if(!FormTools.isNull(jo.getString("details"))){
                seeDoctorVo.setDetails(jo.getString("details"));
            }
            if(!FormTools.isNull(jo.getString("state"))){
                seeDoctorVo.setState(jo.getString("state"));
            }
        }

        List<SeeDoctor> SeeDoctorList = seeDoctorMapper.selectAllSeeDoctorByPrimaryKeySelective(seeDoctorVo);

        JSONArray ja_seeDoctor = JsonUtils.toJA(SeeDoctorList);

        return Result.done().fluentPut("res",ja_seeDoctor);
    }

    @Override
    @MyLog(operationType="POST", operationName = "查询留言板")
    public JSONObject selectAllMessage(JSONObject jo) {

        Assert.notNull(jo.getString("pageNum"),"pageNum不允许为空");
        Assert.notNull(jo.getString("pageSize"),"pageSize不允许为空");

        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(jo.getIntValue("pageNum"));
        pageRequest.setPageSize(jo.getIntValue("pageSize"));
        PageInfo<SeeDoctorVo> pageInfo = getPageInfo(pageRequest);
        PageResult pageResult = PageUtils.getPageResult(pageRequest,pageInfo);

        return Result.done().fluentPut("res",pageResult);
    }

    @Override
    @MyLog(operationType="POST", operationName = "修改预约就诊信息")
    public JSONObject updateSeeDoctorByPrimaryKey(JSONObject jo) {
        Assert.notNull(jo.get("seeDoctorId"),"seeDoctorId不允许为空");

        SeeDoctor seeDoctor = new SeeDoctor();
        if(!jo.isEmpty()){
            seeDoctor.setSeedoctorid(jo.getString("seeDoctorId"));
            if(!FormTools.isNull(jo.getString("userId"))){
                seeDoctor.setUserid(jo.getIntValue("userId"));
            }
            if(!FormTools.isNull(jo.getString("userName"))){
                seeDoctor.setUsername(jo.getString("userName"));
            }
            if(!FormTools.isNull(jo.getString("doctorId"))){
                //通过doctorId查询name
                User user = userMapper.selectByPrimaryKey(jo.getIntValue("doctorId"));
                if(FormTools.isNull(user)){
                    throw new MyException("E","该医生不存在");
                }
                seeDoctor.setDoctorid(jo.getIntValue("doctorId"));
                seeDoctor.setDoctorname(user.getUsername());

            }
            if(!FormTools.isNull(jo.getString("doctorName"))){
                seeDoctor.setDoctorname(jo.getString("userName"));
            }
            if(!FormTools.isNull(jo.getString("petName"))){
                seeDoctor.setPetname(jo.getString("petName"));
            }
            if(!FormTools.isNull(jo.getString("phone"))){
                seeDoctor.setPhone(jo.getString("phone"));
            }
            if(!FormTools.isNull(jo.getString("doctorTime"))){
                seeDoctor.setDoctortime(jo.getDate("doctorTime"));
            }
            if(!FormTools.isNull(jo.getString("createTime"))){
                seeDoctor.setCreatetime(jo.getDate("createTime"));
            }
            if(!FormTools.isNull(jo.getString("details"))){
                seeDoctor.setDetails(jo.getString("details"));
            }
            if(!FormTools.isNull(jo.getString("state"))){
                seeDoctor.setState(jo.getString("state"));
            }
        }
        int i = seeDoctorMapper.updateByPrimaryKeySelective(seeDoctor);
        if(1 != i){
            throw new MyException("E","修改失败");
        }

        return Result.done();
    }

    @Override
    @MyLog(operationType="POST", operationName = "处理预约就诊信息为预约成功")
    public JSONObject updateSeeDoctorToArrange(JSONObject jo) {
        Assert.notNull(jo.get("seeDoctorId"),"seeDoctorId不允许为空");

        SeeDoctor seeDoctor = new SeeDoctor();
        seeDoctor.setSeedoctorid(jo.getString("seeDoctorId"));
        seeDoctor.setState(Constast.SEEDOCTOR_STATE_ARRANGE);
        int i = seeDoctorMapper.updateByPrimaryKeySelective(seeDoctor);
        if(1 != i){
            throw new MyException("E","修改失败");
        }

        return Result.done();
    }

    @Override
    @MyLog(operationType="POST", operationName = "处理预约就诊信息为已完成")
    public JSONObject updateSeeDoctorToTreatment(JSONObject jo) {
        Assert.notNull(jo.get("seeDoctorId"),"seeDoctorId不允许为空");

        SeeDoctor seeDoctor = new SeeDoctor();
        seeDoctor.setSeedoctorid(jo.getString("seeDoctorId"));
        seeDoctor.setState(Constast.SEEDOCTOR_STATE_TREATMENT);
        int i = seeDoctorMapper.updateByPrimaryKeySelective(seeDoctor);
        if(1 != i){
            throw new MyException("E","修改失败");
        }

        return Result.done();
    }

    @Override
    @MyLog(operationType="POST", operationName = "预约就诊信息评价")
    public JSONObject updateSeeDoctorEvaluation(JSONObject jo) {
        Assert.notNull(jo.get("seeDoctorId"),"seeDoctorId不允许为空");
        Assert.notNull(jo.get("evaluation"),"evaluation不允许为空");


        SeeDoctorVo seeDoctorVo = new SeeDoctorVo();
        seeDoctorVo.setSeedoctorid(jo.getString("seeDoctorId"));
        seeDoctorVo.setState(Constast.SEEDOCTOR_STATE_TREATMENT);
        List<SeeDoctor> seeDoctorList = seeDoctorMapper.selectAllSeeDoctorByPrimaryKeySelective(seeDoctorVo);
        if(seeDoctorList.isEmpty()){
            throw new MyException("E","该单据未完成，暂不能评价");
        }else{
            seeDoctorVo.setEvaluation(jo.getString("evaluation"));
            int i = seeDoctorMapper.updateSeeDoctorEvaluation(seeDoctorVo);
            if(1 != i){
                throw new MyException("E","评价失败");
            }
        }
        return Result.done();
    }

    @Override
    @MyLog(operationType="POST", operationName = "处理预约就诊信息为拒绝")
    public JSONObject updateRefuseTreatment(JSONObject jo) {
        Assert.notNull(jo.get("seeDoctorId"),"seeDoctorId不允许为空");
        Assert.notNull(jo.get("evaluation"),"evaluation不允许为空");

        SeeDoctor seeDoctor = seeDoctorMapper.selectByPrimaryKey(jo.getString("seeDoctorId"));

        //计算原单据金额
        BigDecimal money = BigDecimal.valueOf(Constast.SEE_DOCTOR_MONEY);;
        //原单用户
        User user = userMapper.selectByPrimaryKey(seeDoctor.getUserid());
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

        SeeDoctorVo seeDoctorVo = new SeeDoctorVo();
        seeDoctorVo.setSeedoctorid(jo.getString("seeDoctorId"));
        seeDoctorVo.setEvaluation(jo.getString("evaluation"));
        seeDoctorVo.setState(Constast.SEEDOCTOR_STATE_REFUSE);
        int i = seeDoctorMapper.updateSeeDoctorEvaluation(seeDoctorVo);
        if(1 != i){
            throw new MyException("E","拒绝失败");
        }

        return Result.done();
    }

    @Override
    @MyLog(operationType="POST", operationName = "删除处理预约就诊信息")
    public JSONObject deleteSeeDoctorByPrimaryKey(JSONObject jo) {
        Assert.notNull(jo.get("seeDoctorId"),"seeDoctorId不允许为空");

        int i = seeDoctorMapper.deleteByPrimaryKey(jo.getString("seeDoctorId"));
        if(1 != i){
            throw new MyException("E","删除失败");
        }
        return Result.done();
    }
    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<SeeDoctorVo> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<SeeDoctorVo> seeDoctorList = seeDoctorMapper.selectAllMessage();
        return new PageInfo<SeeDoctorVo>(seeDoctorList);
    }
}
