package org.search.flight;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.search.flight.model.Flight;
import org.search.flight.model.Trip;
import org.search.flight.model.passengers.Passenger;
import org.search.flight.model.passengers.PassengerBuilder;
import org.search.flight.service.FlightService;
import org.search.flight.service.PriceCalculatorService;

public class FlightSearchApp {

	static final Logger LOG = Logger.getLogger(FlightSearchApp.class);
	private PriceCalculatorService priceCalculator;
	private FlightService flightService;
	private List<Passenger> passengers;

	public FlightSearchApp() {
		priceCalculator = new PriceCalculatorService();
		flightService = new FlightService();
	}

	public List<Trip> calculateTrips(int numberAdult, int numberChild, int numberInfant, String from, String to, LocalDate when) {
		

		LOG.info(numberAdult +" Adults, "+numberChild+" Childs, "+numberInfant+ " Infants. In "+when+" flying "+from+ "->"+to);

		passengers = PassengerBuilder.getPassengerBuilder().createListOfPassengers(numberAdult,
				numberChild, numberInfant);
		
		List<Flight> flights = flightService.selecctFlightByAirportFromAndAirportTo(from, to);
		List<Trip> trips = flights.stream().map(flight -> new Trip(flight, passengers, when))
				.map(priceCalculator::calculatePrice).collect(Collectors.toList());

		showTripsResults(trips);

		return trips;

	}

	private void showTripsResults(List<Trip> trips) {

		if (trips.isEmpty()) {
			LOG.info("no fligth");
		} else {
			LOG.info("fligths:");
			for (Trip trip : trips) {
				LOG.info("\t"+trip.toString());
			}
			trips.forEach(trip -> trip.toString());
		}
	}

}
