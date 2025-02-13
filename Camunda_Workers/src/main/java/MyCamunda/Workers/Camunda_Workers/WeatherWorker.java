package MyCamunda.Workers.Camunda_Workers;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobHandler;
import io.camunda.zeebe.spring.client.annotation.Variable;

@Component
public class WeatherWorker implements JobHandler  {
  final Logger logger = LoggerFactory.getLogger(WeatherWorker.class);
    private final static Logger LOG = LoggerFactory.getLogger(WeatherWorker.class);

  public Map<String, Double> chargeCreditCard(@Variable(name = "totalWithTax") Double totalWithTax) {
    LOG.info("charging credit card: {}", totalWithTax);
    return Map.of("amountCharged", totalWithTax);
  }

  @Override
  public void handle(JobClient client, ActivatedJob job) throws Exception {
    logger.info("Order: Tracking status");
    logger.info(job.getVariable("totalWithTax").toString());
    if(job.getVariable("totalWithTax").toString().equals("100")){

      /*client.newFailCommand(job.getKey())
      .retries(0)
      .errorMessage("etwas ging schief")
      .send()
      .join();*/

      client.newThrowErrorCommand(job.getKey())
      .errorCode("erroristgeworfen")
      .send()
      .join();

    }else {

    client.newCompleteCommand(job.getKey())
    .variables(Map.of("packaged", "test"))
    .send()
    .join();
    }
    

  }

}
