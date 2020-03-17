package com.example.pethospitalmanagement.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.pethospitalmanagement.entity.MyLog;
import com.example.pethospitalmanagement.entity.UserLoginToken;
import com.example.pethospitalmanagement.service.FileUpAndDownService;
import com.example.pethospitalmanagement.util.JsonUtils;
import com.example.pethospitalmanagement.util.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@UserLoginToken
@RestController
@RequestMapping("/ptm/upload")
public class FileUploadController {
    @Autowired
    private FileUpAndDownService fileUpAndDownService;

    @MyLog(operationType="POST", operationName = "图片上传")
    @RequestMapping("/setFileUpload")
    public JSONObject setFileUpload(@RequestParam(value = "file", required = false)MultipartFile file){
        JSONObject result = JsonUtils.JO();
        try{
            JSONObject resultJo = upload(file);
            if(!"S".equals(resultJo.getString("res"))){
                result.put("ID","E");
                result.put("msg",resultJo.getString("msg"));
                return result;
            }
            result.putAll(resultJo);
        }catch (Exception e) {
            throw new MyException("E","上传图片为空文件");
        }
        return result;
    }

    private JSONObject upload(MultipartFile file) throws MyException{
        JSONObject returnJo = JsonUtils.JO();
        try{
            if(!file.isEmpty()){
                JSONObject picJo = fileUpAndDownService.uploadPicture(file);
                if("S".equals(picJo.getString("res"))){
                    return picJo;
                }else{
                    returnJo.put("res","E");
                    returnJo.put("msg",picJo.get("res"));
                }
            }
        }catch(Exception e){
            throw new MyException("E","图片上传失败");
        }
        return returnJo;
    }
}
