package com.wangj.dao.entity.quartz;

import java.util.Arrays;

public class Job {
    private String name;
    private String group;
    private boolean durability;
    private String script;
    private String content;
    private String[] env;

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

    public boolean isDurability() {
        return durability;
    }

    public void setDurability(boolean durability) {
        this.durability = durability;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getEnv() {
        return env;
    }

    public void setEnv(String[] env) {
        this.env = env;
    }

    @Override
    public String toString() {
        return "Job{" +
                "name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", durability=" + durability +
                ", script='" + script + '\'' +
                ", content='" + content + '\'' +
                ", env=" + Arrays.toString(env) +
                '}';
    }
}
