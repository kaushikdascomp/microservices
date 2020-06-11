package employeeservice.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import employeeservice.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import employeeservice.query.EmployeeInformationQuery;

@Component
public class EmployeeGraphQLMutations implements GraphQLMutationResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeGraphQLMutations.class);

    @Autowired
    EmployeeInformationQuery employeeInformationQuery;

    public Employee newEmployee(Employee employee) {
        LOGGER.info("Employee add: employee={}", employee);
        return employeeInformationQuery.add(employee);
    }
    public Employee deleteEmployee(String id) {
        LOGGER.info("Employee delete: id={}", id);
        return employeeInformationQuery.delete(id);
    }
    public Employee updateEmployee(String id, Employee employee) {
        LOGGER.info("Employee update: id={}, employee={}", id, employee);
        return employeeInformationQuery.update(id, employee);
    }


}
