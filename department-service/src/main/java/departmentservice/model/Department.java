package departmentservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("department")
public class Department {

    @Id
    private String id;
    private String deptId;
    private String organizationId;

    //@TextIndexed
    private String departmentName;
    private List<Employee> employeeList;

    public Department(){

    }

    public Department(String id, String organizationId, String departmentName, List<Employee> employeeList){
        this.id = id;
        this.organizationId = organizationId;
        this.departmentName = departmentName;
        this.employeeList = employeeList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", employeeList=" + employeeList +
                '}';
    }
}

