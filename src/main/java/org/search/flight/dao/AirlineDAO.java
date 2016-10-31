package org.search.flight.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.search.flight.model.Airline;

public final class AirlineDAO{

	private static List<Airline> airlineList = new ArrayList<Airline>();
	
//	public AirlineDAO(){
//		airlineList= new ArrayList<Airline>();
//	}
		
	public static void addAirline(Airline airline){
		airlineList.add(airline);
	}
	
	public static Optional<Airline> findAirlineByIATA(String IATACode){
		return airlineList.stream().filter(a -> a.getIATACode().equals(IATACode)).findFirst();
	}
	
	public static boolean deleteAirline(String IATACode){
		
		Optional<Airline> toDelete = AirlineDAO.findAirlineByIATA(IATACode);
		
		if(toDelete.isPresent()){
			airlineList.remove(toDelete.get());
			return true;
		}else{
			return false;
		}
	}
	
	public static void deleteAll(){
		airlineList.clear();
	}
}
