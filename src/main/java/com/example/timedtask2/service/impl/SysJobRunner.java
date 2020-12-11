package com.example.timedtask2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.timedtask2.common.CronTaskRegistrar;
import com.example.timedtask2.common.SchedulingRunnable;
import com.example.timedtask2.entity.MyTimedTask;
import com.example.timedtask2.service.IMyTimedTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author yds
 * @version 1.0
 * @date 2020/12/10 16:01
 * @description:
 */
@Service
@Slf4j
public class SysJobRunner implements CommandLineRunner {

    @Autowired
    private IMyTimedTaskService iMyTimedTaskService;

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @Override
    public void run(String... args) throws Exception {
        QueryWrapper<MyTimedTask> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status","0");
        List<MyTimedTask> list = iMyTimedTaskService.list(queryWrapper);
        for (MyTimedTask myTimedTask : list) {
            SchedulingRunnable task = new SchedulingRunnable(
                    myTimedTask.getClassName(),myTimedTask.getMethodName()
            );
            cronTaskRegistrar.addCronTask(task,myTimedTask.getCron());
        }
        log.info("****************************数据库中定时任务加载完毕；共{}条...",list.size());
    }
}
