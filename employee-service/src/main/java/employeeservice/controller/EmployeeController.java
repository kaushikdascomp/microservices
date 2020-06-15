package employeeservice.controller;

import employeeservice.model.Employee;
import employeeservice.query.EmployeeInformationQuery;
import employeeservice.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/rest/employees")
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeInformationQuery employeeInformationQuery;

    @Autowired
    ReportService reportService;

    @GetMapping("/getEmployees")
    public List<Employee> getEmployees(){
        return employeeInformationQuery.getAllEmployees();
    }

    @GetMapping("/bydepartment/{departmentId}")
    public List<Employee> getEmployeesbyDepartmentId(@PathVariable String departmentId){
        log.info("Inside Employee Controller:: /bydepartment/");
        return employeeInformationQuery.getAllEmployeesByDept(departmentId);
    }

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return reportService.exportReport(format);
    }



}
