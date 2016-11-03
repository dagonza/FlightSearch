package org.search.flight.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.search.flight.model.Airline;

public class AirlineDAO {

	private List<Airline> airlineList;
	public static final AirlineDAO instance = new AirlineDAO();

	private AirlineDAO() {
		airlineList = new ArrayList<Airline>();
	}

	public void addAirline(Airline airline) {
		airlineList.add(airline);
	}

	public Optional<Airline> findAirlineByIATA(String IATACode) {
		return airlineList.stream().filter(a -> a.getIATACode().equals(IATACode)).findFirst();
	}

	public boolean deleteAirline(String IATACode) {

		Optional<Airline> toDelete = findAirlineByIATA(IATACode);

		if (toDelete.isPresent()) {
			airlineList.remove(toDelete.get());
			return true;
		} else {
			return false;
		}
	}

	public void deleteAll() {
		airlineList.clear();
	}

	public List<Airline> getAllAirlines() {
		return airlineList;
	}
}
