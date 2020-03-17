package com.example.pethospitalmanagement.entity;

import java.lang.annotation.*;

/**
 * 元注解：负责注解其它注释
 * @Target :Annotation所修饰的对象范围  作用：用于描述注解的使用范围
 * @Retention :定义了该Annotation被保留的时间长短  作用：表示需要在什么级别保存该注释信息，用于描述注解的生命周期（即：被描述的注解在什么范围内有效）
 * @Documented :用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化。Documented是一个标记注解，没有成员。
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {
    public String operationType() default "";
    public String operationName() default "";
}
