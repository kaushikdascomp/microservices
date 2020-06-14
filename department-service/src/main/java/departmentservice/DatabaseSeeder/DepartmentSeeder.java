package departmentservice.DatabaseSeeder;

import departmentservice.model.Department;
import departmentservice.model.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Order(1)
public class DepartmentSeeder implements CommandLineRunner {

    private MongoTemplate mongoTemplate;

    public DepartmentSeeder(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        empty();
        System.out.println("Start Inserting Department documents into Mongo:: ");
        seed();
    }

    private void seed(){
        Department department = new Department();
        department.setDeptId("1");
        department.setOrganizationId("1");
        department.setDepartmentName("Accounts");

        Query query = Query.query(Criteria.where("departmentId").is("1"));
        List<Employee> employeeListByDepartmentId = mongoTemplate.find(query, Employee.class);
        System.out.println("Employee List:  "+employeeListByDepartmentId);

        //department.setEmployeeList(employeeListByDepartmentId);

        Department department2 = new Department();
        department2.setDeptId("2");
        department2.setOrganizationId("1");
        department2.setDepartmentName("HR");

        Query query1 = Query.query(Criteria.where("departmentId").is("2"));
        List<Employee> employeeListByDepartmentId2 = mongoTemplate.find(query1, Employee.class);
        System.out.println("Employee List:  "+employeeListByDepartmentId2);

        //department2.setEmployeeList(employeeListByDepartmentId2);

        List<Department> departments = Arrays.asList(department, department2);
        mongoTemplate.insertAll(departments);
    }

    private void empty(){
        mongoTemplate.remove(new Query(),Department.class);
    }
}
