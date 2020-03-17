package com.example.pethospitalmanagement.service;

import com.alibaba.fastjson.JSONObject;
import com.example.pethospitalmanagement.util.MyException;
import org.springframework.web.multipart.MultipartFile;

public interface FileUpAndDownService {
    JSONObject uploadPicture(MultipartFile file) throws MyException;
}
