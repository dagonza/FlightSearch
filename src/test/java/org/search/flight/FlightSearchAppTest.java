package org.search.flight;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.search.flight.dao.FlightDAO;
import org.search.flight.model.Airline;
import org.search.flight.model.Airport;
import org.search.flight.model.Flight;
import org.search.flight.model.Trip;
import org.search.flight.model.passengers.Adult;
import org.search.flight.model.passengers.Child;
import org.search.flight.model.passengers.Infant;
import org.search.flight.model.passengers.Passenger;
import org.search.flight.model.passengers.PassengerBuilder;
import org.search.flight.model.passengers.PassengerBuilder.PassengerType;

@RunWith(MockitoJUnitRunner.class)
public class FlightSearchAppTest {
		
	@InjectMocks
	private FlightSearchApp victim = new FlightSearchApp();
	
	@Mock
	private FlightDAO flightDAO;

	private List<Passenger> passengers;
	private Airport from;
	private Airport to;
	private LocalDate when;
	private Airline airline;
	private List<Trip> returnTrips;

	@Before
	public void init(){
		
		passengers = new ArrayList<Passenger>();

		from = new Airport("FRA", "france");
		to = new Airport("MAD", "Madrid");
		when = LocalDate.now();
		airline = new Airline("IB", "Iberia", new BigDecimal(7));

	}
	
	@Test
	public final void givenAdultPassenger_whenSearchFlightToday_thenSearchFlightAdult() {
		
		givenPassenger(PassengerBuilder.getPassengerBuilder().createPassenger(PassengerType.ADULT));
		givenMockFlights(from,to,"IB1234",airline,new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("150.00");
	}


	@Test
	public final void givenAdultPassenger_whenSearchFlightInThreeDays_thenSearchFlightAdult() {
		
		when = when.plusDays(3);
		
		givenPassenger(PassengerBuilder.getPassengerBuilder().createPassenger(PassengerType.ADULT));
		givenMockFlights(from,to,"IB1234",airline,new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("120.00");
	}
	
	@Test
	public final void given3AdultPassenger_whenSearchFlightInThreeDays_thenSearchFlightAdult() {
		
		when = when.plusDays(3);
		
		givenPassenger(PassengerBuilder.getPassengerBuilder().createListOfPassengers(3, 0, 0));
		givenMockFlights(from,to,"IB1234",airline,new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("360.00");
	}
	
	@Test
	public final void given1Adult1Child1InfantPassenger_whenSearchFlightInTwentyDays_thenSearchFlightAdult() {
		
		when = when.plusDays(20);
		
		givenPassenger(PassengerBuilder.getPassengerBuilder().createListOfPassengers(1, 1, 1));
		givenMockFlights(from,to,"IB1234",airline,new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("174.00");
	}
	
	@Test
	public final void given1Adult1Child1InfantPassenger_whenSearchFlightInThirtyDays_thenSearchFlightAdult() {
		
		when = when.plusDays(31);
		
		givenPassenger(PassengerBuilder.getPassengerBuilder().createListOfPassengers(1, 1, 1));
		givenMockFlights(from,to,"IB1234",airline,new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("140.60");
	}
	
	
	@Test
	public final void givenChildPassenger_whenSearchFlightToday_thenSearchFlightChild() {
		
		givenPassenger(PassengerBuilder.getPassengerBuilder().createPassenger(PassengerType.CHILD));
		givenMockFlights(from,to,"IB1234",airline,new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("100.5");

	}
	
	@Test
	public final void givenInfantPassenger_whenSearchFlight_thenSearchFlightInfant() {
		
		givenPassenger(PassengerBuilder.getPassengerBuilder().createPassenger(PassengerType.INFANT));
		givenMockFlights(from,to,"IB1234",airline,new BigDecimal(100));
		whenSearchFlights();
		thenPriceOfFirstTripIs("7");
	}

	
	@Test
	public final void givenAdultPassenger_whenSearchFlight_thenNoResults() {
		
		givenPassenger(PassengerBuilder.getPassengerBuilder().createPassenger(PassengerType.ADULT));
		givenMockFlightsNoResult(from,to,"IB1234",airline,new BigDecimal(100));
		whenSearchFlights();
		thenNoResults();
	}
	
	private void givenMockFlights(Airport from, Airport to, String flightCode, Airline airline, BigDecimal price){
		List<Flight> flights = Collections.singletonList(new Flight(from, to, flightCode, airline, price));
		when(flightDAO.selecctFlightByAirportFromAndAirportTo(from,to)).thenReturn(flights);
	}
	
	private void givenMockFlightsNoResult(Airport from, Airport to, String flightCode, Airline airline, BigDecimal price){
		List<Flight> flights = Collections.emptyList();
		when(flightDAO.selecctFlightByAirportFromAndAirportTo(from,to)).thenReturn(flights);
	}
	
	private void givenPassenger(Passenger passenger){
		passengers.add(passenger);
	}
	
	private void givenPassenger(List<Passenger> passenger){
		passengers.addAll(passenger);
	}

	private void whenSearchFlights(){
		returnTrips = victim.calculateTrips(passengers,from,to,when);
	}
	
	private void thenPriceOfFirstTripIs(String price) {
		assertThat(returnTrips,not(empty()));
		assertThat(returnTrips.get(0).getTripTotalPrice(), closeTo(new BigDecimal(price),new BigDecimal("0.1")));
	}
	
	private void thenNoResults(){
		assertThat(returnTrips,empty());
	}
}
