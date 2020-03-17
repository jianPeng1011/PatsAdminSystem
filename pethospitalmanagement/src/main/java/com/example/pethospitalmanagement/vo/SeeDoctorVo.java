package com.example.pethospitalmanagement.vo;

import com.example.pethospitalmanagement.entity.SeeDoctor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SeeDoctorVo extends SeeDoctor {

    private String id;

    private String serveType;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date doctorTime_s;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date doctorTime_e;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime_s;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime_e;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServeType() {
        return serveType;
    }

    public void setServeType(String serveType) {
        this.serveType = serveType;
    }

    public Date getDoctorTime_s() {
        return doctorTime_s;
    }

    public void setDoctorTime_s(Date doctorTime_s) {
        this.doctorTime_s = doctorTime_s;
    }

    public Date getDoctorTime_e() {
        return doctorTime_e;
    }

    public void setDoctorTime_e(Date doctorTime_e) {
        this.doctorTime_e = doctorTime_e;
    }

    public Date getCreateTime_s() {
        return createTime_s;
    }

    public void setCreateTime_s(Date createTime_s) {
        this.createTime_s = createTime_s;
    }

    public Date getCreateTime_e() {
        return createTime_e;
    }

    public void setCreateTime_e(Date createTime_e) {
        this.createTime_e = createTime_e;
    }
}
