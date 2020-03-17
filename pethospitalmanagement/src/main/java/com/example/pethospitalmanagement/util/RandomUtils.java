package com.example.pethospitalmanagement.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 随机数据及字符串生成工具类
 *
 */
public class RandomUtils {

    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd");
    private static Random random = new Random();

    /*
     * 使用时间+四位随机数
     * 生成文件名称
     */
    public static String createFileNewNameByTime(String oldName){
        //生成时间字符串
        String time = sdf1.format(new Date());
        //生成四位随机数
        Integer num = (random.nextInt(9000)+1000);
        //获取文件名后缀
        String suffix = oldName.substring(oldName.lastIndexOf("."),oldName.length());

        return time +num.toString()+suffix;
    }

    /*
     * 根据当前时间获取文件夹名
     */
    public static String getDirNameUseTime(){
        return sdf2.format(new Date());
    }

    /*
     * 使用UUID生成文件名
     */
    public static String createFileNewNameUseUUID(String oldName){
        //使用uuid生成文件名称
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //获取文件后缀名
        String suffix = oldName.substring(oldName.lastIndexOf("."), oldName.length());

        return uuid + suffix;
    }

    /*
     * 生成随机单号
     */
    public static String createRandomStrUseTime(String prefix){
        String time = sdf3.format(new Date());

        Integer num = random.nextInt(9000)+1000;

        return prefix+time+num.toString();
    }
}
