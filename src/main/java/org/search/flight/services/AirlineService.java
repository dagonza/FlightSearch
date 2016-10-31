package org.search.flight.services;

import java.util.Optional;

import org.search.flight.dao.AirlineDAO;
import org.search.flight.model.Airline;

public class AirlineService {

	public Optional<Airline> getAirlineFromITACACode(String iATACode){
		
		return AirlineDAO.findAirlineByIATA(iATACode);
	}
}
