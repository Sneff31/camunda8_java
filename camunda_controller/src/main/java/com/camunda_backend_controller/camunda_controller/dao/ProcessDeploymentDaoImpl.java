package com.camunda_backend_controller.camunda_controller.dao;

import com.camunda_backend_controller.camunda_controller.ProcessDeployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProcessDeploymentDaoImpl implements ProcessDeploymentDao {

    private final JdbcTemplate restTemplate;

    public ProcessDeploymentDaoImpl(JdbcTemplate restTemplate){this.restTemplate = restTemplate;}

    public void creat(ProcessDeployment processDeployment){
        restTemplate.update(
                "INSERT INTO processdeployment (name, businesskey, deployment, id) VALUES (?, ?, ?, ?)",
                processDeployment.getName(),
                processDeployment.getBusinessKey(),
                processDeployment.getDeployment(),
                processDeployment.getId()
        );
    };

}
