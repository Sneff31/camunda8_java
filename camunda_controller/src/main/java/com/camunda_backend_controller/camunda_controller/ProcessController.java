package com.camunda_backend_controller.camunda_controller;

import ch.qos.logback.classic.Logger;
import com.camunda_backend_controller.camunda_controller.dao.ProcessDeploymentDaoImpl;
import io.camunda.zeebe.client.api.response.Topology;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import io.camunda.zeebe.client.ZeebeClient;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ProcessController {

    private final ProcessDeploymentDaoImpl processDeploymentDaoImpl;

    private ZeebeClient zeebeClient;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(ProcessController.class);

    private String bpmnFilePath = "src/main/resources/";

    public ProcessController(JdbcTemplate restTemplate) {
        this.processDeploymentDaoImpl = new ProcessDeploymentDaoImpl(restTemplate);
        this.zeebeClient = ZeebeClient.newClientBuilder().gatewayAddress("localhost:26500").usePlaintext().build();
    }

    /*@GetMapping(path = "/")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }*/

    @GetMapping(path = "/")
    public String controllerAlive() {
        return "Controller is alive!";
    }

    @GetMapping(path = "/deployments/get")
    public List<ProcessDeployment> getDeployments() {
        List<ProcessDeployment> listOfDeployments = new ArrayList<>();
        ProcessDeployment myDeployment = new ProcessDeployment();
        myDeployment.setName("myProcess");
        myDeployment.setBusinessKey("1234");
        myDeployment.setId("testid");
        myDeployment.setDeployment("hallo");
        listOfDeployments.add(myDeployment);
        processDeploymentDaoImpl.creat(myDeployment);
        return listOfDeployments;
    }

    @GetMapping(path = "/alive/camunda")
    public String getCamundaAlive() {
        Topology myTopology;
        try {
            myTopology = zeebeClient.newTopologyRequest().send().join();
        } catch (Exception e) {
            return e.toString();
        }
        return "Camunda is alive:  " + myTopology.toString();
    }

    @GetMapping(path = "/process/start/{name}")
    public String getProcessStart(@PathVariable String name) {
        try {
            zeebeClient.newCreateInstanceCommand().bpmnProcessId(name).latestVersion().send().join();
        } catch (Exception e) {
            return e.toString();
        }
        return "Process started.";
    }

    @PostMapping(path = "/deploy/process")
    public String deployProcessDefinition(@RequestParam String name){
        logger.error("upload file " + name);
        try {
            zeebeClient.newDeployResourceCommand().addResourceFile(bpmnFilePath+name+".bpmn").send().join();
        } catch (Exception e) {
            return e.toString();
        }
        return "Process deployed.";
    }

}
