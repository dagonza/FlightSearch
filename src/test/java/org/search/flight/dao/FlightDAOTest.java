package org.search.flight.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Test;
import org.search.flight.dao.FlightDAO;
import org.search.flight.model.Airline;
import org.search.flight.model.Airport;
import org.search.flight.model.Flight;

public class FlightDAOTest {

	
	@After
	public void clean(){
		FlightDAO.getFlightDAO().deleteAll();
	}
	
	@Test
	public final void InsertAFlight_ThenGetTheFlightInserted() {
		
		Airline iberia = new Airline("IB", "IBERIA", new BigDecimal(7));
		Airport cope = new Airport("CPH", "cope");
		Airport france = new Airport("FRA", "france");

		Flight aFlight = new Flight(cope, france, "IB2818", iberia , new BigDecimal(186));
		FlightDAO.getFlightDAO().addFlight(aFlight);
		
		assertEquals(aFlight, FlightDAO.getFlightDAO().selecctFlightByAirportFromAndAirportTo(cope, france).get(0));
		
	}	
	
	@Test
	public final void InsertAFlight_ThenGetFligthNoExists() {

		Airline iberia = new Airline("IB", "IBERIA", new BigDecimal(7));
		Airport cope = new Airport("CPH", "cope");
		Airport france = new Airport("FRA", "france");
		Airport madrid = new Airport("MAD", "madrid");

		Flight aFlight = new Flight(cope, france, "IB2818", iberia , new BigDecimal(186));
		FlightDAO.getFlightDAO().addFlight(aFlight);
		
		assertTrue(FlightDAO.getFlightDAO().selecctFlightByAirportFromAndAirportTo(cope,madrid).isEmpty());
		
		clean();
	}

}
