package org.search.flight.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;

import org.search.flight.model.passengers.Passenger;

public class Trip {
	
	private Flight flight;
	private Collection<Passenger> passengersList;
	private Date plannedDate;
	
	public Trip(Flight flight, Collection<Passenger> passengersList, Date plannedDate) {
		super();
		this.flight = flight;
		this.passengersList = passengersList;
		this.setPlannedDate(plannedDate);
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

	public Date getPlannedDate() {
		return plannedDate;
	}

	public void setPlannedDate(Date plannedDate) {
		this.plannedDate = plannedDate;
	}


	@Override
	public String toString() {
		return flight.getairlineCodeFlight() + ", " + getTripTotalPrice() +" €";
	}
	
	
}
