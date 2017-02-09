package com.launchVisor.config;

import com.launchVisor.services.ComidaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.launchVisor.services.TaskService;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.launchVisor")
//@ComponentScan(basePackages = "com.javacodegeeks.examples.realtimeapp.part1")
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public TaskService taskService() {
        return new TaskService();
    }

    @Bean
    public ComidaService comidaService() {
        return new ComidaService();
    }
}
