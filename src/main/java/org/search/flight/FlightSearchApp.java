package org.search.flight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.search.flight.bussines.PriceCalculator;
import org.search.flight.model.Airport;
import org.search.flight.model.Flight;
import org.search.flight.model.Trip;
import org.search.flight.model.passengers.Passenger;
import org.search.flight.services.FlightService;

public class FlightSearchApp {
	
	private Collection<Flight> flightList;
	private FlightService flightService;
	private PriceCalculator priceCalculator;
	
	public FlightSearchApp(){
		flightList = new ArrayList<Flight>();
		flightService = new FlightService();
		priceCalculator = new PriceCalculator();
	}
	
	public List<Trip> searchFlight(List<Passenger> passengers, Airport from, Airport to, Date when){
		
		List<Trip> trips = new ArrayList<Trip>(0);
		
		flightList.addAll(flightService.getFlightList(from, to));
		
		if(flightList.isEmpty()){
			System.out.println("no flights available");
		}else{
		
			for(Flight flight: flightList){
				
				Trip newTrip = new Trip(flight, passengers, when);
				
				priceCalculator.caculatePrice(newTrip);
				newTrip.toString();
				
				trips.add(newTrip);
			}
		}
		
		return trips;
	}

}
