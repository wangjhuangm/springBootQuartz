package com.wangj.controller.quartz;

import com.alibaba.fastjson.JSONObject;
import com.wangj.dao.entity.quartz.Job;
import com.wangj.dao.entity.quartz.JobTrigger;
import com.wangj.dao.entity.quartz.Trigger;
import com.wangj.service.quartz.MonitorJob;
import com.wangj.service.quartz.SchedulerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;

@RestController
@RequestMapping("/quartz")
public class QuartzController {

    private final static Logger logger = LoggerFactory.getLogger(QuartzController.class);

    @Autowired
    private SchedulerManager schedulerManager;

    @RequestMapping(value = "/job", method = RequestMethod.POST)
    public String add(@RequestBody JSONObject jsonObject) {
        logger.info(jsonObject.toString());
        JobTrigger jobTrigger = JSONObject.parseObject(jsonObject.toString(), JobTrigger.class);
        Job job = jobTrigger.getJob();
        Trigger trigger = jobTrigger.getTrigger();
        logger.info(job.toString());
        logger.info(trigger.toString());
        if (jobTrigger.isCron()){
            schedulerManager.addJob(job.getName(),job.getGroup(), trigger.getName(), trigger.getGroup(), MonitorJob.class, job.isDurability(), trigger.getCronExpress(),
                    trigger.getDelay(), job.getScript(), job.getContent(), Arrays.toString(job.getEnv()));
        }else {
            schedulerManager.addJob(job.getName(),job.getGroup(), trigger.getName(), trigger.getGroup(), MonitorJob.class, job.isDurability(), trigger.getInterval(),
                    trigger.getDelay(), job.getScript(), job.getContent(), Arrays.toString(job.getEnv()));
        }
        return "add job successful";
    }

    @RequestMapping(value = "/job", method = RequestMethod.GET)
    public String action(@RequestParam("name") String jobName, @RequestParam("group") String jobGroup, @RequestParam("action") String action) {
        logger.info("name: {}",jobName);
        logger.info("group: {}", jobGroup);
        logger.info("action: {}", action);
        switch (action) {
            case "start":
                schedulerManager.start(jobName, jobGroup);
                break;
            case "pause":
                schedulerManager.pause(jobName, jobGroup);
                break;
            default:
                break;
        }
        return "action successful";
    }
}
