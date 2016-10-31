package org.search.flight.services;

import org.search.flight.dao.AirportDAO;
import org.search.flight.model.Airport;

public class AirportService {

	
	public boolean validateExistsAirport(Airport airport){
		
		return AirportDAO.findAirportByIATA(airport.getIATACode()).isPresent();
	}
	
}
