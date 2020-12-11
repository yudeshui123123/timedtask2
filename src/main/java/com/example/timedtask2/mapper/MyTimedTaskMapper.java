package com.example.timedtask2.mapper;

import com.example.timedtask2.entity.MyTimedTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 定时任务表 Mapper 接口
 * </p>
 *
 * @author yds
 * @since 2020-12-10
 */
@Repository
public interface MyTimedTaskMapper extends BaseMapper<MyTimedTask> {

}
