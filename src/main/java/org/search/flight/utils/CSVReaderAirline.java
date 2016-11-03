package org.search.flight.utils;

import java.math.BigDecimal;

import org.search.flight.dao.AirlineDAO;
import org.search.flight.model.Airline;

public class CSVReaderAirline extends CSVReader {

	public CSVReaderAirline(String csvFile) {
		super(csvFile);
	}
	
	public void importCSV(){
		
		while(extractLine()){
			String[] splitedLine=getSplitedLine();
			AirlineDAO.instance.addAirline(new Airline(splitedLine[0], splitedLine[1], extractAmount(splitedLine[2])));
		}
	}
	
	private BigDecimal extractAmount(String value){
		
		String number = value.replaceAll("€","").trim();
		
		return new BigDecimal(number);
	}

}
