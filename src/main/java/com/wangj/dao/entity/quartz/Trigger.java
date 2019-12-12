package com.wangj.dao.entity.quartz;

public class Trigger {
    private String name;
    private String group;
    private int interval;
    private String cronExpress;
    private int delay;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getCronExpress() {
        return cronExpress;
    }

    public void setCronExpress(String cronExpress) {
        this.cronExpress = cronExpress;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    @Override
    public String toString() {
        return "Trigger{" +
                "name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", interval=" + interval +
                ", cronExpress='" + cronExpress + '\'' +
                ", delay=" + delay +
                '}';
    }
}
