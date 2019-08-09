package com.worstEzreal.melon.task.exec;

import com.worstEzreal.melon.task.entity.Task;
import com.worstEzreal.melon.task.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 注册任务执行线程
 *
 * @author worstEzreal
 * @version V1.0.0
 * @date 2019/8/8
 */
@Slf4j
@Component
public class MelonTaskExecutor implements InitializingBean, DisposableBean, SmartLifecycle {

    @Value("${task.thread.num}")
    private int threadNum;

    private ExecutorService fixedThreadPool;

    private volatile boolean isRunning = false;

    @Autowired
    private TaskService taskService;

    @Override
    public void start() {
        isRunning = true;
        log.info(MelonTaskExecutor.class.getSimpleName() + "启动");

        for (int i = 0; i < threadNum; i++) {
            fixedThreadPool.execute(
                    () -> {
                        try {
                            while (true) {
                                Task task = taskService.getLastUnDoneTask();
                                if (task != null) {
                                    log.info(Thread.currentThread().getName() + ": 任务" + task.getTaskId() + "处理开始");
                                    //do something

                                    task.setTaskStatus("D");
                                    task.setUpdateTime(LocalDateTime.now());
                                    taskService.update(task);
                                    log.info(Thread.currentThread().getName() + ": 任务" + task.getTaskId() + "处理结束");
                                } else {
                                    try {
                                        TimeUnit.SECONDS.sleep(1L);
                                    } catch (InterruptedException e) {
                                        log.error(MelonTaskExecutor.class.getSimpleName() + Thread.currentThread().getName()
                                                + ": 任务休眠打断", e);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            log.error("Exception", e);
                        }
                    }
            );
        }
    }

    @Override
    public void stop() {
        fixedThreadPool.shutdown();
        isRunning = false;
        log.info(MelonTaskExecutor.class.getSimpleName() + "停止");
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.fixedThreadPool =
                Executors.newFixedThreadPool(threadNum, Executors.defaultThreadFactory());
        log.info(MelonTaskExecutor.class.getSimpleName() + "初始化完成");
    }

    @Override
    public void destroy() throws Exception {
        fixedThreadPool.shutdown();
        log.info(MelonTaskExecutor.class.getSimpleName() + "关闭");
    }

}
