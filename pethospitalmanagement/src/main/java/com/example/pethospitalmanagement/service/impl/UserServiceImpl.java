package com.example.pethospitalmanagement.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.pethospitalmanagement.constast.Constast;
import com.example.pethospitalmanagement.entity.MyLog;
import com.example.pethospitalmanagement.entity.User;
import com.example.pethospitalmanagement.mapper.UserMapper;
import com.example.pethospitalmanagement.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @MyLog(operationType="POST", operationName = "添加医生信息")
    public JSONObject insertUserForDoctor(JSONObject jo) {

        Assert.notNull(jo.getString("username"),"username不允许为空");
        Assert.notNull(jo.getString("phone"),"phone不允许为空");
        Assert.notNull(jo.getString("level"),"level不允许为空");
        Assert.notNull(jo.getString("workYears"),"workYears不允许为空");
        Assert.notNull(jo.getString("sex"),"sex不允许为空");

        User user = new User();
        user.setPhone(jo.getString("phone"));
        List<User> userList = userMapper.selectAllByPrimaryKey(user);
        if(!userList.isEmpty()){
            throw new MyException("E","改手机号已注册");
        }else {
            if(FormTools.isNull(jo.getString("password"))){
                jo.put("password", Constast.USER_PWD_DEFAULT);
            }
            if(FormTools.isNull(jo.getString("details"))){
                jo.put("details", Constast.NoINRODUCTION);
            }
            jo.put("type",Constast.USER_TYPE_DOCUOR);
            user.setUsername(jo.getString("username"));
            user.setPassword(jo.getString("password"));
            user.setType(jo.getIntValue("type"));
            user.setLevel(jo.getString("level"));
            user.setWorkyears(jo.getIntValue("workYears"));
            user.setSex(jo.getString("sex"));
            if(!FormTools.isNull(jo.getString("imgUrl"))){
                user.setImgurl(jo.getString("imgUrl"));
            }
            user.setDetails(jo.getString("details"));


            int i = userMapper.insertSelective(user);
            if(1 != i){
                throw new MyException("E","新增失败");
            }

        }
        return Result.done();
    }

    @Override
    @MyLog(operationType="POST", operationName = "添加用户信息")
    public JSONObject insertUserForCustomer(JSONObject jo) {
        Assert.notNull(jo.getString("username"),"username不允许为空");
        Assert.notNull(jo.getString("phone"),"phone不允许为空");

        User user = new User();
        user.setPhone(jo.getString("phone"));
        List<User> userList = userMapper.selectAllByPrimaryKey(user);
        if(!userList.isEmpty()){
            throw new MyException("E","改手机号已注册");
        }else {
            if(FormTools.isNull(jo.getString("password"))){
                jo.put("password", Constast.USER_PWD_DEFAULT);
            }
            jo.put("type",Constast.USER_TYPE_NORMAL);
            user.setUsername(jo.getString("username"));
            user.setPassword(jo.getString("password"));
            user.setType(jo.getIntValue("type"));

            int i = userMapper.insertSelective(user);
            if(1 != i){
                throw new MyException("E","新增失败");
            }
        }
        return Result.done();
    }

    @Override
    @MyLog(operationType="POST", operationName = "查询所有医生信息")
    public JSONObject selectAllDoctor() {
        User user = new User();
        user.setType(Constast.USER_TYPE_DOCUOR);

        List<User> userList = userMapper.selectAllByPrimaryKey(user);

        JSONArray ja_user = JsonUtils.toJA(userList);

        return Result.done().fluentPut("res",ja_user);
    }

    @Override
    @MyLog(operationType="POST", operationName = "查询所有用户信息")
    public JSONObject selectAllCustomer() {
        User user = new User();
        user.setType(Constast.USER_TYPE_NORMAL);

        List<User> userList = userMapper.selectAllByPrimaryKey(user);

        JSONArray ja_user = JsonUtils.toJA(userList);

        return Result.done().fluentPut("res",ja_user);
    }

    @Override
    @MyLog(operationType="POST", operationName = "查询我的钱包")
    public JSONObject selectMyMoney(JSONObject jo) {
        Assert.notNull(jo.getString("id"),"id不允许为空");

        User user = userMapper.selectByPrimaryKey(jo.getIntValue("id"));

        JSONObject res = JsonUtils.toJO(user);

        return Result.done().fluentPut("res",res);
    }

    @Override
    @MyLog(operationType="POST", operationName = "修改医生/用户信息")
    public JSONObject updateUserByPrimaryKey(JSONObject jo) {
        Assert.notNull(jo.getString("id"),"id不允许为空");

        Assert.notNull(jo.getString("username"),"username不允许为空");
        Assert.notNull(jo.getString("phone"),"phone不允许为空");
        Assert.notNull(jo.getString("level"),"level不允许为空");
        Assert.notNull(jo.getString("workYears"),"workYears不允许为空");
        Assert.notNull(jo.getString("sex"),"sex不允许为空");

        User user = new User();
        user.setId(jo.getIntValue("id"));
        List<User> userList = userMapper.selectAllByPrimaryKey(user);
        if(!userList.isEmpty()){
            if(!jo.getString("phone").equals(userList.get(0).getPhone())){
                user.setPhone(jo.getString("phone"));
                User user2 = new User();
                user2.setPhone(jo.getString("phone"));
                //通过手机号查询user表，判断是否重复
                List<User> userListByTel = userMapper.selectAllByPrimaryKey(user2);
                if(!userListByTel.isEmpty()){
                    throw new MyException("E","手机号已存在");
                }
            }
        }else{
            throw new MyException("E","改用户不存在");
        }

        if(!FormTools.isNull(jo.getString("username"))) {
            user.setUsername(jo.getString("username"));
        }
        if(!FormTools.isNull(jo.getString("password"))) {
            user.setPassword(jo.getString("password"));
        }
        if(!FormTools.isNull(jo.getString("level"))) {
            user.setLevel(jo.getString("level"));
        }
        if(!FormTools.isNull(jo.getString("workYears"))) {
            user.setWorkyears(jo.getIntValue("workYears"));
        }
        if(!FormTools.isNull(jo.getString("sex"))) {
            user.setSex(jo.getString("sex"));
        }
        if(!FormTools.isNull(jo.getString("imgUrl"))) {
            user.setImgurl(jo.getString("imgUrl"));
        }
        if(!FormTools.isNull(jo.getString("details"))) {
            user.setDetails(jo.getString("details"));
        }

        int i = userMapper.updateByPrimaryKeySelective(user);
        if(1 != i){
            throw new MyException("E","修改失败");
        }
        return Result.done();
    }

    @Override
    @MyLog(operationType="POST", operationName = "修改个人密码")
    public JSONObject updateMyPassword(JSONObject jo) {
        Assert.notNull(jo.getString("userId"),"userId不允许为空");
        Assert.notNull(jo.getString("oldPassword"),"oldPassword不允许为空");
        Assert.notNull(jo.getString("newPassword"),"newPassword不允许为空");

        User user = new User();
        user.setId(jo.getIntValue("userId"));
        user.setPassword(jo.getString("oldPassword"));
        List<User> userList = userMapper.selectAllByPrimaryKey(user);
        if(userList.isEmpty()){
            throw new MyException("E","原始密码输入不正确");
        }
        if(jo.getString("oldPassword").equals(jo.getString("newPassword"))){
            throw new MyException("E","新密码与原始密码相同，请重新输入");
        }
        user.setPassword(jo.getString("newPassword"));
        int i = userMapper.updateByPrimaryKeySelective(user);
        if(1 != i){
            throw new MyException("E","修改失败");
        }

        return Result.done();
    }

    @Override
    public JSONObject resetPassword(JSONObject jo) {
        Assert.notNull(jo.getString("id"),"id不允许为空");

        User user = new User();
        user.setId(jo.getIntValue("id"));
        user.setPassword(Constast.USER_PWD_DEFAULT);
        int i = userMapper.updateByPrimaryKeySelective(user);
        if(1 != i){
            throw new MyException("E","修改失败");
        }

        return Result.done().fluentPut("newPassword",Constast.USER_PWD_DEFAULT);
    }

    @Override
    @MyLog(operationType="POST", operationName = "修改我的钱包金额")
    public JSONObject updateMyMoney(JSONObject jo) {
        Assert.notNull(jo.getString("id"),"id不允许为空");
        Assert.notNull(jo.getString("enterAmount"),"enterAmount不允许为空");

        User user = userMapper.selectByPrimaryKey(jo.getIntValue("id"));
        BigDecimal money = user.getMoney();
        BigDecimal enterAmount = jo.getBigDecimal("enterAmount");
        user.setMoney(money.add(enterAmount));

        try{
            int i = userMapper.updateByPrimaryKeySelective(user);
            if(1 != i){
                throw new MyException("E","支付失败");
            }
        }catch (Exception e){
            throw new MyException("E","支付失败");
        }

        return Result.done().fluentPut("money",user.getMoney());
    }

    @Override
    @MyLog(operationType="POST", operationName = "删除用户/医生")
    public JSONObject deleteByPrimaryKey(JSONObject jo) {
        Assert.notNull(jo.getString("id"),"id不允许为空");

        int i = userMapper.deleteByPrimaryKey(jo.getIntValue("id"));
        if(1 != i){
            throw new MyException("E","删除失败");
        }
        return Result.done();
    }
}
