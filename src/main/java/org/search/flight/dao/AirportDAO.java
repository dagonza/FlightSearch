package org.search.flight.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.search.flight.model.Airport;

public final class AirportDAO{

	private static List<Airport> airportList = new ArrayList<Airport>(0);
	
//	public AirportDAO(){
//		airportList= new ArrayList<Airport>();
//	}
		
	public static void addAirport(Airport airport){
		airportList.add(airport);
	}
	
	public static Optional<Airport> findAirportByIATA(String iATACode){
		return airportList.stream().filter(a -> a.getIATACode().equals(iATACode)).findFirst();
	}
	
	public static boolean deleteAirport(String iATACode){
		
		Optional<Airport> toDelete = AirportDAO.findAirportByIATA(iATACode);
		
		if(toDelete.isPresent()){
			airportList.remove(toDelete.get());
			return true;
		}else{
			return false;
		}
	}
	
	public static void deleteAll(){
		airportList.clear();
	}
}
