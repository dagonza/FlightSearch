package org.search.flight.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.search.flight.dao.AirportDAO;
import org.search.flight.model.Airport;

public class AirportDAOTest {

	@Test
	public final void FirstAddAirport_ThenGetAirport() {
		
		Airport madrid = new Airport("MAD", "Madrid");
		AirportDAO.getAirportDAO().addAirport(madrid);
		
		assertEquals(madrid.getCity(), AirportDAO.getAirportDAO().findAirportByIATA("MAD").get().getCity());
	}

	@Test
	public final void FirstAddAirport_ThenDeleteIt() {
		
		Airport madrid = new Airport("MAD", "Madrid");
		AirportDAO.getAirportDAO().addAirport(madrid);
		
		assertTrue(AirportDAO.getAirportDAO().deleteAirport("MAD"));
	}

}
