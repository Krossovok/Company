package domain;

import java.time.LocalTime;
import java.util.List;

public class Director extends Employee {

    public Director(JobFunction mainJobFunction, List<JobFunction> additionalJobFunctions, Integer salary, boolean fixedSalary, LocalTime[] schedule, String name) {
        super(mainJobFunction, additionalJobFunctions, salary, fixedSalary, schedule, name);
    }

    public void giveTask(Week week, Employee employee, JobFunction jobFunction, Integer time, LocalTime timeNow, Day day) {

        employee.doTask(week, jobFunction, time, timeNow, day);
    }

}
