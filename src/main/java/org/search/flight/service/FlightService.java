package org.search.flight.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.search.flight.dao.AirlineDAO;
import org.search.flight.dao.AirportDAO;
import org.search.flight.dao.FlightDAO;
import org.search.flight.model.Airline;
import org.search.flight.model.Airport;
import org.search.flight.model.Flight;

public class FlightService {

	private FlightDAO flightDAO;
	private AirportDAO airportDAO;
	private AirlineDAO airlineDAO;
	private AirportService airportService;

	public FlightService() {
		flightDAO = FlightDAO.instance;
		airportDAO = AirportDAO.instance;
		airlineDAO = AirlineDAO.instance;
		airportService = new AirportService();
	}

	public void addFlight(String origin, String destination, String airlineCodeFlight, String basePrice) {

		Optional<Airport> originAirport = airportDAO.findAirportByIATA(origin);
		Optional<Airport> destinyAirport = airportDAO.findAirportByIATA(destination);
		Optional<Airline> airline = airlineDAO.findAirlineByIATA(airlineCodeFlight.substring(0, 2));

		if (originAirport.isPresent() && destinyAirport.isPresent() && airline.isPresent()) {
			flightDAO.addFlight(new Flight(originAirport.get(), destinyAirport.get(), airlineCodeFlight, airline.get(),
					new BigDecimal(basePrice)));
		}
	}

	public List<Flight> selecctFlightByAirportFromAndAirportTo(String from, String to) {

		Airport originAirport = airportService.findAirportByIATACodeOrDefault(from);
		Airport destinyAirport = airportService.findAirportByIATACodeOrDefault(to);

		return flightDAO.selecctFlightByAirportFromAndAirportTo(originAirport, destinyAirport);
	}

}
