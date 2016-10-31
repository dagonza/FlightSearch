package org.search.flight.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.search.flight.dao.FlightDAO;
import org.search.flight.model.Airline;
import org.search.flight.model.Airport;
import org.search.flight.model.Flight;

public class FlightDAOTest {

	private void clean(){
		FlightDAO.deleteAll();
	}
	
	@Test
	public final void test() {
		
		Airline iberia = new Airline("IB", "IBERIA", new BigDecimal(7));
		Airport cope = new Airport("CPH", "cope");
		Airport france = new Airport("FRA", "france");

		Flight aFlight = new Flight(cope, france, "IB2818", iberia , new BigDecimal(186));
		FlightDAO.addFlight(aFlight);
		
//		assertEquals(aFlight, victim.findFlightByAirportFromAndAirportTo(new Airport("CPH", "cope"), new Airport("FRA", "france")).get());
		
		assertEquals(aFlight, FlightDAO.selecctFlightByAirportFromAndAirportTo(new Airport("CPH", "cope"), new Airport("FRA", "france")).get(0));
		
		clean();
	}
	
//	@Test
//	public final void test2() {
//
//		Flight aFlight = new Flight(new Airport("CPH", "cope"), new Airport("FRA", "france"), "IB2818", new BigDecimal(186));
//		FlightDAO.addFlight(aFlight);
//		
////		assertEquals(aFlight, victim.findFlightByAirportFromAndAirportTo(new Airport("CPH", "cope"), new Airport("FRA", "france")).get());
//		
//		assertEquals(aFlight, FlightDAO.SelecctFlightByAirportFromAndAirportTo("CPH","FRA").get(0));
//		
//		clean();
//	}

}
