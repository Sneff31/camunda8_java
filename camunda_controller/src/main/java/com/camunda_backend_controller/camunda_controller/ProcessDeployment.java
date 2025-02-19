package com.camunda_backend_controller.camunda_controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProcessDeployment {
    private String name;
    private String businessKey;
    private String deployment;
    private String id;
}
