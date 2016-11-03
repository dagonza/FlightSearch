package org.search.flight.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.search.flight.dao.AirlineDAO;
import org.search.flight.model.Airline;

public class AirlineDAOTest {

	@Test
	public final void FirstAddAirline_ThenGetAirline() {
		
		Airline iberia = new Airline("IB", "iberia", new BigDecimal(10.0));
		AirlineDAO.instance.addAirline(iberia);
		
		assertEquals(iberia.getName(), AirlineDAO.instance.findAirlineByIATA("IB").get().getName());
	}

	@Test
	public final void FirstAddAirline_ThenDeleteIt() {
		
		Airline iberia = new Airline("IB", "iberia", new BigDecimal(10.0));
		AirlineDAO.instance.addAirline(iberia);
		
		assertTrue(AirlineDAO.instance.deleteAirline("IB"));
	}

}
