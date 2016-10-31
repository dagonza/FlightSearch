package org.search.flight.dao;

import java.util.ArrayList;
import java.util.List;

import org.search.flight.model.Airport;
import org.search.flight.model.Flight;

public final class FlightDAO {
	
	private static ArrayList<Flight> flightList = new ArrayList<Flight>();
	

	public static void addFlight(Flight Flight){
		flightList.add(Flight);
	}
		
	public static List<Flight>  selecctFlightByAirportFromAndAirportTo(Airport origin, Airport destiny){
		
		List<Flight> toReturn = new ArrayList<Flight>(0);
		
		for(Flight flight:flightList){
			if(flight.getFrom().getIATACode().equalsIgnoreCase(origin.getIATACode())
					&& flight.getTo().getIATACode().equalsIgnoreCase(destiny.getIATACode())){
				toReturn.add(flight);
			}
		}
		
		return toReturn;
		
	}
	
	public static void deleteAll(){
		flightList.clear();
	}
	
}
