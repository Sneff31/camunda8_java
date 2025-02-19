package com.camunda_backend_controller.camunda_controller;

import io.camunda.zeebe.client.ZeebeClient;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@Log
public class CamundaControllerApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(CamundaControllerApplication.class, args);
	}

	@Override
	public void run(String... args) {
		restTemplate.execute("select 1");
	}

}
