package org.search.flight.service;

import java.util.Optional;

import org.search.flight.dao.AirportDAO;
import org.search.flight.model.Airport;

public class AirportService {

	public Airport findAirportByIATACodeOrDefault(String iATACodeAirport) {

		Optional<Airport> theAirport = AirportDAO.instance.findAirportByIATA(iATACodeAirport);

		if (!theAirport.isPresent()) {
			return new Airport();
		}else{
			return theAirport.get();
		}
	}
}
