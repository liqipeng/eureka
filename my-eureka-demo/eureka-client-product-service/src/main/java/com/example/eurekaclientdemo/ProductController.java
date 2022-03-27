package com.example.eurekaclientdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping(value = "/product/{name}")
    public String getProduct(@PathVariable("name") String name) {
        logger.info("product service invoked! name={}", name);

        return "product: " + name;
    }
}