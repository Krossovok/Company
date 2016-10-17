package domain;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public abstract class Employee extends Person {

    private JobFunction mainJobFunction;
    private List<JobFunction> additionalJobFunctions = new ArrayList<>();
    private Integer salary;
    private boolean fixedSalary;
    private LocalTime[] schedule = new LocalTime[2];
    private LocalTime endTimeOfTask;
    private List<Double> weekSalary = new ArrayList<>();
    private Map<Week, List<Task>> workList = new EnumMap<>(Week.class);

    public Employee(JobFunction mainJobFunction, List<JobFunction> additionalJobFunctions,
                    Integer salary, boolean fixedSalary, LocalTime[] schedule,
                    String name) {
        this.mainJobFunction = mainJobFunction;
        this.additionalJobFunctions = additionalJobFunctions;
        this.salary = salary;
        this.fixedSalary = fixedSalary;
        this.schedule = schedule;
        this.name = name;
    }

    public void doTask(Week week, JobFunction jobFunction, Integer workTime, LocalTime timeNow, Day day) {

        LocalTime[] workPeriod = new LocalTime[2];
        workPeriod[0] = timeNow;
        workPeriod[1] = timeNow.plusMinutes(workTime);

        if (workList.containsKey(week)) {
            workList.get(week).add(new Task(jobFunction, workTime, workPeriod, day));
            endTimeOfTask = timeNow.plusMinutes(workTime);
        } else {
            List<Task> list = new ArrayList<>();
            list.add(new Task(jobFunction, workTime, workPeriod, day));
            workList.put(week, list);
            endTimeOfTask = timeNow.plusMinutes(workTime);
        }
    }

    public List<Double> getWeekSalary() {
        return weekSalary;
    }


    public LocalTime getEndTimeOfTask() {
        return endTimeOfTask;
    }

    public void setEndTimeOfTask(LocalTime endTimeOfTask) {
        this.endTimeOfTask = endTimeOfTask;
    }

    public Map<Week, List<Task>> getWorkList() {
        return workList;
    }

    public boolean isFixedSalary() {
        return fixedSalary;
    }

    public JobFunction getMainJobFunction() {
        return mainJobFunction;
    }

    public List<JobFunction> getAdditionalJobFunctions() {
        return additionalJobFunctions;
    }

    public LocalTime[] getTimePeriod() {
        return schedule;
    }

    public Integer getSalary() {
        return salary;
    }

}


