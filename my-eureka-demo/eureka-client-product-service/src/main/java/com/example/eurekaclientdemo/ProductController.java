package com.example.eurekaclientdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping(value = "/product/{name}")
    public String getProduct(@PathVariable("name") String name) {
        logger.info("product service invoked! name={}", name);

        return "product: " + name;
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        // from : https://spring.io/guides/gs/service-registration-and-discovery/
        return this.discoveryClient.getInstances(applicationName);
    }
}