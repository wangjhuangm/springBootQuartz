package com.wangj.service.quartz;

import com.wangj.service.GlobalConfig;
import com.wangj.util.FileUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.*;

public class MonitorJob extends QuartzJobBean
{

    Logger logger = LoggerFactory.getLogger(MonitorJob.class);

    private final static String SCRIPT = "script";
    private final static String ENV = "env";

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String script = jobExecutionContext.getJobDetail().getJobDataMap().getString(SCRIPT);
        String[] env = jobExecutionContext.getJobDetail().getJobDataMap().getString(ENV).replaceAll("\\[", GlobalConfig.NULL).replaceAll(GlobalConfig.BRACKET_LEFT, GlobalConfig.NULL).
                replaceAll(GlobalConfig.BRACKET_RIGHT, GlobalConfig.NULL).split(GlobalConfig.COMMA + GlobalConfig.SPACE + GlobalConfig.ASTERISK);
        logger.info("begin to exec: {}", script);
        String result = callShell(script, env);
        logger.info("script exec result: {}", result);
    }

    private String callShell(String script, String[] env) {
        String cmd = "bash " + script;
        File path = new File(GlobalConfig.SCRIPT_PATH);
        try {
            Process process = Runtime.getRuntime().exec(cmd, env, path);
            return FileUtil.read(process.getInputStream());
        } catch (IOException e) {
            logger.error("exec shell failed: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}
