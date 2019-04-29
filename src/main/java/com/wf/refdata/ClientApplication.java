package com.wf.refdata;

import com.wf.refdata.model.OptionData;
import com.wf.refdata.publisher.RefDataPublisher;
import com.wf.refdata.store.OptionDataStore;
import com.wf.refdata.subscriber.PriceTickSubscriber;
import com.wf.refdata.util.AppProperties;
import com.wf.refdata.util.CSVDataReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import java.util.Iterator;
import java.util.List;


@SpringBootApplication
@ComponentScan(basePackages={"com.wf.refdata"})
@EnableConfigurationProperties(AppProperties.class)
public class ClientApplication implements ApplicationRunner{

	@Autowired
	AppProperties appProperties;

	@Autowired
	OptionDataStore optionDataStore;

	@Autowired
	PriceTickSubscriber priceTickSubscriber;

	private static Logger log = LoggerFactory.getLogger(ClientApplication.class);


	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {

		optionDataStore.loadStore();

		//publishOptionData();

	}

	/*private void publishOptionData() {

		log.info("Publishing RefData to Topic..Start");
		log.debug(appProperties.getDateFormat() + "-" + appProperties.getJmsUrl()
				+ "-" + appProperties.getOptionDataFile() + "-" + appProperties.getTopicNameForOptionData());
		try {
			List<OptionData> optionDataList = csvDataReader.parseFile(appProperties.getOptionDataFile());
			if (optionDataList != null && !optionDataList.isEmpty()) {
				Iterator<OptionData> iterator = optionDataList.iterator();
				while (iterator.hasNext()) {
					refDataPublisher.sendToTopic(iterator.next());
				}
			}
		}catch(Exception ex) {
			log.error("Exception occured while publishing OptionData(RefData) to jms Topic", ex);
		}
		log.info("Publishing RefData to Topic..End");
	}*/



	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}
}
