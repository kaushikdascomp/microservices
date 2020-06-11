package departmentservice.query;

import com.mongodb.client.result.DeleteResult;
import departmentservice.model.Department;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentInformationQuery implements CommandLineRunner {

    private MongoTemplate mongoTemplate;

    public DepartmentInformationQuery(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public void run(String... args) throws Exception {
        getAllDepartments();

    }

    public List<Department> getAllDepartments(){
        System.out.println("Get All Department List::  ");
        List<Department> allDepartmentList = mongoTemplate.findAll(Department.class);
        allDepartmentList.stream().forEach(System.out::println);
        return allDepartmentList;
    }

    public Department getDepartmentById(String deptId){
        System.out.println("Get Department by deptId::  ");
        Query query = Query.query(Criteria.where("deptId").is(deptId));
        Department departmentByDeptId = mongoTemplate.findById(query,Department.class);
        return departmentByDeptId;
    }

    public List<Department> getAllDepartmentsByOrganizationId(String organizationId){
        System.out.println("Get All Department by Organization::  ");
        Query query = Query.query(Criteria.where("organizationId").is(organizationId));
        List<Department> departmentByOrganizationId = mongoTemplate.find(query, Department.class);
        departmentByOrganizationId.stream().forEach(System.out::println);

        return departmentByOrganizationId;
    }

    public Department add(Department department){
        Department insertedValue = mongoTemplate.insert(department);
        return insertedValue;
    }

    public Department delete(String id){
        Query query = Query.query(Criteria.where("id").is(id));
        return mongoTemplate.findAndRemove(query,Department.class);
    }

    public Department update(String id, Department department){
        Query query = Query.query(Criteria.where("id").is(id));
        Department saveDept = mongoTemplate.save(department);
        return saveDept;
    }




}
