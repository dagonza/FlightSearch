package org.search.flight.services;

import java.util.List;

import org.search.flight.dao.FlightDAO;
import org.search.flight.model.Airport;
import org.search.flight.model.Flight;

public class FlightService {

	public FlightService() {
	}

	public void addFlight(Flight flight) {
		FlightDAO.addFlight(flight);
	}
	
	public List<Flight> getFlightList(Airport from, Airport to) {
		return FlightDAO.selecctFlightByAirportFromAndAirportTo(from, to);
	}

	public void deleteAllFlights() {
		FlightDAO.deleteAll();
	}
}
