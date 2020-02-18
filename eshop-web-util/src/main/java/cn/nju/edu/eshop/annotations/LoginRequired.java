package cn.nju.edu.eshop.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//定义注解生效范围是“方法”
@Retention(RetentionPolicy.RUNTIME)//定义注解在虚拟机运行时也生效
public @interface LoginRequired {
    boolean loginSuccess() default true;
}
