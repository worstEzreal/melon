package com.worstEzreal.melon.task.dao;

import com.worstEzreal.melon.task.entity.Task;
import org.apache.ibatis.annotations.Mapper;

/**
 * 任务dao
 *
 * @author worstEzreal
 * @version V1.0.0
 * @date 2019/8/8
 */
@Mapper
public interface TaskDao {

    Task getByTaskId(String taskId);

    Task getLastUnDoneTask();

    int update(Task task);

    void save(Task task);
}
