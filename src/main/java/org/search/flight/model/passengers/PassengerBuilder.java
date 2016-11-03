package org.search.flight.model.passengers;

import java.util.ArrayList;
import java.util.List;

import org.search.flight.error.InvalidPassengerTypeException;

public class PassengerBuilder {

	private static PassengerBuilder instance = null;

	public enum PassengerType {
		ADULT, CHILD, INFANT;
	}

	public static PassengerBuilder getPassengerBuilder() {
		if (instance == null) {
			instance = new PassengerBuilder();
		}
		return instance;
	}

	public Passenger createPassenger(PassengerType passenger, String name, String gender, int age) {

		switch (passenger) {

		case ADULT:
			return new Adult(name, gender, age);
		case CHILD:
			return new Child(name, gender, age);
		case INFANT:
			return new Infant(name, gender, age);
		default:
			throw new InvalidPassengerTypeException("Invalid passenger Type");
		}
	}

	public Passenger createPassenger(PassengerType passenger) {

		switch (passenger) {

		case ADULT:
			return new Adult();
		case CHILD:
			return new Child();
		case INFANT:
			return new Infant();
		default:
			throw new InvalidPassengerTypeException("Invalid passenger Type");
		}
	}

	public List<Passenger> createListOfPassengers(int numberAdult, int numberChild, int numberInfant) {

		List<Passenger> passengers = new ArrayList<Passenger>();

		for (int adult = 0; adult < numberAdult; adult++) {
			passengers.add(createPassenger(PassengerType.ADULT));
		}

		for (int child = 0; child < numberChild; child++) {
			passengers.add(createPassenger(PassengerType.CHILD));
		}

		for (int infant = 0; infant < numberInfant; infant++) {
			passengers.add(createPassenger(PassengerType.INFANT));
		}

		return passengers;

	}

}
