package org.search.flight;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.search.flight.model.Airline;
import org.search.flight.model.Airport;
import org.search.flight.model.Flight;
import org.search.flight.model.Trip;
import org.search.flight.service.FlightService;

@RunWith(MockitoJUnitRunner.class)
public class IntegrationTest {

	@InjectMocks
	private FlightSearchApp victim = new FlightSearchApp();

	@Mock
	private FlightService flightService;

	private int adult;
	private int child;
	private int infant;
	private String from;
	private Airport airportFrom;
	private Airport airportTo;
	private String to;
	private LocalDate when;
	private Airline airline;
	private List<Trip> returnTrips;

	@Before
	public void init() {

		airportFrom = new Airport("FRA", "france");
		airportTo = new Airport("MAD", "Madrid");
		from = "FRA";
		to = "MAD";
		when = LocalDate.now();
		airline = new Airline("IB", "Iberia", new BigDecimal(7));

	}

	@Test
	public final void givenAdultPassenger_whenSearchFlightToday_thenSearchFlight() {

		givenPassenger(1, 0, 0);
		givenMockFlights(from, to, "IB1234", airline, new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("150.00");
	}

	@Test
	public final void givenAdultPassenger_whenSearchFlightInThreeDays_thenSearchFlight() {

		when = LocalDate.now().plusDays(3);

		givenPassenger(1, 0, 0);
		givenMockFlights(from, to, "IB1234", airline, new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("120.00");
	}

	@Test
	public final void givenAdultPassenger_whenSearchFlightInTwentyDays_thenSearchFlight() {

		when = LocalDate.now().plusDays(20);

		givenPassenger(1, 0, 0);
		givenMockFlights(from, to, "IB1234", airline, new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("100.00");
	}

	@Test
	public final void given1Adult_whenSearchFlightInThirtyOneDays_thenSearchFlight() {

		when = LocalDate.now().plusDays(31);

		givenPassenger(1, 0, 0);
		givenMockFlights(from, to, "IB1234", airline, new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("80.00");
	}

	@Test
	public final void givenChildPassenger_whenSearchFlightToday_thenSearchFlight() {

		givenPassenger(0, 1, 0);
		givenMockFlights(from, to, "IB1234", airline, new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("100.50");
	}

	@Test
	public final void givenChildPassenger_whenSearchFlightInThreeDays_thenSearchFlight() {

		when = LocalDate.now().plusDays(3);

		givenPassenger(0, 1, 0);
		givenMockFlights(from, to, "IB1234", airline, new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("80.40");
	}

	@Test
	public final void givenChildPassenger_whenSearchFlightInTwentyDays_thenSearchFlight() {

		when = LocalDate.now().plusDays(20);

		givenPassenger(0, 1, 0);
		givenMockFlights(from, to, "IB1234", airline, new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("67.00");
	}

	@Test
	public final void given1Child_whenSearchFlightInThirtyOneDays_thenSearchFlight() {

		when = LocalDate.now().plusDays(31);

		givenPassenger(0, 1, 0);
		givenMockFlights(from, to, "IB1234", airline, new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("53.60");
	}

	@Test
	public final void givenInfantPassenger_whenSearchFlightToday_thenSearchFlight() {

		givenPassenger(0, 0, 1);
		givenMockFlights(from, to, "IB1234", airline, new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("7");
	}

	@Test
	public final void givenInfantPassenger_whenSearchFlightInThreeDays_thenSearchFlight() {

		when = LocalDate.now().plusDays(3);

		givenPassenger(0, 0, 1);
		givenMockFlights(from, to, "IB1234", airline, new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("7");
	}

	@Test
	public final void givenInfantPassenger_whenSearchFlightInTwentyDays_thenSearchFlightChild() {

		when = LocalDate.now().plusDays(20);

		givenPassenger(0, 0, 1);
		givenMockFlights(from, to, "IB1234", airline, new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("7");
	}

	@Test
	public final void given1Infant_whenSearchFlightInThirtyOneDays_thenSearchFlightChild() {

		when = LocalDate.now().plusDays(31);

		givenPassenger(0, 0, 1);
		givenMockFlights(from, to, "IB1234", airline, new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("7");
	}

	@Test
	public final void given3AdultPassenger_whenSearchFlightInThreeDays_thenSearchFlightAdult() {

		when = LocalDate.now().plusDays(3);

		givenPassenger(3, 0, 0);
		givenMockFlights(from, to, "IB1234", airline, new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("360.00");
	}

	@Test
	public final void given1Adult1Child1InfantPassenger_whenSearchFlightInTwentyDays_thenSearchFlight() {

		when = LocalDate.now().plusDays(20);

		givenPassenger(1, 1, 1);
		givenMockFlights(from, to, "IB1234", airline, new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("174.00");
	}

	@Test
	public final void given1Adult1Child1InfantPassenger_whenSearchFlightInThirtyDays_thenSearchFlight() {

		when = LocalDate.now().plusDays(31);

		givenPassenger(1, 1, 1);
		givenMockFlights(from, to, "IB1234", airline, new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("140.60");
	}

	@Test
	public final void givenAdultPassenger_whenSearchFlight_thenNoResults() {

		givenPassenger(1, 0, 0);
		givenMockFlightsNoResult(from, to, "IB1234", airline, new BigDecimal(100));
		whenSearchFlights();
		thenNoResults();
	}

	@Test
	public final void given1Adult1Child1InfantPassenger_whenSearchFlightInThirtyDays_thenSearchFlightAndGet3TimeSameTrip() {

		when = LocalDate.now().plusDays(31);

		givenPassenger(1, 1, 1);
		givenListMockFlights(from, to, "IB1234", airline, new BigDecimal(100));
		whenSearchFlights();
		String[] prices = { "140.60", "140.60", "140.60" };
		thenPriceOfEachTripIs(prices);
	}

	private void thenPriceOfEachTripIs(String[] price) {
		assertThat(returnTrips, not(empty()));
		assertEquals(returnTrips.size(), price.length);

		for (int i = 0; i < returnTrips.size(); i++) {
			assertThat(returnTrips.get(i).getTripPrice(), closeTo(new BigDecimal(price[i]), new BigDecimal("0.1")));
		}
	}

	private void givenListMockFlights(String from, String to, String flightCode, Airline airline, BigDecimal price) {
		List<Flight> flights = Collections.nCopies(3, new Flight(airportFrom, airportTo, flightCode, airline, price));

		when(flightService.selecctFlightByAirportFromAndAirportTo(from, to)).thenReturn(flights);
	}

	private void givenMockFlights(String from, String to, String flightCode, Airline airline, BigDecimal price) {
		List<Flight> flights = Collections
				.singletonList(new Flight(airportFrom, airportTo, flightCode, airline, price));
		when(flightService.selecctFlightByAirportFromAndAirportTo(from, to)).thenReturn(flights);
	}

	private void givenMockFlightsNoResult(String from, String to, String flightCode, Airline airline,
			BigDecimal price) {
		List<Flight> flights = Collections.emptyList();
		when(flightService.selecctFlightByAirportFromAndAirportTo(from, to)).thenReturn(flights);
	}

	private void givenPassenger(int adult, int child, int infant) {
		this.adult = adult;
		this.child = child;
		this.infant = infant;
	}

	private void whenSearchFlights() {
		returnTrips = victim.calculateTrips(adult, child, infant, from, to, when);
	}

	private void thenPriceOfFirstTripIs(String price) {
		assertThat(returnTrips, not(empty()));
		assertThat(returnTrips.get(0).getTripPrice(), closeTo(new BigDecimal(price), new BigDecimal("0.1")));
	}

	private void thenNoResults() {
		assertThat(returnTrips, empty());
	}
}
