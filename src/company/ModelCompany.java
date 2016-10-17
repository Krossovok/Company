package company;

import domain.*;
import generator.RandomGenerator;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ModelCompany {


    public List<Employee> modelCompany(List<Employee> employeesList) {

        /*generate necessary employees*/
        Director director = new Director(JobFunction.GIVE_TASK, new ArrayList<JobFunction>(), 10000,
                true, RandomGenerator.getWorkPeriod(), RandomGenerator.getNameEmployee());
        Manager manager = new Manager(JobFunction.SELL_SERVICE, RandomGenerator.getAdditionalJobFunctions(JobFunction.SELL_SERVICE), 6000,
                true, RandomGenerator.getWorkPeriod(), RandomGenerator.getNameEmployee());
        Accountant accountant = new Accountant(JobFunction.PREPARE_REPORT, RandomGenerator.getAdditionalJobFunctions(JobFunction.PREPARE_REPORT), 4500,
                true, RandomGenerator.getWorkPeriod(), RandomGenerator.getNameEmployee());
        employeesList.add(director);
        employeesList.add(manager);
        employeesList.add(accountant);

      /*4 week*/
        for (Week week : Week.values()) {
           /*5 days*/
            for (Day day : Day.values()) {
              /*8 work hours*/
                RandomGenerator.resetFinishTime(employeesList);
                LocalTime timeNow = LocalTime.of(7, 1);
                for (int j = 0; j < 8; j++) {
                    timeNow = timeNow.plusMinutes(60);

                    List<JobFunction> jobFunctions = RandomGenerator.getJobFunctions();
                    for (JobFunction jobFunction : jobFunctions) {
                        Employee employee = RandomGenerator.selectFromMain(jobFunction, timeNow, employeesList);
                        if (employee != null) {
                            director.giveTask(week, employee, jobFunction, RandomGenerator.getTimeToOneTask(), timeNow, day);
                            continue;
                        }
                        employee = RandomGenerator.selectFromAdditional(jobFunction, timeNow, employeesList);
                        if (employee != null) {
                            director.giveTask(week, employee, jobFunction, RandomGenerator.getTimeToOneTask(), timeNow, day);

                        } else {
                            employee = new Freelancer(jobFunction, new ArrayList<JobFunction>(), 40,
                                    false, RandomGenerator.getWorkPeriod(), RandomGenerator.getNameEmployee());
                            employee.setEndTimeOfTask(employee.getTimePeriod()[0]);
                            director.giveTask(week, employee, jobFunction, RandomGenerator.getTimeToOneTask(), timeNow, day);
                            employeesList.add(employee);
                        }

                    }

                }
            }
            accountant.generateWeekReport(employeesList, week);
        }
        accountant.generateMonthReport(employeesList);
        return employeesList;
    }


}