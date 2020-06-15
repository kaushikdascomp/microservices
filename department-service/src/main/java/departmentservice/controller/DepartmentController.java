package departmentservice.controller;

import departmentservice.async.AsyncClient;
import departmentservice.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/department")
public class DepartmentController {

    private static final Logger log = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    public AsyncClient asyncClient;

    @GetMapping("/getEmployeesByDepartment/{departmentId}")
    public List<Employee> getEmployeesByDepartment(@PathVariable String departmentId) throws Exception{
        log.info("Inside getEmployeesByDepartment::  ");
        return asyncClient.findByDepartment(departmentId);
    }

}
