package com.wangj.service.impl.quartz;

import com.wangj.service.GlobalConfig;
import com.wangj.service.quartz.SchedulerManager;
import com.wangj.util.FileUtil;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;

@Service
public class SchedulerManagerImpl implements SchedulerManager {

//    private static Scheduler scheduler;
    private final static Logger logger = LoggerFactory.getLogger(SchedulerManagerImpl.class);

    @Autowired
    private Scheduler scheduler;

//    static {
//        try {
//            scheduler = StdSchedulerFactory.getDefaultScheduler();
//            scheduler.start();
//        } catch (SchedulerException e) {
//            logger.info(e.getMessage());
//            e.printStackTrace();
//        }
//    }

    @Override
    public boolean addJob(String jobName, String jobGroup, String triggerName, String triggerGroup, Class jobClass, boolean durability, int interval, int delay,
                          String script, String content, String env) {
        logger.info("begin to add a simple job..................");
        if (!this.createScript(script, content)) {
            logger.error("create script failed");
            return false;
        }
        logger.info("env---" + env);
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroup).storeDurably(durability).build();
        jobDetail.getJobDataMap().put("script", script);
        jobDetail.getJobDataMap().put("env", env);
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(interval).repeatForever();
        Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail).withIdentity(triggerName, triggerGroup).withSchedule(simpleScheduleBuilder).startAt(new Date(System.currentTimeMillis() + delay * 1000)).build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
            return true;
        } catch (SchedulerException e) {
            logger.error("add simple job {} failed:" + e.getMessage(), jobName);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addJob(String jobName, String jobGroup, String triggerName, String triggerGroup, Class jobClass, boolean durability, String cronExpress, int delay,
                          String script, String content, String env) {
        logger.info("begin to add a cron job..................");
        if (!this.createScript(script, content)) {
            logger.error("create script failed");
            return false;
        }
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroup).storeDurably(durability).build();
        jobDetail.getJobDataMap().put("script", script);
        jobDetail.getJobDataMap().put("content", content);
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpress);
        Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail).withIdentity(triggerName, triggerGroup).withSchedule(cronScheduleBuilder).startAt(new Date(System.currentTimeMillis() + delay * 1000)).build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
            return true;
        } catch (SchedulerException e) {
            logger.error("add cron job {} failed:" + e.getMessage(), jobName);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean start(String jobName, String jobGroup) {
        JobKey jobKey = new JobKey(jobName, jobGroup);
        try {
            scheduler.resumeJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            logger.error("start job {}, failed:" + e.getMessage(), jobName);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean pause(String jobName, String jobGroup) {
        JobKey jobKey = new JobKey(jobName, jobGroup);
        try {
            scheduler.pauseJob(jobKey);
            return true;
        } catch (SchedulerException e) {
            logger.error("pause job {}, failed:" + e.getMessage(), jobName);
            e.printStackTrace();
        }
        return false;
    }

    public boolean createScript(String script, String content) {
        File file = new File(GlobalConfig.SCRIPT_PATH + "/" + script);
        if (file.exists()) {
            logger.info("the script file is exist, check the content");
            String content_old = FileUtil.read(file);
            if (content == null) {
                return false;
            }
            if (content_old.equals(content)) {
                logger.info("the script exist and content is same to the new, skipping create script");
                return true;
            }else {
                logger.error("the script exist and content is different from the new, make sure please");
                return false;
            }
        }
        return FileUtil.write(file, content);
    }

}
