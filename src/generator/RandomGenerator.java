package generator;

import domain.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomGenerator {

    static Random random = new Random();

    private RandomGenerator() {
    }

    /* generate a random number of employees*/
    public static int getNumberOfEmployees() {
        return random.nextInt(88) + 10;
    }

    /*generate employees */
    public static List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < getNumberOfEmployees(); i++) {
            Employee employee = getJobFunctionsEmployee();
            employees.add(employee);
        }
        return employees;
    }

    /*generate random list of Job Functions without GIVE_TASK*/
    public static List<JobFunction> getJobFunctions() {
        List<JobFunction> randomJobFunctions = new ArrayList<>();
        List<JobFunction> jobFunctions = new ArrayList<>(Arrays.asList(JobFunction.values()));
        jobFunctions.remove(JobFunction.GIVE_TASK);

        for (int i = 0; i < random.nextInt(jobFunctions.size()) + 1; i++) {
            JobFunction jobFunction = jobFunctions.get(random.nextInt(jobFunctions.size()));
            if (!randomJobFunctions.contains(jobFunction)) {
                randomJobFunctions.add(jobFunction);
            }
        }
        return randomJobFunctions;
    }

    /*generate start and finish of work day*/
    public static LocalTime[] getWorkPeriod() {
        LocalTime[] localTimes = new LocalTime[2];
        LocalTime startWork = LocalTime.of(random.nextInt(3) + 8, 0);
        LocalTime finishWork = LocalTime.of(startWork.getHour() + 8, 0);
        localTimes[0] = startWork;
        localTimes[1] = finishWork;
        return localTimes;

    }

    /*generate different type of employees*/
    public static Employee getJobFunctionsEmployee() {

        int i = random.nextInt(5);
        switch (i) {

            case 0:
                return new Designer(JobFunction.DRAW_MODEL, getAdditionalJobFunctions(JobFunction.DRAW_MODEL), 55,
                        false, getWorkPeriod(), getNameEmployee());
            case 1:
                return new Manager(JobFunction.SELL_SERVICE, getAdditionalJobFunctions(JobFunction.SELL_SERVICE), 6000,
                        true, getWorkPeriod(), getNameEmployee());
            case 2:
                return new Tester(JobFunction.TEST_PROGRAM, getAdditionalJobFunctions(JobFunction.TEST_PROGRAM), 50,
                        false, getWorkPeriod(), getNameEmployee());
            case 3:
                return new Programmer(JobFunction.WRITE_CODE, getAdditionalJobFunctions(JobFunction.WRITE_CODE), 70,
                        false, getWorkPeriod(), getNameEmployee());
        }
        return new Accountant(JobFunction.PREPARE_REPORT, getAdditionalJobFunctions(JobFunction.PREPARE_REPORT), 4500,
                true, getWorkPeriod(), getNameEmployee());
    }

    /*generate additional job functions for employee*/
    public static List<JobFunction> getAdditionalJobFunctions(JobFunction jobFunction) {

        List<JobFunction> randomJobFunctions = new ArrayList<>();
        List<JobFunction> jobFunctions = new ArrayList<>(Arrays.asList(JobFunction.values()));
        jobFunctions.remove(JobFunction.GIVE_TASK);
        jobFunctions.remove(jobFunction);

        for (int i = 0; i < random.nextInt(jobFunctions.size()); i++) {
            JobFunction currentJobFunction = jobFunctions.get(random.nextInt(jobFunctions.size()));
            if (!randomJobFunctions.contains(currentJobFunction)) {
                randomJobFunctions.add(currentJobFunction);
            }
        }
        return randomJobFunctions;

    }

    /*set the time in the the beginning of work day*/
    public static List<Employee> resetFinishTime(List<Employee> employeesList) {
        for (Employee employee : employeesList) {
            employee.setEndTimeOfTask(employee.getTimePeriod()[0]);
        }
        return employeesList;
    }

    /*generate time to one task*/
    public static Integer getTimeToOneTask() {
        return random.nextInt(61) + 60;
    }

    /*generate random employee name*/
    public static String getNameEmployee() {
        String[] firstNames = {"MARK", "PETER", "ANTONY", "JOB", "STEVEN",
                "BENTON", "BORIS", "IVAN", "LEV", "ROMAN",
                "CHLOE", "ANGELA", "BRIGIT", "MICHEL", "MARIA",
                "RUBEN", "NICK", "JORGE", "ALEX", "LINDA"};
        String[] lastNames = {"SMITH", "JOHNSON", "WILLIAMS", "BROWN",
                "MILLER", "DAVIS", "GARCIA", "RODRIGUEZ",
                "WILSON", "MARTINEZ", "ANDERSON", "TAYLOR",
                "THOMAS", "HERNANDEZ", "MOORE", "MARTIN",};

        return firstNames[random.nextInt(firstNames.length)] + " " +
                lastNames[random.nextInt(lastNames.length)];

    }

    /*get random suitable employee from additional job functions list */
    public static Employee selectFromAdditional(JobFunction jobFunction, LocalTime now, List<Employee> employees) {

        List<Employee> readyToWorkEmployees = new ArrayList<>();
        for (Employee employee : employees) {

            if (employee.getAdditionalJobFunctions().contains(jobFunction) &&
                    !(employee instanceof Freelancer) &&
                    employee.getTimePeriod()[0].isBefore(now) &&
                    employee.getTimePeriod()[1].isAfter(now) &&
                    employee.getEndTimeOfTask().isBefore(now)) {
                readyToWorkEmployees.add(employee);
            }
        }
        if (readyToWorkEmployees.isEmpty()) {
            return null;
        } else
            return readyToWorkEmployees.get(random.nextInt(readyToWorkEmployees.size()));


    }

    /*get random suitable employee from main job functions list */
    public static Employee selectFromMain(JobFunction jobFunction, LocalTime now, List<Employee> employees) {

        List<Employee> readyToWorkEmployees = new ArrayList<>();
        for (Employee employee : employees) {

            if (employee.getMainJobFunction().equals(jobFunction) &&
                    !(employee instanceof Freelancer) &&
                    employee.getTimePeriod()[0].isBefore(now) &&
                    employee.getTimePeriod()[1].isAfter(now) &&
                    employee.getEndTimeOfTask().isBefore(now)) {
                readyToWorkEmployees.add(employee);
            }
        }
        if (readyToWorkEmployees.isEmpty()) {
            return null;
        } else return readyToWorkEmployees.get(random.nextInt(readyToWorkEmployees.size()));


    }


}
