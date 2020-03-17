package com.example.pethospitalmanagement.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.pethospitalmanagement.entity.MessageProperties;
import com.example.pethospitalmanagement.service.FileUpAndDownService;
import com.example.pethospitalmanagement.util.JsonUtils;
import com.example.pethospitalmanagement.util.MyException;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileUpAndDownServiceImpl implements FileUpAndDownService {
    @Autowired
    private MessageProperties messageProperties;

    @Override
    public JSONObject uploadPicture(MultipartFile file) throws MyException{
        JSONObject res = JsonUtils.JO();
        try{
            String[] IMAGE_YPPE = messageProperties.getImageType().split(",");
            String path = null;
            boolean flag = false;
            for(String type : IMAGE_YPPE){
                if(StringUtils.endsWithIgnoreCase(file.getOriginalFilename(),type)){
                    flag = true;
                    break;
                }
            }
            if(flag){
                res.put("res","S");
                String uuid = UUID.randomUUID().toString().replaceAll("-","");
                //获得文件类型
                String fileType = file.getContentType();
                //获取文件后缀名称
                String imageName = fileType.substring(fileType.indexOf("/") + 1);
                // 原名称
                String oldFileName = file.getOriginalFilename();
                // 新名称
                String newFileName = uuid + "." + imageName;
                // 年月日文件夹
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String basedir = sdf.format(new Date());
                //进行压缩（大于4M）
                if(file.getSize() > messageProperties.getFileSize()){
                    //重新生成
                    String newUUID = UUID.randomUUID().toString().replaceAll("-","");
                    newFileName = newUUID + imageName;
                    path =  messageProperties.getUpPath() + "/" + basedir + "/" + newUUID + "." + imageName;
                    //如果目录不存在则创建目录
                    File oldFile = new File(path);
                    if(!oldFile.exists()){
                        oldFile.mkdirs();
                    }
                    file.transferTo(oldFile);
                    // 压缩图片
                    Thumbnails.of(oldFile).scale(messageProperties.getScaleRatio()).toFile(path);
                    //显示路径
                    res.put("path","/" + basedir + "/" + newUUID + "." +imageName);

                }else{
                    path = messageProperties.getUpPath() + "/" +basedir + "/" + uuid + "." + imageName;
                    //如果目录不存在则创建目录
                    File uploadFile = new File(path);
                    if(!uploadFile.exists()){
                        uploadFile.mkdirs();
                    }
                    file.transferTo(uploadFile);
                    //显示路径
                    res.put("path","/" + basedir + "/" + uuid + "." +imageName);
                }
            }else{
                res.put("res","图片格式不正确，支持png|jpg|jpeg");
            }
            return res;
        }catch (Exception e){
            throw new MyException("E",e.getMessage());
        }
    }
}
