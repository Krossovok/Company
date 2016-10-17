import company.ModelCompany;
import generator.RandomGenerator;

public class Main {

    public static void main(String[] args) {

        new ModelCompany().modelCompany(RandomGenerator.getEmployees());
    }
}
