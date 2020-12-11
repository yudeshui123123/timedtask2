package com.example.timedtask2.controller;


import com.example.timedtask2.entity.MyTimedTask;
import com.example.timedtask2.service.IMyTimedTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 定时任务表 前端控制器
 * </p>
 *
 * @author yds
 * @since 2020-12-10
 */
@RestController
@RequestMapping("/myTimedTask")
public class MyTimedTaskController{

    @Autowired
    IMyTimedTaskService iMyTimedTaskService;

    @PostMapping("/insert")
    public boolean insert(MyTimedTask myTimedTask){
        return iMyTimedTaskService.addTimedTask(myTimedTask);
    }

    @PostMapping("/update")
    public boolean update(MyTimedTask myTimedTask){
        return iMyTimedTaskService.editTimedTask(myTimedTask);
    }

    @PostMapping("/delete")
    public boolean delete(String id){
        return iMyTimedTaskService.deleteTimedTask(id);
    }

    @PostMapping("/status")
    public boolean status(String id){
        return iMyTimedTaskService.manageTaskStatus(id);
    }
}
