package org.search.flight.model.passengers;

import org.search.flight.model.Discount;

public class Infant extends Passenger {

	public Infant(String name, String gender, int age) {
		addTarifa(Discount.INFANT_PRICE);
	}

	public Infant() {
		addTarifa(Discount.INFANT_PRICE);
	}

}
