package org.search.flight;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.search.flight.dao.FlightDAO;
import org.search.flight.model.Airport;
import org.search.flight.model.Flight;
import org.search.flight.model.Trip;
import org.search.flight.model.passengers.Passenger;
import org.search.flight.service.PriceCalculatorService;

public class FlightSearchApp {
	
	private FlightDAO flightDAO;
	private PriceCalculatorService priceCalculator;
	
	public FlightSearchApp(){
		flightDAO = FlightDAO.getFlightDAO();
		priceCalculator = new PriceCalculatorService();
	}
	
	public List<Trip> calculateTrips(List<Passenger> passengers, Airport from, Airport to, LocalDate when){
		
		List<Flight> flights = flightDAO.selecctFlightByAirportFromAndAirportTo(from, to);
		
		return flights.stream().map(flight -> new Trip(flight,passengers,when)).
                map(priceCalculator::calculatePrice).collect(Collectors.toList());
	}

}
