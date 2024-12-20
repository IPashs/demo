package ru.task.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.task.demo.exception.handlers.ExceptionHandlerStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ExceptionHandlerConfig {

    @Bean
    public Map<Class<? extends RuntimeException>, ExceptionHandlerStrategy> exceptionHandlerStrategies(List<ExceptionHandlerStrategy> strategies) {
        Map<Class<? extends RuntimeException>, ExceptionHandlerStrategy> map = new HashMap<>();
        for (ExceptionHandlerStrategy strategy : strategies) {
            map.put(strategy.getSupportedException(), strategy);
        }
        return map;
    }
}
