package com.wf.refdata.handler;

import com.wf.refdata.model.OptionData;
import com.wf.refdata.model.Stock;
import com.wf.refdata.publisher.RefDataPublisher;
import com.wf.refdata.store.OptionDataStore;
import com.wf.refdata.util.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by hems on 26/04/19.
 */
@Service
public class PriceTickEventHandler {

    private static Logger log = LoggerFactory.getLogger(RefDataPublisher.class);

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private RefDataPublisher refDataPublisher;

    @Autowired
    private OptionDataStore optionDataStore;


    public void handleEvent(final Stock stockPrice) {
        log.info("handleEvent..{}", stockPrice.symbol);
        List<OptionData> optionDataList = optionDataStore.getOptionData(stockPrice.symbol);
        if(optionDataList != null && !optionDataList.isEmpty()) {
            optionDataList.stream().forEach(optionData -> {
                optionData.setStockPrice(stockPrice.price);
                refDataPublisher.sendToTopic(optionData);
            });

        }else {
            log.warn("Not Options Found For Symbol: {}" , stockPrice.symbol);
        }

    }

    public AppProperties getAppProperties() {
        return appProperties;
    }

    public void setAppProperties(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public RefDataPublisher getRefDataPublisher() {
        return refDataPublisher;
    }

    public void setRefDataPublisher(RefDataPublisher refDataPublisher) {
        this.refDataPublisher = refDataPublisher;
    }

    public OptionDataStore getOptionDataStore() {
        return optionDataStore;
    }

    public void setOptionDataStore(OptionDataStore optionDataStore) {
        this.optionDataStore = optionDataStore;
    }
}
