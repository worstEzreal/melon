package com.worstEzreal.melon.task.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 任务实体类
 *
 * @author worstEzreal
 * @date 2019/8/8
 */
@Data
public class Task implements Serializable {

    private Long id;
    private String taskId;
    private String taskType;
    private String taskStatus;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime updateTime;

}
