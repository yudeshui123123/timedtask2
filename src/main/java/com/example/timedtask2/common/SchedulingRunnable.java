package com.example.timedtask2.common;

import com.example.timedtask2.utils.SpringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * TODO
 *
 * @author yds
 * @version 1.0
 * @date 2020/12/10 14:49
 * @description:
 */
@Slf4j
@Data
public class SchedulingRunnable implements Runnable{

    private String beanName;

    private String methodName;

    private String params;

    public SchedulingRunnable(String beanName, String methodName) {
        this(beanName, methodName, null);
    }

    public SchedulingRunnable(String beanName, String methodName, String params) {
        this.beanName = beanName;
        this.methodName = methodName;
        this.params = params;
    }

    @Override
    public void run() {
        log.info("定时任务开始执行*********************bean{}，方法:{}",beanName,methodName);
        long startTime = System.currentTimeMillis();
        try {
            Class clazz = this.getClass().getClassLoader().loadClass(beanName);
            Object bean = SpringUtil.getBean(clazz);
            Method method = bean.getClass().getDeclaredMethod(methodName);

            method.invoke(bean);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("定时任务执行异常*************************bean{},方法{}",beanName,methodName);
        }
        long times = System.currentTimeMillis() - startTime;
        log.info("定时任务结束***********************bean{},方法{},耗时{}",beanName,methodName,times);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchedulingRunnable that = (SchedulingRunnable) o;
        if (params == null) {
            return beanName.equals(that.beanName) &&
                    methodName.equals(that.methodName) &&
                    that.params == null;
        }

        return beanName.equals(that.beanName) &&
                methodName.equals(that.methodName) &&
                params.equals(that.params);
    }


    @Override
    public int hashCode() {

        if (params == null) {
            return Objects.hash(beanName, methodName);
        }

        return Objects.hash(beanName, methodName, params);
    }
}
