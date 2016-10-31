package org.search.flight.services;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.search.flight.dao.FlightDAO;
import org.search.flight.model.Airline;
import org.search.flight.model.Airport;
import org.search.flight.model.Flight;

public class FlightServiceTest {

	private FlightService victim = new FlightService();
	
	@Before
	public void init(){
		
		Airport madrid = new Airport("MAD", "Madrid");
		Airport france = new Airport("FRA", "France");
		Airport italy = new Airport("ITA", "Italy");
		Airport barcelona = new Airport("BAR", "Barcelona");
		Airline iberia = new Airline("IB", "Iberia", new BigDecimal(7));
		
		FlightDAO.addFlight(new Flight(madrid, france,"IB1234", iberia, new BigDecimal(330)));
		FlightDAO.addFlight(new Flight(madrid, italy,"IB2345", iberia, new BigDecimal(330)));
		FlightDAO.addFlight(new Flight(madrid, barcelona,"IB3456", iberia, new BigDecimal(330)));
		
	}
	
	@After
	public void clean(){
		victim.deleteAllFlights();
	}
	
	@Test
	public final void testExistsFlight() {
		
		
		Airport madrid = new Airport("MAD", "Madrid");
		Airport france = new Airport("FRA", "France");
		
		assertFalse(victim.getFlightList(madrid, france).isEmpty());
		
	}

	@Test
	public final void testNotExistsFlight() {
		
		
		Airport madrid = new Airport("MAD", "Madrid");
		Airport poland = new Airport("PLN", "Poland");
		
		assertTrue(victim.getFlightList(madrid, poland).isEmpty());
		
	}
}
