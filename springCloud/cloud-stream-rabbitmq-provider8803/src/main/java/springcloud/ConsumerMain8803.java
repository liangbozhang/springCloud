package springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConsumerMain8803 {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerMain8803.class, args);
    }
}
