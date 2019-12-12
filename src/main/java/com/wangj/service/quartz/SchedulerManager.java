package com.wangj.service.quartz;

public interface SchedulerManager {

    boolean addJob(String jobName, String jobGroup, String triggerName, String triggerGroup, Class jobClass, boolean durability, int interval, int delay, String script, String content, String env);

    boolean addJob(String jobName, String jobGroup, String triggerName, String triggerGroup, Class jobClass, boolean durability, String cronExpress, int delay, String script, String content, String env);

    boolean start(String jobName, String jobGroup);

    boolean pause(String jobName, String jobGroup);
}
