package com.wangj.service.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class QuartzConfiguration {

    //@Bean
    public JobDetail firstJobDetail() {
        JobDetail jobDetail = JobBuilder.newJob(MonitorJob.class).withIdentity("firstJob").storeDurably().build();
        jobDetail.getJobDataMap().put("first", "first:hello world");
        return jobDetail;
    }

    //@Bean
    public Trigger firstJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(30).repeatForever();
        //创建任务触发器
        return TriggerBuilder.newTrigger()
                .forJob(firstJobDetail())
                .withIdentity("firstJobTrigger")
                .withSchedule(scheduleBuilder) //将触发器与任务绑定到调度器内
                //.startNow() //使用startNow()马上启动不生效，因为scheduler还没启动
                //.startAt(new Date())//使用startAt(new Date())也不能生效，因为scheduler还没启动
                //.startAt(new Date(System.currentTimeMillis()))//也不生效，因为scheduler还没启动
                .startAt(new Date(System.currentTimeMillis() + 5000))//只有延时时间大于等于scheduler延时启动时间才行
                .build();
    }

    //@Bean
    public JobDetail secondJobDetail() {
        JobDetail jobDetail = JobBuilder.newJob(MonitorJob.class).withIdentity("secondJob").storeDurably().build();
        jobDetail.getJobDataMap().put("first", "second:hello world");
        return jobDetail;
    }

    //@Bean
    public Trigger secondJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(30).repeatForever();
        //创建任务触发器
        return TriggerBuilder.newTrigger()
                .forJob(secondJobDetail())
                .withIdentity("secondJobTrigger")
                .withSchedule(scheduleBuilder) //将触发器与任务绑定到调度器内
                //.startNow() //使用startNow()马上启动不生效，因为scheduler还没启动
                //.startAt(new Date())//使用startAt(new Date())也不能生效，因为scheduler还没启动
                //.startAt(new Date(System.currentTimeMillis()))//也不生效，因为scheduler还没启动
                .startAt(new Date(System.currentTimeMillis() + 5000))//只有延时时间大于等于scheduler延时启动时间才行
                .build();
    }
}
