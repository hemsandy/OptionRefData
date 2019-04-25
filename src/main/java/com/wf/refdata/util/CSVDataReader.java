package com.wf.refdata.util;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import com.wf.refdata.model.OptionData;

@Component
public class CSVDataReader {
	
	public List<OptionData> parseFile(String fileName){
		String fileContents="";
		CSVParser parser = null;
		OptionData data = null;
		List<OptionData> optionDataList = new ArrayList();
		try{
//			InputStreamReader inputStreamReader = new InputStreamReader(getClass()
//					.getClassLoader().getResourceAsStream(fileName));
			File file = new File(fileName);
			FileInputStream inputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			parser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(inputStreamReader);
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		for(CSVRecord record: parser){
			data = mapRecord(record);
			if(data != null)
				optionDataList.add(data);
		}
		return optionDataList;
	}

	private OptionData mapRecord(CSVRecord record) {
		LocalDate expiryDate = null;
		double strike = new Double(record.get(2)).doubleValue();
		String strVol = record.get(10).substring(0,record.get(10).length()-1);		
		double volatility = new Double(strVol).doubleValue();
		if(volatility !=0.0) {
			volatility = volatility / 100;
		}
		
		try{
			expiryDate = LocalDate.parse(record.get(11), OptionData.formatter);
		}catch(Exception pe){
			pe.printStackTrace();
		}
		
		OptionData data = new OptionData(record.get(0), strike, volatility, expiryDate);
		return data;
	}
	
	public static void main(String[] args) {
		CSVDataReader reader = new CSVDataReader();
		List<OptionData> optDataList = reader.parseFile("OptionData.csv");
		
		for(OptionData data: optDataList){
			System.out.println(data.toString());
		}
		System.out.println("No of records : "+optDataList.size());
	}
}
