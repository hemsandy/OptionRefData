package com.wf.refdata.publisher;

/**
 * Created by hems on 18/04/19.
 */
import com.wf.refdata.util.AppProperties;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;


import java.util.Arrays;

@EnableJms
@Configuration
public class ActiveMQConfig {

    @Autowired
    private AppProperties appProperties;

    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(appProperties.getJmsUrl());
        connectionFactory.setTrustedPackages(Arrays.asList("com.wf.refdata"));
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(appProperties.getTopicName());
        return template;
    }

    public AppProperties getAppProperties() {
        return appProperties;
    }

    public void setAppProperties(AppProperties appProperties) {
        this.appProperties = appProperties;
    }
}