package departmentservice.graphql;


import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import departmentservice.async.AsyncClient;
import departmentservice.model.Department;
import departmentservice.query.DepartmentInformationQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class DepartmentGraphQLQueries implements GraphQLQueryResolver {

    private static final Logger log = LoggerFactory.getLogger(DepartmentGraphQLQueries.class);

    @Autowired
    DepartmentInformationQuery departmentInformationQuery;

    @Autowired
    AsyncClient asyncClient;

    public List<Department> departments(){
        log.info("Departments List:: ");
        return departmentInformationQuery.getAllDepartments();
    }

    public Department department(String deptId){
        return departmentInformationQuery.getDepartmentById(deptId);
    }

    public List<Department> departmentsByOrganization(String organizationId){
        log.info("Department List by Organization Id:: ",organizationId);
        return departmentInformationQuery.getAllDepartmentsByOrganizationId(organizationId);
    }

    public List<Department> departmentsByOrganizationWithEmployees(String organizationId) throws IOException {
        List<Department> departments = departmentInformationQuery.getAllDepartmentsByOrganizationId(organizationId);
        departments.stream().forEach(e->{
           try{
                e.setEmployeeList((asyncClient.findByDepartment(e.getDeptId())));
            } catch (InterruptedException ex){
                log.error("Error while calling employee service:: ",ex);
            }
        });

        return departments;
    }





}
