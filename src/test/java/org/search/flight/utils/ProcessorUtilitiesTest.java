package org.search.flight.utils;

import static org.junit.Assert.*;

import java.time.LocalDate;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

import org.junit.Test;
import org.search.flight.dao.AirlineDAO;
import org.search.flight.dao.AirportDAO;
import org.search.flight.dao.FlightDAO;

public class ProcessorUtilitiesTest {

	ProcessorUtilities victim = ProcessorUtilities.getProcessorUtilities();

	private String input;
	private int result;
	private LocalDate dateResult;

	@Test
	public void givenValidInput_whenParseToInt_thenGetIntNumber() {
		givenInput("3");
		whenParseToInt();
		thenGetIntNumber(3);
	}

	@Test
	public void givenInvalidInput_whenParseToInt_thenGetZero() {
		givenInput("bad");
		whenParseToInt();
		thenGetIntNumber(0);
	}

	@Test
	public void givenValidInputDate_whenParseDate_thenGetDate() {
		givenInput("02-12-2016");
		whenParseDate();
		thenDateExists();
	}

	public void WhenImportData_thenDAOHasData() {
		whenImportData();
		thenDaoHasData();
	}

	private void givenInput(String string) {
		input = string;
	}

	private void whenParseToInt() {
		result = victim.stringParseToInt(input);
	}

	private void whenParseDate() {
		dateResult = victim.stringToLocalDate(input);
	}

	private void thenGetIntNumber(int expected) {
		assertEquals(expected, result);
	}

	private void thenDateExists() {
		assertTrue(dateResult != null);
	}
	
	private void thenDaoHasData() {
		assertThat(AirportDAO.instance.getAllAirports(), not(empty()));
		assertThat(AirlineDAO.instance.getAllAirlines(), not(empty()));
		assertThat(FlightDAO.instance.getAllFlights(), not(empty()));
	}

	private void whenImportData() {
		victim.importSampleData();
	}

}
