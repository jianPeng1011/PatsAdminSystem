package com.example.pethospitalmanagement.service;

import com.alibaba.fastjson.JSONObject;

public interface SeeDoctorService {

    JSONObject insertSeeDoctor(JSONObject jo);

    JSONObject selectAllSeeDoctorByUser(JSONObject jo);

    JSONObject selectAllSeeDoctorByPrimaryKey(JSONObject jo);

    JSONObject selectAllMessage(JSONObject jo);

    JSONObject updateSeeDoctorByPrimaryKey(JSONObject jo);

    JSONObject updateSeeDoctorToArrange(JSONObject jo);

    JSONObject updateSeeDoctorToTreatment(JSONObject jo);

    JSONObject updateSeeDoctorEvaluation(JSONObject jo);

    JSONObject updateRefuseTreatment(JSONObject jo);

    JSONObject deleteSeeDoctorByPrimaryKey(JSONObject jo);
}
