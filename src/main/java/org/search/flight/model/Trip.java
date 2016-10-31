package org.search.flight.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Collection;

import org.search.flight.model.passengers.Passenger;

public class Trip {
	
	private Flight flight;
	private Collection<Passenger> passengersList;
	private LocalDate plannedDate;
	
	public Trip(Flight flight, Collection<Passenger> passengersList, LocalDate plannedDate) {
		super();
		this.flight = flight;
		this.passengersList = passengersList;
		this.plannedDate = plannedDate;
	}

	
	/**
	 * @return the flight
	 */
	public Flight getFlight() {
		return flight;
	}

	/**
	 * @param flight the flight to set
	 */
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	/**
	 * @param passenger to add
	 */
	public void addPassenger(Passenger passenger) {
		this.passengersList.add(passenger);
	}
	
	public Collection<Passenger> getPassengersList(){
		return this.passengersList;
	}
	
	public BigDecimal getTripTotalPrice(){
	
		BigDecimal total = new BigDecimal(BigInteger.ZERO);
		
		for(Passenger passenger:passengersList){
			total = total.add(passenger.getPrice());
		}
		
		return total;
	}

	public LocalDate getPlannedDate() {
		return plannedDate;
	}

	public void setPlannedDate(LocalDate plannedDate) {
		this.plannedDate = plannedDate;
	}


	@Override
	public String toString() {
		return flight.getairlineCodeFlight() + ", " + getTripTotalPrice() +" €";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flight == null) ? 0 : flight.hashCode());
		result = prime * result + ((passengersList == null) ? 0 : passengersList.hashCode());
		result = prime * result + ((plannedDate == null) ? 0 : plannedDate.hashCode());
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
		Trip other = (Trip) obj;
		if (flight == null) {
			if (other.flight != null)
				return false;
		} else if (!flight.equals(other.flight))
			return false;
		if (passengersList == null) {
			if (other.passengersList != null)
				return false;
		} else if (!passengersList.equals(other.passengersList))
			return false;
		if (plannedDate == null) {
			if (other.plannedDate != null)
				return false;
		} else if (!plannedDate.equals(other.plannedDate))
			return false;
		return true;
	}
	
	
	
}
