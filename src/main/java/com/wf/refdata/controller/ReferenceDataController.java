package com.wf.refdata.controller;

import java.io.IOException;
import java.util.List;

import com.wf.refdata.util.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wf.refdata.model.OptionData;
import com.wf.refdata.util.CSVDataReader;

@RestController
@RequestMapping(value ="/refdata")
public class ReferenceDataController {
	
	@Autowired
	private CSVDataReader reader;

	@Autowired
	private AppProperties appProperties;
	
	@RequestMapping(value="/option" , method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OptionData> getData() throws IOException{
		return reader.parseFile(appProperties.getOptionDataFile());
	}

	public AppProperties getAppProperties() {
		return appProperties;
	}

	public void setAppProperties(AppProperties appProperties) {
		this.appProperties = appProperties;
	}
}
