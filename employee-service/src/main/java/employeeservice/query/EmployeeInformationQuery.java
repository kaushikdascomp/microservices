package employeeservice.query;


import employeeservice.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeInformationQuery implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(EmployeeInformationQuery.class);

    private MongoTemplate mongoTemplate;

    public EmployeeInformationQuery(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        getAllEmployees();
        getAllEmployeesByDept("1");
    }

    public List<Employee> getAllEmployees(){
        System.out.println("Get All Employee List::  ");
        List<Employee> allEmployeeList = mongoTemplate.findAll(Employee.class);
        allEmployeeList.stream().forEach(System.out::println);
        return allEmployeeList;
    }

    public List<Employee> getAllEmployeesByDept(String departmentId){
        System.out.println("Get All Employees by department::  ");
        Query query = Query.query(Criteria.where("departmentId").is(departmentId));
        List<Employee> employeesByDept = mongoTemplate.find(query, Employee.class);
        employeesByDept.stream().forEach(System.out::println);
        log.info("Provide Data from Mongo:: ");
        return employeesByDept;
    }

    public List<Employee> getAllEmployeesByOrganizationId(String organizationId){
        System.out.println("Get All Employees by orgaization::  ");
        Query query = Query.query(Criteria.where("organizationId").is(organizationId));
        List<Employee> employeesByOrganizationId = mongoTemplate.find(query, Employee.class);
        employeesByOrganizationId.stream().forEach(System.out::println);

        return employeesByOrganizationId;
    }

    public Employee getEmployeeByEmpId(String id){
        System.out.println("Get Employees by empid::  ");
        Query query = Query.query(Criteria.where("id").is(id));
        Employee employeeByEmpId = mongoTemplate.findById(query,Employee.class);
        return employeeByEmpId;
    }

    public Employee add(Employee employee){
        Employee insertedValue = mongoTemplate.insert(employee);
        return insertedValue;
    }

    public Employee delete(String id){
        Query query = Query.query(Criteria.where("id").is(id));
        return mongoTemplate.findAndRemove(query,Employee.class);
    }

    public Employee update(String id, Employee department){
        Query query = Query.query(Criteria.where("id").is(id));
        Employee saveDept = mongoTemplate.save(department);
        return saveDept;
    }
}
