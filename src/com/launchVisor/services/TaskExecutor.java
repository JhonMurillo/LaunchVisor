package com.launchVisor.services;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.launchVisor.domain.Task;
import com.launchVisor.domain.TaskStatus;
import java.util.logging.Logger;

@Component
@Scope("singleton")
public class TaskExecutor {

    private List<Task> pool = new LinkedList<>();
    private static final Logger LOG = Logger.getLogger(TaskExecutor.class.getName());

    @PostConstruct
    public void initialize() {
        Runnable taskPoolConsumer;
        taskPoolConsumer = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TaskExecutor.this.pool.stream().filter(task -> task.isRunning() && task.getDuration() > 0).forEach(task -> {
                            task.decrementDuration();
                        });
                        TaskExecutor.this.pool.stream().filter(task -> task.isRunning() && task.getDuration() == 0).forEach(task -> task.setStatus(TaskStatus.SUCCESS));
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        LOG.warning(e.getMessage());
                    }
                }
            }
        };

        new Thread(taskPoolConsumer).start();
    }

    public void startAllTasks() throws InterruptedException {
        this.pool.stream().forEach(task -> task.start());
    }

    public List<Task> getPool() {
        return this.pool;
    }

    public void addTask(Task taskToAdd) {
        this.pool.add(taskToAdd);
    }

}
