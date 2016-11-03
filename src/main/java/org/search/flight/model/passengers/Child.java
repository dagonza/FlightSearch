package org.search.flight.model.passengers;

import org.search.flight.model.Discount;

public class Child extends Passenger {

	public Child(String name, String gender, int age) {
		addTarifa(Discount.DISCOUNT_PLUS_33_PERCENT);
	}

	public Child() {
		addTarifa(Discount.DISCOUNT_PLUS_33_PERCENT);
	}

}
