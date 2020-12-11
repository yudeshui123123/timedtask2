package com.example.timedtask2.service.impl;

import com.example.timedtask2.common.CronTaskRegistrar;
import com.example.timedtask2.common.SchedulingRunnable;
import com.example.timedtask2.entity.MyTimedTask;
import com.example.timedtask2.mapper.MyTimedTaskMapper;
import com.example.timedtask2.service.IMyTimedTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 定时任务表 服务实现类
 * </p>
 *
 * @author yds
 * @since 2020-12-10
 */
@Service
@Transactional
public class MyTimedTaskServiceImpl extends ServiceImpl<MyTimedTaskMapper, MyTimedTask> implements IMyTimedTaskService {

    @Autowired
    private MyTimedTaskMapper myTimedTaskMapper;

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    public void test(){
        System.out.println("执行测试任务");
    }

    public void test2(){
        System.out.println("执行添加的测试任务");
    }

    @Override
    public Boolean addTimedTask(MyTimedTask myTimedTask) {
        int insert = myTimedTaskMapper.insert(myTimedTask);
        if(insert <= 0){
            return false;
        }
        if(myTimedTask.getStatus().equals("0")){
            cronTaskRegistrar.addCronTask(getSchedulingRunnableNoParams(myTimedTask),myTimedTask.getCron());
        }
        return true;
    }

    @Override
    public Boolean editTimedTask(MyTimedTask myTimedTask) {
        int i = myTimedTaskMapper.updateById(myTimedTask);
        MyTimedTask myTimedTask2 = myTimedTaskMapper.selectById(myTimedTask.getId());
        if(i <= 0){
            return false;
        }
        cronTaskRegistrar.removeCronTask(getSchedulingRunnableNoParams(myTimedTask2));
        if(myTimedTask.getStatus().equals("1")){
            return true;
        }
        cronTaskRegistrar.addCronTask(getSchedulingRunnableNoParams(myTimedTask2),myTimedTask.getCron());
        return true;
    }

    @Override
    public Boolean deleteTimedTask(String id) {
        MyTimedTask myTimedTask = myTimedTaskMapper.selectById(id);
        int i = myTimedTaskMapper.deleteById(id);
        if(i <= 0){
            return false;
        }
        cronTaskRegistrar.removeCronTask(getSchedulingRunnableNoParams(myTimedTask));
        return true;
    }

    @Override
    public Boolean manageTaskStatus(String id) {
        MyTimedTask myTimedTask = myTimedTaskMapper.selectById(id);
        MyTimedTask myTimedTask1 = new MyTimedTask();
        myTimedTask1.setId(id);
        //如果为0就是关闭任务
        if(myTimedTask.getStatus().equals("0")){
            myTimedTask1.setStatus("1");
            myTimedTaskMapper.updateById(myTimedTask1);
            cronTaskRegistrar.removeCronTask(getSchedulingRunnableNoParams(myTimedTask));
        }
        //如果为1就是开启任务
        if(myTimedTask.getStatus().equals("1")){
            myTimedTask1.setStatus("0");
            myTimedTaskMapper.updateById(myTimedTask1);
            cronTaskRegistrar.addCronTask(getSchedulingRunnableNoParams(myTimedTask),myTimedTask.getCron());
        }
        return true;
    }

    private SchedulingRunnable getSchedulingRunnableNoParams(MyTimedTask myTimedTask){
        return new SchedulingRunnable(myTimedTask.getClassName(),myTimedTask.getMethodName());
    }
}
