package com.example.timedtask2;

import com.example.timedtask2.entity.MyTimedTask;
import com.example.timedtask2.service.IMyTimedTaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Timedtask2ApplicationTests {

    @Autowired
    IMyTimedTaskService iMyTimedTaskService;

    @Test
    void contextLoads() {


    }

    @Test
    void insertTest(){
        //添加任务测试
        MyTimedTask myTimedTask = new MyTimedTask();
        myTimedTask.setId("4");
        myTimedTask.setTaskName("测试动态任务2");
        myTimedTask.setClassName("com.example.timedtask2.service.impl.MyTimedTaskServiceImpl");
        myTimedTask.setMethodName("test2");
        myTimedTask.setCron("0/10 * * * * ?");
        myTimedTask.setStatus("0");
        myTimedTask.setDataDescribe("");

        Boolean aBoolean = iMyTimedTaskService.addTimedTask(myTimedTask);
        System.out.println(aBoolean);
    }

    @Test
    void updateTest(){
        //修改测试
        MyTimedTask myTimedTask = new MyTimedTask();
        myTimedTask.setId("4");
        myTimedTask.setTaskName("测试动态任务3");
        myTimedTask.setClassName("com.example.timedtask2.service.impl.MyTimedTaskServiceImpl");
        myTimedTask.setMethodName("test2");
        myTimedTask.setCron("0/5 * * * * ?");
        myTimedTask.setStatus("0");
        myTimedTask.setDataDescribe("测试修改可以不");
        Boolean aBoolean = iMyTimedTaskService.editTimedTask(myTimedTask);
        System.out.println(aBoolean);
    }

    @Test
    void stateTest(){
        Boolean aBoolean = iMyTimedTaskService.manageTaskStatus("4");
        System.out.println(aBoolean);
    }

    @Test
    void deleteTest(){
        Boolean aBoolean = iMyTimedTaskService.deleteTimedTask("4");
        System.out.println(aBoolean);
    }


}
