package employeeservice.service;

import employeeservice.model.Employee;
import employeeservice.query.EmployeeInformationQuery;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReposrtService {

    @Autowired
    EmployeeInformationQuery employeeInformationQuery;

    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String destinationPath = "C:\\Users\\703245032\\genpact-workspace\\java_projects";
        List<Employee> allEmployees = employeeInformationQuery.getAllEmployees();
        //Load file and compile it
        File file = ResourceUtils.getFile("classpath:employees.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        //prepare data source
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(allEmployees);
        //Parameters is just for adding some specific value like who created the reports etc etc
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Kaushik");
        //Prepare PDF
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        if(reportFormat.equalsIgnoreCase("html")){
            //generate HTML reports
            JasperExportManager.exportReportToHtmlFile(jasperPrint,destinationPath+"\\employees.html");
        }else if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,destinationPath+"\\employees.pdf");
        }

        return "Report Generated to path:"+destinationPath;
    }

}
