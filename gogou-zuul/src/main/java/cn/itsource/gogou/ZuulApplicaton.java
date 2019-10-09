package cn.itsource.gogou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuulApplicaton {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplicaton.class,args);
    }
}
