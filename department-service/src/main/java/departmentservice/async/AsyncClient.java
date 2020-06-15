package departmentservice.async;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import departmentservice.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class AsyncClient {

    @Autowired
    public WebClient.Builder webClientBuilder;

    private static final String EMPLOYEE_SERVICE = "EMPLOYEE-SERVICE";

    Random r = new Random();

    @Autowired
    private EurekaClient discoveryClient;

    //Synchronous call to Employee Service as at the end we are using .block
    // For asynchronous, we need to use a Mono or a Flux Object
    public List<Employee> findByDepartment(String departmentId) throws InterruptedException {
        Application application = discoveryClient.getApplication(EMPLOYEE_SERVICE);
        InstanceInfo instanceInfo = application.getInstances().get(r.nextInt(application.size()));
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/rest/employees/bydepartment/" + departmentId;
        System.out.println("URL" + url);


        return Arrays.asList(webClientBuilder.build()
                .get()
                .uri(url)
                .retrieve()
                .bodyToMono(Employee[].class)
                .block());
    }
}
