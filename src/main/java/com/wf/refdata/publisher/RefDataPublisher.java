package com.wf.refdata.publisher;

/**
 * Created by hems on 18/04/19.
 */


import com.wf.refdata.model.OptionData;
import com.wf.refdata.util.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;


@Service
public class RefDataPublisher {

    private static Logger log = LoggerFactory.getLogger(RefDataPublisher.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private AppProperties appProperties;

    public void sendToTopic(OptionData optionData) {
        log.info("sending toTopic() <" + optionData.toJSONString() + ">");
        jmsTemplate.send(appProperties.getTopicName(), new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                Message textMessage = session.createTextMessage(optionData.toJSONString());
                return textMessage;
            }
        });
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public AppProperties getAppProperties() {
        return appProperties;
    }

    public void setAppProperties(AppProperties appProperties) {
        this.appProperties = appProperties;
    }
}


