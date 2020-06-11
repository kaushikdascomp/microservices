package employeeservice.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import employeeservice.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import employeeservice.query.EmployeeInformationQuery;

import java.util.List;

@Component
public class EmployeeGraphQLQueries implements GraphQLQueryResolver {

    private static final Logger log = LoggerFactory.getLogger(EmployeeGraphQLQueries.class);

    @Autowired
    EmployeeInformationQuery employeeInformationQuery;

    public List<Employee> employees(){
        log.info("Employee List:: ");
        return employeeInformationQuery.getAllEmployees();
    }

    public List<Employee> employeesByDepartment(String departmentId){
        log.info("Employee List by Dept Id:: ",departmentId);
        return employeeInformationQuery.getAllEmployeesByDept(departmentId);
    }

    public List<Employee> employeesByOrganization(String organizationId){
        log.info("Employee List by Organization Id:: ",organizationId);
        return employeeInformationQuery.getAllEmployeesByOrganizationId(organizationId);
    }

    public Employee employee(String id){
        return employeeInformationQuery.getEmployeeByEmpId(id);
    }

}
