DROP TABLE
IF EXISTS melon_task;

CREATE TABLE `melon_task` (
  `id` BIGINT (20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `task_id` VARCHAR (64) DEFAULT NULL COMMENT '任务编号',
  `task_type` VARCHAR (32) DEFAULT NULL COMMENT '任务类型',
  `task_status` VARCHAR (10) DEFAULT NULL COMMENT '任务状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4;