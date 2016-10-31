package org.search.flight.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.search.flight.model.Airport;

public class AirportDAO{

	private List<Airport> airportList = new ArrayList<Airport>(0);
	private static AirportDAO instance = null;
	
	public static AirportDAO getAirportDAO(){
	    if(instance == null) {
	       instance = new AirportDAO();
	    }
		return instance;
	}
		
	public void addAirport(Airport airport){
		airportList.add(airport);
	}
	
	public Optional<Airport> findAirportByIATA(String iATACode){
		return airportList.stream().filter(a -> a.getIATACode().equals(iATACode)).findFirst();
	}
	
	public boolean deleteAirport(String iATACode){
		
		Optional<Airport> toDelete = findAirportByIATA(iATACode);
		
		if(toDelete.isPresent()){
			airportList.remove(toDelete.get());
			return true;
		}else{
			return false;
		}
	}
	
	public void deleteAll(){
		airportList.clear();
	}
}
