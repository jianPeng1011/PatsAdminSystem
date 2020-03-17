package com.example.pethospitalmanagement.service;

import com.alibaba.fastjson.JSONObject;

public interface PetService {
    JSONObject insertPet(JSONObject jo);

    JSONObject selectAllPetByPrimaryKey(JSONObject jo);

    JSONObject updatePetByPrimaryKey(JSONObject jo);

    JSONObject deletePetByPrimaryKey(JSONObject jo);
}
