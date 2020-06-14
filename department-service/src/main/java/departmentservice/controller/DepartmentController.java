package departmentservice.controller;

import departmentservice.async.AsyncClient;
import departmentservice.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rest/department")
public class DepartmentController {

    @Autowired
    public AsyncClient asyncClient;

    @GetMapping("/getEmployeesByDepartment/{departmentId}")
    public List<Employee> getEmployeesByDepartment(@PathVariable String departmentId) throws Exception{
        return Arrays.asList(asyncClient.findByDepartment(departmentId));
    }

}
