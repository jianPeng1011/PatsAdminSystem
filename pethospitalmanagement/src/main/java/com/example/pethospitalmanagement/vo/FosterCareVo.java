package com.example.pethospitalmanagement.vo;

import com.example.pethospitalmanagement.entity.FosterCare;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FosterCareVo extends FosterCare {

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startTime_s;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startTime_e;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime_s;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endTime_e;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime_s;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createTime_e;

    public Date getStartTime_s() {
        return startTime_s;
    }

    public void setStartTime_s(Date startTime_s) {
        this.startTime_s = startTime_s;
    }

    public Date getStartTime_e() {
        return startTime_e;
    }

    public void setStartTime_e(Date startTime_e) {
        this.startTime_e = startTime_e;
    }

    public Date getEndTime_s() {
        return endTime_s;
    }

    public void setEndTime_s(Date endTime_s) {
        this.endTime_s = endTime_s;
    }

    public Date getEndTime_e() {
        return endTime_e;
    }

    public void setEndTime_e(Date endTime_e) {
        this.endTime_e = endTime_e;
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
