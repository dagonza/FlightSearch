package org.search.flight.utils;

import org.search.flight.dao.AirportDAO;
import org.search.flight.model.Airport;

public class CSVReaderAirport extends CSVReader {

	public CSVReaderAirport(String csvFile) {
		super(csvFile);
	}
	
	public void importCSV(){

		while(extractLine()){
			String[] splitedLine=getSplitedLine();
			AirportDAO.instance.addAirport(new Airport(splitedLine[0], splitedLine[1]));
		}
	}

}
