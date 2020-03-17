package com.example.pethospitalmanagement.aspect;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.example.pethospitalmanagement.constast.Constast;
import com.example.pethospitalmanagement.entity.MyLog;
import com.example.pethospitalmanagement.entity.User;
import com.example.pethospitalmanagement.mapper.UserMapper;
import com.example.pethospitalmanagement.service.LogService;
import com.example.pethospitalmanagement.util.FormTools;
import com.example.pethospitalmanagement.util.JsonUtils;
import com.example.pethospitalmanagement.util.MyException;
import com.example.pethospitalmanagement.util.RandomUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspect {

    @Resource
    private LogService logService;

    @Autowired
    private UserMapper userMapper;

    /**
     * Controller层切点
     */
    @Pointcut("execution (* com.example.pethospitalmanagement.controller..*.*(..))")
    public void controllerAspect() {

    }

    @Pointcut("execution (* com.example.pethospitalmanagement.service..*.*(..))")
    public void serviceAspect() {

    }

    /**
     * 后置返回通知
     * @param joinPoint
     */
    @AfterReturning("controllerAspect()")
    public void afterReturn(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        User user;
        if (FormTools.isNull(token)) {
            user = new User();
            user.setUsername("未知用户");
        }else{
            String phone = JWT.decode(token).getAudience().get(0);
            user = userMapper.selectByPhone(phone);
        }
        String ip = request.getRemoteAddr();
        JSONObject log = JsonUtils.JO();
        try{
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            String operationType = "";
            String operationName = "";
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length){
                        operationType = method.getAnnotation(MyLog.class).operationType();
                        operationName = method.getAnnotation(MyLog.class).operationName();
                        break;
                    }
                }
            }
            //========控制台输出=========//
            System.out.println("==========controller后置通知开始==========");
            System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")+"."+operationType);
            System.out.println("方法描述:" + operationName);
            System.out.println("请求人:" + user.getUsername());
            System.out.println("请求IP:" + ip);
            //========数据库日志=========//
            log.put("logId",RandomUtils.createRandomStrUseTime("LOG"));
            log.put("description",operationName);
            log.put("method",(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")+"."+operationType);
            log.put("logType",Constast.LOG_STATE_DEFAULT);
            log.put("requestIp",ip);
            log.put("createBy",user.getUsername());
            log.put("createDate",new Date());
            logService.insertLog(log);
        }catch (Exception e){
            System.out.println("异常通知异常:"+e.toString());
        }
    }
    @AfterThrowing(pointcut = "serviceAspect()", throwing="e")
    public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");// 从 http 请求头中取出 token
        User user;
        if (FormTools.isNull(token)) {
            user = new User();
            user.setUsername("未知用户");
        }else{
            String phone = JWT.decode(token).getAudience().get(0);
            user = userMapper.selectByPhone(phone);
        }
        String ip = request.getRemoteAddr();
        JSONObject log = JsonUtils.JO();
        try{
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            String operationType = "";
            String operationName = "";
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length){
                        operationType = method.getAnnotation(MyLog.class).operationType();
                        operationName = method.getAnnotation(MyLog.class).operationName();
                        break;
                    }
                }
            }
            //========控制台输出=========//
            System.out.println("==========controller异常通知开始==========");
            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")+"."+operationType);
            System.out.println("异常描述:" + operationName);
            System.out.println("请求人:" + user.getUsername());
            System.out.println("请求IP:" + ip);
            //========数据库日志=========//
            log.put("logId",RandomUtils.createRandomStrUseTime("LOG"));
            log.put("description",operationName);
            log.put("method",(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")+"."+operationType);
            log.put("logType",Constast.LOG_STATE_ERROR);
            log.put("exceptionCode",e.toString());
            log.put("exceptionDetail",e.getMessage());
            log.put("requestIp",ip);
            log.put("createBy",user.getUsername());
            log.put("createDate",new Date());
            logService.insertLog(log);
        }catch (Exception ex){
            System.out.println("异常通知异常:"+ex.getMessage());
        }
    }
}
