package com.example.pethospitalmanagement.mapper;

import com.example.pethospitalmanagement.entity.SeeDoctor;
import com.example.pethospitalmanagement.vo.SeeDoctorVo;

import java.util.List;

public interface SeeDoctorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_seedoctor
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String seedoctorid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_seedoctor
     *
     * @mbggenerated
     */
    int insert(SeeDoctor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_seedoctor
     *
     * @mbggenerated
     */
    int insertSelective(SeeDoctor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_seedoctor
     *
     * @mbggenerated
     */
    SeeDoctor selectByPrimaryKey(String seedoctorid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_seedoctor
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SeeDoctor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_seedoctor
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SeeDoctor record);

    int updateSeeDoctorEvaluation(SeeDoctor record);

    List<SeeDoctorVo> selectAllMessage();

    List<SeeDoctor> selectAllSeeDoctorByPrimaryKeySelective(SeeDoctorVo seeDoctorVo);
}