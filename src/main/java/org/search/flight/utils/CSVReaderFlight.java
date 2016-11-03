package org.search.flight.utils;


import org.search.flight.service.FlightService;


public class CSVReaderFlight extends CSVReader {

	public CSVReaderFlight(String csvFile) {
		super(csvFile);
	}
	
	public void importCSV(){
		
		FlightService flightService = new FlightService();
		
		while(extractLine()){
			String[] splitedLine=getSplitedLine();
			flightService.addFlight(splitedLine[0], splitedLine[1], splitedLine[2] , splitedLine[3]);
//			AirlineDAO.getAirlineDAO().addAirline(new Airline(splitedLine[0], splitedLine[1], new BigDecimal(splitedLine[2])));
		}
	}

}
