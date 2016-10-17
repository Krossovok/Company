package domain;

import java.time.LocalTime;

public class Task {

    JobFunction jobFunction;
    Integer taskTime;
    LocalTime[] workPeriod = new LocalTime[2];
    Day day;

    public Task(JobFunction jobFunction, Integer taskTime, LocalTime[] workPeriod, Day day) {
        this.jobFunction = jobFunction;
        this.taskTime = taskTime;
        this.workPeriod = workPeriod;
        this.day = day;
    }

    public Integer getTimeTask() {
        return taskTime;
    }

    @Override
    public String toString() {
        return String.format("%s:%s(%s-%s)", jobFunction, day, workPeriod[0], workPeriod[1]);
    }
}
