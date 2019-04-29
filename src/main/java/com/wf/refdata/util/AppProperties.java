package com.wf.refdata.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by hems on 19/04/19.
 */
@ConfigurationProperties(prefix = "cmt.options.refdata")
public class AppProperties {

    private String jmsUrl;
    private String topicNameForRefData;
    private String topicNameForOptionData;
    private String queueNameForTickPrice;
    private String optionDataFile;
    private String dateFormat;


    public String getJmsUrl() {
        return jmsUrl;
    }

    public void setJmsUrl(String jmsUrl) {
        this.jmsUrl = jmsUrl;
    }

    public String getTopicNameForRefData() {
        return topicNameForRefData;
    }

    public void setTopicNameForRefData(String topicNameForRefData) {
        this.topicNameForRefData = topicNameForRefData;
    }

    public String getTopicNameForOptionData() {
        return topicNameForOptionData;
    }

    public void setTopicNameForOptionData(String topicNameForOptionData) {
        this.topicNameForOptionData = topicNameForOptionData;
    }

    public String getOptionDataFile() {
        return optionDataFile;
    }

    public void setOptionDataFile(String optionDataFile) {
        this.optionDataFile = optionDataFile;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getQueueNameForTickPrice() {
        return queueNameForTickPrice;
    }

    public void setQueueNameForTickPrice(String queueNameForTickPrice) {
        this.queueNameForTickPrice = queueNameForTickPrice;
    }
}
