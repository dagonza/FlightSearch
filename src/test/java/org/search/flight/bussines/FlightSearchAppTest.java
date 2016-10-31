package org.search.flight.bussines;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.search.flight.FlightSearchApp;
import org.search.flight.dao.FlightDAO;
import org.search.flight.model.Airline;
import org.search.flight.model.Airport;
import org.search.flight.model.Flight;
import org.search.flight.model.Trip;
import org.search.flight.model.passengers.Adult;
import org.search.flight.model.passengers.Child;
import org.search.flight.model.passengers.Infant;
import org.search.flight.model.passengers.Passenger;

public class FlightSearchAppTest {

	private FlightSearchApp victim = new FlightSearchApp();
	
	List<Passenger> passengers;
	Airport from;
	Airport to;
	Date when;
	Airline airline;
	
	public void init(){
		
		passengers = new ArrayList<Passenger>();

		from = new Airport("FRA", "france");
		to = new Airport("MAD", "Madrid");
		when = new Date();
		airline = new Airline("IB", "Iberia", new BigDecimal(7));

	}
	
	@Test
	public final void testSearchFlightAdult() {
		
		init();
		passengers.add(new Adult("david", "male", 7));
		FlightDAO.addFlight(new Flight(from, to, "IB1234", airline,new BigDecimal(100)) );
		
		List<Trip> returnTrips = victim.searchFlight(passengers, from, to, when);
		
		assertEquals(returnTrips.get(0).getTripTotalPrice(), new BigDecimal("150.00"));
		System.out.println(returnTrips.size());
		
		FlightDAO.deleteAll();
	}
	
	@Test
	public final void testSearchFlightChild() {
		
		init();
		passengers.add(new Child("david", "male", 7));
		FlightDAO.addFlight(new Flight(from, to, "IB1234", airline,new BigDecimal(100)) );
		
		List<Trip> returnTrips = victim.searchFlight(passengers, from, to, when);
		
		assertEquals(returnTrips.get(0).getTripTotalPrice(), new BigDecimal("100.50"));
		System.out.println(returnTrips.size());
		
		FlightDAO.deleteAll();
	}
	
	@Test
	public final void testSearchFlightInfant() {
		
		init();
		passengers.add(new Infant("david", "male", 7));
		FlightDAO.addFlight(new Flight(from, to, "IB1234", airline,new BigDecimal(100)) );
		
		List<Trip> returnTrips = victim.searchFlight(passengers, from, to, when);
		
		assertEquals(getPriceFirstTrip(returnTrips), new BigDecimal("7"));
		System.out.println(returnTrips.size());
		FlightDAO.deleteAll();
	}
	
	private BigDecimal getPriceFirstTrip(List<Trip> tripList){
		
		if(!tripList.isEmpty()){
			return tripList.get(0).getTripTotalPrice();
		}else{
			return new BigDecimal(0);
		}
		
	}

}
