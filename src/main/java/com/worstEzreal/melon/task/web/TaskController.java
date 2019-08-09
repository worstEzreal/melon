package com.worstEzreal.melon.task.web;

import com.worstEzreal.melon.common.Result;
import com.worstEzreal.melon.task.TaskModel;
import com.worstEzreal.melon.task.entity.Task;
import com.worstEzreal.melon.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 任务Controller
 *
 * @author worstEzreal
 * @version V1.0.0
 * @date 2019/8/8
 */

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/get")
    public Result<Task> get(@RequestParam("taskId") String taskId) {
        Task task = taskService.getByTaskId(taskId);
        return Result.success(task);
    }

    @PostMapping("/put")
    public Result<String> put(@RequestBody @Valid TaskModel model) {
        return Result.success(taskService.newTask(model.getTaskType()));
    }


}
