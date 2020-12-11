package com.example.timedtask2.common;

import java.util.concurrent.ScheduledFuture;

/**
 * TODO
 *
 * @author yds
 * @version 1.0
 * @date 2020/12/10 14:46
 * @description:
 */
public class ScheduledTask {

    volatile ScheduledFuture<?> future;

    /**
     * 取消定时任务
     */
    public void cancel(){
        ScheduledFuture<?> future = this.future;
        if(future != null){
            future.cancel(true);
        }
    }
}
