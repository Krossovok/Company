package domain;

import java.time.LocalTime;
import java.util.List;

public class Programmer extends Employee {

    public Programmer(JobFunction mainJobFunction, List<JobFunction> additionalJobFunctions, Integer salary, boolean fixedSalary, LocalTime[] schedule, String name) {
        super(mainJobFunction, additionalJobFunctions, salary, fixedSalary, schedule, name);
    }
}
