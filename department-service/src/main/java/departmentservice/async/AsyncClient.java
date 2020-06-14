package departmentservice.async;

import departmentservice.exception.DepartmentException;
import departmentservice.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AsyncClient {

    @Autowired
    public WebClient.Builder webClientBuilder;

    //Synchronous call to Employee Service as at the end we are using .block
    // For asynchronous, we need to use a Mono or a Flux Object
    public Employee[] findByDepartment(String departmentId) throws InterruptedException {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8083/rest/employees/bydepartment/" + departmentId)
                .retrieve()
                .bodyToMono(Employee[].class)
               .block();
       // employeeByDeptflux.subscribe(System.out::println);
        //return employeeByDeptflux;
    }
}
