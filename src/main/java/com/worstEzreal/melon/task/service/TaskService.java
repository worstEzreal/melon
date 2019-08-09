package com.worstEzreal.melon.task.service;

import com.worstEzreal.melon.task.dao.TaskDao;
import com.worstEzreal.melon.task.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 任务Service
 *
 * @author worstEzreal
 * @date 2019/8/8
 */
@Service
public class TaskService {

    @Autowired
    private TaskDao dao;

    private static final Object lock = new Object();

    public Task getLastUnDoneTask() {
        synchronized (lock) {
            Task task = dao.getLastUnDoneTask();
            if (task != null) {
                task.setTaskStatus("I");
                task.setUpdateTime(LocalDateTime.now());
                dao.update(task);
                return task;
            }
            return null;
        }
    }

    public Task getByTaskId(String taskId) {
        return dao.getByTaskId(taskId);
    }

    public String newTask(String taskType) {
        String taskId = UUID.randomUUID().toString().replaceAll("-", "");
        LocalDateTime now = LocalDateTime.now();
        Task task = new Task();
        task.setTaskId(UUID.randomUUID().toString().replaceAll("-", ""));
        task.setTaskStatus("W");
        task.setTaskType(taskType);
        task.setCreateTime(now);
        task.setUpdateTime(now);
        dao.save(task);
        return taskId;
    }


    public int update(Task task) {
        return dao.update(task);
    }

}
