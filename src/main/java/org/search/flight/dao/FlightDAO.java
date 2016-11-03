package org.search.flight.dao;

import java.util.ArrayList;
import java.util.List;

import org.search.flight.model.Airport;
import org.search.flight.model.Flight;

public class FlightDAO {

	private List<Flight> flightList;
	public static final FlightDAO instance = new FlightDAO();

	private FlightDAO() {
		flightList = new ArrayList<Flight>();
	}

	public void addFlight(Flight Flight) {
		flightList.add(Flight);
	}

	public List<Flight> selecctFlightByAirportFromAndAirportTo(Airport origin, Airport destiny) {

		List<Flight> toReturn = new ArrayList<Flight>(0);

		for (Flight flight : flightList) {
			if (flight.getFrom().getIATACode().equalsIgnoreCase(origin.getIATACode())
					&& flight.getTo().getIATACode().equalsIgnoreCase(destiny.getIATACode())) {
				toReturn.add(flight);
			}
		}

		return toReturn;

	}

	public void deleteAll() {
		flightList.clear();
	}

	public List<Flight> getAllFlights() {
		return flightList;
	}

}
