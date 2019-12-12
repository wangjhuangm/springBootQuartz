package com.wangj.controller;

import com.wangj.service.quartz.MonitorJob;
import com.wangj.service.quartz.SchedulerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Index {

    Logger logger = LoggerFactory.getLogger(Index.class);

    @RequestMapping("/")
    public String index() {
        logger.info("first springBoot demo");
        return "hello, spring boot";
    }
}
