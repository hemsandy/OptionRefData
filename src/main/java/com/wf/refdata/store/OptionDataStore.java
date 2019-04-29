package com.wf.refdata.store;

import com.wf.refdata.model.OptionData;
import com.wf.refdata.util.AppProperties;
import com.wf.refdata.util.CSVDataReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by hems on 26/04/19.
 */

@Service
public class OptionDataStore {

    private static Logger log = LoggerFactory.getLogger(OptionDataStore.class);

    private Map<String, List<OptionData>> optionStoreMap = null;

    private List<OptionData> optionDataList = null;

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private CSVDataReader csvDataReader;


    public long loadStore() {
        log.info("Loading OptionDataStore from csv File :{}",appProperties.getOptionDataFile());
        optionDataList = csvDataReader.parseFile(appProperties.getOptionDataFile());

        optionStoreMap = optionDataList.parallelStream().collect(Collectors.groupingBy(OptionData::getStockName));
        log.info("OptionDataStore size :{}",optionStoreMap.size());

        return optionDataList.size();
    }

    public AppProperties getAppProperties() {
        return appProperties;
    }

    public void setAppProperties(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public List<OptionData> getOptionData(String stockName) {
        return optionStoreMap.get(stockName);
    }
    public CSVDataReader getCsvDataReader() {
        return csvDataReader;
    }

    public void setCsvDataReader(CSVDataReader csvDataReader) {
        this.csvDataReader = csvDataReader;
    }

    public List<OptionData> getOptionDataList() {
        return optionDataList;
    }

    public void setOptionDataList(List<OptionData> optionDataList) {
        this.optionDataList = optionDataList;
    }
}
