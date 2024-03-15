package com.yyw.p2p.servicecore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.yyw.p2p"}) // pom中的gav和java项目实际的packages是两个不同的概念，pom中groupId中P2P为大写，而实际的package是p2p
public class ServiceCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCoreApplication.class, args);
    }

}
