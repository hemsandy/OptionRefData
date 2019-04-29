package com.wf.refdata.subscriber;

import com.google.gson.*;
import com.wf.refdata.handler.PriceTickEventHandler;
import com.wf.refdata.model.Stock;
import com.wf.refdata.util.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by hems on 26/04/19.
 */
@EnableJms
@Component
public class PriceTickSubscriber {

    private static Logger log = LoggerFactory.getLogger(PriceTickSubscriber.class);

    @Autowired
    private PriceTickEventHandler priceTickEventHandler;

    @Autowired
    private AppProperties appProperties;

    public static DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");

    public static Gson gson  = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>(){

                public JsonElement serialize(LocalDateTime localDateTime, Type typeOfSrc,
                                             JsonSerializationContext context) {
                    return new JsonPrimitive(formatter.format(localDateTime));
                }
            }).registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                public LocalDateTime deserialize(JsonElement json, Type typeOfT,
                                                 JsonDeserializationContext context) throws JsonParseException {
                    LocalDateTime dt = LocalDateTime.parse(json.getAsString(),formatter);
                    return dt;
                }
            }).create();

    @JmsListener(destination = "${cmt.options.refdata.queueNameForTickPrice}")
    public void receiveMessage(final Message jsonMessage) throws JMSException {
        log.info("receiveMessage..");
        try{
            if(jsonMessage instanceof  TextMessage) {
                TextMessage textMessage = (TextMessage) jsonMessage;
                String payLoad = textMessage.getText();
                log.debug("Payload: {} ", payLoad);

                Stock stock = gson.fromJson(payLoad, Stock.class);

                priceTickEventHandler.handleEvent(stock);

            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            jsonMessage.acknowledge();
        }
    }

    public AppProperties getAppProperties() {
        return appProperties;
    }

    public void setAppProperties(AppProperties appProperties) {
        this.appProperties = appProperties;
    }
}

