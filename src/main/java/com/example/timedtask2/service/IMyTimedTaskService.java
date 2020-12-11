package com.example.timedtask2.service;

import com.example.timedtask2.entity.MyTimedTask;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 定时任务表 服务类
 * </p>
 *
 * @author yds
 * @since 2020-12-10
 */
public interface IMyTimedTaskService extends IService<MyTimedTask> {

    /**
     * 新增定时任务
     */
    Boolean addTimedTask(MyTimedTask myTimedTask);

    /**
     * 修改定时任务
     */
    Boolean editTimedTask(MyTimedTask myTimedTask);

    /**
     * 删除定时任务
     */
    Boolean deleteTimedTask(String id);

    /**
     * 启动或停止定时任务
     */
    Boolean manageTaskStatus(String id);
}
