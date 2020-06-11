package employeeservice.DatabaseSeeder;

import employeeservice.model.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Order(1)
public class EmployeeDatabaseSeeder implements CommandLineRunner {

    private MongoTemplate mongoTemplate;

    public EmployeeDatabaseSeeder(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Start Inserting data into Mongo DB:: ");
        empty();
        seedData();
    }

    private void seedData(){
        Employee employee1 = new Employee();
        employee1.setOrganizationId("1");
        employee1.setDepartmentId("1");
        employee1.setName("Alex");
        employee1.setAge(25);
        employee1.setPosition("Manager");
        employee1.setSalary(25000);


        Employee employee2 = new Employee();
        employee2.setOrganizationId("1");
        employee2.setDepartmentId("2");
        employee2.setName("Bob");
        employee2.setAge(32);
        employee2.setPosition("Senior Manager");
        employee2.setSalary(52000);

        Employee employee3 = new Employee();
        employee3.setOrganizationId("2");
        employee3.setDepartmentId("1");
        employee3.setName("Samuel");
        employee3.setAge(28);
        employee3.setPosition("Manager");
        employee3.setSalary(30000);

        List<Employee> employeeInformation = Arrays.asList(employee1, employee2, employee3);

        mongoTemplate.insertAll(employeeInformation);

    }

    private void empty(){
        mongoTemplate.remove(new Query(),Employee.class);
    }
}
