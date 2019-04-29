package com.wf.refdata.controller;

import java.io.IOException;
import java.util.List;

import com.wf.refdata.publisher.RefDataPublisher;
import com.wf.refdata.store.OptionDataStore;
import com.wf.refdata.util.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.wf.refdata.model.OptionData;
import com.wf.refdata.util.CSVDataReader;

@RestController
@RequestMapping(value ="/refdata")
public class ReferenceDataController {

	private static Logger log = LoggerFactory.getLogger(ReferenceDataController.class);

	@Autowired
	OptionDataStore optionDataStore;
	
	@RequestMapping(value="/option" , method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OptionData> getData() throws IOException{
		return optionDataStore.getOptionDataList();
	}

	@RequestMapping(value="/option/{symbol}" , method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OptionData> getData(@PathVariable(value ="symbol",required = false) String symbol) throws IOException{
		return optionDataStore.getOptionData(symbol);
	}

	public OptionDataStore getOptionDataStore() {
		return optionDataStore;
	}

	public void setOptionDataStore(OptionDataStore optionDataStore) {
		this.optionDataStore = optionDataStore;
	}
}
