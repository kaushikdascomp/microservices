package departmentservice.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import departmentservice.model.Department;
import departmentservice.query.DepartmentInformationQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentGraphQLMutations implements GraphQLMutationResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentGraphQLMutations.class);

    @Autowired
    DepartmentInformationQuery repository;

    public Department newDepartment(Department department) {
        LOGGER.info("Department add: department={}", department);
        return repository.add(department);
    }

    public Department deleteDepartment(String id) {
        LOGGER.info("Department delete: id={}", id);
        Department deletedDocument = repository.delete(id);
        return deletedDocument;
    }

    public Department updateDepartment(String id, Department department) {
        LOGGER.info("Department update: id={}, department={}", id, department);
        return repository.update(id, department);
    }

}
