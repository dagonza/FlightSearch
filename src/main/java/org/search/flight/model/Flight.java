package org.search.flight.model;

import java.math.BigDecimal;

public class Flight {
	
	private Airport from;
	private Airport to;
	private String airlineCodeFlight;
	private Airline airline;
	private BigDecimal basePrice;

	//Constructor with Airports
	public Flight(Airport from, Airport to, String airlineCodeFlight, Airline airline, BigDecimal basePrice) {
		this.from = from;
		this.to = to;
		this.airline=airline;
		this.airlineCodeFlight = airlineCodeFlight;
		this.basePrice = basePrice;
	}
	
	

	/**
	 * @return the from
	 */
	public Airport getFrom() {
		return from;
	}


	/**
	 * @param from the from to set
	 */
	public void setFrom(Airport from) {
		this.from = from;
	}


	/**
	 * @return the to
	 */
	public Airport getTo() {
		return to;
	}


	/**
	 * @param to the to to set
	 */
	public void setTo(Airport to) {
		this.to = to;
	}


	public String getairlineCodeFlight() {
		return airlineCodeFlight;
	}


	public void setairlineCodeFlight(String airlineCodeFlight) {
		this.airlineCodeFlight = airlineCodeFlight;
	}


	public BigDecimal getBasePrice() {
		return basePrice;
	}


	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}


	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

}