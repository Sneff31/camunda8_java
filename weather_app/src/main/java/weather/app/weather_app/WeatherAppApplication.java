package weather.app.weather_app;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;

@SpringBootApplication
@Deployment(resources = "classpath:process-payments.bpmn")
public class WeatherAppApplication {

   private static final Logger LOG = LoggerFactory.getLogger(WeatherAppApplication.class);

   @Autowired
   private ZeebeClient zeebeClient;

   @Override
   public void run(final String... args) {
      var bpmnProcessId = "process-payments";
      var event = zeebeClient.newCreateInstanceCommand()
            .bpmnProcessId(bpmnProcessId)
            .latestVersion()
            .variables(Map.of("total", 100))
            .send()
            .join();
      LOG.info("started a process instance: {}", event.getProcessInstanceKey());
	  zeebeClient.newWorker().jobType(bpmnProcessId).handler(handler)
   }
   
	public static void main(String[] args) {
		SpringApplication.run(WeatherAppApplication.class, args);
	}

}
