package org.search.flight.model.passengers;

import org.search.flight.model.Discount;

public class Infant extends Passenger {

	public Infant(String name, String gender, int age) {
		super(name, gender, age);
		addTarifa(Discount.INFANT_PRICE);
	}

	public Infant() {
		super();
		addTarifa(Discount.INFANT_PRICE);
	}

}
