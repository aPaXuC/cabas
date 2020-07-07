package com.springexercise.alerter.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableConfigurationProperties
public class AppConfig {

    private final AmqpProperties amqpProperties;

    @Autowired
    public AppConfig(AmqpProperties amqpProperties) {
        this.amqpProperties = amqpProperties;
    }

    @Bean
    public Queue queue() {
        return new Queue(amqpProperties.getQueue(), false);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(amqpProperties.getExchange());
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(amqpProperties.getQueue());
    }

    @Bean
    public Jackson2RepositoryPopulatorFactoryBean getRepositoryPopulator() {
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        factory.setResources(new Resource[]{new ClassPathResource("Citizens.json"), new ClassPathResource("Areas.json")});
        return factory;
    }
}
