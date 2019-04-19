package com.wf.refdata.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by hems on 19/04/19.
 */
@ConfigurationProperties(prefix = "cmt.options.refdata")
public class AppProperties {

    private String jmsUrl;
    private String topicName;
    private String optionDataFile;
    private String dateFormat;


    public String getJmsUrl() {
        return jmsUrl;
    }

    public void setJmsUrl(String jmsUrl) {
        this.jmsUrl = jmsUrl;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
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
}
