package domain;

import java.io.*;
import java.time.LocalTime;
import java.util.List;

public class Accountant extends Employee {


    public Accountant(JobFunction mainJobFunction, List<JobFunction> additionalJobFunctions, Integer salary, boolean fixedSalary, LocalTime[] schedule, String name) {
        super(mainJobFunction, additionalJobFunctions, salary, fixedSalary, schedule, name);
    }

    public Double calculationSalary(Employee employee) {
        if (employee.isFixedSalary()) {
            return (double) employee.getSalary();
        } else {
              Double salary = 0.0;
                    for (Double d: employee.getWeekSalary()){
                        salary = salary + d;
                    }
            return salary;
        }
    }

    public void generateWeekReport(List<Employee> employees, Week week) {

        for (Employee employee : employees) {

            if (employee.getWorkList().containsKey(week) && !employee.isFixedSalary()) {
                double totalTime = 0;
                for (Task task : employee.getWorkList().get(week)) {
                    totalTime = totalTime + task.getTimeTask();
                }
                employee.getWeekSalary().add(Math.rint((totalTime / 60.0) * employee.getSalary()));

            }
        }
    }


    public void generateMonthReport(List<Employee> employees) {

        StringBuilder sb = new StringBuilder();

        for (Employee employee : employees) {
            sb.append("\n");
            sb.append("\n");
            sb.append(String.format("Employee name: %s ", employee.getNameEmployee()));
            sb.append("\n");
            sb.append(String.format("Employee position: %s",employee.getClass().getSimpleName()));
            sb.append("\n");
            sb.append(String.format("Main job function : %s", employee.getMainJobFunction()));
            sb.append("\n");
            sb.append(String.format("Additional job functions : %s", employee.getAdditionalJobFunctions()));
            sb.append("\n");
            sb.append(String.format("Work time: %s-%s", employee.getTimePeriod()[0], employee.getTimePeriod()[1]));
            sb.append("\n");
            sb.append("\n");
            sb.append(String.format("Completed tasks  : %s ",employee.getWorkList()));
            sb.append("\n");
            sb.append("Week salary : " + employee.getWeekSalary().toString());
            sb.append("\n");
            sb.append(String.format("Total salary : %s ", calculationSalary(employee)));
            sb.append("\n");
            sb.append("-----------------------------------------------------------------------");

        }

        try (Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("company.doc"), "UTF-8"))) {

            out.write(sb.toString());
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

