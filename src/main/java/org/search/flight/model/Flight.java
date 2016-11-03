package org.search.flight.model;

import java.math.BigDecimal;

public class Flight {

	private Airport from;
	private Airport to;
	private String airlineCodeFlight;
	private Airline airline;
	private BigDecimal basePrice;

	public Flight(Airport from, Airport to, String airlineCodeFlight, Airline airline, BigDecimal basePrice) {
		this.from = from;
		this.to = to;
		this.airline = airline;
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
	 * @param from
	 *            the from to set
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
	 * @param to
	 *            the to to set
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((airline == null) ? 0 : airline.hashCode());
		result = prime * result + ((airlineCodeFlight == null) ? 0 : airlineCodeFlight.hashCode());
		result = prime * result + ((basePrice == null) ? 0 : basePrice.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (airline == null) {
			if (other.airline != null)
				return false;
		} else if (!airline.equals(other.airline))
			return false;
		if (airlineCodeFlight == null) {
			if (other.airlineCodeFlight != null)
				return false;
		} else if (!airlineCodeFlight.equals(other.airlineCodeFlight))
			return false;
		if (basePrice == null) {
			if (other.basePrice != null)
				return false;
		} else if (!basePrice.equals(other.basePrice))
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Flight [from=" + from + ", to=" + to + ", airlineCodeFlight=" + airlineCodeFlight + ", airline="
				+ airline + ", basePrice=" + basePrice + "]";
	}



}