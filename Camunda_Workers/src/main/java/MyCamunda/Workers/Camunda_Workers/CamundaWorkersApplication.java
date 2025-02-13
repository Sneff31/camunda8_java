package MyCamunda.Workers.Camunda_Workers;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.client.ZeebeClient;

@SpringBootApplication
public abstract class CamundaWorkersApplication implements CommandLineRunner {

   private static final int WORKER_TIMEOUT = 1;
   private static final Logger logger = LoggerFactory.getLogger(CamundaWorkersApplication.class);

   @Autowired
   private ZeebeClient zeebeClient;
   
   public static void main(String[] args) {
      SpringApplication.run(CamundaWorkersApplication.class, args);
   }

   @Override
   public void run(final String... args) {

      ZeebeClient zeebeClient = ZeebeClient.newClientBuilder().gatewayAddress("localhost:26500").usePlaintext().build();


      //Map<String, Object> inputVariables = new HashMap<String, Object>();
      //inputVariables.put("orderId", uniqueId);

      var event = zeebeClient.newCreateInstanceCommand()
      .bpmnProcessId("get-weather-data")
      .latestVersion()
      .send()
      .join();
      logger.info("Process instance: {} started", event.getProcessInstanceKey());

      zeebeClient.newWorker()
      .jobType("weatherTyp")
      .handler(new WeatherWorker())
      .timeout(Duration.ofSeconds(WORKER_TIMEOUT).toMillis())
                .fetchVariables("totalWithTax")
                .open();

      /* 
      var bpmnProcessId = "get-weather-data";
      var event = zeebeClient.newCreateInstanceCommand()
            .bpmnProcessId(bpmnProcessId)
            .latestVersion()
            .variables(Map.of("total", 100))
            .send()
            .join();
      LOG.info("started a process instance: {}", event.getProcessInstanceKey());
      */
   }

}
