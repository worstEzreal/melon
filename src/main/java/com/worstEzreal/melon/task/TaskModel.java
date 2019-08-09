package com.worstEzreal.melon.task;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 任务Model
 *
 * @author zengxzh@yonyou.com
 * @date 2019/8/9
 */
@Setter
@Getter
public class TaskModel {

    @NotBlank(message = "任务类型不能为空")
    private String taskType;

}
