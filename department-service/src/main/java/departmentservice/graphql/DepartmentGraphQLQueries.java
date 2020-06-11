package departmentservice.graphql;


import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import departmentservice.model.Department;
import departmentservice.query.DepartmentInformationQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentGraphQLQueries implements GraphQLQueryResolver {

    private static final Logger log = LoggerFactory.getLogger(DepartmentGraphQLQueries.class);

    @Autowired
    DepartmentInformationQuery departmentInformationQuery;

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





}
