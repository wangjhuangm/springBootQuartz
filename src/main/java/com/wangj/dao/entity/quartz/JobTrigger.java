package com.wangj.dao.entity.quartz;

public class JobTrigger {
    private Job job;
    private Trigger trigger;
    private boolean cron;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public boolean isCron() {
        return cron;
    }

    public void setCron(boolean cron) {
        this.cron = cron;
    }
}
