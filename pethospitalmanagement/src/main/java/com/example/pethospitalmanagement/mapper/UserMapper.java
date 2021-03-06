package com.example.pethospitalmanagement.mapper;

import com.example.pethospitalmanagement.entity.User;

import java.util.List;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated
     */
    User selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(User record);

    /**
     * 登录
     * @param record
     * @return
     */
    User login(User record);

    /**
     * 查询所有user
     * @param record
     * @return
     */
    List<User> selectAllByPrimaryKey(User record);

    /**
     * 通过手机号查询用户
     * @param phone
     * @return
     */
    User selectByPhone(String phone);
}