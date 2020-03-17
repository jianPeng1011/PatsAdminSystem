package com.example.pethospitalmanagement.constast;


public interface Constast {
    /*
     * 用户类型
     */
    public static final Integer USER_TYPE_DOCUOR = 1;//医生

    public static final Integer USER_TYPE_NORMAL = 2;//普通

    public static final String USER_TYPE_ADMIN = "ADMIN";//管理员

    /*
     * 可用类型
     */
    public static final Integer TYPE_AVAILABLE_YES = 1;
    public static final Integer TYPE_AVAILABLE_NO = 0;
    /*
     * 公用类型
     */
    public static final Integer TYPE_PUBLIC_ZERO = 0;
    public static final Integer TYPE_PUBLIC_ONE = 1;
    public static final Integer TYPE_PUBLIC_TWO = 2;
    public static final Integer TYPE_PUBLIC_THREE = 3;
    public static final Integer TYPE_PUBLIC_FOUR = 4;
    /*
     * 默认密码
     */
    public static final String USER_PWD_DEFAULT = "111111";

    /*
     * 默认评价
     */
    public static final String NoINRODUCTION = "暂无简介";

    /**
     * 预约单状态
     */
    public static final String SUBSCRIBE_STATE_DEFAULT = "待审核";
    public static final String SUBSCRIBE_STATE_REGISTER = "预约成功";
    public static final String SUBSCRIBE_STATE_ARRANGE = "已完成";
    public static final String SUBSCRIBE_STATE_REFUSE = "拒绝";

    /**
     * 就诊单状态
     */
    public static final String SEEDOCTOR_STATE_DEFAULT = "待审核";
    public static final String SEEDOCTOR_STATE_ARRANGE = "预约成功";
    public static final String SEEDOCTOR_STATE_TREATMENT = "已完成";
    public static final String SEEDOCTOR_STATE_REFUSE = "拒绝";

    /**
     * 医生单次预约价格
     */
    public static final Double SEE_DOCTOR_MONEY = 300D;

    /**
     * 预约寄养单日价格
     */
    public static final Double SUBSCRIBE_DAY_MONEY = 300D;

    /**
     * LOG日志类型
     */
    public static final String LOG_STATE_DEFAULT = "INFO";
    public static final String LOG_STATE_ERROR = "ERROR";
}
